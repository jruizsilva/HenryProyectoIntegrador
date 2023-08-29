package henryproyectointegrador.dao;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.interfaces.CRUD;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseDao extends CRUD<ExpenseDto, Integer> {
    List<ExpenseDto> selectAllByCategoryId(Integer categoryId);

    List<ExpenseDto> selectAllByDate(LocalDate date);

    List<ExpenseDto> selectAllBetweenTwoDates(LocalDate date, LocalDate currentDate);
}
