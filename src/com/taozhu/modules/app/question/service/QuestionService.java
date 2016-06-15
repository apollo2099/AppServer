package com.taozhu.modules.app.question.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.taozhu.common.base.dao.BaseDAO;

@Service(value="questionService")
public class QuestionService {
	
	private static final String sqlMap ="QuestionMapper";
	
	@Resource
	private BaseDAO baseDAO;

	public List<Map<String,Object>> queryList(Map<String,Object> mp){
		List<Map<String,Object>> list = this.baseDAO.selectList(this.sqlMap+".queryList", mp);
		if(list==null){
			list = new ArrayList<Map<String,Object>>();
		}
		return list;
	}
	
	/**
	 * 根据问题编码查询问题答案详情信息
	 * @param mp
	 * @return
	 */
	public List<Map<String,Object>> queryQuestionAttentionList(Map<String,Object> mp){
		List<Map<String,Object>> list = this.baseDAO.selectList(this.sqlMap+".queryQuestionAttentionListByQCode", mp);
		if(list==null){
			list = new ArrayList<Map<String,Object>>();
		}
		return list;
	}
	
	public List<Map<String,Object>> queryUserQuesListList(Map<String,Object> mp){
		List<Map<String,Object>> list = this.baseDAO.selectList(this.sqlMap+".queryUserQuesList", mp);
		if(list==null){
			list = new ArrayList<Map<String,Object>>();
		}
		return list;
	}
	public Map<String,Object> queryUserQuestion(Map<String,Object> mp){
		int num = (Integer) this.baseDAO.select(this.sqlMap+".queryUserQuestion", mp);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("questionCount", num);
		return map;
	}
	public int insertUserQues(Map<String,Object> paramMap){
		//用户与问题关联数据
		List<Map<String, Object>> userQuesRelateList = new ArrayList<Map<String,Object>>();
		String userCode="";
		Set<String> userCodeSet = paramMap.keySet();
		for(String uc : userCodeSet){
			userCode = uc;
			List<Map<String,Object>> userQuesRelateSubList = (List<Map<String, Object>>) paramMap.get(uc);
			for(Map<String,Object> userQuesRelate : userQuesRelateSubList){
				userQuesRelate.put("userCode", uc);
				userQuesRelateList.add(userQuesRelate);
			}
		}
		int state = 0;
		try{
			//插入用户关联问题表
			this.baseDAO.save(this.sqlMap+".insertUserQuesRelate", userQuesRelateList);
			Map<String,Object> mp = new HashMap<String,Object>();
			mp.put("userCode", userCode);
			//回答问题过后修改用户是否回答过问题
			this.baseDAO.update(this.sqlMap+".updateUserIsAnswer",mp);
			state = 1;
		}catch(Exception e){
			e.printStackTrace();
			state = 0;
		}
		return state;
	}
}
