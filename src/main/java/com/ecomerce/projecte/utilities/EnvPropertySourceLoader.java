package com.ecomerce.projecte.utilities;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EnvPropertySourceLoader implements PropertySourceFactory {

    @NotNull
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
        Resource yamlResource = resource.getResource();
        return sourceLoader.load(name, yamlResource).get(0);
    }
}
