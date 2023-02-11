package com.ecomerce.projecte.entities.enums.converter;

import com.ecomerce.projecte.entities.enums.UserType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, String> {

    @Override
    public String convertToDatabaseColumn(UserType userType) {
        if(userType == null) return null;
        return userType.getType();
    }

    @Override
    public UserType convertToEntityAttribute(String type) {
        if(type == null) return null;
        return Stream.of(UserType.values())
                .filter(t -> t.getType().equals(type))
                .findFirst().orElseThrow(RuntimeException::new);
    }
}
