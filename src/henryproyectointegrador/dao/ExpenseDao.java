package henryproyectointegrador.dao;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.interfaces.CRUD;

import java.util.List;

public interface ExpenseDao extends CRUD<ExpenseDto, Integer> {
    List<ExpenseDto> selectAllByCategoryId(Integer categoryId);
}
