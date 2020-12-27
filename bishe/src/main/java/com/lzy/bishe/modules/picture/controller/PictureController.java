package com.lzy.bishe.modules.picture.controller;

import com.lzy.bishe.util.ResultDTO;
import com.lzy.bishe.modules.picture.service.PictureService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResultDTO uploadPicture(@RequestParam("file") MultipartFile file){
        ResultDTO resultDTO = pictureService.doUploadPicture(file);
        return resultDTO;
    }

}
