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
import com.ecomerce.projecte.utilities.Environment;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class AWSComponent {

    private AmazonS3 s3Client;

    private static final String ENPOINT_URL = Environment.ENDPOINT_URL;

    private static final String BUCKET_NAME = Environment.BUCKET_NAME;

    private static final String SECRET_KEY = Environment.SECRET_KEY;

    private static final String ACCESS_KEY = Environment.ACCESS_KEY;

    private final ResourceLoader resourceLoader;

    @Autowired
    public AWSComponent(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
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
        s3Client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try{
            File file = convertMultipartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = "https://" + BUCKET_NAME + "." + ENPOINT_URL + "/" + fileName;
            uploadFileTos3Bucket(fileName, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileUrl;
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3Client.deleteObject(new DeleteObjectRequest(BUCKET_NAME, fileName));
        return "SUCCESS";
    }

}
