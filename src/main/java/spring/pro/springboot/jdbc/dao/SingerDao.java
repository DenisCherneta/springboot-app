package spring.pro.springboot.jdbc.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.pro.springboot.jdbc.dao.interfaces.repositories.SingerRepository;
import spring.pro.springboot.jdbc.dao.entities.Singer;
import spring.pro.springboot.jdbc.service.exeptions.NotFoundApplicationException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Repository
public class SingerDao {

	private SingerRepository repository;

	@Autowired
	public void setRepository(SingerRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<Singer> findAll()
	{
		log.info("Try GetAll");
		List<Singer> result = repository.findAll();
		log.debug("Try GetAll count: %d", result.size());
		return result;
	}

	@Transactional(readOnly = true)
	public Singer findById(Long id) {
		log.info("Try FindById(%d)", id);
		try{
			Singer result = repository.getOne(id);
			log.debug("Try FindById(%d) result: [%s]", id, result);
			return result;
		}catch(EntityNotFoundException ex){
			throw new NotFoundApplicationException("Singer with id=[%d] not found!", id);
		}
	}

	@Transactional
	public Singer update(Singer singer) {
		log.info("Try Update Singer [%s]", singer);
		Optional<Singer> current = repository.findById(singer.getId());

		if (!current.isPresent()){
			throw new NotFoundApplicationException("Singer with id=[%d] not found!", singer.getId());
		}

			Singer entity = current.get();

			if (entity.getBirthDate().compareTo(singer.getBirthDate())!=0){
				entity.setBirthDate(singer.getBirthDate());
			}
			if (entity.getFirstName().compareTo(singer.getFirstName())!=0){
				entity.setFirstName(singer.getFirstName());
			}
			if (entity.getLastName().compareTo(singer.getLastName())!=0){
				entity.setLastName(singer.getLastName());
			}

			entity.getAlbums().clear();
			singer.getAlbums().forEach(a->entity.addAlbum(a));

			entity.getInstruments().forEach(i -> entity.removeInstrument(i));
			singer.getInstruments().forEach(i -> entity.addInstrument(i));

			repository.saveAndFlush(entity);
			log.debug("Try Update Singer [%s] result: [%s]", singer, entity);
			return singer;
	}

	@Transactional
	public Singer create(Singer singer) {
		log.info("Try Create Singer [%s]", singer);
		Singer result = repository.save(singer);
		log.debug("Try Create Singer [%s] result:[%s]", result.getId());
		return singer;
	}

	@Transactional
	public void deleteById(Long id) {
		log.info("Try DeleteById(%d)", id);
		Optional<Singer> current = repository.findById(id);
		if (current.isPresent()){
			repository.deleteById(id);
			log.debug("Try DeleteById(%d) Singer was successful", id);
		}else {
			log.error("Singer with id=[%d] not found!", id);
			throw new NotFoundApplicationException("Singer with id=[%d] not found!", id);
		}
	}


}
