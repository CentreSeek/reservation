/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: ManagerController
 * Author:   CentreS
 * Date:     2019-06-19 17:24
 * Description: 管理员模块
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author CentreS
 * @Description: 管理员模块
 * @create 2019-06-19
 */
@RestController
@RequestMapping(value = "/manage")
public class ManagerController extends BaseController {

    @Resource
    RedisTemplate redisTemplate;

    @RequestMapping("/login")
    public void managerLogin(@RequestParam(value = "phone", required = true) String phone,
                             @RequestParam(value = "phoneCode", required = true) String phonecode,
                             HttpServletRequest request, HttpServletResponse response) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("code:"+"13074500888", "", 5, TimeUnit.HOURS);
        boolean hasCode = redisTemplate.hasKey("code:");
        if(hasCode){
            System.out.println(redisTemplate.getKeySerializer());
        }



    }
//

    @RequestMapping("/list")
    public void getEmployeeList(HttpServletRequest request, HttpServletResponse response) {

    }
    @RequestMapping("add")
    public void addEmployee(HttpServletRequest request, HttpServletResponse response){

    }
    @RequestMapping("delete")
    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response){

    }

}
