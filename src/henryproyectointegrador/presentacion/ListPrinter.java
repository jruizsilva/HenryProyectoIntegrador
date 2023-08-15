package henryproyectointegrador.presentacion;

import henryproyectointegrador.domain.Gasto;
import henryproyectointegrador.utils.TableList;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Stream;

public class ListPrinter {
    public void print(List<? extends Gasto> list, Class clase) {
        Field[] fields = clase.getDeclaredFields();
        String[] fieldNames = Stream.of(fields)
                                    .filter(field -> !Modifier.isStatic(field.getModifiers()))
                                    .map(Field::getName)
                                    .toArray(String[]::new);

        TableList tl = new TableList(fieldNames).withUnicode(true);
        list.forEach(gasto -> {
            String id_gasto = String.valueOf(gasto.getId_gasto());
            String monto = String.valueOf(gasto.getMonto());
            String categoriaGasto = String.valueOf(gasto.getCategoriaGasto());
            String fecha = String.valueOf(gasto.getFecha());
            tl.addRow(id_gasto, monto, categoriaGasto, fecha);
        });
        tl.print();
    }
}

