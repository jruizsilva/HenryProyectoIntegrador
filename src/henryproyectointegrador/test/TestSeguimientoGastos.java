package henryproyectointegrador.test;

import henryproyectointegrador.dao.dto.CategoryDto;
import henryproyectointegrador.dao.impl.h2.CategoryDaoImplH2;
import henryproyectointegrador.dao.impl.h2.ExpenseDaoImplH2;
import henryproyectointegrador.presentacion.Presentacion;

import java.util.List;

public class TestSeguimientoGastos {
    public static void main(String[] args) {
        Presentacion presentacion = Presentacion.getInstance();
        ExpenseDaoImplH2 expenseDao = new ExpenseDaoImplH2();
        CategoryDaoImplH2 categoryDao = new CategoryDaoImplH2();

        List<CategoryDto> categories = categoryDao.findAll();
        presentacion.printCategories(categories);
    }
}
