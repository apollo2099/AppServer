package com.taozhu.modules.web.excel.pojo;

public class ColDefine {
	private FileDefine file;
	private String cuid;
	/**
	 * 文件列名
	 */
	private String fileCol;
	/**
	 * 文件所在列
	 */
	private int fileColIndex;
	/**
	 * 临时表字段名称
	 */
	private String fromCol;
	
	/**
	 * 临时表字段需要翻译的存放ID的字段
	 */
	private String fromColCuid;
	
	/**
	 * 需要根据已经翻译后的某个临时表字段进行翻译
	 */
	private String transCol;
	
	private String toTable;
	private String bmClassId;
	public String getBmClassId() {
		return bmClassId;
	}

	public void setBmClassId(String bmClassId) {
		this.bmClassId = bmClassId;
	}

	private String toCol;
	private String needTranslate;
	private String recordSql;
	private String recordLabelCol;
	private String recordValueCol;
	private String whereSql;
	private String needCal;
	
	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getFileCol() {
		return fileCol;
	}

	public void setFileCol(String fileCol) {
		this.fileCol = fileCol;
	}

	public String getFromCol() {
		return fromCol;
	}

	public void setFromCol(String fromCol) {
		this.fromCol = fromCol;
	}

	public String getToTable() {
		return toTable;
	}

	public void setToTable(String toTable) {
		this.toTable = toTable;
	}

	public String getToCol() {
		return toCol;
	}

	public void setToCol(String toCol) {
		this.toCol = toCol;
	}

	public String getNeedTranslate() {
		return needTranslate;
	}

	public void setNeedTranslate(String needTranslate) {
		this.needTranslate = needTranslate;
	}

	public String getRecordSql() {
		return recordSql;
	}

	public void setRecordSql(String recordSql) {
		this.recordSql = recordSql;
	}

	public String getRecordLabelCol() {
		return recordLabelCol;
	}

	public void setRecordLabelCol(String recordLabelCol) {
		this.recordLabelCol = recordLabelCol;
	}

	public String getRecordValueCol() {
		return recordValueCol;
	}

	public void setRecordValueCol(String recordValueCol) {
		this.recordValueCol = recordValueCol;
	}

	public String getNeedCal() {
		return needCal;
	}

	public void setNeedCal(String needCal) {
		this.needCal = needCal;
	}

	public FileDefine getFile() {
		return file;
	}

	public void setFile(FileDefine file) {
		this.file = file;
	}

	public int getFileColIndex() {
		return fileColIndex;
	}

	public void setFileColIndex(int fileColIndex) {
		this.fileColIndex = fileColIndex;
	}

	public String getWhereSql() {
		return whereSql;
	}

	public void setWhereSql(String whereSql) {
		this.whereSql = whereSql;
	}

	public String getTransCol() {
		return transCol;
	}

	public void setTransCol(String transCol) {
		this.transCol = transCol;
	}

	public String getFromColCuid() {
		return fromColCuid;
	}

	public void setFromColCuid(String fromColCuid) {
		this.fromColCuid = fromColCuid;
	}
}
