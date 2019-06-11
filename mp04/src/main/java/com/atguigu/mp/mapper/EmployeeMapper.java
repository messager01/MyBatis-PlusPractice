package com.atguigu.mp.mapper;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.queryVo.NameAndEmailVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * @author spx
 * mapper 接口
 * 基于mybatis中：  在mapper接口中  编写crud 的相关方法  提供mapper接口对应的SQL映射文件
 * 对于MyBatis-plus  让mapper接口继承BaseMapper接口即可  
 * BaseMapper<?>   泛型  即操作的实体类的类型
 *
 */

public interface EmployeeMapper extends BaseMapper<Employee> {
	Employee getEmpByName(String name);
	
	NameAndEmailVo getNameAndEmailVoById(Integer id);
	
	
	/**
	 * 自定义全局操作，在mapper中自定义方法， 可以不在对应的xml文件中编写SQL
	 * 通过设置可以  像BaseMapper中的方法一样自动注入     通过AutoSqlInjector  就可以在加载mybatis环境时就自动注入
	 *   举例：deleteAll
	 * 
	 */
	
	int deleteAll();
	
	
}
