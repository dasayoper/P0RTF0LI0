package ru.itis.deshevin.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.itis.deshevin.dto.CategoryDto;
import ru.itis.deshevin.mappers.CategoryMapper;
import ru.itis.deshevin.models.CategoryEntity;
import ru.itis.deshevin.repositories.CategoryRepository;
import ru.itis.deshevin.services.CategoryService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategory() {
        log.info("Get all categories");
        return categoryMapper.toCategoryDtoList(categoryRepository.findAll());
    }

    @Override
    @Transactional
    public void saveCategory(CategoryDto categoryDto) {
        log.info("Start saving Category " + categoryDto);
        categoryRepository.save(categoryMapper.toCategoryEntity(categoryDto));
        log.info("Finish saving");
    }

    @Override
    @Transactional
    public void deleteCategory(String id) {
        log.info("Start deleting Category with id = " + UUID.fromString(id));
        categoryRepository.findById(UUID.fromString(id)).ifPresent(
                category -> category.getDrugs().forEach( drug -> {
                    log.info("Delete drug related with analogue class = " + drug);
                    drug.getDrugsCategory().remove(category);
                })
        );
        categoryRepository.deleteById(UUID.fromString(id));

        log.info("Finish deleting");
    }

    @Override
    public Set<CategoryEntity> getCategoriesById(Set<String> categoriesId) {
        return new HashSet<>(categoryRepository.findAllById(categoriesId.stream().map(UUID::fromString)
                .collect(Collectors.toSet())));
    }
}
