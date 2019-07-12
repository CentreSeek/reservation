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

import com.yjjk.reservation.entity.Authority;
import com.yjjk.reservation.entity.Manager;
import com.yjjk.reservation.entity.Role;
import com.yjjk.reservation.entity.RoleAndAuthority;
import com.yjjk.reservation.utility.PasswordUtils;
import com.yjjk.reservation.utility.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.MediaName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author CentreS
 * @Description: 管理员模块
 * @create 2019-06-19
 */
@RestController
@RequestMapping(value = "/manage")
public class ManagerController extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
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
        if (StringUtils.listIsNullOrEmpty(list)) {
            message = "账户不存在";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        Manager manager = list.get(0);
        if(!PasswordUtils.verify(password, manager.getPassword())){
            message = "密码错误";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        manager.setPassword(null);
        message = "登录成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, manager);
    }

    /**************************************** 管理员信息模块 ****************************************/
    /**
     * 更新管理员信息
     *
     * @param manager
     * @param request
     * @param response
     */
    @RequestMapping(value = "manager", method = RequestMethod.PUT)
    public void updateManagerInfo(Manager manager, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        if (manager.getManagerId() == null){
            message = "参数错误";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        manager.setStatus(0);

        Manager tmpManager = new Manager();
        tmpManager.setManagerId(manager.getManagerId());
        List<Manager> list = super.managerService.getManagerInfoSelective(tmpManager);
        if (StringUtils.listIsNullOrEmpty(list)) {
            message = "账户不存在";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        int i = super.managerService.updateSelective(manager);
        if (i == 0) {
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
     *
     * @param managerId
     * @param request
     * @param response
     */
    @RequestMapping(value = "manager", method = RequestMethod.GET)
    public void getManagerInfo(@RequestParam(value = "managerId", required = true) Integer managerId,
                               HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        Manager manager = new Manager();
        manager.setManagerId(managerId);

        List<Manager> list = super.managerService.getManagerInfoSelective(manager);
        if (StringUtils.listIsNullOrEmpty(list)) {
            message = "查询失败,未查询到相关用户信息";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        message = "查询成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, list.get(0));
    }

    /**
     * 新增管理员账号
     *
     * @param manager
     * @param request
     * @param response
     */
    @RequestMapping(value = "manager", method = RequestMethod.POST)
    public void addManager(Manager manager,
                           @RequestParam(value = "roleId", required = false) Integer roleId,
                           HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        if (StringUtils.isNullorEmpty(manager.getAccount()) || StringUtils.isNullorEmpty(manager.getPassword())){
            message = "参数错误";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }

        // 验证account是否已注册
        Manager tempManager = new Manager();
        tempManager.setAccount(manager.getAccount());
        List<Manager> list = super.managerService.getManagerInfoSelective(tempManager);
        if (!StringUtils.listIsNullOrEmpty(list)) {
            message = "该账户名已存在";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        // 加盐
        String salt = PasswordUtils.salt();
        String generate = PasswordUtils.generate(manager.getPassword(), salt);
        manager.setSalt(salt);
        manager.setPassword(generate);
        int i = super.managerService.insertSelective(manager);
        int managerId = manager.getManagerId();
        if (i == 0) {
            message = "插入失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        int j = super.managerService.updateManagerRole(managerId, roleId);
        if (j == 0) {
            message = "角色添加失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        message = "插入成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, i);
    }

    @RequestMapping(value = "manager", method = RequestMethod.DELETE)
    public void deleteManager(@RequestParam(value = "managerId") Integer managerId,
                              HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        super.managerService.deleteManagerRole(managerId);
        Manager manager = new Manager();
        manager.setManagerId(managerId);
        manager.setStatus(1);
        int i = super.managerService.updateSelective(manager);
        if (i == 0) {
            message = "用户删除失败";
            returnResult(startTime, request, response, resultCode, message, i);
            return;
        }
        message = "用户删除成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, i);
    }


    /**************************************** 角色管理模块 ****************************************/

    /**
     * 更新/新增管理员角色信息
     *
     * @param roleId
     * @param managerId
     * @param request
     * @param response
     */
    @RequestMapping(value = "managerRole", method = RequestMethod.PUT)
    public void updateManagerRole(@RequestParam(value = "roleId") Integer roleId,
                                  @RequestParam(value = "managerId") Integer managerId,
                                  HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        int i = super.managerService.updateManagerRole(managerId, roleId);
        if (i == 0) {
            message = "更新失败!";
            returnResult(startTime, request, response, resultCode, message, i);
            return;
        }
        message = "更新成功!";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, i);
    }

    /**
     * 删除管理员角色
     *
     * @param managerId
     * @param request
     * @param response
     */
    @RequestMapping(value = "managerRole", method = RequestMethod.DELETE)
    public void deleteManagerRole(@RequestParam(value = "managerId") Integer managerId,
                                  HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        int i = super.managerService.deleteManagerRole(managerId);
        if (i == 0) {
            message = "删除失败！";
            returnResult(startTime, request, response, resultCode, message, i);
            return;
        }
        message = "删除成功！";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, i);
    }

    /**
     * 获取角色信息
     *
     * @param role
     * @param request
     * @param response
     */
    @RequestMapping(value = "role", method = RequestMethod.GET)
    public void getRole(Role role, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        List<Role> list = super.roleService.getRoleInfo(role);
        if (StringUtils.listIsNullOrEmpty(list)) {
            message = "获取失败";
            returnResult(startTime, request, response, resultCode, message, list);
            return;
        }
        message = "获取成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, list);
    }

    /**
     * 新增角色
     *
     * @param role
     * @param roleAndAuthority
     * @param request
     * @param response
     */
    @RequestMapping(value = "role", method = RequestMethod.POST)
    public void addRole(Role role, RoleAndAuthority roleAndAuthority, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        if (StringUtils.isNullorEmpty(role.getName())) {
            message = "参数错误";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }

        int i = super.roleService.addRole(role, roleAndAuthority);
        if (i == 0) {
            message = "添加失败";
            returnResult(startTime, request, response, resultCode, message, i);
            return;
        }
        message = "添加成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, i);
    }

    /**
     * 删除角色
     *
     * @param role
     * @param request
     * @param response
     */
    @RequestMapping(value = "role", method = RequestMethod.DELETE)
    public void deleteRole(Role role, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        if (role.getRoleId() == null) {
            message = "参数错误";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }

        int i = super.roleService.deleteRole(role.getRoleId());
        if (i == 0) {
            message = "删除失败";
            returnResult(startTime, request, response, resultCode, message, i);
            return;
        }
        message = "删除成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, i);
    }

    /**
     * 更新角色权限
     *
     * @param role
     * @param authorityIds
     * @param request
     * @param response
     */
    @RequestMapping(value = "role", method = RequestMethod.PUT)
    public void updateRole(Role role, @RequestParam(value = "authorityIds") List<Integer> authorityIds,
                           HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        if (role.getRoleId() == null || authorityIds == null) {
            message = "参数错误";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }

        int i = super.roleService.updateRole(role, authorityIds);
        if (i == 0) {
            message = "修改失败";
            returnResult(startTime, request, response, resultCode, message, i);
            return;
        }
        message = "修改成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, i);
    }

    /**************************************** 权限信息模块 ****************************************/

    /**
     * 查询所有权限信息
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "authority", method = RequestMethod.GET)
    public void getAuthorityList(Authority authority, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        List<Authority> authorityList = super.authorityService.getAuthorityInfoSelective(authority);
        if (StringUtils.listIsNullOrEmpty(authorityList)) {
            message = "查询失败";
            returnResult(startTime, request, response, resultCode, message, authorityList);
            return;
        }
        message = "查询成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, authorityList);
    }

    /**
     * 新增权限信息
     *
     * @param authority
     * @param request
     * @param response
     */
    @RequestMapping(value = "authority", method = RequestMethod.POST)
    public void addAuthority(Authority authority, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        int i = super.authorityService.insertSelective(authority);
        if (i == 0) {
            message = "更新失败";
            returnResult(startTime, request, response, resultCode, message, i);
            return;
        }
        message = "更新成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, i);
    }
}
