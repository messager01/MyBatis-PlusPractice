package com.atguigu.mp.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.mapper.Condition;

/**
 * 
 * @author spx
 *
 */

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:applicationContext.xml")

public class TestCondition {
	
	@Autowired
	EmployeeMapper emploteeMapper;
	
	
	//  查询 tbl_employee 中   年龄   30-35  的男性
	@Test
	public void test1() {
		List<Employee> emps = emploteeMapper.selectList(Condition.create()
	//  Preparing: SELECT id,last_name AS lastName,email,gender,age FROM tbl_employee WHERE (age BETWEEN ? AND ? AND gender = ?) 
				.between("age", 30, 35)
				.eq("gender", 1)
				);
		System.out.println(emps);
	}
	
}
