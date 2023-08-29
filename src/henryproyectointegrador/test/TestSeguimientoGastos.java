package henryproyectointegrador.test;

import henryproyectointegrador.presentacion.Presentacion;

public class TestSeguimientoGastos {
    public static void main(String[] args) {
        Presentacion presentacion = Presentacion.getInstance();
        /*LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getYear());
        LocalDate firstDayOfTheYear = LocalDate.of(localDate.getYear(), 1, 1);
        System.out.println(localDate.getMonth());
        System.out.println(firstDayOfTheYear);*/

        presentacion.initConsoleMenu();
    }
}
