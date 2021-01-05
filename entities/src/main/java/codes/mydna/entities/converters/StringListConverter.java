package codes.mydna.entities.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    public static final String ELEMENT_DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(List<String> strings) {

        if(strings == null || strings.isEmpty())
            return "";

        return String.join(ELEMENT_DELIMITER, strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {

        if(s == null || s.isEmpty())
            return new ArrayList<>();

        return Arrays.asList(s.split(ELEMENT_DELIMITER));
    }
}
