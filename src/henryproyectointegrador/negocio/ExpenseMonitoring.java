package henryproyectointegrador.negocio;

import henryproyectointegrador.dao.dto.CategoryDto;
import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.dao.h2.H2CategoryCRUD;
import henryproyectointegrador.dao.h2.H2ExpenseCRUD;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExpenseMonitoring implements IExpenseMonitoring {
    private static ExpenseMonitoring expenseMonitoring;
    private final H2ExpenseCRUD expenseDao = new H2ExpenseCRUD();
    private final H2CategoryCRUD categoryDao = new H2CategoryCRUD();
    private final Map<Integer, String> categoryMapList = new TreeMap<>();
    private List<CategoryDto> categoryDtoList = new ArrayList<>();

    private ExpenseMonitoring() {
        loadCategoryList();
    }

    public void loadCategoryList() {
        categoryMapList.clear();
        List<CategoryDto> categoryDtos = categoryDao.selectAll();
        categoryDtoList = categoryDtos;
        for (int i = 0; i < categoryDtos.size(); i++) {
            categoryMapList.put(i + 1, categoryDtos.get(i)
                                                   .getName());
        }
    }

    public synchronized static ExpenseMonitoring getInstance() {
        if (expenseMonitoring == null) {
            expenseMonitoring = new ExpenseMonitoring();
        }
        return expenseMonitoring;
    }

    public Map<Integer, String> getCategoryMapList() {
        return categoryMapList;
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

    public List<CategoryDto> getCategoryDtoList() {
        return categoryDtoList;
    }
}
