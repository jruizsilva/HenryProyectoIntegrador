package henryproyectointegrador.dao.interfaces;

import java.util.List;

public interface CRUD<TDto, NId> {
    int insert(TDto dataDto);

    int update(NId id, TDto dataDto);

    int deleteById(NId id);

    List<TDto> findAll();

    TDto findById(NId id);
}
