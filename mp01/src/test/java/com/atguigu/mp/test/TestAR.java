package com.atguigu.mp.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
/**
 * 
 * @author spx
 *   如何使用AR（Active Record）   
 *    仅需要让实体类继承Model 类且实现主键指定方法  
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:applicationContext.xml")


//  
public class TestAR {

	/*
	 *   AR 插入操作
	 */
	
	@Test
	public void test1() {
		Employee employee = new Employee();
		
		employee.setLastName("Harden");
		employee.setGender(1);
		employee.setEmail("Harden@qq.com");
		employee.setAge(28);
		
		//   AR  语法： 对象自己操作对象本身
		boolean result = employee.insert();
		System.out.println("插入是否成功："+(result == true?"成功":"失败"));
		
	}
	
	
	/**
	 *   AR  修改操作
	 */
	@Test
	public void test2() {
		Employee employee = new Employee();
		employee.setId(15);
		employee.setLastName("JanmesHarden");
		employee.setGender(1);
		employee.setEmail("Harden@qq.com");
		employee.setAge(29);
		boolean result = employee.updateById();
		System.out.println("更新是否成功："+(result == true?"成功":"失败"));
	}
	
	/**
	 * AR 查询操作
	 */
	
	@Test
	public void test3() {
		Employee employee = new Employee();
		Employee emp = employee.selectById(15);
		System.out.println(emp);
	}
	
	@Test
	public void test4() {
		Employee employee = new Employee();
		List<Employee> selectAll = employee.selectAll();
		for (Employee employee2 : selectAll) {
			System.out.println(employee2);
		}
	}
	
	
	//  条件查询
	@Test
	public void test5() {
		Employee employee = new Employee();
		List<Employee> selectList = employee.selectList(new EntityWrapper<Employee>()
				.like("last_name", "de")
				);
		System.out.println(selectList);
	}
	
	
	@Test
	public void test6() {
		Employee employee = new Employee();
		int size = employee.selectCount(new EntityWrapper<Employee>()
				.eq("gender", 0)
				);
		System.out.println(size);
	}
	
	
	
	// 删除
	@Test
	public void test7() {
		Employee employee = new Employee();
		Boolean deleteById = employee.deleteById(15);
		System.out.println(deleteById);
	}
	
	//  AR 分页复杂操作
	@Test
	public void testARPage() {
		Employee employee = new Employee();
		//  返回一个page对象
		Page<Employee> page = employee.selectPage(new Page<Employee>(2,2),new EntityWrapper<Employee>()
				.like("last_name", "e")
				);
		System.out.println(page.getRecords());
	}

}
