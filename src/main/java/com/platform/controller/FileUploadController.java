/*
 * 创建时间：2017-09-30 09:51
 * 项目名称:platform
 * 类名称:FileUploadController.java
 * 包名称:com.platform.controller
 *
 * 修改履历:
 *          日期              修正者        主要内容
 *                                      
 *
 * Copyright (c) 2016-2017 兆尹科技
 */
package com.platform.controller;

/**
 * 名称：FileUploadController <br>
 * 描述：文件上传<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */

import com.platform.common.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController extends MultiActionController {

    /**
     * @return
     */
    @RequestMapping(value = "/view")
    public String view() {
        return "common/upload";
    }

    /**
     * 1、文件上传
     *
     * @param request
     * @param response
     * @return
     */
    public ModelAndView uploadFiles(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        // 转型为MultipartHttpRequest
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得上传的文件（根据前台的name名称得到上传的文件）
        MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();
        List<MultipartFile> file = multiValueMap.get("clientFile");
        //MultipartFile file = multipartRequest.getFile("clientFile");
        if (!file.isEmpty()) {
            //在这里就可以对file进行处理了，可以根据自己的需求把它存到数据库或者服务器的某个文件夹
            System.out.println("=================" + file.get(0).getName() + file.get(0).getSize());
        }

        return mav;
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public Result uploadFile(HttpServletRequest request) {
        // 转型为MultipartHttpRequest
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得上传的文件（根据前台的name名称得到上传的文件）
        MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();
        List<MultipartFile> fileList = multiValueMap.get("clientFile");
        //MultipartFile file = multipartRequest.getFile("clientFile");
        if (!fileList.isEmpty()) {
            //在这里就可以对file进行处理了，可以根据自己的需求把它存到数据库或者服务器的某个文件夹
            for (MultipartFile file : fileList) {
                System.out.println(file.getOriginalFilename());
            }
        }
        return Result.ok("文件上传成功");
    }

}
