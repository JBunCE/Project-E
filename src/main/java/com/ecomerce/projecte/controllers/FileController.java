package com.ecomerce.projecte.controllers;

import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.services.interfaces.IAWSService;
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

    private final IAWSService awsService;

    @Autowired
    public FileController(IAWSService awsServiceImpl){
        this.awsService = awsServiceImpl;
    }

    @PostMapping("/profile/{idUser}")
    public ResponseEntity<BaseResponse> uploadUserProfilePicture(MultipartFile file,
                                                                 @PathVariable Long idUser){
        BaseResponse response = awsService.uploadProfilePicture(file, idUser);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
