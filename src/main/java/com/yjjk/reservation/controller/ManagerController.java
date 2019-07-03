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

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public void managerLogin(@RequestParam(value = "account", required = true) String account,
                             @RequestParam(value = "password", required = true) String password,
                             HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        Manager tempManager = new Manager();
        tempManager.setAccount(account);
        List<Manager> list = super.managerService.getManagerInfoSelective(tempManager);
        if (list == null && list.size() == 0){
            message = "账户不存在";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        Manager manager = list.get(0);

    }


    /**
     * 更新管理员信息
     * @param manager
     * @param request
     * @param response
     */
    @RequestMapping(value = "manager",method = RequestMethod.PUT)
    public void updateManagerInfo(Manager manager, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        manager.setStatus(0);

        Manager tmpManager = new Manager();
        tmpManager.setManagerId(manager.getManagerId());
        List<Manager> list = super.managerService.getManagerInfoSelective(tmpManager);
        if (list == null || list.size() == 0){
            message = "账户不存在";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        int i = super.managerService.updateSelective(manager);
        if (i == 0){
            message = "更新失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        message = "更新成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, i);
    }

    /**
     * 查询管理员信息
     * @param managerId
     * @param request
     * @param response
     */
    @RequestMapping(value = "manager",method = RequestMethod.GET)
    public void getManagerInfo(@RequestParam(value = "managerId", required = true) Integer managerId,
                               HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        Manager manager = new Manager();
        manager.setManagerId(managerId);

        List<Manager> list = super.managerService.getManagerInfoSelective(manager);
        if (list == null && list.size() == 0){
            message = "查询失败,未查询到相关用户信息";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        message = "插入成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, list.get(0));
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
