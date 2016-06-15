package com.taozhu.modules.web.banner.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.taozhu.common.base.controller.BaseWebController;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.AjaxUtil;
import com.taozhu.common.util.CodeUtils;
import com.taozhu.modules.web.bankBranch.service.BankBranchService;
import com.taozhu.modules.web.banner.service.EnterBannerService;
/**
 * 广告信息录入
 *
 */
@Controller
@RequestMapping(value="enterBanner.do")
public class EnterBannerController extends BaseWebController{
	
	@Autowired
	EnterBannerService enterBannerService;
	@Autowired
	BankBranchService bankBranchService;
	/**
	 * 首页广告信息处理
	 * 数据模型lv_banner
	 *  返回值 1：表示成功，0，表示失败
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="lv_banner")
	public void lv_banner(HttpServletResponse res,HttpServletRequest req ){
		Map banner = getRequestParams(req);
		banner.put("modify_name", "admin");
		int id = BaseDAOUtil.getIntValue(banner, "id");
		if(id==0){
			//设置广告内置编码
			Integer bannerId= enterBannerService.bannerCode();
			banner.put("code",  bannerId);     
			int result = enterBannerService.saveLv_banner(banner);
			banner.clear();
			banner.put("msg", "插入成功！");
		}else{
			banner.put("banner_desc",BaseDAOUtil.getStringValue(banner, "banner_desc2"));
			int result = enterBannerService.updateBanner(banner);
			banner.clear();
			banner.put("msg", "修改成功！");
		}
		banner.put("success", true);
		AjaxUtil.ajaxResponse(res, JSONArray.toJSONString(banner), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	/**
	 * 网店广告信息处理
	 * 数据模型lv_branch_banner
	 * 返回值 1：表示成功，0，表示失败
	 */
	@RequestMapping(params="lv_branch_banner")
	public void lv_branch_banner(HttpServletRequest req, HttpServletResponse res){
		Map banner = getRequestParams(req);
                                                       
		banner.put("modify_name", "admin");
		int id = BaseDAOUtil.getIntValue(banner, "id");
		if(id==0){
			//设置广告内置编码
			Integer branchBannerId= enterBannerService.branchBannerCode();
			banner.put("code",  branchBannerId);     
			int result =enterBannerService.saveLv_branch_banner(banner);
			banner.clear();
			banner.put("msg", "插入成功！");
		}else{
			banner.put("banner_desc",BaseDAOUtil.getStringValue(banner, "banner_desc2"));
			int result = enterBannerService.updateBranchBanner(banner);
			banner.clear();
			banner.put("msg", "修改成功！");
		}
		banner.put("success", true);
		AjaxUtil.ajaxResponse(res, JSONArray.toJSONString(banner), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	/**
	 * 显示广告详细内容
	 * @param req
	 * @return
	 */
	@RequestMapping(params="showDetail")
	public String showDetail(HttpServletResponse response,HttpServletRequest req,Map<String,Object> model){
		Map params = this.getRequestParams(req);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = enterBannerService.getBannerById(params);
		}
		model.put("mp", mp);
		return "banner/EnterBanner";
	}
	
	@RequestMapping(params="bannerList")
	public String bannerList(HttpServletRequest req, HttpServletResponse res){
		
		return "banner/BannerList";
	}
	
	@RequestMapping(params="deleteBanner")
	public void deleteBanner(HttpServletRequest req, HttpServletResponse res){
		Map params = this.getRequestParams(req);
		enterBannerService.deleteBanner(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(res, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	
	//网点广告=====================================================
	
	@RequestMapping(params="enterBranchBanner")
	public String enterBranchBanner(HttpServletRequest req, HttpServletResponse res,Map<String,Object> model){
		Map params = this.getRequestParams(req);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = enterBannerService.getBranchBannerById(params);
		}
		model.put("mp", mp);
		//查询所有的网点信息
		List<Map<String,Object>> list = bankBranchService.getBankBranchList();
		model.put("list", list);
		return "banner/EnterBranchBanner";
	}
	@RequestMapping(params="branchBannerList")
	public String enterBranchList(HttpServletRequest req, HttpServletResponse res){
		
		return "banner/BranchBannerList";
	}
	@RequestMapping(params="deleteBranchBanner")
	public void deleteBranchBanner(HttpServletRequest req, HttpServletResponse res){
		Map params = this.getRequestParams(req);
		enterBannerService.deleteBranchBanner(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(res, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
}
