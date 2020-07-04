package com.zime.maven.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zime.maven.entity.Department;
import com.zime.maven.service.DepartmentService;
import com.zime.maven.util.Msg;

@Controller
public class DepartHandler {
	@Autowired
	private DepartmentService departService;
	@ResponseBody
	@RequestMapping("/departs")
	public Msg getDeparts() {
		List<Department> departs = departService.getAll();
		return Msg.success().add("departs",departs);
	}
	
}
