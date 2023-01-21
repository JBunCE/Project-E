package com.ecomerce.projecte.services.interfaces;

import com.ecomerce.projecte.controllers.dtos.request.CreateUserRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdateUserRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.controllers.dtos.response.GetUserResponse;

import java.util.List;

public interface IUserService {
    BaseResponse get(Long idUser);
    BaseResponse getAll();
    BaseResponse create(CreateUserRequest request);
    BaseResponse update(UpdateUserRequest request, Long idUer);
    void delete(Long id);
}
