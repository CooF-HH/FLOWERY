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
	//��ȡ����ѧ����Ϣ
	public List<Functionary> getAll(){
		FunctionaryExample functionaryExample=new FunctionaryExample();
		functionaryExample.setOrderByClause("Job_id ASC");
		return functionaryMapper.selectByExampleWithDepart(functionaryExample);
	}
	//��ȡһ��ѧ����Ϣ
	public Functionary getFunctionary(Integer id) {
		return functionaryMapper.selectByPrimaryKeyWithDepart(id);
	}
	//��������Ƿ����
	public boolean validateName(String name) {
		FunctionaryExample functionaryExample=new FunctionaryExample();
		Criteria criteria = functionaryExample.createCriteria();
		criteria.andNameEqualTo(name);
		long count = functionaryMapper.countByExample(functionaryExample);
		return count == 0;
	}
	//����
	public void saveFunctionary(Functionary functionary) {
		functionaryMapper.insertSelective(functionary);
		
	}
	//����
	public void updateFunctionary(Functionary functionary) {
		functionaryMapper.updateByPrimaryKeySelective(functionary);
	}
	//ɾ������ѧ��
	public void deleteFunctionary(Integer id) {
		functionaryMapper.deleteByPrimaryKey(id);
	}
	//����ɾ��
	public void deleteFunctionaryBatch(List<Integer> ids) {
		FunctionaryExample functionaryExample=new FunctionaryExample();
		Criteria criteria = functionaryExample.createCriteria();
		criteria.andJobIdIn(ids);
		functionaryMapper.deleteByExample(functionaryExample);
	}
	
}
