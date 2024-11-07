package ru.itis.restaurant.validation.validators;

import ru.itis.restaurant.models.MenuItem;
import ru.itis.restaurant.validation.annotations.ValidCategory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CategoryValidator implements ConstraintValidator<ValidCategory, String> {

    @Override
    public void initialize(ValidCategory validCategory) {
    }

    @Override
    public boolean isValid(String category, ConstraintValidatorContext constraintValidatorContext) {
        MenuItem.Category[] categories = MenuItem.Category.values();
        boolean isEquals = false;
        for (MenuItem.Category menuItemCategory : categories) {
            if (category.equals(menuItemCategory.toString())) {
                isEquals = true;
                break;
            }
        }
        return isEquals;
    }
}
