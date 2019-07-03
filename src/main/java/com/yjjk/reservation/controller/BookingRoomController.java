/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: BookingRoomController
 * Author:   CentreS
 * Date:     2019-06-20 16:06
 * Description: 会议室预约系统
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.controller;

import com.yjjk.reservation.entity.*;
import com.yjjk.reservation.utility.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 会议室预约系统
 * @create 2019-06-20
 */
@RestController
@RequestMapping(value = "/room")
public class BookingRoomController extends BaseController {

    /**
     * 获取时间段信息列表
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/timesList", method = RequestMethod.GET)
    public void getTimesList(HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        List<Times> list = super.timesService.getTimesList();

        if (list == null) {
            resultCode = false;
            message = "失败";
            returnResult(startTime, request, response, resultCode, message, list);
            return;
        }
        message = "成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, list);
    }

    /**
     * 用户登录
     *
     * @param openId
     * @param unionId
     * @param request
     * @param response
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(@RequestParam(value = "openId", required = false) String openId,
                      @RequestParam(value = "unionId", required = false) String unionId,
                      HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        List<User> userInfo = super.userService.getUserInfoByWX(openId, unionId);
        if (userInfo == null || userInfo.size() == 0) {
            message = "登录失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        resultCode = true;
        message = "登录成功";
        returnResult(startTime, request, response, resultCode, message, userInfo);
    }

    /**
     * 获取会议室列表
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public void getRooms(HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        List<ConferenceRoom> list = super.conferenceRoomService.getRooms();

        if (list == null) {
            message = "获取失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        resultCode = true;
        message = "获取成功";
        returnResult(startTime, request, response, resultCode, message, list);
    }

    /**
     * 关闭会议室
     * @param roomId
     * @param request
     * @param response
     */
    @RequestMapping(value = "/room", method = RequestMethod.DELETE)
    public void cancleRoom(@RequestParam(value = "roomId") Integer roomId,
                           HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        int i = super.conferenceRoomService.cancleRoom(roomId);
        if (i == 0){
            message = "关闭失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        resultCode = true;
        message = "关闭成功";
        returnResult(startTime, request, response, resultCode, message, i);
    }
    /**
     * 绑定微信
     *
     * @param openId
     * @param unionId
     * @param name
     * @param phone
     * @param request
     * @param response
     */
    @RequestMapping(value = "/banding", method = RequestMethod.PUT)
    public void banding(@RequestParam(value = "openId", required = false) String openId,
                        @RequestParam(value = "unionId", required = false) String unionId,
                        @RequestParam(value = "name", required = true) String name,
                        @RequestParam(value = "phone", required = true) Long phone,
                        HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        if (StringUtils.isNullorEmpty(openId) && StringUtils.isNullorEmpty(unionId) && !StringUtils.isPhoneNumber(phone.toString())) {
            message = "参数错误";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        List<User> userInfo = super.userService.getUserInfo(name, phone);
        if (userInfo == null || userInfo.size() == 0) {
            message = "用户不存在";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        int i = super.userService.bandingWx(openId, unionId, name, phone);
        if (i == 0 || i == -1) {
            message = "绑定失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        userInfo.get(0).setOpenId(openId);
        resultCode = true;
        message = "绑定成功";
        returnResult(startTime, request, response, resultCode, message, userInfo);
    }


    /**
     * 获取当前日期的预约列表
     *
     * @param orderDate
     * @param roomId
     * @param request
     * @param response
     */
    @RequestMapping(value = "/orderList", method = RequestMethod.GET)
    public void getOrderList(@RequestParam(value = "orderDate", required = true) String orderDate,
                             @RequestParam(value = "roomId", required = true) Integer roomId,
                             HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setOrderDate(orderDate);
        orderRecord.setRoomId(roomId);
        List<OrderRecord> list = super.orderRecordService.getOrderRecords(orderRecord);
        if (list == null) {
            message = "失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        resultCode = true;
        message = "获取成功";
        returnResult(startTime, request, response, resultCode, message, list);

    }

    /**
     * 预约会议室
     *
     * @param orderDate
     * @param timesIds
     * @param roomId
     * @param userName
     * @param request
     * @param response
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public void orderRoom(@RequestParam(value = "orderDate", required = true) String orderDate,
                          @RequestParam(value = "timesIds", required = true) String timesIds,
                          @RequestParam(value = "roomId", required = true) Integer roomId,
                          @RequestParam(value = "userId", required = true) Integer userId,
                          @RequestParam(value = "theme", required = false) String theme,
                          @RequestParam(value = "userName",required = false) String userName,
                          HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setOrderDate(orderDate);
        orderRecord.setRoomId(roomId);
        orderRecord.setUserId(userId);
        if (!StringUtils.isNullorEmpty(userName)) {
            orderRecord.setUserName(userName);
        }
        if (!StringUtils.isNullorEmpty(theme)) {
            orderRecord.setTheme(theme);
        }
        orderRecord.setTimesIds(timesIds.split(","));
        int count = super.orderRecordService.selectByTimesIds(orderRecord);
        if(count > 0){
            message = "预约失败,该时段已被预约";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        String[] ids = timesIds.split(",");
        int i = 0;
        for (int j = 0; j < ids.length; j++) {
            orderRecord.setTimesId(Integer.parseInt(ids[i]));
            i += super.orderRecordService.insertSelective(orderRecord);
        }
        if (i == 0 || i == -1) {
            message = "预约失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        resultCode = true;
        message = "预约成功";
        returnResult(startTime, request, response, resultCode, message, i);
    }

    /**
     * 查看历史
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public void getMyOrderListHistory(@RequestParam(value = "openId", required = false) String openId,
                                      @RequestParam(value = "unionId", required = false) String unionId,
                                      @RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "phone", required = false) Long phone,
                                      @RequestParam(value = "status", required = true) Integer status,
                                      @RequestParam(value = "status2", required = false) Integer status2,
                                      HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        if (StringUtils.isNullorEmpty(openId) && StringUtils.isNullorEmpty(unionId)) {
            message = "参数错误";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("openId", openId);
        paramMap.put("unionId", unionId);
        paramMap.put("name", name);
        paramMap.put("phone", phone);
        paramMap.put("status", status);
        List<OrderRecord> list = super.orderRecordService.selectHistory(paramMap);
        List<OrderHistory> historyList = super.orderRecordService.historyProcessor(list);
        if (status2 != null) {
            paramMap.put("status", status2);
            List<OrderRecord> list2 = super.orderRecordService.selectHistory(paramMap);
            List<OrderHistory> historyList2 = super.orderRecordService.historyProcessor(list2);
            historyList.addAll(historyList2);
        }
        if (historyList == null) {
            resultCode = false;
            message = "查询失败";
            returnResult(startTime, request, response, resultCode, message, historyList);
            return;
        }
        message = "查询成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, historyList);
    }

    /**
     * 取消订单
     *
     * @param orderIds
     * @param request
     * @param response
     */
    @RequestMapping(value = "/order", method = RequestMethod.DELETE)
    public void deleteOrder(
            @RequestParam(value = "orderIds", required = true) String orderIds,
            HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        OrderRecord orderRecord = new OrderRecord();
        String[] list = orderIds.split(",");
        int i = 0;
        for (int j = 0; j < list.length; j++) {
            orderRecord.setOrderId(Integer.parseInt(list[j]));
            i += super.orderRecordService.cancelOrder(orderRecord);
        }
        if (i == 0 || i == -1) {
            message = "取消失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        resultCode = true;
        message = "取消成功";
        returnResult(startTime, request, response, resultCode, message, i);

    }


}