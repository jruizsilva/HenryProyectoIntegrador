package henryproyectointegrador.dao;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.dao.interfaces.CRUD;
import henryproyectointegrador.dao.interfaces.MapDataMethods;
import henryproyectointegrador.entities.ExpenseEntity;

public interface ExpenseDao extends CRUD<ExpenseDto, int>, MapDataMethods<ExpenseDto, ExpenseEntity> {
}
