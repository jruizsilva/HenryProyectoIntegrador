package henryproyectointegrador.negocio;

import henryproyectointegrador.dao.ExpenseDao;

import java.util.Map;

public interface IExpenseMonitoring extends ExpenseDao {
    void loadCategoryList();

    Map<Integer, String> getCategoryMapList();
}
