package henryproyectointegrador.negocio;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.interfaces.CRUD;

import java.util.Map;

public interface IExpenseMonitoring extends CRUD<ExpenseDto, Integer> {
    void loadCategoryList();

    Map<String, String> getCategoryList();
}
