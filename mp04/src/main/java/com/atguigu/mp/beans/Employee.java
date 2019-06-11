package com.atguigu.mp.beans;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 
 * @author spx
 * 
 * javaBean
 * 
 *   实体类中的 数据类型要用包装类  方便判断
 *
 */


//@TableName(value = "tbl_employee")
public class Employee extends Model<Employee> {
	
	//@TableId(type = IdType.AUTO)    //  自增
	private Integer id;
	
	@TableField(value = "last_name")       //  表明这个属性  对应数据库中的哪个字段
	private String lastName;
	
	private String email;
	
	private Integer gender;
	
	private Integer age;
	
	
	
	@TableField(exist = false)   //   标明该属性   是否为数据库中对应的字段   默认为 true
	private Double salary;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + ", age="
				+ age + ", salary=" + salary + "]";
	}

	
	//  指定主键
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return id;
	}

	
	

}
