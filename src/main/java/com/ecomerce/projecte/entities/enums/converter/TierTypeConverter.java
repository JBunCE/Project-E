package com.ecomerce.projecte.entities.enums.converter;

import org.springframework.stereotype.Component;

import com.ecomerce.projecte.entities.enums.TierType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Component 
@Converter(autoApply = true)
public class TierTypeConverter implements AttributeConverter<TierType, String>{

    @Override
    public String convertToDatabaseColumn(TierType tierType) {
        if(tierType== null) return null;
        return tierType.getTierCode();
    }

    @Override
    public TierType convertToEntityAttribute(String type) {
        if(type==null)return null;
        return Stream.of(TierType.values())
            .filter(t-> t.getTierCode().equalsIgnoreCase(type))
            .findFirst().orElseThrow(RuntimeException::new);
    }
    
}
