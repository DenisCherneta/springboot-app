package spring.pro.springboot.jdbc.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.pro.springboot.jdbc.dao.interfaces.repositories.InstrumentRepository;
import spring.pro.springboot.jdbc.dao.entities.Instrument;
import spring.pro.springboot.jdbc.service.exeptions.NotFoundApplicationException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Repository
public class InstrumentDao {
    private InstrumentRepository repository;

    @Autowired
    public void setRepository(InstrumentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Instrument> getAll(){
        log.info("Try GetAll");
        List<Instrument> result = repository.findAll();
        log.debug("Try GetAll result: count = %d", result.size());
        return result;
    }

    @Transactional(readOnly = true)
    public Instrument getById(String id){
        log.info("Try GetById({})", id);
        try {
            Instrument result = repository.getOne(id);
            log.debug("Try GetById({}) result: [{}]", id, result);
            return result;
        }catch(EntityNotFoundException ex){
            log.error("Instrument with id=[{}] not found!", id);
            throw new NotFoundApplicationException("Instrument with id=[{}] not found!", id);
        }
    }

    @Transactional(readOnly = true)
    public boolean isPresent(String id){
        return repository.findById(id).isPresent();
    }

    @Transactional
    public Instrument create(Instrument instrument){
        log.info("Try Create Instrument [{}]", instrument);
        Instrument result = repository.save(instrument);
        log.debug("Try Create Instrument [{}] result:[{}]", instrument, result);
        return result;
    }

    @Transactional
    public void delete(String id){
        log.info("Try DeleteById({})", id);
        Optional<Instrument> current = repository.findById(id);
        if (current.isPresent()){
            repository.deleteById(id);
            log.debug("Try DeleteById({}) Instrument was successful", id);
        }else {
            log.error("Instrument with id[{}] not found!", id);
            throw new NotFoundApplicationException("Instrument with id=[{}] not found!", id);
        }
    }
}
