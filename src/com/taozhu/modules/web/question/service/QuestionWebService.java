package com.taozhu.modules.web.question.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service("questionWebService")
public class QuestionWebService {
	
	@Autowired
	private BaseDAO baseDao;
//	
	public List<Map> getQuestionList(Map params){
		List<Map> meunList = this.baseDao.selectList("QuestionMapper.queryQuestionList", params);
		return meunList;
	}
	public Map getQuestionById(Map params){
		Map mp = (Map) this.baseDao.select("QuestionMapper.queryQuestionById", params);
		return mp;
	}
	public boolean saveQuestion(Map params){
		this.baseDao.save("QuestionMapper.insert", params);
		return true;
	}
	
	public boolean updateQuestion(Map params){
		this.baseDao.update("QuestionMapper.update", params);
		return true;
	}
	public void deleteQuestion(Map params) {
		// TODO Auto-generated method stub
		this.baseDao.delete("QuestionMapper.deleteQuestion", params);
	}
	
	/**
	 * 查询问题编码
	 * @return
	 */
	public synchronized int questionCode(){
		int questionCode = (Integer)baseDao.select("QuestionMapper.questionCode");
		if(questionCode<500000){
			questionCode=500000;
		}else{
			questionCode+=1;
		}
		return questionCode;
	}

}
