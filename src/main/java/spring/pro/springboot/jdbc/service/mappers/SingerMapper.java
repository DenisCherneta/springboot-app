package spring.pro.springboot.jdbc.service.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.pro.springboot.jdbc.dao.entities.Album;
import spring.pro.springboot.jdbc.dao.entities.Instrument;
import spring.pro.springboot.jdbc.dao.entities.Singer;
import spring.pro.springboot.jdbc.service.mappers.interfaces.CustomMapper;
import spring.pro.springboot.jdbc.service.views.AlbumView;
import spring.pro.springboot.jdbc.service.views.InstrumentView;
import spring.pro.springboot.jdbc.service.views.SingerView;

import java.util.Set;

@Service
public class SingerMapper implements CustomMapper<Singer, SingerView> {
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private InstrumentMapper instrumentMapper;

    @Override
    public Singer convertToEntity(SingerView view) {
        Singer entity = new Singer();
        entity.setId(view.getId());
        entity.setFirstName(view.getFirstName());
        entity.setLastName(view.getLastName());
        entity.setBirthDate(view.getBirthDate());
        Set<Album> albums = albumMapper.convertToEntitySet(view.getAlbums());
//        albums.forEach(a->a.setSinger(entity));
        entity.setAlbums(albums);
        Set<Instrument> instruments = instrumentMapper.convertToEntitySet(view.getInstruments());
        entity.setInstruments(instruments);
        return entity;
    }

    @Override
    public SingerView convertToView(Singer entity) {
        SingerView view = new SingerView();
        view.setId(entity.getId());
        view.setFirstName(entity.getFirstName());
        view.setLastName(entity.getLastName());
        view.setBirthDate(entity.getBirthDate());
        Set<AlbumView> albums = albumMapper.convertToViewSet(entity.getAlbums());
//        albums.forEach(a->a.setSinger(entity));
        view.setAlbums(albums);
        Set<InstrumentView> instruments = instrumentMapper.convertToViewSet(entity.getInstruments());
        view.setInstruments(instruments);
        return view;
    }
}
