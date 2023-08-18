package henryproyectointegrador.dao.impl;

import henryproyectointegrador.dao.ExpenseDao;
import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.entities.ExpenseEntity;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao {
    private final List<ExpenseEntity> expenseEntityList = new ArrayList<>();

    @Override
    public void insert(ExpenseDto dataDto) {

    }

    @Override
    public void update(Long aLong, ExpenseDto dataDto) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public List<ExpenseDto> findAll() {
        return null;
    }

    @Override
    public ExpenseDto findById(Long aLong) {
        return null;
    }

    @Override
    public ExpenseEntity mapDtoToEntity(ExpenseDto expenseDto) {
        return null;
    }

    @Override
    public ExpenseDto mapEntityToDto(ExpenseEntity expenseEntity) {
        return null;
    }
}
