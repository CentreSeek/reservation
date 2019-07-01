/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: SaticScheduleTask
 * Author:   CentreS
 * Date:     2019-06-21 17:55
 * Description: 定时批量过期预约
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.configer;

import com.yjjk.reservation.controller.BaseController;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @Description: 定时批量过期预约
 * @author CentreS
 * @create 2019-06-21
 */

@Component
@Configuration      // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask extends BaseController {


    /**
     * 定时清理过期预约
     */
    @Scheduled(fixedRate=1000*60*15)
    private void configureTasks() {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = dateFormat.format(date);
        int i = super.orderRecordService.cancelOrderTask(format);
        System.err.println("执行预约过期定时计划    时间: " + format + "   执行条数:" + i);
    }
}
