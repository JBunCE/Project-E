package com.ecomerce.projecte.services.interfaces;

import org.springframework.stereotype.Service;

import com.ecomerce.projecte.controllers.dtos.request.CreateTagRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdateTagRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.entities.Tag;

@Service
public interface ITagService {
    BaseResponse create(CreateTagRequest request);

    BaseResponse update(Long id, UpdateTagRequest request);

    void delete(Long id);

    BaseResponse list();

    BaseResponse get(Long id);

    Tag findById(Long id);
}
