package com.atguigu.mp.injector;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.mapper.AutoSqlInjector;

/**
 * 自定义全局操作
 *   扩展AutoSqlInjector    注入到basemapper中
 *
 */
public class MySqlInjector extends AutoSqlInjector {
	
	/**
	 * 扩展inject
	 */

	@Override
	public void inject(Configuration configuration, MapperBuilderAssistant builderAssistant, Class<?> mapperClass,
			Class<?> modelClass, TableInfo table) {
		//  将EmployeeMapper中的deleteAll   处理成对应的MappedStatement对象   加入到configuration对象中
		
		//  要注入的SQL语句
		String sql = "delete from "+ table.getTableName();
		System.out.println(sql);
		
		// 要注入的方法名    与mapper接口中的方法名一致
		String method = "deleteAll";
		
		// 构造SqlSource对象
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
		
		// 构造删除的statement
		this.addDeleteMappedStatement(mapperClass, method, sqlSource);
		
	}
	
	
	
}
