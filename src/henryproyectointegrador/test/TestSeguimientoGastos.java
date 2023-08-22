package henryproyectointegrador.test;

import henryproyectointegrador.dao.dto.CategoryDto;
import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.dao.h2.H2CategoryCRUD;
import henryproyectointegrador.dao.h2.H2ExpenseCRUD;
import henryproyectointegrador.presentacion.Presentacion;

import java.util.List;

public class TestSeguimientoGastos {
    public static void main(String[] args) {
        Presentacion presentacion = Presentacion.getInstance();
        H2ExpenseCRUD expenseDao = new H2ExpenseCRUD();
        H2CategoryCRUD categoryDao = new H2CategoryCRUD();
        List<CategoryDto> categories = categoryDao.selectAll();
        List<ExpenseDto> expenses = expenseDao.selectAll();
        presentacion.printCategories(categories);
        presentacion.printExpenses(expenses);
    }
}
