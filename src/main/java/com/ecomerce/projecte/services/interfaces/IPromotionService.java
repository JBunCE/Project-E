package com.ecomerce.projecte.services.interfaces;

import org.springframework.stereotype.Service;

import com.ecomerce.projecte.controllers.dtos.request.CreatePromotionRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdatePromotionRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.entities.Promotion;

@Service
public interface IPromotionService {
    BaseResponse create(CreatePromotionRequest request);

    BaseResponse update(Long id, UpdatePromotionRequest request);

    void delete(Long id);

    BaseResponse list();

    BaseResponse get(Long id);

    Promotion findById(Long id);
}
