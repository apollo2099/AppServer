package com.taozhu.modules.web.question.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
@Service("questionAttentionServic")
public class QuestionAttentionService {
	@Resource
	private BaseDAO baseDAO;

	public Map getQuestionAttentionById(Map params) {
		Map mp = (Map) this.baseDAO.select("QuestionMapper.queryQuestionAttentionList", params);
		return mp;
	}

	public void deleteQuestionAttention(Map params) {
		// TODO Auto-generated method stub
		this.baseDAO.delete("QuestionMapper.deleteQuestionAttention", params);
	}
	
	public boolean saveQuestionAttention(Map params){
		this.baseDAO.save("QuestionMapper.saveQuestionAttention", params);
		return true;
	}
	
	public boolean updateQuestionAttention(Map params){
		this.baseDAO.update("QuestionMapper.updateQuestionAttention", params);
		return true;
	}
	
}
