package com.taozhu.modules.web.excel.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.util.CUIDHexGenerator;
import com.taozhu.common.util.SpringContextUtil;
import com.taozhu.modules.web.excel.pojo.ColDefine;
import com.taozhu.modules.web.excel.pojo.FileDefine;
@Service("EltMainProcessService")
public class EltMainProcessService {
	@Resource
	private BaseDAO baseDAO;
	@SuppressWarnings("unchecked")
	public FileDefine execute(InputStream in,Map<String,String> param){
		FileDefine df = null;
		List<Map<String,Object>> datas = null;
		boolean lockError = false;
		try {
			String fileName = param.get("fileName");
			
			Object obj = null;
			if(StringUtils.endsWithIgnoreCase(fileName, ".xls")){
				obj = new HSSFWorkbook(in);
			}else if(StringUtils.endsWithIgnoreCase(fileName, ".xlsx")){
				obj = new XSSFWorkbook(in);
			}
			if(obj==null)throw new RuntimeException("无法解析传入的文件格式，请确认格式类型为:[xls,xlsx,xml,txt]");
			String templateId = param.get("templateId");
			IFileDefineHandler handler = null;
			if(StringUtils.isNotEmpty(templateId)){
				handler = (IFileDefineHandler)SpringContextUtil.getBean("TemplateDefineHandler");
			}else{
				handler = (IFileDefineHandler)SpringContextUtil.getBean("MetaDefineHandlerBO");
			}
			//表头读取
			df = handler.execute(obj,param);
			if(df.getBatchNo()==null)throw new RuntimeException("需要在IFileDefineHandler实现类中生成导入批号并返回！");
			//设置文件名，用于日志
			df.setFileName(fileName);
			//数据根据df的定义进入临时库
			IFileDataHandler dataHandler = null;
			if(StringUtils.endsWithIgnoreCase(fileName, ".xls") || StringUtils.endsWithIgnoreCase(fileName, ".xlsx")){
				String fileBO = df.getFileHandlerBO();
				dataHandler = (IFileDataHandler)SpringContextUtil.getBean(fileBO);
				dataHandler.execute(obj, df, param);
			}
			
			String tempDatasql = "select * from " + df.getTemplateTable()
			+ " t where t.related_template_cuid='" + df.getTemplateCuid()
			+ "' and t.batch_no='" + df.getBatchNo() + "' and t.msg is null";
			datas = this.baseDAO.querySql(tempDatasql);
			//设置全局锁，防止并发
			if(df.getLockBO()!=null){
				ILockHandler lockHandler  = (ILockHandler)SpringContextUtil.getBean(df.getLockBO());
				try {
					lockHandler.setLock(df,datas);
				} catch (Exception e) {
					lockError = true;
					throw e;
				}
			}
			
			//临时库入资源库,Df中的dataHandlerBO
			ITempDataHandler tempDataHandler  = (ITempDataHandler)SpringContextUtil.getBean(df.getDataHandlerBO());
			tempDataHandler.execute(datas, df, param);
			ICalculateHandler calculateHandler  = (ICalculateHandler)SpringContextUtil.getBean(df.getCalculateBO());
			calculateHandler.execute(df, param);
			return df;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally{
			if(df!=null){
				//如果没有锁异常，则解除全局锁，防止并发
				if(df.getLockBO()!=null&&datas!=null&& !lockError){
					ILockHandler lockHandler  = (ILockHandler)SpringContextUtil.getBean(df.getLockBO());
					lockHandler.releaseLock(df,datas);
				}
			}
		}
	}
}
