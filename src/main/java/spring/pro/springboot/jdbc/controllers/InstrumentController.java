package spring.pro.springboot.jdbc.controllers;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.pro.springboot.jdbc.dao.InstrumentDao;
import spring.pro.springboot.jdbc.dao.entities.Instrument;
import spring.pro.springboot.jdbc.service.mappers.InstrumentMapper;
import spring.pro.springboot.jdbc.service.mappers.SingerMapper;
import spring.pro.springboot.jdbc.service.views.InstrumentView;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@Api(value = "Instrument Controller", tags = {"Instruments"})
@SwaggerDefinition(tags = {@Tag(name = "Instrument Controller", description = "Available actions with Entity 'Instrument'")})

@Slf4j
@RestController
@RequestMapping("/api/instruments")
public class InstrumentController {
    private InstrumentDao instrumentDao;
    private InstrumentMapper instrumentMapper;
    private SingerMapper singerMapper;

    @Autowired
    public void setInstrumentDao(InstrumentDao instrumentDao) {
        this.instrumentDao = instrumentDao;
    }

    @Autowired
    public void setInstrumentMapper(InstrumentMapper instrumentMapper) {
        this.instrumentMapper = instrumentMapper;
    }

    @Autowired
    public void setSingerMapper(SingerMapper singerMapper) {
        this.singerMapper = singerMapper;
    }

    @ApiOperation(value="Get collection of all Instruments", response = Set.class, tags = "Instruments", produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Collection of instruments")
    })
    @GetMapping(path = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<InstrumentView> getAll(){
        return instrumentMapper.convertToViewSet(instrumentDao.getAll().stream().collect(Collectors.toSet()));
    }

    @ApiOperation(value="Get info about Instrument by Id", response = InstrumentView.class, tags = "Instruments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Entity"),
            @ApiResponse(code = 404, message="NOT FOUND")
    })
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public InstrumentView getById(@PathVariable String id){
        try{
            Instrument instrument = instrumentDao.getById(id);
            InstrumentView view = instrumentMapper.convertToView(instrument);
            view.setSingers(instrument.getSingers().stream().map(singerMapper::convertToView).collect(Collectors.toSet()));
            return view;
        }catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id {'"+id+"'} not found", null);
        }
    }

    @ApiOperation(value="Create entity Instrument", response = InstrumentView.class, tags = "Instruments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Set of all instruments"),
            @ApiResponse(code = 400, message="BAD REQUEST")
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public InstrumentView create(@RequestBody InstrumentView instrument){
        log.debug("Create view " + instrument);
        if (instrumentDao.isPresent(instrument.getInstrumentId())){
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instrument with id {'" + instrument.getInstrumentId() + "'} already exist!");
        }
        Instrument entity = instrumentDao.create(instrumentMapper.convertToEntity(instrument));
        return instrumentMapper.convertToView(entity);
    }

    @ApiOperation(value="Delete entity Instrument by Id", tags = "Instruments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message=""),
            @ApiResponse(code = 404, message="NOT FOUND")
    })
    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void delete(@PathVariable String id){
        if (instrumentDao.isPresent(id)){
            instrumentDao.delete(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instrument with id {'" + id + "'} not found!");
        }
    }
}
