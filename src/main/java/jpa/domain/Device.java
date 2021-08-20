package jpa.domain;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Entity
@Getter
public class Device {
    private static final String patternRegex = "\\d{3}\\-\\d{3}\\-\\d{3}-\\d{3}";

    @Id
    @Column(nullable = false)
    @TableGenerator(name = "table_generate_id", table = "generate_id", pkColumnValue = "deviceId",
            initialValue = 0, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generate_id")
    private Long id;

    @Length(min = 15, max =15, message = "The field must be 15 characters")
    @Column(nullable = false, length = 15)
    @Pattern(regexp = patternRegex, message="Invalid serialNumber pattern")
    private String serialNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id")
    @Setter private List<Location> locations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id")
    @Setter private List<TempHumPress> tempHumPress;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createTimestamp;



    public Device()
    {
        this.createTimestamp = new Timestamp(new Date().getTime());
    }

    public Device(String serialNumber)
    {
        if(serialNumber.length() != 15) {
            throw new IllegalArgumentException("serialNumber must have 15 characters: " + serialNumber);
        }
        if(!serialNumber.matches(patternRegex)) {
            throw new IllegalArgumentException("serialNumber bad regex: "+ serialNumber);
        }
        this.serialNumber = serialNumber;
        this.createTimestamp = new Timestamp(new Date().getTime());
    }

    public boolean checkSerialNumber()
    {
        return this.serialNumber.matches(this.patternRegex);
    }


    public String toString() {
        return "Device{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", locations=" + locations.size() +
                ", tempHumPress=" + tempHumPress.size() +
                ", createTimestamp=" + createTimestamp +
                '}';
    }


    public String toStringLocation() {
        return "Device{" +
                "locations=" + locations +
                '}';
    }


    public String toStringHumTempPress() {
        return "Device{" +
                "locations=" + tempHumPress +
                '}';
    }
}
