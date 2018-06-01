package movies.spring.data.neo4j.domain;

import org.neo4j.ogm.typeconversion.AttributeConverter;

/**
 * Created by denis on 23/05/2018.
 */
public class TypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String toGraphProperty(Type value) {
        String newValue = "";
        if (value.equals(Type.Competence)) newValue = "Competence";
        if (value.equals(Type.Question)) newValue = "Question";
        if (value.equals(Type.Subtopic)) newValue = "Subtopic";
        if (value.equals(Type.Topic)) newValue = "Topic";
        if (value.equals(Type.Test)) newValue = "Test";
        return newValue;
    }

    @Override
    public Type toEntityAttribute(String value) {
        return Type.valueOf(value);
    }

}