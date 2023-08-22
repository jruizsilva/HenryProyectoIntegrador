package henryproyectointegrador.negocio;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.dao.h2.H2ExpenseCRUD;

import java.util.List;

public class ExpenseMonitoring implements IExpenseMonitoring {
    private final H2ExpenseCRUD expenseDao = new H2ExpenseCRUD();

    public ExpenseMonitoring() {
    }

    @Override
    public Integer insert(ExpenseDto expense) {
        return expenseDao.insert(expense);
    }

    @Override
    public Integer update(ExpenseDto expense) {
        return expenseDao.update(expense);
    }

    @Override
    public Integer delete(ExpenseDto expense) {
        return expenseDao.delete(expense);
    }

    @Override
    public List<ExpenseDto> selectAll() {
        return expenseDao.selectAll();
    }

    @Override
    public ExpenseDto selectOne(Integer id) {
        return expenseDao.selectOne(id);
    }
}
