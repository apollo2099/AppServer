package com.taozhu.modules.web.excel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.vo.Record;
import com.taozhu.common.poi.PoiExcelUtils;
import com.taozhu.modules.web.excel.pojo.ColDefine;
import com.taozhu.modules.web.excel.pojo.FileDefine;

public abstract class FileDataHandler implements IFileDataHandler {
	
	@Resource
	protected BaseDAO baseDAO;

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	/**
	 * 临时数据校验方法
	 * @param fd
	 */
	public abstract void validate(Map<String,Object> mp);
	@Override
	public void execute(Object obj, FileDefine df, Map<String, String> param) {
		List<Record> dataList=new ArrayList<Record>();
		Workbook wb = (Workbook) obj;
		String tableName=df.getTemplateTable();
		String templateCuid=df.getTemplateCuid();
		Sheet sheet = wb.getSheetAt(df.getSheetNo());
		Map pm = new HashMap();
		pm.put("templateCuid", df.getTemplateCuid());
		pm.put("batchNo", df.getBatchNo());
		if (sheet == null)throw new RuntimeException("无法获取导入文件的第二个SHEET数据，请检查导入文件！");
		int endR = sheet.getLastRowNum();
		int dataRow = df.getDataRowNum();
		for (int r = dataRow; r <= endR; r++) {
			Row titleRow = sheet.getRow(df.getTitleRowNum());
			int endC = titleRow.getLastCellNum();
			int startC = titleRow.getFirstCellNum();
			Row row = sheet.getRow(r);
			if (row == null) continue;	
			Record record = new Record(tableName);
			for (int i = startC; i < endC; i++) {
				Cell cell = row.getCell(i);
				Object value = null;
				if (cell != null) {
					value = PoiExcelUtils.getCellValue(cell, sheet);
				}
				ColDefine col = df.getColIndexMap().get(i);
				if(col!=null){
					record.addColValue(col.getFromCol(), value);
					
				}
			}
			record.addColValue("BATCH_NO", df.getBatchNo());
			record.addColValue("RELATED_TEMPLATE_CUID", df.getTemplateCuid());
			record.addColValue("NUM", r);
			dataList.add(record);
		}
		baseDAO.insertDynamicTableBatch(dataList);
		validate(pm);
	}
}
