package com.example.demo.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.product.domain.Student;
import com.example.demo.product.service.StudentService;
import com.example.demo.product.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author 13789
* @description 针对表【student】的数据库操作Service实现
* @createDate 2025-05-11 02:11:19
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




