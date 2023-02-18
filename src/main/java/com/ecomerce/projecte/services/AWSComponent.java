package com.ecomerce.projecte.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ecomerce.projecte.controllers.dtos.request.UpdateUserRequest;
import com.ecomerce.projecte.controllers.dtos.response.BaseResponse;
import com.ecomerce.projecte.entities.User;
import com.ecomerce.projecte.entities.enums.converters.UserTypeConverter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class AWSComponent {

    private AmazonS3 s3Client;

    @Value(value = "${AWS_BUCKET.ENDPOINT_URL}")
    private String endpointUrl;

    @Value(value = "${AWS_BUCKET.BUCKET_NAME}")
    private String bucketName;

    @Value(value = "${AWS_BUCKET.SECRET_KEY}")
    private String secretKey;

    @Value(value = "${AWS_BUCKET.ACCESS_KEY}")
    private String accessKey;

    private final UserServiceImpl userService;
    private final UserTypeConverter userTypeConverter;

    @Autowired
    public AWSComponent(UserServiceImpl userService,
                        UserTypeConverter userTypeConverter){
        this.userService = userService;
        this.userTypeConverter = userTypeConverter;
    }

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    private File convertMultipartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try(FileOutputStream fos = new FileOutputStream(convFile)){
            fos.write(file.getBytes());
        }catch (Exception e){
            throw new RuntimeException();
        }
        return convFile;
    }

    private String generateFileName(MultipartFile multipartFile) {
        return Objects
                .requireNonNull(multipartFile.getOriginalFilename())
                .replace(" ", "_");
    }

    private void uploadFileTos3Bucket(String fileName, File file) {
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public BaseResponse uploadProfilePicture(MultipartFile multipartFile, Long idUser) {
        User user = userService.getUser(idUser);
        String awsPath = "persons/users/" + user.getEmail() + "/profile_picture/";
        String fileUrl;

        try{
            File file = convertMultipartToFile(multipartFile);
            String finalPath = awsPath + generateFileName(multipartFile);
            fileUrl = "https://" + bucketName + "." + endpointUrl + "/" + finalPath;
            uploadFileTos3Bucket(finalPath, file);
            user.setProfilePicture(fileUrl);
            userService.update(from(user), user.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return BaseResponse.builder()
                .data(fileUrl)
                .message("The profile picture was uploaded")
                .httpStatus(HttpStatus.CREATED)
                .success(true).build();
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return "SUCCESS";
    }

    private UpdateUserRequest from(User user){
        return UpdateUserRequest.builder()
                .name(user.getName())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .profilePicture(user.getProfilePicture())
                .userType(userTypeConverter.convertToDatabaseColumn(user.getUserType()))
                .build();
    }

}
