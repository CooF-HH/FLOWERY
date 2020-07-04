package com.zime.maven.dao;

import com.zime.maven.entity.Functionary;
import com.zime.maven.entity.FunctionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FunctionaryMapper {
    long countByExample(FunctionaryExample example);

    int deleteByExample(FunctionaryExample example);

    int deleteByPrimaryKey(Integer jobId);

    int insert(Functionary record);

    int insertSelective(Functionary record);

    List<Functionary> selectByExample(FunctionaryExample example);

    Functionary selectByPrimaryKey(Integer jobId);
    
    List<Functionary> selectByExampleWithDepart(FunctionaryExample example);

    Functionary selectByPrimaryKeyWithDepart(Integer jobId);

    int updateByExampleSelective(@Param("record") Functionary record, @Param("example") FunctionaryExample example);

    int updateByExample(@Param("record") Functionary record, @Param("example") FunctionaryExample example);

    int updateByPrimaryKeySelective(Functionary record);

    int updateByPrimaryKey(Functionary record);
}