/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: SmsUtil
 * Author:   CentreS
 * Date:     2019-06-18 17:19
 * Description: Short Message Service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.utility;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.yjjk.reservation.constant.SmsConstant;


/**
 * @Description: Short Message Service
 * @author CentreS
 * @create 2019-06-18
 */

public class SmsUtil {
    /**
     * 发送短信:阿里sms接口调用
     * @param phone
     */
    public void sendSms(String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("default", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", SmsConstant.SIGN_NAME_TEST);
        request.putQueryParameter("TemplateCode", SmsConstant.TEMPLATE_CODE_TEST);
        request.putQueryParameter("TemplateParam", "{\"code\":\"1111\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成验证码
     * @return
     */
    public static String smsCode() {
        String random = (int) ((Math.random() * 9 + 1) * 100000) + "";
        return random;
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(smsCode());
//        }
//    }



}
