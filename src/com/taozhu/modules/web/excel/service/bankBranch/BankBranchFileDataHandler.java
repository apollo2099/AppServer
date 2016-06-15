package com.taozhu.modules.web.excel.service.bankBranch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.vo.Record;
import com.taozhu.common.poi.PoiExcelUtils;
import com.taozhu.modules.web.excel.pojo.ColDefine;
import com.taozhu.modules.web.excel.pojo.FileDefine;
import com.taozhu.modules.web.excel.service.FileDataHandler;
import com.taozhu.modules.web.excel.service.IFileDataHandler;
/**
 * 入临时表
 * @author admin
 *
 */
@Service("BankBranchFileDataHandler")
public class BankBranchFileDataHandler extends FileDataHandler {
	@Override
	public void validate(Map<String,Object> mp) {
		//校验银行不进行校验，银行下面的网点是否存在
		baseDAO.update("BankBranchMapper.validateByBankBranch", mp);
	}
	
}
