package com.ecomerce.projecte.controllers;

import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.services.AWSComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file")
public class FileController {

    private final AWSComponent awsComponent;

    @Autowired
    public FileController(AWSComponent awsComponent){
        this.awsComponent = awsComponent;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> uploadUserProfilePicture(MultipartFile file,
                                                                 @PathVariable Long idUser){
        BaseResponse response = awsComponent.uploadProfilePicture(file, idUser);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
