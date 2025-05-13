package com.example.daythree.mapper;

import com.example.daythree.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 13789
* @description 针对表【employee(员工表)】的数据库操作Mapper
* @createDate 2025-05-11 17:17:07
* @Entity com.example.daythree.domain.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}




