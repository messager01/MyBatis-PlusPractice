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

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMP {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	
	/**
	 *   测试 分页插件
	 */
	@Test
	public void testPage() {
		Page<Employee> page = new Page<Employee>(3,2);    //  查询第3页  每页2条数据
		// ==>  Preparing: SELECT id,last_name AS lastName,email,gender,age FROM tbl_employee LIMIT 4,2 
		//  使用了分页插件之后  会真正的进行物理分页  而不是内存分页
		
		List<Employee> employees = employeeMapper.selectPage(page, null);   //  只分页
		
		System.out.println(employees);
		System.out.println("==================获取分页相关的信息========================");
		System.out.println("总条数:"+page.getTotal());
		System.out.println("当前页码:"+page.getCurrent());
		System.out.println("总页码:"+page.getPages());
		System.out.println("每页显示的条数:"+page.getSize());
		System.out.println("是否有上一页:"+page.hasPrevious());
		System.out.println("是否有下一页:"+page.hasNext());
	}
	
	/**
	 *   测试执行分析插件               只建议在开发环境中使用
	 */
	@Test
	public void testSQLExplain() {
		employeeMapper.delete(null);       // 不传入wrapper  即为全部删除
		
		//  Full table operation is prohibited. SQL: DELETE FROM tbl_employee
	}
	
	/**
	 *   测试性能分析插件
	 */
	
		@Test
		public void testPerformance() {
			Employee employee = new Employee();
			employee.setLastName("Durant");
			employee.setEmail("Durant@qq.com");
			employee.setAge(27);
			employee.setGender(1);
			employeeMapper.insert(employee);
		}
		
		
		/**
		 *   测试乐观锁插件
		 */
		
		@Test
		public void testOptimisticLocker() {
			// 更新操作
			
			List<Employee> list = employeeMapper.selectList(new EntityWrapper<Employee>()
									.eq("last_name", "SuperKevinDurant")
					);
			Employee emp = list.get(0);
			emp.setLastName("KD");
			Integer updateById = employeeMapper.updateById(emp);
		}
	
	
	

}
