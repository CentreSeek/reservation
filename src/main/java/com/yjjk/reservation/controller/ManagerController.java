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

import com.yjjk.reservation.entity.Manager;
import com.yjjk.reservation.utility.PasswordUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author CentreS
 * @Description: 管理员模块
 * @create 2019-06-19
 */
@RestController
@RequestMapping(value = "/manager")
public class ManagerController extends BaseController {

    private static final String secretKey = "97495A6982D40CD7F0640072AEE8D058";

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public void managerLogin(@RequestParam(value = "account", required = true) String account,
                             @RequestParam(value = "password", required = true) String password,
                             HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
    }

    @RequestMapping(value = "manager",method = RequestMethod.PUT)
    public void updateManagerInfo(HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

    }

    @RequestMapping(value = "manager",method = RequestMethod.GET)
    public void getManagerInfo(HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

    }

    /**
     * 新增管理员账号：需要验证secret
     * @param manager
     * @param secret
     * @param request
     * @param response
     */
    @RequestMapping(value = "manager",method = RequestMethod.POST)
    public void addManager(Manager manager,
                           @RequestParam(value = "secret", required = true) String secret,
            HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        if (!secretKey.equals(secret)){
            message = "secret验证失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        // 验证account是否已注册
        Manager tempManager = new Manager();
        tempManager.setAccount(manager.getAccount());
        List<Manager> list = super.managerService.getManagerInfoSelective(tempManager);
        if (list != null && list.size() != 0){
            message = "该账户已存在";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        // 加盐
        String salt = PasswordUtils.salt();
        String generate = PasswordUtils.generate(manager.getPassword(), salt);
        manager.setSalt(salt);
        manager.setPassword(generate);
        int i = super.managerService.insertSelective(manager);
        if (i == 0){
            message = "插入失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        message = "插入成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, "");
    }
}
