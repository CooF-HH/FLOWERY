package com.zime.maven.entity;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Functionary {
    private Integer jobId;
    @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,20})",message="请输入姓名（姓名格式：大于2位的汉字或者6~18位的字符）")
    private String name;

    private String position;
    @Email
    private String email;
    @Pattern(regexp="^((13[0-9])|(14[5|7])|(15[0-3][5-9]))\\d{8}$",message="无效的手机号码")
    private String phoneNum;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date entryTime;

    private Integer departmentId;
    
    private Department depart;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }
  
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
  
    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    


	@Override
	public String toString() {
		return "Functionary [jobId=" + jobId + ", name=" + name + ", position=" + position + ", email=" + email
				+ ", phoneNum=" + phoneNum + ", birth=" + birth + ", entryTime=" + entryTime + ", departmentId="
				+ departmentId + "]";
	}

	public Functionary(Integer jobId, String name, String position, String email, String phoneNum, Date birth,
			Date entryTime, Integer departmentId) {
		super();
		this.jobId = jobId;
		this.name = name;
		this.position = position;
		this.email = email;
		this.phoneNum = phoneNum;
		this.birth = birth;
		this.entryTime = entryTime;
		this.departmentId = departmentId;
	}

	public Functionary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}
    
    
}