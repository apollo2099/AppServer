package com.taozhu.modules.web.excel.service;

import java.io.FileNotFoundException;


public interface IFileErrorBO {

	public String exportErrorFile(String batchNo, String relatedTemplateCuid) throws FileNotFoundException;
}
