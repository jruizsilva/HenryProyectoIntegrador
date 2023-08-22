package henryproyectointegrador.dao;

import henryproyectointegrador.dao.dto.CategoryDto;
import henryproyectointegrador.entities.CategoryEntity;
import henryproyectointegrador.interfaces.CRUD;

public interface CategoryCRUD extends CRUD<CategoryDto, Integer> {
    default CategoryDto mapToDto(CategoryEntity entity) {
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    default CategoryEntity mapToEntity(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
