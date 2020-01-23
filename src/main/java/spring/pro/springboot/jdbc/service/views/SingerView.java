package spring.pro.springboot.jdbc.service.views;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SingerView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Min(value = 1)
    @ApiModelProperty(notes = "Singer id", position = 0)
    private Long id;

    @NotBlank
    @Size(max = 49)
    @ApiModelProperty(notes = "First Name", position = 1)
    private String firstName;

    @NotBlank
    @Size(max = 49)
    @ApiModelProperty(notes = "Last Name", position = 2)
    private String lastName;

    @NotNull
    @ApiModelProperty(notes = "Birth date", position = 3)
    private Date birthDate;

    @Valid
    @ApiModelProperty(notes = "Collection of singer's albums", position = 4)
    private Set<AlbumView> albums = new HashSet<>();

    @Valid
    @ApiModelProperty(notes = "List of musical instruments used by the singer", position = 5)
    private Set<InstrumentView> instruments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<AlbumView> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumView> albums) {
        this.albums = albums;
    }

    public Set<InstrumentView> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<InstrumentView> instruments) {
        this.instruments = instruments;
    }
}
