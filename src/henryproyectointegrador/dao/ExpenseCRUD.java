package henryproyectointegrador.dao;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.entities.ExpenseEntity;
import henryproyectointegrador.interfaces.CRUD;

public interface ExpenseCRUD extends CRUD<ExpenseDto, Integer> {
    default ExpenseDto mapToDto(ExpenseEntity entity) {
        ExpenseDto dto = new ExpenseDto();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setIdCategory(entity.getIdCategory());
        dto.setDate(entity.getDate());
        return dto;
    }

    default ExpenseEntity mapToEntity(ExpenseDto dto) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setId(dto.getId());
        entity.setAmount(dto.getAmount());
        entity.setIdCategory(dto.getIdCategory());
        entity.setDate(dto.getDate());
        return entity;
    }
}
