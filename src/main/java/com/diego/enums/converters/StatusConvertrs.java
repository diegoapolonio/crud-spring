package com.diego.enums.converters;

import com.diego.enums.Category;
import com.diego.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

    @Converter(autoApply = true)
    public class StatusConvertrs implements AttributeConverter<Status,String> {
        @Override
        public String convertToDatabaseColumn(Status category) {
            if (category==null){
                return null;
            }
            return category.getValue();
        }

        @Override
        public Status convertToEntityAttribute(String value) {
            if (value == null) {
                return null;
            }
            return Stream.of(Status.values())
                    .filter(c -> c.getValue().equals(value))
                    .findFirst()//filtra um valor
                    .orElseThrow(IllegalArgumentException::new);

        }
    }
