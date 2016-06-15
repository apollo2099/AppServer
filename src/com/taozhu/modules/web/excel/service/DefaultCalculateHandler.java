package com.taozhu.modules.web.excel.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.modules.web.excel.pojo.FileDefine;

@Service("DefaultCalculateHandler")
public class DefaultCalculateHandler implements ICalculateHandler {
	@Resource
	private BaseDAO baseDAO;

	public FileDefine execute(FileDefine df,
			Map<String, String> param) {
		//统计数据
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT (SELECT COUNT(*)                                                       ");    
		sql.append("          FROM "+df.getTemplateTable()+" T                              ");
		sql.append("         WHERE T.RELATED_TEMPLATE_CUID = '"+df.getTemplateCuid()+"'       ");
		sql.append("           AND T.BATCH_NO = '"+df.getBatchNo()+"') AS TOTAL,                 ");
		sql.append("       (SELECT COUNT(*)                                                       ");
		sql.append("          FROM "+df.getTemplateTable()+" T                              ");
		sql.append("         WHERE T.RELATED_TEMPLATE_CUID = '"+df.getTemplateCuid()+"'       ");
		sql.append("           AND T.BATCH_NO = '"+df.getBatchNo()+"'                    ");
		sql.append("           AND T.MSG IS NULL) AS S_TOTAL,                                     ");
		sql.append("       (SELECT COUNT(*)                                                       ");
		sql.append("          FROM "+df.getTemplateTable()+" T                              ");
		sql.append("         WHERE T.RELATED_TEMPLATE_CUID = '"+df.getTemplateCuid()+"'       ");
		sql.append("           AND T.BATCH_NO = '"+df.getBatchNo()+"'                    ");
		sql.append("           AND T.MSG IS NOT NULL) AS W_TOTAL                                  ");
		sql.append("  FROM DUAL                                                                   ");
		List<Map> resultList = this.baseDAO.querySql(sql.toString());
		Map result = resultList.get(0);
		long total = BaseDAOUtil.getLongValue(result, "TOTAL");
		long s_total = BaseDAOUtil.getLongValue(result, "S_TOTAL");
		long w_total = BaseDAOUtil.getLongValue(result, "W_TOTAL");
		df.setTotal((int)total);
		df.setSucess((int)s_total);
		df.setError((int)w_total);
		return df;
	}

}
