package com.taozhu.modules.web.excel.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.poi.PoiExcelUtils;
import com.taozhu.common.util.GoatOidAccount;
import com.taozhu.common.util.SpringContextUtil;
import com.taozhu.modules.web.excel.pojo.ColDefine;
import com.taozhu.modules.web.excel.pojo.FileDefine;


@Service("TemplateDefineHandler")
public class TemplateDefineHandler implements IFileDefineHandler{
	@Resource
	private BaseDAO baseDAO;
	

	private final String sqlMap = "FileHandlerMapper";
	
	@SuppressWarnings("unchecked")
	public FileDefine execute(Object obj,
			Map<String, String> param) throws IOException {
		//根据templateId找T_ELT_COL_DEFINE和T_ELT_FILE_DEFINE表，返回FileDefine
		String templateId = param.get("templateId");
		//通过唯一序列获取数据，避免并发重复 -1不能与classId冲突
		long nextId = GoatOidAccount.getGoatOidAccount().getNextIdInit(-1);
		Map<String,String> fileDefinemap = (Map<String, String>) this.baseDAO.select(sqlMap+".getFileDefine", templateId);
		FileDefine fd = new FileDefine(fileDefinemap.get("TEMPLATED_CUID"));
		fd.setCreateTime(new Date());
//		fd.setUserId(ac.getUserId());
		fd.setBatchNo(fileDefinemap.get("TEMPLATE_CUID")+Calendar.getInstance().getTimeInMillis()+"_"+nextId);	
		fd.setSheetNo(BaseDAOUtil.getIntValue(fileDefinemap, "SHEET_NUM"));	
		fd.setTitleRowNum(BaseDAOUtil.getIntValue(fileDefinemap, "TITLE_ROW_NUM"));//
		fd.setDataRowNum(BaseDAOUtil.getIntValue(fileDefinemap, "DATA_ROW_NUM"));//
		fd.setDataHandlerBO(fileDefinemap.get("DATA_BO"));
		fd.setErrorHandlerBO(fileDefinemap.get("ERROR_BO"));
		fd.setFileHandlerBO(BaseDAOUtil.getStringValue(fileDefinemap, "FILE_BO"));
		String tampTable = BaseDAOUtil.getStringValue(fileDefinemap, "TEMP_TABLE");
		if(StringUtils.isNotEmpty(tampTable)){
			fd.setTemplateTable(tampTable);
		}
		String headBo = BaseDAOUtil.getStringValue(fileDefinemap, "HEAD_BO");
		fd.setHeadBO(headBo);
		String calculateBO = BaseDAOUtil.getStringValue(fileDefinemap, "CALCULATE_BO");
		if(StringUtils.isNotEmpty(calculateBO)){
			fd.setCalculateBO(calculateBO);
		}
		Workbook wb = (Workbook)obj;
		Sheet sheet = null;
		Row row = null;
		try{
			sheet = wb.getSheetAt(BaseDAOUtil.getIntValue(fileDefinemap, "SHEET_NUM"));
			row = sheet.getRow(BaseDAOUtil.getIntValue(fileDefinemap, "TITLE_ROW_NUM"));
		}catch(Exception e){
			throw new RuntimeException("导入数据信息和模版信息不一致，请检查模版或重新下载模版！");
		}
		if(sheet==null)throw new RuntimeException("无法获取导入文件的第一个SHEET数据，请检查导入文件！");
		if(row==null)throw new RuntimeException("无法获取导入文件的第一行表头数据，请检查导入文件！");
		fd.setSheetName(sheet.getSheetName());//sheet名称
		
		List<ColDefine> cols = new ArrayList<ColDefine>();
		if(StringUtils.isEmpty(headBo)){
			List<Map<String,String>> colsmap = (List<Map<String,String>>) this.baseDAO.selectList(sqlMap+".findColDefines", templateId);
			int endC = row.getLastCellNum();
			int startC = row.getFirstCellNum();
			for(int i=startC;i<endC;i++){
				Cell cell = row.getCell(i);
				Object value = null;
				if(cell!=null){
					value = PoiExcelUtils.getCellValue(cell);
				}
				ColDefine col = new ColDefine();
				for(Map<String,String> colMap : colsmap){
					if(i==Integer.valueOf(colMap.get("file_col_index"))){
						try{
							if(value ==null || !value.equals(colMap.get("file_col"))){
								throw new RuntimeException();
							}else{
								col.setCuid(colMap.get("id"));
								col.setFile(fd);
								col.setFromCol(colMap.get("from_col"));
								col.setFileColIndex(Integer.valueOf(colMap.get("file_col_index")));
								col.setFileCol(colMap.get("file_col"));
								col.setToTable(colMap.get("to_table"));
								col.setToCol(colMap.get("to_col"));
								col.setBmClassId(colMap.get("BM_CLASS_ID"));
								if("Y".equals(colMap.get("NEED_TRANSLATE"))){
									col.setNeedTranslate("Y");
									col.setRecordSql(colMap.get("RECORD_SQL"));
									col.setRecordLabelCol(colMap.get("RECORD_LABEL_COL"));
									col.setRecordValueCol(colMap.get("RECORD_VALUE_COL"));
								}
							}
						}catch(Exception e){
							throw new RuntimeException("导入数据信息和模版信息不一致，请检查模版或重新下载模版！");
						}
					}
				}
				cols.add(col);
			}
		}else{
			fd.setTemplateCuid(fd.getBatchNo());
			IHeadDefineHandler handler = (IHeadDefineHandler)SpringContextUtil.getBean(headBo);
			cols = handler.execute(fd, obj, param);
			fd.setTemplateCuid(templateId);
		}
		fd.setCols(cols);
		return fd;
	}

}
