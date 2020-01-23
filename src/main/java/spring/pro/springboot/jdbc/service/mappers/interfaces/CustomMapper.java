package spring.pro.springboot.jdbc.service.mappers.interfaces;

import java.util.Set;
import java.util.stream.Collectors;

public interface CustomMapper <E, V>{
    E convertToEntity(V view);
    V convertToView(E entity);
    default Set<E> convertToEntitySet(Set<V> views){
        return views.stream().map(this::convertToEntity).collect(Collectors.toSet());
    }
    default Set<V> convertToViewSet(Set<E> entities){
        return entities.stream().map(this::convertToView).collect(Collectors.toSet());
    };


}
