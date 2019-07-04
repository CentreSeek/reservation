/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: FileUploadController
 * Author:   CentreS
 * Date:     2019/7/1 16:06
 * Description: 文件上传
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.controller;

import com.yjjk.reservation.entity.OrderRecords2Excel;
import com.yjjk.reservation.entity.User;
import com.yjjk.reservation.service.UserService;
import com.yjjk.reservation.utility.FileNameUtils;
import com.yjjk.reservation.utility.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 文件上传
 * @create 2019/7/1
 */
@Controller
@RequestMapping("file")
public class FileUploadController extends BaseController {
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileUploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.upload-path}")
    private String path;

    /**
     * 上传文件
     *
     * @param file
     * @return 文件存储的路径
     */
    @RequestMapping(value = "fileUpload")
    public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        // 要上传的目标文件存放路径
        String localPath = path;
        // 生成新的文件名
        String fileName = FileNameUtils.getFileName(file.getOriginalFilename());
        if (!FileUtils.upload(file, localPath, fileName)) {
            message = "插入失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        message = "插入成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, path + "\\" + fileName);
    }

    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        List<OrderRecords2Excel> list = super.orderRecordService.getRecords2Excel();

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("sheet1");

        HSSFRow row = null;
        // 创建第一个单元格
        row = sheet.createRow(0);
        row.setHeight((short) (26.25 * 20));
        // 为第一行单元格设值
        row.createCell(0).setCellValue("会议室预约信息");

        /*
         * 为标题设计空间
         * firstRow从第1行开始
         * lastRow从第0行结束
         *
         * 从第1个单元格开始
         * 从第3个单元格结束
         */
        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(rowRegion);

        /*
         * 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
         * 第一个table_name 表名字
         * 第二个table_name 数据库名称
         * */
        row = sheet.createRow(1);
        //设置行高
        row.setHeight((short) (22.50 * 20));
        //为单元格设值
        row.createCell(0).setCellValue("日期");
        row.createCell(1).setCellValue("时间段");
        row.createCell(2).setCellValue("会议室");
        row.createCell(3).setCellValue("使用人");
        row.createCell(4).setCellValue("会议主题");
        row.createCell(5).setCellValue("状态");
        row.createCell(6).setCellValue("预约人");
        row.createCell(7).setCellValue("操作时间");

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 2);
            OrderRecords2Excel record = list.get(i);
            row.createCell(0).setCellValue(record.getOrderDate());
            row.createCell(1).setCellValue(record.getTimes());
            row.createCell(2).setCellValue(record.getRoomName());
            row.createCell(3).setCellValue(record.getUserName());
            row.createCell(4).setCellValue(record.getTheme());
            Integer status = record.getStatus();
            if (status == 0){
                row.createCell(5).setCellValue("预约中");
            }else if (status == 1){
                row.createCell(5).setCellValue("已完成/已失效");
            }else if (status == 2){
                row.createCell(5).setCellValue("已取消");
            }
            row.createCell(6).setCellValue(record.getOrderUser());
            row.createCell(7).setCellValue(record.getCreateTime());
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        //默认Excel名称
        response.setHeader("Content-disposition", "attachment;filename=OrderRecords.xls");
        wb.write(os);
        os.flush();
        os.close();
    }

//    @RequestMapping(value = "/import")
//    public String exImport(@RequestParam(value = "filename") MultipartFile file, HttpSession session) {
//        boolean a = false;
//        String fileName = file.getOriginalFilename();
//        try {
//            a = userService.batchImport(fileName, file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:index";
//    }
}
