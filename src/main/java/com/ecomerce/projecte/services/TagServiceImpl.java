package com.ecomerce.projecte.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomerce.projecte.controllers.dtos.request.CreateTagRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdateTagRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.controllers.dtos.response.GetTagResponse;
import com.ecomerce.projecte.entities.Tag;
import com.ecomerce.projecte.repositories.ITagRepository;
import com.ecomerce.projecte.services.interfaces.ITagService;

@Service
public class TagServiceImpl implements ITagService{

    @Autowired
    private ITagRepository repository;

    @Override
    public BaseResponse create(CreateTagRequest request) {
        Tag tag= from(request);
        GetTagResponse response=from(repository.save(tag));
        return BaseResponse.builder()
            .data(response)
            .message("Tag has been create")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();

    }

    @Override
    public BaseResponse update(Long id, UpdateTagRequest request) {
        Tag tag=repository.findById(id)
            .orElseThrow(()-> new RuntimeException("The Tag does not exist"));
            tag= update(tag, request);
        GetTagResponse response= from(tag);

        return BaseResponse.builder()
            .data(response)
            .message("Tag has been updated")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    private Tag update(Tag tag, UpdateTagRequest request){
        tag.setName(request.getName());
        tag.setDescription(request.getDescription());
        tag.setColor(request.getColor());
        tag.setIconUrl(request.getIconUrl());
        return repository.save(tag);
    }

    @Override
    public BaseResponse list() {
        List<GetTagResponse> response= repository.findAll()
            .stream()
            .map(this::from)
            .collect(Collectors.toList());
        
        return BaseResponse.builder()
            .data(response)
            .message("Tags have been found")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetTagResponse response=from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Tag has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Tag findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("The tag does not exist"));
    }

    private GetTagResponse from(long id){
        return repository.findById(id)
            .map(this::from)
            .orElseThrow(() -> new RuntimeException("The tag does not exist"));
    }

    private GetTagResponse from(Tag tag){
        GetTagResponse response= new GetTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());
        response.setDescription(tag.getDescription());
        response.setIconUrl(tag.getIconUrl());
        response.setColor(tag.getColor());
        return response;
    }

    private Tag from(CreateTagRequest request){
        Tag tag=new Tag();
        tag.setName(request.getName());
        tag.setIconUrl(request.getIconUrl());
        tag.setDescription(request.getDescription());
        tag.setColor(request.getColor());
        return repository.save(tag);
    }
    
}
