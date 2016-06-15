package com.taozhu.modules.web.excel.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.SysPropertyUtil;

public abstract class FileErrorBO implements IFileErrorBO {
	/**
	 * 表头方法
	 * @return
	 */
	public abstract Map<String,String> createCol();
	/**
	 * 数据方法
	 * @param mp
	 * @return
	 */
	public abstract List<Map<String, Object>> getErrorDatas(Map<String,Object> mp);
	private static int dataBeginRow = 0;
	private static int dataBeginCell = 0;
	@Override
	public String exportErrorFile(String batchNo, String relatedTemplateCuid)
			throws FileNotFoundException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		Map<String,String> exportErrorMap=this.createCol();
		Map<String, Object> pm = new HashMap<String, Object>();
		pm.put("batchNo", batchNo);
		pm.put("relatedTemplateCuid", relatedTemplateCuid);
		List<Map<String, Object>> errorDatas = getErrorDatas(pm);
		if(errorDatas.size() == 0) {
			return null;
		}
		String sheetName = BaseDAOUtil.getStringValue(errorDatas.get(0), "SHEET_NAME");
		HSSFSheet sheet = this.createSheet(exportErrorMap,workbook, sheetName);
		int rowIndex = dataBeginRow+1;
		for(Map<String, Object> errorData : errorDatas) {
			HSSFRow row = null;
			row = sheet.createRow(rowIndex);
			int headIndex = 0;
			for(String key : exportErrorMap.keySet()) {
				String value = BaseDAOUtil.getStringValue(errorData, key);
				String columnName = exportErrorMap.get(key);
				int colIndex = dataBeginCell+headIndex;
				headIndex++;
				HSSFCell cell  = row.createCell(colIndex);
				cell.setCellValue(value);
			}
			rowIndex++;
		}
		
		String tempfilePath = SysPropertyUtil.getInstance().getValue("tempfile");
		File folder = new File(tempfilePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		String filePath = tempfilePath + "/"+relatedTemplateCuid+"-"+System.currentTimeMillis()+".xls";
		File xlsFile = new File(filePath);
		if(xlsFile.exists()) {
			xlsFile.delete();
		}
		FileOutputStream out = new FileOutputStream(xlsFile);
		try {
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out = null;
		}
		return filePath;
	}
	/**
	 * 
	 * @param workbook
	 * @param sheetname
	 * @return
	 */
	private HSSFSheet createSheet(Map<String,String> exportErrorMap,HSSFWorkbook workbook, String sheetname) {
		if(StringUtils.isBlank(sheetname)) {
			sheetname = " "+System.currentTimeMillis();
		}
		HSSFSheet sheet = workbook.createSheet(sheetname);
		HSSFRow headRow = sheet.createRow(dataBeginRow);
		int headIndex = 0;
		
		for(String columnName : exportErrorMap.values()) {
			String[] s = StringUtils.split(columnName, "#");		
			int cellIndex = (dataBeginCell)+headIndex;
			headIndex++;
			HSSFCell headCell1 = headRow.createCell(cellIndex);
			headCell1.setCellValue(s[0]);
		}
		return sheet;
	}
}
