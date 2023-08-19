package henryproyectointegrador.interfaces;

import henryproyectointegrador.dao.dto.ExpenseDto;

import java.util.List;

public interface IExpenseMonitoring {
    List<ExpenseDto> getExpenses();

    ExpenseDto getExpenseById(int id);

    void insertExpense(ExpenseDto expenseDto);

    void updateExpense(int id, ExpenseDto expenseDto);

    void deleteExpense(int id);
}
