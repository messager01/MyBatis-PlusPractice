package com.atguigu.mp.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * @author spx
 * MyBatis-plus 是通过 EntityWrapper (简称EW  是 MP 的一个查询条件构造器)
 * 或者  Condition(与EW 类似) 来让用户自由的构建条件查询，简单便捷，没有额外负担
 * 能够有效提升开发效率
 *
 *
 *  注意： 使用的时候是数据库的字段  而不是 Java属性
 */

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:applicationContext.xml")

public class TestEntityWrapper {
	/**
	 *  条件构造器  查询操作
	 */
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Test
	public void testEntityWrapper() {
		//  查询 tbl_employee 中   年龄   30-35  的男性
	//  ==> Preparing: SELECT id,last_name AS lastName,email,gender,age FROM tbl_employee WHERE (age BETWEEN ? AND ? AND gender = ?) 
	//	==> Parameters: 30(Integer), 35(Integer), 1(Integer)	
		List<Employee> selectPage = employeeMapper.selectPage(new Page<Employee>(2,2),new EntityWrapper<Employee>()
				.between("age", 30, 35)
				.eq("gender", 1)
				);
		System.out.println(selectPage);
		
	}
	
	//  条件查询       selectList
	//  插叙年龄在30-35  性别为男  名字中含有 'e'   或者邮箱中 含有'd'
	@Test
	public void test1() {
		List<Employee> list = employeeMapper.selectList(new EntityWrapper<Employee>()
				.between("age", 30, 35)
				.like("last_name", "e")
				//.or()    // WHERE (age BETWEEN ? AND ? AND last_name LIKE ? OR email LIKE ?) 
				.orNew()  //  WHERE (age BETWEEN ? AND ? AND last_name LIKE ?) OR (email LIKE ?) 
				.like("email","a")
				);
		System.out.println(list);
	}
	
	//带条件的修改     update
	// 修改年龄为35   且名字为wade的信息
	@Test
	public void test2() {
		Employee employee = new Employee();
		employee.setLastName("Wade");
		employee.setEmail("dw@163.com");
		employee.setGender(0);
		EntityWrapper<Employee> entityWrapper = new EntityWrapper<Employee>();
		//Preparing: UPDATE tbl_employee SET last_name=?, email=?, gender=? WHERE (last_name = ? AND age = ?)
		//entityWrapper.eq("last_name", "Wade").and().eq("age", 35);
		
		//==>  Preparing: UPDATE tbl_employee SET last_name=?, email=?, gender=? WHERE (last_name = ? AND age = ?) 
		entityWrapper.eq("last_name", "DW").eq("age", 35);
		Integer size = employeeMapper.update(employee, entityWrapper);
		System.out.println("更新"+size+"条数据");
	}
	
	
	//  带条件的删除操作
	@Test    // delete
	public void test3() {
		//  ==> DELETE FROM tbl_employee WHERE (last_name = ? AND age = ?) 
		Integer size = employeeMapper.delete(new EntityWrapper<Employee>()
				.eq("last_name", "CP3")
				.eq("age", 33)
				);
		System.out.println("删除"+size+"条数据");
	}
	
	
	/**
	 *   practice  
	 */
	
	// 查询性别为女的   且按年龄排序（asc/desc）
	@Test
	public void test4() {
		List<Employee> selectList = employeeMapper.selectList(new EntityWrapper<Employee>()
				.eq("gender", 1)
				.orderAsc(Arrays.asList(new String[] {"age"}))           //  将条件构造成一个集合   升序
				// desc
				);
		for (Employee employee : selectList) {
			System.out.println(employee);
		}
	}

}
