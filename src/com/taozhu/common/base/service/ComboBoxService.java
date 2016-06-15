package com.taozhu.common.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.easyui.compont.ComboBox;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
@Service(value="comboBoxService")
public class ComboBoxService implements IComboBoxService {
	@Resource
	private BaseDAO baseDAO;
	@Override
	public List<ComboBox> getComboxDataByType(Map<String, Object> mp) {
		// TODO Auto-generated method stub
		List<ComboBox> comboBoxList = new ArrayList<ComboBox>();
		String mapperXml = BaseDAOUtil.getStringValue(mp, "mapperXml");
		List<Map<String,Object>> list = baseDAO.selectList(mapperXml, mp);
		for(Map<String,Object> pm :list){
			ComboBox comboBox = new ComboBox();
			String id = BaseDAOUtil.getStringValue(pm, "id");
			String text = BaseDAOUtil.getStringValue(pm, "text");
			comboBox.setId(id);
			comboBox.setText(text);
			comboBoxList.add(comboBox);
		}
		return comboBoxList;
	}

}
