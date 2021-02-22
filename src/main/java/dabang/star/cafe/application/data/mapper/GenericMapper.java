package dabang.star.cafe.application.data.mapper;

public interface GenericMapper<D, E> {
    D toDto(E entity);

    E toEntity(D dto);
}
