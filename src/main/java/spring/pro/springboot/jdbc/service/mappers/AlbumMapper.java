package spring.pro.springboot.jdbc.service.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.pro.springboot.jdbc.dao.entities.Album;
import spring.pro.springboot.jdbc.service.mappers.interfaces.CustomMapper;
import spring.pro.springboot.jdbc.service.views.AlbumView;

@Service
public class AlbumMapper implements CustomMapper<Album, AlbumView> {

    @Autowired
    SingerMapper singerMapper;

    @Override
    public Album convertToEntity(AlbumView view) {
        Album entity = new Album();
        entity.setId(view.getId());
        entity.setTitle(view.getTitle());
//        entity.setSinger(singerMapper.convertToEntity(view.getSinger()));
        entity.setReleaseDate(view.getReleaseDate());
        return entity;
    }

    @Override
    public AlbumView convertToView(Album entity) {
        AlbumView view = new AlbumView();
        view.setId(entity.getId());
        view.setTitle(entity.getTitle());
        view.setReleaseDate(entity.getReleaseDate());
        if (entity.getSinger()!=null && entity.getSinger().getId()!=null)
            view.setSingerId(entity.getSinger().getId());
//        view.setSinger(singerMapper.convertToView(entity.getSinger()));
        return view;
    }
}
