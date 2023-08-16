package henryproyectointegrador.utils;

public class Utilidades {
    public static boolean contieneString(String[] array, String entrada) {
        for (String el : array) {
            if (el.equals(entrada))
                return true;
        }
        return false;
    }
}
