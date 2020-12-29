package com.lzy.bishe.modules.picture.controller;

import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.util.ResultDTO;
import com.lzy.bishe.modules.picture.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Lzy
 */
@RestController
@RequestMapping("/api/picture")
@Api(tags = {"PictureController"}, description = "图片上传相关接口")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @PostMapping("/upload")
    @UserLoginToken @CrossOrigin
    @ApiOperation(value = "图片上传", notes = "图片上传")
    public ResultDTO uploadPicture(@RequestParam("file") MultipartFile file){
        ResultDTO resultDTO = pictureService.doUploadPicture(file);
        return resultDTO;
    }

    @GetMapping("/static/picture/图片名")
    @UserLoginToken @CrossOrigin
    @ApiOperation(value = "图片查看", notes = "图片查看")
    public ResultDTO seePicture(){
        return null;
    }

}
