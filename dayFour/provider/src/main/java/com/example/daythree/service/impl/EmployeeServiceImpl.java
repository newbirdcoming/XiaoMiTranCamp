package com.example.daythree.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.daythree.domain.Employee;
import com.example.daythree.service.EmployeeService;
import com.example.daythree.mapper.EmployeeMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
* @author 13789
* @description 针对表【employee(员工表)】的数据库操作Service实现
* @createDate 2025-05-11 17:17:07
*/
@DubboService
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




