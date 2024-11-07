package ru.itis.restaurant.validation.validators;

import org.springframework.data.util.ReflectionUtils;
import ru.itis.restaurant.validation.annotations.ValidData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataValidator implements ConstraintValidator<ValidData, Object> {
    private String[] fields;

    @Override
    public void initialize(ValidData validData) {
        this.fields = validData.fields();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        List<String> fieldValues = new ArrayList<>();

        for (String fieldName : fields) {
            try {
                Field field = ReflectionUtils.findRequiredField(object.getClass(), fieldName);
                field.setAccessible(true);
                fieldValues.add((String)field.get(object));
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date bFrom = sdf.parse(fieldValues.get(0));
            Date bTo = sdf.parse(fieldValues.get(1));

            return bFrom.compareTo(bTo) < 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }
}
