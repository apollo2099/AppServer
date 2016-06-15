package com.taozhu.modules.web.excel.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.util.CUIDHexGenerator;
import com.taozhu.common.poi.PoiExcelUtils;
import com.taozhu.modules.web.excel.pojo.ColDefine;
import com.taozhu.modules.web.excel.pojo.FileDefine;


public class ExcelDataHandlerBO implements IFileDataHandler {

	private BaseDAO baseDAO;
	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}



	public void execute(Object obj, FileDefine df, Map<String, String> param) {
		Workbook wb = (Workbook) obj;
		Sheet sheet = wb.getSheetAt(df.getSheetNo());
		if (sheet == null)
			throw new RuntimeException("无法获取导入文件的第一个SHEET数据，请检查导入文件！");
		int endR = sheet.getLastRowNum();
		int dataRow = df.getDataRowNum();
		for (int r = dataRow; r <= endR; r++) {
			Row row = sheet.getRow(r);
			if(row==null)continue;
			int hasvalue = 0;
			int endC = row.getLastCellNum();
			int startC = row.getFirstCellNum();
			Map data = new HashMap();
			for (int i = startC; i < endC; i++) {
				Cell cell = row.getCell(i);
				Object value = null;
				if (cell != null) {
					value = PoiExcelUtils.getCellValue(cell, sheet);
				}
				if(value!=null){
					hasvalue++;
				}
				ColDefine col = df.getColIndexMap().get(i);
				if (col == null)
					continue;
				data.put(col.getFromCol(), value);
			}
			if (!data.isEmpty()&&hasvalue!=0) {
				data.put("CUID", CUIDHexGenerator.getInstance().generate());
				data.put("BATCH_NO",df.getBatchNo());
				data.put("RELATED_TEMPLATE_CUID", df.getTemplateCuid());
				this.baseDAO.insertDynamicTable(df.getTemplateTable(), data);
			}
		}
	}

}
