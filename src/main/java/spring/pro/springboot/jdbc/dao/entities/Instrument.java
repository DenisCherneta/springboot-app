package spring.pro.springboot.jdbc.dao.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Entity
//@Table(name = "instrument", schema = "test")
public class Instrument{

	private String instrumentId;
	private Set<Singer> singers = new HashSet<>();

	@Id
	@Column(name = "INSTRUMENT_ID")
	public String getInstrumentId() {
		return this.instrumentId;
	}

	@ManyToMany
	@JoinTable(name = "singer_instrument",
			joinColumns = @JoinColumn(name = "INSTRUMENT_ID"),
			inverseJoinColumns = @JoinColumn(name = "SINGER_ID"))
	public Set<Singer> getSingers() {
		return this.singers;
	}

	public void setSingers(Set<Singer> singers) {
			this.singers = singers;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	@Override
	public String toString() {
		return "Instrument: {Id:" + getInstrumentId() + "\n singers.size:" + singers.size() + "}" ;
	}
}