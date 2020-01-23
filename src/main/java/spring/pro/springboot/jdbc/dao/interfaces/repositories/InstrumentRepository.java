package spring.pro.springboot.jdbc.dao.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.pro.springboot.jdbc.dao.entities.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, String> {}
