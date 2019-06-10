package com.baomidou.service.impl;

import com.baomidou.com.atguigu.mp.beans.Employee;
import com.baomidou.com.atguigu.mp.mapper.EmployeeMapper;
import com.baomidou.com.atguigu.mp.serive.EmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 史沛鑫
 * @since 2019-06-10
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
