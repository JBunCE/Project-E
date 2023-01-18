package com.ecomerce.projecte.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.projecte.controllers.dtos.request.CreateTagRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdateTagRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.services.interfaces.ITagService;

@RestController
@RequestMapping("tag")
public class TagController {
    @Autowired
    private ITagService service;

    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse= service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable long id){
        BaseResponse baseResponse= service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateTagRequest request){
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse>  update(@RequestBody UpdateTagRequest request, @PathVariable Long id){
        BaseResponse baseResponse= service.update(id,request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
    
    @GetMapping("health")
    public String health() {
        return "Ok";
    }
}
