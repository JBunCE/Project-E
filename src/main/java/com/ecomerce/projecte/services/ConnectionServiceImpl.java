package com.ecomerce.projecte.services;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomerce.projecte.controllers.dtos.request.CreateConnectionRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdateConnectionRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.controllers.dtos.response.GetConnectionResponse;
import com.ecomerce.projecte.controllers.dtos.response.GetProviderResponse;
import com.ecomerce.projecte.controllers.dtos.response.GetUserResponse;
import com.ecomerce.projecte.entities.Connection;
import com.ecomerce.projecte.entities.Provider;
import com.ecomerce.projecte.entities.User;
import com.ecomerce.projecte.repositories.IConnectionRepository;
import com.ecomerce.projecte.services.interfaces.IConnectionService;
import com.ecomerce.projecte.services.interfaces.IProviderService;


@Service
public class ConnectionServiceImpl implements IConnectionService {

    @Autowired
    private IConnectionRepository repository;
    @Autowired
    private IProviderService providerService;

    @Override
    public BaseResponse create(CreateConnectionRequest request) {
        Connection connection= from(request);
        GetConnectionResponse response=from(repository.save(connection));
        return BaseResponse.builder()
            .data(response)
            .message("Connection has been create")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateConnectionRequest request) {
        Connection connection=repository.findById(id).orElseThrow(()-> new RuntimeException("The Connection does not exist"));
        connection= update(connection, request);
        GetConnectionResponse response=from(connection);
        return BaseResponse.builder()
            .data(response)
            .message("Connection has been updated")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        
    }
    
    @Override
    public BaseResponse list() {
        List<GetConnectionResponse> responses = repository
        .findAll()
        .stream()
        .map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
        .data(responses)
        .message("find all connections")
        .success(true)
        .httpStatus(HttpStatus.FOUND).build();
    }
    
    @Override
    public BaseResponse get(Long id) {
        GetConnectionResponse response= from(id);
        return BaseResponse.builder()
        .data(response)
        .message("Connection has been found")
        .success(Boolean.TRUE)
        .httpStatus(HttpStatus.OK).build();
    }
    
    
    private Connection update(Connection connection, UpdateConnectionRequest request){
        connection.setName(request.getName());
        connection.setUrlConnection(request.getUrlConnection());
        return connection;
    }
    private GetConnectionResponse from(Long id){
        return repository.findById(id)
        .map(this::from)
        .orElseThrow(() -> new RuntimeException("The Connection does not exist"));
    }

    private GetConnectionResponse from(Connection connection){
        return GetConnectionResponse.builder()
            .id(connection.getId())
            .name(connection.getName())
            .urlConnection(connection.getUrlConnection())
            .provider(from(connection.getProvider())).build();
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

    private Connection from(CreateConnectionRequest request){
        Connection connection= new Connection();
        connection.setName(request.getName());
        connection.setUrlConnection(request.getUrlConnection());
        connection.setProvider(providerService.findById(request.getIdProvider()));
        return connection;
    }

    
}
