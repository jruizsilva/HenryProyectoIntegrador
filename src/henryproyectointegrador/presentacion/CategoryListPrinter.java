package henryproyectointegrador.presentacion;

import henryproyectointegrador.dao.dto.CategoryDto;
import henryproyectointegrador.utils.TableList;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Stream;

public class CategoryListPrinter {
    private static CategoryListPrinter categoryListPrinter;

    private CategoryListPrinter() {

    }

    public synchronized static CategoryListPrinter getInstance() {
        if (CategoryListPrinter.categoryListPrinter == null) {
            CategoryListPrinter.categoryListPrinter = new CategoryListPrinter();
        }
        return CategoryListPrinter.categoryListPrinter;
    }

    public void print(List<CategoryDto> list) {
        Field[] fields = CategoryDto.class.getDeclaredFields();
        String[] fieldNames = Stream.of(fields)
                                    .filter(field -> !Modifier.isStatic(field.getModifiers()))
                                    .map(Field::getName)
                                    .toArray(String[]::new);

        TableList tl = new TableList(fieldNames).withUnicode(true);
        list.forEach(categoryDto -> {
            String id_categoria = String.valueOf(categoryDto.getId());
            String nombre = String.valueOf(categoryDto.getName());
            tl.addRow(id_categoria, nombre);
        });
        tl.print();
    }
}

