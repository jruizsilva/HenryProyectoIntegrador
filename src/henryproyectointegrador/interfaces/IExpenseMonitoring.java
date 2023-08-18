package henryproyectointegrador.interfaces;

import henryproyectointegrador.dao.dto.ExpenseDto;

import java.util.List;

public interface IExpenseMonitoring {
    List<ExpenseDto> getExpenses();

    ExpenseDto getExpenseById(long id);

    void insertExpense(ExpenseDto expenseDto);

    void updateExpense(long id, ExpenseDto expenseDto);

    void deleteExpense(long id);
}
