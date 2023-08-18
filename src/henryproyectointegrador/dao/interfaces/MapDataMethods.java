package henryproyectointegrador.dao.interfaces;

public interface MapDataMethods<TDto, TEntity> {
    TEntity mapDtoToEntity(TDto dto);

    TDto mapEntityToDto(TEntity entity);
}
