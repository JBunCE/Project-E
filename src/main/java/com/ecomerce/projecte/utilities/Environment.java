package com.ecomerce.projecte.utilities;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.net.URL;

public class Environment {

    Environment(){
        throw new IllegalStateException();
    }

    public static final String SECRET_KEY;
    public static final String ACCESS_KEY;
    public static final String BUCKET_NAME;
    public static final String ENDPOINT_URL;
    public static final String ACCESS_TOKEN_SECRET_KEY;

    static {
        SECRET_KEY = getEnv("SECRET_KEY");
        ACCESS_KEY = getEnv("ACCESS_KEY");
        BUCKET_NAME = getEnv("BUCKET_NAME");
        ENDPOINT_URL = getEnv("ENDPOINT_URL");
        ACCESS_TOKEN_SECRET_KEY = getEnv("ACCESS_TOKEN_SECRET_KEY");
    }

    private static String getEnv(String name){
        URL resourceUrl = Environment.class.getClassLoader().getResource(".");
        if(resourceUrl != null){
            File resourceDirectory = new File(resourceUrl.getPath());
            Dotenv dotenv = Dotenv.configure()
                    .directory(resourceDirectory.getAbsolutePath() + "/src/main/resources")
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();
            return dotenv.get(name);
        }
        throw new RuntimeException();
    }
}
