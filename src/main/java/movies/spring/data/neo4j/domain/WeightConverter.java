package movies.spring.data.neo4j.domain;

import org.neo4j.ogm.typeconversion.AttributeConverter;

/**
 * Created by denis on 23/05/2018.
 */
public class WeightConverter implements AttributeConverter<Float, String> {

    @Override
    public String toGraphProperty(Float value) {
        // String s = Float.toString(25.0f);
        return value.toString();
    }

    @Override
    public Float toEntityAttribute(String value) {
        float newValue = Float.parseFloat(value);
        return newValue;
    }

}