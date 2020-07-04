package com.zime.maven.handler;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zime.maven.entity.Functionary;
import com.zime.maven.service.FunctionaryService;
import com.zime.maven.util.Msg;

@Controller
public class FunctionaryHandler {
	@Autowired
	private FunctionaryService functionaryService;
	@ResponseBody
	@RequestMapping("/funs/{jobId}")
	public Msg findFun(@PathVariable("jobId") Integer id) {
		Functionary functionary=functionaryService.getFunctionary(id);
		return Msg.success().add("functionary", functionary);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/func/{jobIds}",method=RequestMethod.DELETE)
	public Msg deleteFun(@PathVariable("jobIds") String ids) {
		if(ids.contains(",")) {
			String[] strIds = ids.split(",");
			List<Integer> funIds = new ArrayList<>();
			for(String s:strIds) {
				funIds.add(Integer.parseInt(s));
			}
			functionaryService.deleteFunctionaryBatch(funIds);
		}
		else {
			Integer id=Integer.parseInt(ids);
			functionaryService.deleteFunctionary(id);
		}
		return Msg.success();
	}
	
	@ResponseBody
	@RequestMapping(value="/funcc/{jobId}",method=RequestMethod.GET)
	public Msg getFun(@PathVariable("jobId") Integer id) {
		Functionary functionary=functionaryService.getFunctionary(id);
		return Msg.success().add("functionary", functionary);
	}
	
	@ResponseBody
	@RequestMapping("/checkName")
	public Msg checkName(@RequestParam("funName") String name) {
		boolean flag = functionaryService.validateName(name);
		return flag==true?Msg.success():Msg.fail();
	}
	
	@ResponseBody
	@RequestMapping(value="/funccc/{jobId}",method=RequestMethod.PUT)
	public Msg update(@Valid Functionary functionary,BindingResult result) {
		System.out.println(functionary);
		if(result.getErrorCount()>0) {
			Map<String,Object> errors=new HashMap<String, Object>();
			for(FieldError error:result.getFieldErrors()) {
				System.out.println(error.getField()+":"+error.getDefaultMessage());
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return Msg.fail().add("errors", errors);
		}
		try {
			functionaryService.updateFunctionary(functionary);
			return Msg.success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Msg.fail().add("errors", e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping("/func")
	public Msg save(@Valid Functionary functionary,BindingResult result) {
		if(result.getErrorCount()>0) {
			Map<String,Object> errors=new HashMap<String, Object>();
			for(FieldError error:result.getFieldErrors()) {
				System.out.println(error.getField()+":"+error.getDefaultMessage());
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return Msg.fail().add("errors", errors);
		}
		try {
			functionaryService.saveFunctionary(functionary);
			return Msg.success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Msg.fail().add("errors", e.getMessage());
		}
	}
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		dateFormat.setLenient(false);
//		binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
//	}
	@ResponseBody
	@RequestMapping("/funcs")
	public Msg getfuncWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum) {
		PageHelper.startPage(pageNum, 8);// 每页的记录数
		List<Functionary> functionary = functionaryService.getAll();
		PageInfo<Functionary> pageInfo = new PageInfo<Functionary>(functionary, 5);
		return Msg.success().add("page", pageInfo);
	}

}