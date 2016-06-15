package com.taozhu.modules.web.banner.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service("enterBannerService")
public class EnterBannerService {

	@Autowired
	private BaseDAO baseDao;
	
	
	public int saveLv_banner(Map params){
		return this.baseDao.save("BannerMapper.saveLv_banner", params);

		
	}
	public int updateBanner(Map params){
		return this.baseDao.save("BannerMapper.updateBanner", params);
	}
	public int updateBranchBanner(Map params){
		return this.baseDao.save("BannerMapper.updateBranchBanner", params);
	}

	public int saveLv_branch_banner(Map branchBannerVo){
		return this.baseDao.save("BannerMapper.saveLv_branch_banner", branchBannerVo);

		
	}
	public Map getBannerById(Map params) {
		Map<String,Object> mp = (Map)this.baseDao.select("BannerMapper.queryBannerList", params);
		return mp;
	}
	public Map getBranchBannerById(Map params) {
		Map<String,Object> mp = (Map)this.baseDao.select("BannerMapper.queryBannerDetail", params);
		return mp;
	}


	public synchronized int bannerCode() {
		int code = (Integer)baseDao.select("BannerMapper.bannerCode");
		if(code<600000){
			code = 600000;
		}else{
			code+=1;
		}
		return code;
	}
	
	public synchronized int branchBannerCode() {
		int code = (Integer)baseDao.select("BannerMapper.branchBannerCode");
		if(code<220000){
			code = 220000;
		}else{
			code+=1;
		}
		return code;
	}
	
	public void deleteBanner(Map params) {
		// TODO Auto-generated method stub
		this.baseDao.delete("BannerMapper.deleteBanner", params);
	}
	public void deleteBranchBanner(Map params) {
		// TODO Auto-generated method stub
		this.baseDao.delete("BannerMapper.deleteBranchBanner", params);
	}


}
