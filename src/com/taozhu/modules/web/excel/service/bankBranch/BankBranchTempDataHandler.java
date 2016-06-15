package com.taozhu.modules.web.excel.service.bankBranch;

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
@Service("BankBranchTempDataHandler")
public class BankBranchTempDataHandler implements ITempDataHandler {
	@Resource
	private BaseDAO baseDAO;
	@Override
	public void execute(List<Map<String, Object>> datas, FileDefine df,
			Map<String, String> param) {
		// TODO Auto-generated method stub
		Map<String,Object> pm = new HashMap<String,Object>();
		pm.put("batchNo", df.getBatchNo());
		pm.put("templateCuid", df.getTemplateCuid());
//		this.baseDAO.save("BankBranchMapper.insertByTempBank",pm);
		//先将银行导入，然后在将相关的网点信息导入,最后网点详情导入
		this.baseDAO.save("BankBranchMapper.insertByBank", pm);
		this.baseDAO.save("BankBranchMapper.insertByBankBranch", pm);
		this.baseDAO.save("BankBranchMapper.insertByBankBranchDetails", pm);
	}

}
