package com.taozhu.test.modules.user.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.taozhu.modules.app.user.controller.UserController;
import com.taozhu.test.common.util.BaseJunitAction;

public class UserControllerTest extends BaseJunitAction{

    @Resource
	private UserController userController;
    
    private MockMvc mockMvc;   
	 @Before  
	 public void setup() {  
	         mockMvc = MockMvcBuilders.standaloneSetup(userController).build();  
	  }  
	 
	@Test
	public void testLogin() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();  
	    MockHttpServletResponse response = new MockHttpServletResponse();  

		String param="{\"account\":\"admin\",\"password\":\"21232f297a57a5a743894a0e4a801fc3\"}";
		ResultActions ra = this.mockMvc.perform(MockMvcRequestBuilders  
				                .post("/userController.do/login")  
				                .accept(MediaType.APPLICATION_JSON)
				                .param("param", param));  
				        MvcResult mr = ra.andReturn();  
				        
				       
				       String result = mr.getResponse().getContentAsString();  
				       System.out.println(result);
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserPwd() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserAttention() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveUserAttention() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserAllow() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryUser() {
		fail("Not yet implemented");
	}

}
