package henryproyectointegrador.negocio;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.dao.impl.ExpenseDaoImplH2;
import henryproyectointegrador.interfaces.IExpenseMonitoring;

import java.util.ArrayList;
import java.util.List;

public class ExpenseMonitoring implements IExpenseMonitoring {
    private final ExpenseDaoImplH2 expenseDao = new ExpenseDaoImplH2();
    private final List<ExpenseDto> expenseDtoList = new ArrayList<>();

    public ExpenseMonitoring() {
    }

    @Override
    public List<ExpenseDto> getExpenses() {
        List<ExpenseDto> expenseDtoList = expenseDao.findAll();
        this.expenseDtoList.addAll(expenseDtoList);
        return expenseDtoList;
    }

    @Override
    public ExpenseDto getExpenseById(long id) {
        return expenseDao.findById(id);
    }

    @Override
    public void insertExpense(ExpenseDto expenseDto) {
        expenseDao.insert(expenseDto);
    }

    @Override
    public void updateExpense(long id, ExpenseDto expenseDto) {
        expenseDao.update(id, expenseDto);
    }

    @Override
    public void deleteExpense(long id) {
        expenseDao.deleteById(id);
    }

    public List<ExpenseDto> getExpenseDtoList() {
        return this.expenseDtoList;
    }
}
