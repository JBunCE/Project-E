package com.ecomerce.projecte.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomerce.projecte.controllers.dtos.request.CreateProviderRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdateProviderRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.controllers.dtos.response.GetProviderResponse;
import com.ecomerce.projecte.controllers.dtos.response.GetUserResponse;
import com.ecomerce.projecte.entities.Provider;
import com.ecomerce.projecte.entities.User;
import com.ecomerce.projecte.entities.enums.TierType;
import com.ecomerce.projecte.entities.enums.converter.TierTypeConverter;
import com.ecomerce.projecte.repositories.IProviderRepository;
import com.ecomerce.projecte.services.interfaces.IProviderService;
import com.ecomerce.projecte.services.interfaces.IUserService;

@Service
public class ProviderServiceImpl implements IProviderService{
    
    @Autowired
    private IProviderRepository repository;
    @Autowired 
    private IUserService userService;
    @Autowired
    private TierTypeConverter converter;
    
    public Provider findById(Long id){
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("The provider does not exist"));
    }
    @Override
    public BaseResponse get(Long idProvider) {
        GetProviderResponse response=from(idProvider);
        return BaseResponse.builder()
                .data(response)
                .message("Provider has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list() {
        List<GetProviderResponse> responses = repository
            .findAll()
            .stream()
            .map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
            .data(responses)
            .message("find all providers")
            .success(true)
            .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse create(CreateProviderRequest request) {
        Provider provider= from(request);
        GetProviderResponse response=from(repository.save(provider));
        return BaseResponse.builder()
            .data(response)
            .message("Provider has been create")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }
    
    @Override
    public void delete(Long id) {
        repository.deleteById(id);        
    }
    @Override
    public BaseResponse update(Long idProvider, UpdateProviderRequest request) {
        Provider provider=repository.findById(idProvider)
        .orElseThrow(()-> new RuntimeException("The Provider does not exist"));
        provider= update(provider, request);
    GetProviderResponse response= from(provider);

    return BaseResponse.builder()
        .data(response)
        .message("Provider has been updated")
        .success(Boolean.TRUE)
        .httpStatus(HttpStatus.OK).build();
    }

    private Provider update(Provider provider, UpdateProviderRequest request){
        TierType type= converter.convertToEntityAttribute(request.getTier());
        provider.setPhoneNumber(request.getPhoneNumber());
        provider.setTier(type);
        return repository.save(provider);
    }

    

    private Provider from(CreateProviderRequest request){
        Provider provider= new Provider();
        provider.setPhoneNumber(request.getPhoneNumber());
        provider.setTier(TierType.FREE);
        provider.setUser(userService.getUser(request.getUserId()));
        return provider;
    }

    private GetProviderResponse from(Long id){
        return repository.findById(id)
            .map(this::from)
            .orElseThrow(() -> new RuntimeException("The Provider does not exist"));
    }

    private GetProviderResponse from(Provider provider){
        return GetProviderResponse.builder()
            .id(provider.getId())
            .numberPhone(provider.getPhoneNumber())
            .tier(provider.getTier().getTierCode())
            .user(from(provider.getUser())).build();
    }

    private GetUserResponse from(User user){
        return GetUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture()).build();
    }

    
}
