package com.ecomerce.projecte.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.ecomerce.projecte.controllers.dtos.request.CreatePromotionRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdatePromotionRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.controllers.dtos.response.GetPromotionResponse;
import com.ecomerce.projecte.entities.Promotion;
import com.ecomerce.projecte.repositories.IPromotionRepository;
import com.ecomerce.projecte.services.interfaces.IPromotionService;

public class PromotionServiceImpl implements IPromotionService {

    @Autowired
    private IPromotionRepository repository;

    @Override
    public BaseResponse create(CreatePromotionRequest request) {
        Promotion promotion= from(request);
        GetPromotionResponse response=from(repository.save(promotion));
        return BaseResponse.builder()
            .data(response)
            .message("Promotion has been create")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();

    }

    @Override
    public BaseResponse update(Long id, UpdatePromotionRequest request) {
        Promotion promotion=repository.findById(id)
            .orElseThrow(()-> new RuntimeException("The Promotion does not exist"));
            promotion= update(promotion, request);
            GetPromotionResponse response= from(promotion);

        return BaseResponse.builder()
            .data(response)
            .message("Promotion has been updated")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    private Promotion update(Promotion promotion, UpdatePromotionRequest request){
        promotion.setTitle(request.getTitle());
        promotion.setDescription(request.getDescription());
        promotion.setPrice(request.getPrice());
        return repository.save(promotion);
    }

    @Override
    public BaseResponse list() {
        List<GetPromotionResponse> response= repository.findAll()
            .stream()
            .map(this::from)
            .collect(Collectors.toList());
        
        return BaseResponse.builder()
            .data(response)
            .message("Promotions have been found")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetPromotionResponse response=from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Promotion has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Promotion findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("The promotion does not exist"));
    }

    private GetPromotionResponse from(long id){
        return repository.findById(id)
            .map(this::from)
            .orElseThrow(() -> new RuntimeException("The promotion does not exist"));
    }

    private GetPromotionResponse from(Promotion promotion){
        GetPromotionResponse response= new GetPromotionResponse();
        response.setTitle(promotion.getTitle());
        response.setPrice(promotion.getPrice());
        response.setDescription(promotion.getDescription());
        return response;
    }

    private Promotion from(CreatePromotionRequest request){
        Promotion promotion=new Promotion();
        promotion.setTitle(request.getTitle());
        promotion.setDescription(request.getDescription());
        promotion.setPrice(request.getPrice());
        return repository.save(promotion);
    }
    
}
