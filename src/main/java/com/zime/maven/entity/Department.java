package com.zime.maven.entity;

public class Department {
    private Integer departmentId;

    private String departName;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName == null ? null : departName.trim();
    }

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departName=" + departName + "]";
	}

	public Department(Integer departmentId, String departName) {
		super();
		this.departmentId = departmentId;
		this.departName = departName;
	}

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}