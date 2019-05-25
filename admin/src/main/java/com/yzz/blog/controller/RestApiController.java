package com.yzz.blog.controller;

import com.yzz.blog.business.annotation.BussinessLog;
import com.yzz.blog.business.enums.FileUploadType;
import com.yzz.blog.business.service.BizArticleService;
import com.yzz.blog.business.service.SysConfigService;
import com.yzz.blog.core.websocket.server.ZydWebsocketServer;
import com.yzz.blog.core.websocket.util.WebSocketUtil;
import com.yzz.blog.FileUploader;
import com.yzz.blog.entity.VirtualFile;
import com.yzz.blog.framework.object.ResponseVO;
import com.yzz.blog.plugin.file.GlobalFileUploader;
import com.yzz.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 其他api性质的接口
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    private BizArticleService articleService;
    @Autowired
    private SysConfigService configService;
    @Autowired
    private ZydWebsocketServer websocketServer;

    @BussinessLog("wangEditor编辑器中上传文件")
    @RequiresPermissions("article:publish")
    @PostMapping("/uploadFile")
    public ResponseVO uploadFile(@RequestParam("file") MultipartFile file) {
        FileUploader uploader = new GlobalFileUploader();
        VirtualFile virtualFile = uploader.upload(file, FileUploadType.SIMPLE.getPath(), true);
        return ResultUtil.success("图片上传成功", virtualFile.getFullFilePath());
    }

    @BussinessLog("simpleMD编辑器中上传文件")
    @RequiresPermissions("article:publish")
    @PostMapping("/uploadFileForMd")
    public Object uploadFileForMd(@RequestParam("file") MultipartFile file) {
        FileUploader uploader = new GlobalFileUploader();
        VirtualFile virtualFile = uploader.upload(file, FileUploadType.SIMPLE.getPath(), true);
        Map<String, Object> resultMap = new HashMap<>(3);
        resultMap.put("success", 1);
        resultMap.put("message", "上传成功");
        resultMap.put("filename", virtualFile.getFullFilePath());
        return resultMap;
    }

    /**
     * 发送消息通知
     *
     * @return
     */
    @RequiresPermissions("notice")
    @PostMapping("/notice")
    @BussinessLog("通过websocket向前台发送通知")
    public ResponseVO notice(String msg) throws UnsupportedEncodingException {
        WebSocketUtil.sendNotificationMsg(msg, websocketServer.getOnlineUsers());
        return ResultUtil.success("消息发送成功");
    }
}
