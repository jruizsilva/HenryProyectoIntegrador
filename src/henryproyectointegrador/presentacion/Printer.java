package henryproyectointegrador.presentacion;

import henryproyectointegrador.dao.dto.CategoryDto;
import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.negocio.ExpenseMonitoring;
import henryproyectointegrador.utils.TableList;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class Printer {
    private static ExpenseMonitoring expenseMonitoring = ExpenseMonitoring.getInstance();
    private static Printer printer;

    private Printer() {
        expenseMonitoring = ExpenseMonitoring.getInstance();
    }

    public synchronized static Printer getInstance() {
        if (Printer.printer == null) {
            Printer.printer = new Printer();
        }
        return Printer.printer;
    }

    public void printExpenses(List<ExpenseDto> expenseDtoList, boolean showId) {
        Map<Integer, String> categoryMapList = expenseMonitoring.getCategoryMapList();
        LinkedHashSet<String> fieldNames = new LinkedHashSet<>();
        if (showId) {
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
            String category = categoryMapList.get(expense.getIdCategory());
            String date = String.valueOf(expense.getDate());
            tl.addRow(id, amount, category, date);
        });
        tl.print();
    }

    public void print(ExpenseDto expenseDto, boolean showId) {
        Map<Integer, String> categoryMapList = expenseMonitoring.getCategoryMapList();
        LinkedHashSet<String> fieldNames = new LinkedHashSet<>();
        if (showId) {
            fieldNames.add("id");
        }
        fieldNames.add("amount");
        fieldNames.add("category");
        fieldNames.add("date");

        String[] fieldNamesArr = fieldNames.toArray(new String[0]);

        TableList tl = new TableList(fieldNamesArr).withUnicode(true);

        String id = String.valueOf(expenseDto.getId());
        String amount = String.valueOf(expenseDto.getAmount());
        String category = "null";
        if (expenseDto.getIdCategory() != null) {
            category = categoryMapList.get(expenseDto.getIdCategory());
        }
        String date = String.valueOf(expenseDto.getDate());
        if (showId) {
            tl.addRow(id, amount, category, date);
        } else {
            tl.addRow(amount, category, date);
        }
        tl.print();
    }

    public void printCategories(List<CategoryDto> categoryDtoList, boolean showId) {
        LinkedHashSet<String> fieldNames = new LinkedHashSet<>();
        if (showId) {
            fieldNames.add("id");
        }
        fieldNames.add("name");

        String[] fieldNamesArr = fieldNames.toArray(new String[0]);

        TableList tl = new TableList(fieldNamesArr).withUnicode(true);
        categoryDtoList.forEach(categoryDto -> {
            String id_categoria = String.valueOf(categoryDto.getId());
            String nombre = String.valueOf(categoryDto.getName());
            if (showId) {
                tl.addRow(id_categoria, nombre);
            } else {
                tl.addRow(nombre);
            }
        });
        tl.print();
    }

    public void print(CategoryDto categoryDto, boolean includeId) {
        LinkedHashSet<String> fieldNames = new LinkedHashSet<>();
        if (includeId) {
            fieldNames.add("id");
        }
        fieldNames.add("name");

        String[] fieldNamesArr = fieldNames.toArray(new String[0]);

        TableList tl = new TableList(fieldNamesArr).withUnicode(true);
        String id_categoria = String.valueOf(categoryDto.getId());
        String nombre = String.valueOf(categoryDto.getName());
        if (includeId) {
            tl.addRow(id_categoria, nombre);
        } else {
            tl.addRow(nombre);
        }
        tl.print();
    }
}

