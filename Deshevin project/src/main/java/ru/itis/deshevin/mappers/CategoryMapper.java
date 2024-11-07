package ru.itis.deshevin.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.itis.deshevin.dto.CategoryDto;
import ru.itis.deshevin.models.CategoryEntity;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryEntity toCategoryEntity(CategoryDto categoryDto);
    CategoryDto toCategoryDto(CategoryEntity categoryEntity);
    List<CategoryDto> toCategoryDtoList(List<CategoryEntity> categoryEntitySet);
}
