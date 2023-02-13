package com.ecomerce.projecte.services.interfaces;

import com.ecomerce.projecte.controllers.dtos.request.CreateConnectionRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdateConnectionRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.entities.Connection;

public interface IConnectionService {
    BaseResponse create(CreateConnectionRequest request);

    BaseResponse update(Long id, UpdateConnectionRequest request);

    void delete(Long id);

    BaseResponse list();

    BaseResponse get(Long id);

}
