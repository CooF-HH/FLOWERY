package com.zime.maven.test;
import static org.junit.Assert.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;
import com.zime.maven.dao.DepartmentMapper;
import com.zime.maven.dao.FunctionaryMapper;
import com.zime.maven.entity.Department;
import com.zime.maven.entity.Functionary;
import com.zime.maven.service.DepartmentService;
import com.zime.maven.service.FunctionaryService;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
@Service
public class MapperTest {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private FunctionaryMapper functionaryMapper;
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private FunctionaryService funcservice;
	@Test
	public void test() {
	DataSource dataSource = (applicationContext).getBean(DataSource.class);
		System.out.println(dataSource);
		}
	@Test
	public void testAddDepartment() {
		departmentMapper.insertSelective(new Department(null,"管理层"));
		departmentMapper.insertSelective(new Department(null,"财务部"));
		Department d = new Department() ;
		d.setDepartmentId(556);
		d.setDepartName("猫猫宿舍");
		departmentMapper.insertSelective(d);
	}
	@Test
	public void testAddFunc(){
        Functionary func = new Functionary(888,"李四","男","222333@qq.com","13324578788", new Date(),new Date(),4);
        int n=	functionaryMapper.insertSelective(func);
        System.out.println(n);
	}
	

	
	@Test
	public void testAddFuncs() {
		FunctionaryMapper fmapper=sqlSession.getMapper(FunctionaryMapper.class);
		Random random=new Random();
		for(int i=600;i<=700;i++)
		{
			String name=UUID.randomUUID().toString().substring(0, 5)+i;
			Functionary func = new Functionary(i,name,"男","222333@qq.com","13324578788", new Date(),new Date(),random.nextInt(8));
			int n=fmapper.insertSelective(func);
			System.out.println(n);
		}
   
	}
	@Test
	public void testGetDeparts() {
		System.out.println(departmentService.getAll());
	}
	@Test 
	public void testDelFun() {
		funcservice.deleteFunctionary(7);
	}

}
