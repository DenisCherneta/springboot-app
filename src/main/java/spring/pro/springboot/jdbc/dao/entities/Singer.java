package spring.pro.springboot.jdbc.dao.entities;

import spring.pro.springboot.jdbc.dao.entities.audit.AuditableEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
//@Table(name = "singer", schema = "test")
// left join fetch - немедленно сделать выборку всех свзяей
//@NamedQueries({
//		@NamedQuery(name="Singer.findById",
//				query="select distinct s from Singer s " +"left join fetch s.albums a " +"left join fetch s.instruments i " +"where s.id = :id"),
//		@NamedQuery(name="Singer.findAllWithAlbum",
//				query="select distinct s from Singer s " +"left join fetch s.albums a " +"left join fetch s.instruments i"),
//		@NamedQuery(name = "Singer.findAll",
//				query="Select s from Singer s")
//})
public class Singer extends AuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private int version;
	private Set<Album> albums = new HashSet<>();
	private Set<Instrument> instruments = new HashSet<>();

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	@Version
	@Column(name = "VERSION")
	public int getVersion() {
		return version;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return this.firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return this.lastName;
	}

	//@Temporal со значением TemporalТуре.DATE в качестве аргумента. Это означает, что тиn данных Java(java.util.Date) желательно nреобразовать в тиn данных SQL (java.sql.Date)
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	public Date getBirthDate() {
		return birthDate;
	}

	// атрибут orphanRemoval указывает, что после обновления сведений об альбомах записи, которые больше не существуют в наборе, должны быть удалены из базы данных
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "singer", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<Album> getAlbums() {
		return albums;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "singer_instrument",
			joinColumns = @JoinColumn(name = "SINGER_ID"),
			inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
	public Set<Instrument> getInstruments() {
		return instruments;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean addAlbum(Album album) {
		album.setSinger(this);
		return getAlbums().add(album);
	}

	public void removeAlbum(Album album) {
		getAlbums().remove(album);
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	}

	public boolean addInstrument(Instrument instrument) {
		return getInstruments().add(instrument);
	}

	public boolean removeInstrument(Instrument instrument) {
		return getInstruments().remove(instrument);
	}

	public String toString() {
		return "Singer - Id: " + id + ", First name: " + firstName
				+ ", Last name: " + lastName + ", Birthday: " + birthDate
				+ ", " + super.toString();
	}
}
