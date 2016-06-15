package com.taozhu.common.base.service;

import java.util.List;
import java.util.Map;

import com.taozhu.common.easyui.compont.ComboBox;


public interface IComboBoxService {
	List<ComboBox> getComboxDataByType(Map<String, Object> mp);
}
