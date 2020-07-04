package com.zime.maven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zime.maven.dao.FunctionaryMapper;
import com.zime.maven.entity.FunctionaryExample.Criteria;
import com.zime.maven.entity.Functionary;
import com.zime.maven.entity.FunctionaryExample;

@Service
public class FunctionaryService {
	@Autowired
	private FunctionaryMapper functionaryMapper;
	//获取所有学生信息
	public List<Functionary> getAll(){
		FunctionaryExample functionaryExample=new FunctionaryExample();
		functionaryExample.setOrderByClause("Job_id ASC");
		return functionaryMapper.selectByExampleWithDepart(functionaryExample);
	}
	//获取一个学生信息
	public Functionary getFunctionary(Integer id) {
		return functionaryMapper.selectByPrimaryKeyWithDepart(id);
	}
	//检测姓名是否可用
	public boolean validateName(String name) {
		FunctionaryExample functionaryExample=new FunctionaryExample();
		Criteria criteria = functionaryExample.createCriteria();
		criteria.andNameEqualTo(name);
		long count = functionaryMapper.countByExample(functionaryExample);
		return count == 0;
	}
	//增加
	public void saveFunctionary(Functionary functionary) {
		functionaryMapper.insertSelective(functionary);
		
	}
	//更新
	public void updateFunctionary(Functionary functionary) {
		functionaryMapper.updateByPrimaryKeySelective(functionary);
	}
	//删除单个学生
	public void deleteFunctionary(Integer id) {
		functionaryMapper.deleteByPrimaryKey(id);
	}
	//批量删除
	public void deleteFunctionaryBatch(List<Integer> ids) {
		FunctionaryExample functionaryExample=new FunctionaryExample();
		Criteria criteria = functionaryExample.createCriteria();
		criteria.andJobIdIn(ids);
		functionaryMapper.deleteByExample(functionaryExample);
	}
	
}
