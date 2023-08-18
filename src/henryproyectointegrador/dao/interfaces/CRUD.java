package henryproyectointegrador.dao.interfaces;

import java.util.List;

public interface CRUD<TDto, NId> {
    void insert(TDto dataDto);

    void update(TDto dataDto);

    void deleteById(NId id);

    List<TDto> findAll();

    TDto findById(NId id);
}
