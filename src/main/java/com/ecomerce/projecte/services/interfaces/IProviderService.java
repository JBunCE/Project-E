package com.ecomerce.projecte.services.interfaces;


import com.ecomerce.projecte.controllers.dtos.request.CreateProviderRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdateProviderRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.entities.Provider;


public interface IProviderService {
    BaseResponse get(Long idProvider);
    
    BaseResponse list();
    
    BaseResponse create(CreateProviderRequest request);
    
    BaseResponse update(Long idProvider, UpdateProviderRequest request);
        
    void delete(Long id);

    Provider findById(Long id);
}
