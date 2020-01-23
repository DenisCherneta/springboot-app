package spring.pro.springboot.jdbc.service.mappers;

import org.springframework.stereotype.Service;
import spring.pro.springboot.jdbc.dao.entities.Instrument;
import spring.pro.springboot.jdbc.service.mappers.interfaces.CustomMapper;
import spring.pro.springboot.jdbc.service.views.InstrumentView;

@Service
public class InstrumentMapper implements CustomMapper<Instrument, InstrumentView> {
//    @Autowired
//    SingerMapper singerMapper;

    @Override
    public Instrument convertToEntity(InstrumentView view) {
        Instrument entity = new Instrument();
        entity.setInstrumentId(view.getInstrumentId());
//        entity.setSingers(singerMapper.convertToEntitySet(view.getSingers()));
        return entity;
    }

    @Override
    public InstrumentView convertToView(Instrument entity) {
        InstrumentView view = new InstrumentView();
        view.setInstrumentId(entity.getInstrumentId());
//        view.setSingers(singerMapper.convertToViewSet(entity.getSingers()));
        return view;
    }

//    @Override
//    public Set<Instrument> convertToEntitySet(Set<InstrumentView> views) {
//        return null;
//    }
//
//    @Override
//    public Set<InstrumentView> convertToViewSet(Set<Instrument> entities) {
//        return null;
//    }
}
