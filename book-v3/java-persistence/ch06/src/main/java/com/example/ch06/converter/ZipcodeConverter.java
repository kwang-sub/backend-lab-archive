package com.example.ch06.converter;

import com.example.ch06.model.GermanZipcode;
import com.example.ch06.model.SwissZipcode;
import com.example.ch06.model.Zipcode;
import jakarta.persistence.AttributeConverter;

public class ZipcodeConverter implements AttributeConverter<Zipcode, String> {
    @Override
    public String convertToDatabaseColumn(Zipcode zipcode) {
        return zipcode.getValue();
    }

    @Override
    public Zipcode convertToEntityAttribute(String s) {
        if (s.length() == 5) {
            return new GermanZipcode(s);
        } else if (s.length() == 4) {
            return new SwissZipcode(s);
        }
        throw new IllegalArgumentException("Invalid zipcode format");
    }
}
