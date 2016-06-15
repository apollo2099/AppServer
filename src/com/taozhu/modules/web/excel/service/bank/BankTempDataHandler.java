package com.taozhu.modules.web.excel.service.bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.modules.web.excel.pojo.FileDefine;
import com.taozhu.modules.web.excel.service.ITempDataHandler;
/**
 * 临时表入正式库
 * @author admin
 *
 */
@Service("BankTempDataHandler")
public class BankTempDataHandler implements ITempDataHandler {
	@Resource
	private BaseDAO baseDAO;
	@Override
	public void execute(List<Map<String, Object>> datas, FileDefine df,
			Map<String, String> param) {
		// TODO Auto-generated method stub
		Map<String,Object> pm = new HashMap<String,Object>();
		pm.put("batchNo", df.getBatchNo());
		pm.put("templateCuid", df.getTemplateCuid());
		this.baseDAO.save("BankMapper.insertByTempBank",pm);
	}

}
