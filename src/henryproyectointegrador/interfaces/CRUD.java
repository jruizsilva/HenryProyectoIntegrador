package henryproyectointegrador.interfaces;

import java.util.List;

public interface CRUD<T, K> {
    Integer insert(T data);

    Integer update(T data);

    Integer delete(T data);

    List<T> selectAll();

    T selectOne(K id);
}
