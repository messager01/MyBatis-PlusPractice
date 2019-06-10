package com.atguigu.mp.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.atguigu.mp.queryVo.NameAndEmailVo;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * @author spx
 *  mybatis-plus 閫氱敤crud
 */

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:applicationContext.xml")
public class TestCRUD {
	
	@Autowired
	DataSource datasource;
	
	
	@Autowired
	EmployeeMapper employeeMapepr;
	
	@Test
	public void test1() {
		System.out.println(datasource);
		
		try {
			datasource.getConnection();
		} catch (SQLException e) {
			System.out.println("鏃犺繛鎺�");
			e.printStackTrace();
		}
	}

	//  鎻掑叆鎿嶄綔
	@Test
	public void insert() {
		Employee employee = new Employee();
		employee.setLastName("Duncan");
		employee.setEmail("Duncan@qq.com");
		/*
		 * employee.setAge(33); employee.setGender(1);
		 */
		employee.setSalary(2000.0);
		
		// 浣跨敤 insert  鏂规硶 濡傛灉瀹炰綋涓病鏈夎灞炴�э紝閭ｄ箞SQL璇彞涓氨涓嶄細 鍑虹幇璇ュ瓧娈碉紝  
		//  INSERT INTO tbl_employee ( last_name, email ) VALUES ( ?, ? ) 
		// Integer size = employeeMapepr.insert(employee);
		//  濡傛灉瑕佸嚭鐜版墍鏈夊瓧娈�  鍒欒浣跨敤    insertAllColumn
		Integer size = employeeMapepr.insertAllColumn(employee);
		//    INSERT INTO tbl_employee ( last_name,email,gender,age ) VALUES ( ?,?,?,? )
		System.out.println("鎻掑叆"+size+"鏉�");
		//  mybatis-plus 浼氳嚜鍔ㄧ殑鑾峰彇褰撳墠鎻掑叆鏁版嵁鐨勪富閿��
		System.out.println(employee.getId());
	}
	
	
	//  鏇存柊鎿嶄綔
	
	@Test
	public void update() {
		// 鍒濆鍖栦慨鏀瑰璞�    涔熷彲浠ヨ鏄幏寰楀璞�
		Employee empByName = employeeMapepr.getEmpByName("Tony Parker");
		empByName.setLastName("TonyParker");
		empByName.setSalary(3000.0);
		System.out.println(empByName);
		Integer size = employeeMapepr.updateById(empByName);
		System.out.println("鏇存柊浜�"+size+"鏉¤褰�");
		
		// updateAllColumnById  鍚�   insertAllColumn   鎵�鏈夌殑鍒楅兘浼氬嚭鐜板湪SQL璇彞涓�
		//   濡傛灉鍙兂淇敼閮ㄥ垎鍒�  淇濈暀鍏跺畠閮ㄥ垎鍒楃殑鏁版嵁   搴斾娇鐢�  updateById
	}

	
	//  鏌ヨ鎿嶄綔        selectById     selectOne
	@Test 
	public void query() {
		// 閫氳繃id鏌ヨ
		//Employee selectById = employeeMapepr.selectById(5);
		//System.out.println(selectById);
		
		
		//  閫氳繃澶氫釜鍒楄繘琛屾煡璇�(閫氳繃id+lastname鏌ヨ)
		Employee employee = new Employee();
		employee.setId(5);
		employee.setLastName("Wade");
		//  SELECT id,last_name AS lastName,email,gender,age FROM tbl_employee WHERE id=? AND last_name=? 
		//  鐩稿綋浜庢潯浠舵煡璇�
		//   selectOne  鍙兘鏌ヨ鍑�  null 鎴�  涓�鏉¤褰�   濡傛灉鏌ヨ鍑哄鏉¤褰曞氨浼氭姤閿�
		Employee selectOne = employeeMapepr.selectOne(employee);
		System.out.println(selectOne);
	}
	
	
	//  selectBatchIds   閫氳繃澶氫釜id鏌ヨ  杩斿洖涓�涓泦鍚�  
	//   SELECT id,last_name AS lastName,email,gender,age FROM tbl_employee WHERE id IN ( ? , ? ) 
	@Test
	public void test4() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		List<Employee> selectBatchIds = employeeMapepr.selectBatchIds(ids);
		System.out.println(selectBatchIds);
	}
	
	
	//  閫氳繃map灏佽鏉′欢鏌ヨ
	@Test
	public void test5() {
		Map<String,Object> map = new HashMap();
		map.put("last_name","LBJ");  //  娉ㄦ剰 锛氳繖閲岀殑key鍊艰浣跨敤 鏁版嵁搴撲腑鐨勫瓧娈靛悕锛岃�屼笉鏄璞＄殑灞炴�у悕
		map.put("gender", 1);
		//  SELECT id,last_name AS lastName,email,gender,age FROM tbl_employee WHERE gender = ? AND last_name = ? 
		List<Employee> selectByMap = employeeMapepr.selectByMap(map);
		System.out.println(selectByMap);
	}
	
	//  鍒嗛〉鏌ヨ     鍐呭瓨鍒嗛〉   骞朵笉鏄墿鐞嗗垎椤碉紙浣跨敤  limit 鏌ヨ锛�
	@Test
	public void test6() {
		List<Employee> selectPage = employeeMapepr.selectPage(new Page<Employee>(2,2),null);    //  鏌ヨ褰撳墠绗簩椤�   姣忛〉涓ゆ潯鏁版嵁
		System.out.println(selectPage);
	}
	
	
    //  鍒犻櫎鎿嶄綔    deleteById    DELETE FROM tbl_employee WHERE id=? 
	@Test
	public void test7() {
		Integer size = employeeMapepr.deleteById(14);
		System.out.println("鍒犻櫎"+size+"鏉�");
	}
	
	//  鏉′欢鍒犻櫎
	@Test
	public void test8() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("last_name", "Miller");     //  鍚屼笂  浣跨敤  鏁版嵁搴撲腑鐨勫瓧娈靛悕
		map.put("gender", 1);
		employeeMapepr.deleteByMap(map);
	}
	
	
	//  鎵归噺鍒犻櫎
	@Test
	public void test9() {
		List<Integer> list = new ArrayList<Integer> ();
		for(int i = 11;i <= 13;i = i + 2) {
			list.add(i);
		}
		employeeMapepr.deleteBatchIds(list);  //   DELETE FROM tbl_employee WHERE id IN ( ? , ? ) 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//  娴嬭瘯鑷畾涔夋煡璇�
	@Test
	public void test2() {
		Employee empByName = employeeMapepr.getEmpByName("Wade");
		System.out.println(empByName);
	}
	
	
	// 娴嬭瘯鑷畾涔塺esultMap
	@Test
	public void test3() {
		NameAndEmailVo nameAndEmailVo = employeeMapepr.getNameAndEmailVoById(5);
		System.out.println(nameAndEmailVo);
	}
}
