package com.ecomerce.projecte.entities.enums.converter;

import com.ecomerce.projecte.entities.enums.ColorType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Converter(autoApply = true)
public class ColorTypeConverter implements AttributeConverter<ColorType, String> {

    @Override
    public String convertToDatabaseColumn(ColorType colorType) {
        if(colorType == null) return null;
        return colorType.getColorType();
    }


    @Override
    public ColorType convertToEntityAttribute(String type) {
        if(type == null) return null;
        return Stream.of(ColorType.values())
                .filter(t -> t.getColorType().equals(type))
                .findFirst().orElseThrow(RuntimeException::new);
    }
}
