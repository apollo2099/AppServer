package com.taozhu.modules.web.excel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.taozhu.common.mybatis.util.CUIDHexGenerator;
import com.taozhu.common.poi.PoiExcelUtils;
import com.taozhu.modules.web.excel.pojo.ColDefine;
import com.taozhu.modules.web.excel.pojo.FileDefine;

/**
 * 表头信息
 * @author admin
 *
 */
public abstract class HeadDefineHandler implements IHeadDefineHandler {
	/**
	 * 获取表头信息的抽象方法
	 * @return
	 */
	protected abstract Map<String,String> getMaps();
	public List<ColDefine> execute(FileDefine fd, Object obj, Map<String, String> para) {
		Workbook wb = (Workbook)obj;
		Sheet sheet = wb.getSheetAt(fd.getSheetNo());
		if(sheet==null)throw new RuntimeException("无法获取导入文件的第二个SHEET数据，请检查导入文件！");
		Row row = sheet.getRow(fd.getTitleRowNum());
		if(row==null)throw new RuntimeException("无法获取导入文件的表头数据，请检查导入文件！");
		List<ColDefine> cols = new ArrayList<ColDefine>();
		int endC = row.getLastCellNum();
		int startC = row.getFirstCellNum();
		for(int i=startC;i<endC;i++){
			Cell cell = row.getCell(i);
			Object value = null;
			if(cell!=null){
				value = PoiExcelUtils.getCellValue(cell);
			}
			if(value!=null && value instanceof String){
				ColDefine col = new ColDefine();
				col.setCuid(CUIDHexGenerator.getInstance().generate("COL-"));
				col.setFile(fd);
				if(getMaps().containsKey(value)){
					col.setFromCol(getMaps().get(value).toString());
				}else {
					continue;
				}
				col.setFileColIndex(i);
				col.setFileCol((String)value);
				cols.add(col);
			}
		}
		
		return cols;
	}


}
