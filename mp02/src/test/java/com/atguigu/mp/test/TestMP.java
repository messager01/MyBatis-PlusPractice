package com.atguigu.mp.test;

import org.junit.Test;

import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class TestMP {
	/**
	 *   代码生成器实例代码
	 */
	@Test
	public void testGenerator() {
		//1. 全局配置
		GlobalConfig config = new GlobalConfig();
		config.setActiveRecord(false)  //  是否支持AR模式  如果为true  则 实体类会继承Model
			  .setAuthor("史沛鑫")
			  .setOutputDir("F:\\STSworkspace\\MyBatis-PlusPractice\\mp02\\src\\main\\java")     // 设置生成路径
			  .setFileOverride(true)   //  文件覆盖
			  .setIdType(IdType.AUTO)   // 主键策略
			  .setServiceName("%sService")   // 设置生成的service接口的名字 的首字母是否为I  默认情况生成：IEmployeeService
			  .setBaseResultMap(true)  //  生成mapper接口的映射文件
			  .setBaseColumnList(true);  //  生成基本的SQL片段  方便使用
			  
			  
		//2. 数据源的配置
			  DataSourceConfig dsConfig = new DataSourceConfig();
			  dsConfig.setDbType(DbType.MYSQL)
			  		  .setDriverName("com.mysql.jdbc.Driver")
			  		  .setUrl("jdbc:mysql://127.0.0.1:3306/mp")
			  		  .setUsername("root")
			  		  .setPassword("root");
		//3.  策略配置
			  
			  StrategyConfig stConfig = new StrategyConfig();
			  stConfig.setCapitalMode(true)      //  开启全局大写命名
			  		  .setDbColumnUnderline(true)  //  指定表明  字段名是否使用下划线
			  		  .setNaming(NamingStrategy.underline_to_camel)   //  数据库表映射到实体类的策略   下划线转驼峰
			  		  .setTablePrefix("tbl_")  //  表前缀
			  		  .setInclude("tbl_employee");  //  数据库中的表名
		
		//4.  包名策略配置
			  
			  
			  PackageConfig pkConfig = new PackageConfig();
			  
			  pkConfig.setMapper("com.atguigu.mp.mapper");
			  pkConfig.setService("com.atguigu.mp.serive");
			  pkConfig.setController("com.atguigu.mp.controller");
			  pkConfig.setEntity("com.atguigu.mp.beans");
			  pkConfig.setXml("com.atguigu.mp.mapper");
		//5.  整合配置
			  
			  AutoGenerator ag = new AutoGenerator();
			  ag.setGlobalConfig(config);
			  ag.setDataSource(dsConfig);
			  ag.setPackageInfo(pkConfig);
			  ag.setStrategy(stConfig);
			  
		// 6. 执行
			  ag.execute();
	}

}
