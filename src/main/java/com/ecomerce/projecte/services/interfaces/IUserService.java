package com.ecomerce.projecte.services.interfaces;

import com.ecomerce.projecte.controllers.dtos.request.CreateUserRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdateUserRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.entities.User;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    BaseResponse get(Long idUser);
    BaseResponse getAll();
    BaseResponse create(CreateUserRequest request);
    BaseResponse update(UpdateUserRequest request, Long idUer);
    User getUser(String email);
    User getUser(Long id);
    void delete(Long id);
}
