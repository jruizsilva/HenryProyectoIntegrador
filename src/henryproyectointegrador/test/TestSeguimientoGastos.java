package henryproyectointegrador.test;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.dao.impl.ExpenseDaoImplH2;
import henryproyectointegrador.presentacion.Presentacion;

import java.util.List;

public class TestSeguimientoGastos {
    public static void main(String[] args) {
        Presentacion presentacion = Presentacion.getInstance();
        ExpenseDaoImplH2 expenseDao = new ExpenseDaoImplH2();
        /*presentacion.initConsoleMenu();*/
        List<ExpenseDto> expenseDtoList = expenseDao.findAll();
        System.out.println("expenseDtoList = " + expenseDtoList);
    }
}
