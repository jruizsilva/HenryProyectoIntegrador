package henryproyectointegrador.presentacion;

import henryproyectointegrador.entities.ExpenseEntity;
import henryproyectointegrador.utils.TableList;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Stream;

public class GastoListPrinter {
    private static GastoListPrinter gastoListPrinter;

    private GastoListPrinter() {

    }

    public synchronized static GastoListPrinter getInstance() {
        if (GastoListPrinter.gastoListPrinter == null) {
            GastoListPrinter.gastoListPrinter = new GastoListPrinter();
        }
        return GastoListPrinter.gastoListPrinter;
    }

    public void print(List<? extends ExpenseEntity> list, Class clase) {
        Field[] fields = clase.getDeclaredFields();
        String[] fieldNames = Stream.of(fields)
                                    .filter(field -> !Modifier.isStatic(field.getModifiers()))
                                    .map(Field::getName)
                                    .toArray(String[]::new);

        TableList tl = new TableList(fieldNames).withUnicode(true);
        list.forEach(gasto -> {
            String id_gasto = String.valueOf(gasto.getId());
            String monto = String.valueOf(gasto.getAmount());
            String categoriaGasto = String.valueOf(gasto.getCategoriaGasto());
            String fecha = String.valueOf(gasto.getDate());
            tl.addRow(id_gasto, monto, categoriaGasto, fecha);
        });
        tl.print();
    }
}

