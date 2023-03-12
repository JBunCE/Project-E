package com.ecomerce.projecte.services.interfaces;

import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IAWSService {
    BaseResponse uploadProfilePicture(MultipartFile multipartFile, Long id);
}
