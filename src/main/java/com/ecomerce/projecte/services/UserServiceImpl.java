package com.ecomerce.projecte.services;

import com.ecomerce.projecte.controllers.advices.exceptions.NotFoundException;
import com.ecomerce.projecte.controllers.dtos.request.CreateUserRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdateUserRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.controllers.dtos.response.GetUserResponse;
import com.ecomerce.projecte.entities.User;
import com.ecomerce.projecte.entities.enums.UserType;
import com.ecomerce.projecte.entities.enums.converters.UserTypeConverter;
import com.ecomerce.projecte.repositories.IUserRepository;
import com.ecomerce.projecte.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository repository;
    private final UserTypeConverter typeConverter;

    @Autowired
    public UserServiceImpl(IUserRepository repository,
                           UserTypeConverter typeConverter) {
        this.repository = repository;
        this.typeConverter = typeConverter;
    }

    @Override
    public BaseResponse get(Long idUser){
        User user = repository.findById(idUser)
                .orElseThrow(NotFoundException::new);
        return BaseResponse.builder()
                .data(from(user))
                .message("user for: " + idUser)
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse getAll() {
        List<GetUserResponse> responses = repository
                .findAll()
                .stream()
                .map(this::from).toList();
        return BaseResponse.builder()
                .data(responses)
                .message("find all users")
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        Optional<User> possibleCopy = repository.findByEmail(request.getEmail());

        if(possibleCopy.isPresent()){
            throw new RuntimeException("the user exist"); // (RegisterException)
        }

        User user = repository.save(from(request));
        return BaseResponse.builder()
                .data(from(user))
                .message("El usuario ha sido creado")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(UpdateUserRequest request, Long idUser) {
        User usr = repository.findById(idUser).orElseThrow(RuntimeException::new);
        User upUser = repository.save(update(usr, request));
        return BaseResponse.builder()
                .data(from(upUser))
                .message("user updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public User getUser(String email) {
        return repository.findByEmail(email)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private GetUserResponse from(User user){
        return GetUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture()).build();
    }

    private User from(CreateUserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setUserType(UserType.COMMON_USER);
        user.setProfilePicture("");
        return user;
    }

    private User update(User user, UpdateUserRequest update){
        user.setName(update.getName());
        user.setLastName(update.getLastName());
        user.setEmail(update.getEmail());
        user.setProfilePicture(update.getProfilePicture());
        user.setUserType(typeConverter.convertToEntityAttribute(update.getUserType()));
        return user;
    }

}
