package com.taozhu.common.base.service;

import java.util.Map;

import com.taozhu.common.mybatis.page.vo.PageInfo;

public interface IDatagridService {
	PageInfo queryDataGrid(Map<String,Object> mp);
}
