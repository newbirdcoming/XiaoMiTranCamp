package com.example.daythree.controller;


import com.example.daythree.domain.Employee;
import com.example.daythree.domain.Result;
import com.example.daythree.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Employee employee) {
        boolean save = employeeService.save(employee);
        if (save) {
            return Result.success("员工新增成功");
        } else {
            return Result.error("员工新增失败");
        }
    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable int id) {
//        先从redis缓存获取
        Employee employee1 = (Employee) redisTemplate.opsForValue().get("employee" + id);
        if (employee1 != null) {
            return Result.success(employee1);
        }
        log.info("缓存不存在:employee:{}", id);
        Employee employee = employeeService.getById(id);
//        更新缓存
        if (employee != null) {
            redisTemplate.opsForValue().set("employee" + id, employee);
            return Result.success(employee);
        } else {
//            数据库也没有
            return Result.error("没有该员工");
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody Employee employee) {
//        修改数据库（存在线程不安全 需要使用redison设置分布式锁 防止被修改）
//      设置分布式锁
        RLock lock = redissonClient.getLock("Lock:employ:"+employee.getId());
        try {
            if (lock.tryLock(10, 30, TimeUnit.SECONDS)) {
                try {
                    Employee employee1 = employeeService.getById(employee.getId());
                    if (employee1 == null) {
                        return Result.error("没有该员工");
                    }
                    boolean b = employeeService.updateById(employee);
                    if(b)
                    {
                        //            删除缓存
                        Boolean delete = redisTemplate.delete("employee" + employee.getId());
                        if(!delete){
                            return Result.error("缓存删除失败");
                        }
                        System.out.println("缓存删除成功");
                        return Result.success(employee,"员工信息修改成功");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    // 关键：检查当前线程是否持有锁，避免误释放
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Result.error("获取锁被中断");
        }
        return Result.error("获取锁失败");
    }


    //    删除员工信息
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable int id) {
        boolean b = employeeService.removeById(id);
        if (b) {
            redisTemplate.delete("employee" + id);
            System.out.println("缓存删除成功");
            return Result.success("员工信息删除成功");
        } else {
            return Result.error("员工信息删除失败");
        }
    }

}

