package com.zime.maven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zime.maven.dao.DepartmentMapper;
import com.zime.maven.entity.Department;

@Service
public class DepartmentService {
		@Autowired
		private DepartmentMapper departmentMapper;
		
		public List<Department> getAll(){
			return departmentMapper.selectByExample(null);
			
		}
		public Department getDepartment(Integer id) {
			return departmentMapper.selectByPrimaryKey(id);
		}
}
