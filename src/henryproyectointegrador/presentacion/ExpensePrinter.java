package henryproyectointegrador.presentacion;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.utils.TableList;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class ExpensePrinter {
    public static void print(List<ExpenseDto> expenseDtoList, Map<Integer, String> categoryList, boolean includeId) {
        LinkedHashSet<String> fieldNames = new LinkedHashSet<>();
        if (includeId) {
            fieldNames.add("id");
        }
        fieldNames.add("amount");
        fieldNames.add("category");
        fieldNames.add("date");

        String[] fieldNamesArr = fieldNames.toArray(new String[0]);

        TableList tl = new TableList(fieldNamesArr).withUnicode(true);
        expenseDtoList.forEach(expense -> {
            String id = String.valueOf(expense.getId());
            String amount = String.valueOf(expense.getAmount());
            String category = categoryList.get(expense.getIdCategory());
            String date = String.valueOf(expense.getDate());
            tl.addRow(id, amount, category, date);
        });
        tl.print();
    }
}

