package henryproyectointegrador.negocio;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.dao.impl.h2.ExpenseDaoImplH2;
import henryproyectointegrador.interfaces.IExpenseMonitoring;

import java.util.List;

public class ExpenseMonitoring implements IExpenseMonitoring {
    private final ExpenseDaoImplH2 expenseDao = new ExpenseDaoImplH2();

    public ExpenseMonitoring() {
    }

    @Override
    public List<ExpenseDto> getExpenses() {
        return expenseDao.findAll();
    }

    @Override
    public ExpenseDto getExpenseById(int id) {
        return expenseDao.findById(id);
    }

    @Override
    public void insertExpense(ExpenseDto expenseDto) {
        expenseDao.insert(expenseDto);
    }

    @Override
    public void updateExpense(int id, ExpenseDto expenseDto) {
        expenseDao.update(id, expenseDto);
    }

    @Override
    public void deleteExpense(int id) {
        expenseDao.deleteById(id);
    }
}
