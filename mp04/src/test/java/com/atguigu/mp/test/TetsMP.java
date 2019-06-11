package com.atguigu.mp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.beans.User;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.atguigu.mp.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:applicationContext.xml")

public class TetsMP {
	
	@Autowired
	EmployeeMapper  employeeMapper;
	
	
	@Autowired
	UserMapper userMapper;
	
	//  测试自定义全局操作
	@Test
	public void test1() {
		employeeMapper.deleteAll();
	}
	
	
	/**
	 *   测试逻辑删除
	 */
	@Test
	public void test2() {
		//  ==>  Preparing: UPDATE tbl_user SET logic_flag='-1' WHERE id=?     将删除操作改为更新操作
		Integer size = userMapper.deleteById(2);
		System.out.println("删除"+size);
	}
	
	@Test
	public void test3() {
		//  查询的sql    会带上logic_flag
		//  ==》SELECT id AS id,`name`,logic_flag AS logicFlag FROM tbl_user WHERE id=? AND logic_flag='1' 
		User user = userMapper.selectById(1);
		System.out.println(user);
	}
}
