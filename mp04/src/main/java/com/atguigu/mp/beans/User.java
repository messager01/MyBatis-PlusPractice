package com.atguigu.mp.beans;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value = "tbl_user")
public class User {
	
	@TableId
	private Integer id;
	
	private String name;
	
	@TableLogic    // 标示为逻辑删除属性
	private String logicFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogicFlag() {
		return logicFlag;
	}

	public void setLogicFlag(String logicFlag) {
		this.logicFlag = logicFlag;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", logicFlag=" + logicFlag + "]";
	}
	
	
	
	

}
