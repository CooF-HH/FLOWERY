package com.zime.maven.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.zime.maven.entity.Functionary;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:springmvc.xml" })
public class WebMVCTest {
	 @Autowired
	private WebApplicationContext context;
	// 虚拟MVC请求 获得处理数据
	private MockMvc mockMvc;

	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testPage() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/funcs").param("pn", "2")).andReturn();
		MockHttpServletRequest request = mvcResult.getRequest();
		PageInfo<Functionary> pageInfo=(PageInfo<Functionary>) request.getAttribute("page");
		System.out.println("当前页码"+pageInfo.getPageNum());
		System.out.println("总页码"+pageInfo.getPages());
		System.out.println("总记录数"+pageInfo.getTotal());
		System.out.println("-------------------------------------------------------");
		
		List<Functionary> functionary = pageInfo.getList();
		for(Functionary fun:functionary) {
			System.out.println("ID"+fun.getJobId()+"--name"+fun.getName()+
					"--sex"+fun.getPosition()+"--email"+fun.getEmail()+"--phonenum"+fun.getPhoneNum()
					+"--birth"+fun.getBirth()+"--entry time"+fun.getEntryTime()
					+"--de_id"+fun.getDepartmentId());
		}
	}
}
