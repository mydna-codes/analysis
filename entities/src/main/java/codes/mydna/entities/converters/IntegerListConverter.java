package codes.mydna.entities.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class IntegerListConverter implements AttributeConverter<List<Integer>, String> {

    public static final String ELEMENT_DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(List<Integer> ints) {
        if(ints == null || ints.isEmpty())
            return "";

        return String.join(ELEMENT_DELIMITER, ints.toString());
    }

    @Override
    public List<Integer> convertToEntityAttribute(String s) {
        if(s == null || s.isEmpty())
            return new ArrayList<>();

        List<Integer> ints = new ArrayList<>();
        for(String integer : s.split(ELEMENT_DELIMITER))
            ints.add(Integer.parseInt(integer));

        return ints;
    }
}
