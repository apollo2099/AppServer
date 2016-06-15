package com.taozhu.modules.web.excel.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDefine {
	
	public FileDefine(String cuid){
		this.templateCuid = cuid;
	}
	
	private String templateTable="T_ELT_TEMPDATA"; 
	
	/**
	 * 扩展临时表
	 */
	private List<String> extTempTables = new ArrayList<String>(); 
	/**
	 * 模板ID
	 */
	private String templateCuid;
	
	/**
	 * 批次,通用导入的批次与模板ID一致
	 */
	private String batchNo;
	
	/**
	 * 文件头定义BO
	 */
	private String headBO;
	
	public String getHeadBO() {
		return headBO;
	}

	public void setHeadBO(String headBO) {
		this.headBO = headBO;
	}
	
	private String lockBO;
	
	/**
	 * 结果统计BO
	 */
	private String calculateBO = "DefaultCalculateHandler";

	public String getCalculateBO() {
		return calculateBO;
	}

	public void setCalculateBO(String calculateBO) {
		this.calculateBO = calculateBO;
	}

	/**
	 * 临时数据入正式库
	 */
	private String dataHandlerBO;
	
	public String getDataHandlerBO() {
		return dataHandlerBO;
	}

	public void setDataHandlerBO(String dataHandlerBO) {
		this.dataHandlerBO = dataHandlerBO;
	}
	
	/**
	 * 文件处理入临时库
	 */
	private String fileHandlerBO;
	/**
	 * 导出错误BO
	 */
	private String errorHandlerBO;

	public String getErrorHandlerBO() {
		return errorHandlerBO;
	}

	public void setErrorHandlerBO(String errorHandlerBO) {
		this.errorHandlerBO = errorHandlerBO;
	}

	private int sheetNo = 0;
	
	private String sheetName;
	
	private int titleRowNum = 0;
	
	private int dataRowNum = 1;
	
	private int total;
	
	private int sucess;
	
	private int error;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSucess() {
		return sucess;
	}

	public void setSucess(int sucess) {
		this.sucess = sucess;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	private String filePath;
	
	private String fileName;
	
	private String remark;
	
	private String userId;
	
	private Date createTime;
	
	private List<ColDefine> cols;
	
	/**
	 * 按照临时表的字段名称存储字段定义
	 */
	private Map<String,ColDefine> colMap;
	
	/**
	 * 按照临时表的字段索引存储字段定义
	 */
	private Map<Integer,ColDefine> colIndexMap;
	
	private Integer maxCellIndex = 0;
	
	public Integer getMaxCellIndex() {
		return maxCellIndex;
	}

	public void setMaxCellIndex(Integer maxCellIndex) {
		this.maxCellIndex = maxCellIndex;
	}

	/**
	 * 按照资源类型索引存储字段定义
	 */
	private Map<String,List<ColDefine>> bmClassIdColsMap;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<ColDefine> getCols() {
		return cols;
	}

	public Map<String, List<ColDefine>> getBmClassIdColsMap() {
		return bmClassIdColsMap;
	}

	public void setCols(List<ColDefine> cols) {
		this.cols = cols;
		for(ColDefine col:cols){
			if(this.colMap == null)this.colMap = new HashMap<String, ColDefine>();
			this.colMap.put(col.getFromCol(), col);
			if(this.colIndexMap == null)this.colIndexMap = new HashMap<Integer, ColDefine>();
			this.colIndexMap.put(col.getFileColIndex(), col);
			if(col.getFileColIndex()>this.maxCellIndex){
				this.maxCellIndex = col.getFileColIndex();
			}
			if(this.bmClassIdColsMap==null)this.bmClassIdColsMap = new HashMap<String, List<ColDefine>>();
			if(col.getBmClassId()!=null){
				List<ColDefine> list = this.bmClassIdColsMap.get(col.getBmClassId());
				if(list==null){
					list = new ArrayList<ColDefine>();
					this.bmClassIdColsMap.put(col.getBmClassId(), list);
				}
				list.add(col);
			}
		}
	}

	public String getTemplateCuid() {
		return templateCuid;
	}

	public void setTemplateCuid(String templateCuid) {
		this.templateCuid = templateCuid;
	}

	public int getTitleRowNum() {
		return titleRowNum;
	}

	public void setTitleRowNum(int titleRowNum) {
		this.titleRowNum = titleRowNum;
	}

	public int getDataRowNum() {
		return dataRowNum;
	}

	public void setDataRowNum(int dataRowNum) {
		this.dataRowNum = dataRowNum;
	}

	public int getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(int sheetNo) {
		this.sheetNo = sheetNo;
	}

	public Map<String, ColDefine> getColMap() {
		return colMap;
	}

	public void setColMap(Map<String, ColDefine> colMap) {
		this.colMap = colMap;
	}

	public Map<Integer, ColDefine> getColIndexMap() {
		return colIndexMap;
	}

	public void setColIndexMap(Map<Integer, ColDefine> colIndexMap) {
		this.colIndexMap = colIndexMap;
	}

	public String getTemplateTable() {
		return templateTable;
	}

	public void setTemplateTable(String templateTable) {
		this.templateTable = templateTable;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getFileHandlerBO() {
		return fileHandlerBO;
	}

	public void setFileHandlerBO(String fileHandlerBO) {
		this.fileHandlerBO = fileHandlerBO;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<String> getExtTempTables() {
		return extTempTables;
	}

	public void setExtTempTables(List<String> extTempTables) {
		this.extTempTables = extTempTables;
	}

	public String getLockBO() {
		return lockBO;
	}

	public void setLockBO(String lockBO) {
		this.lockBO = lockBO;
	}
	
	
}
