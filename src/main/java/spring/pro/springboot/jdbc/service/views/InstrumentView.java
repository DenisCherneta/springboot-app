package spring.pro.springboot.jdbc.service.views;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InstrumentView implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max = 49)
    @ApiModelProperty(notes = "Instrument title and id")
    private String instrumentId;

    @Valid
    @ApiModelProperty(notes = "List of singers, which playing on that instrument")
    private Set<SingerView> singers = new HashSet<>();

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Set<SingerView> getSingers() {
        return singers;
    }

    public void setSingers(Set<SingerView> singers) {
        this.singers = singers;
    }

    @Override
    public String toString() {
        return "InstrumentView{" +
                "instrumentId='" + instrumentId + '\'' +
                ", singers.count =" + singers.size() +
                '}';
    }
}
