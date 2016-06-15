package com.taozhu.modules.web.excel.service.batch;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.modules.web.batch.service.BatchWebService;
import com.taozhu.modules.web.excel.pojo.FileDefine;
import com.taozhu.modules.web.excel.service.ICalculateHandler;

@Service("BatchCalculateHandler")
public class BatchCalculateHandler implements ICalculateHandler{

	@Resource
	private BatchWebService batchWebService;
	@Override
	public FileDefine execute(FileDefine df, Map<String, String> param) {
		Map pm = new HashMap();
		pm.put("templateCuid", df.getTemplateCuid());
		pm.put("batchNo", df.getBatchNo());
		Map map =batchWebService.calculate(pm);
		long total = BaseDAOUtil.getLongValue(map, "TOTAL");
		long w_total = BaseDAOUtil.getLongValue(map, "ERROR_TOTAL");
		long s_total = total-w_total;
		df.setTotal((int)total);
		df.setError((int)w_total);
		df.setSucess((int)s_total);
		return df;
	}

}
