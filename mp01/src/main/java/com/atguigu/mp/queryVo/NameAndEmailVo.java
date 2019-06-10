package com.atguigu.mp.queryVo;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(resultMap = "NameAndEmailVo")
public class NameAndEmailVo {
	
	private String name;
	
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "nameAndEmail [name=" + name + ", email=" + email + "]";
	}
	
	
	

}
