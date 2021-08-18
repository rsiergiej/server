package jpa.domain;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
public class Device {
    private static final String patternRegex = "\\d{3}\\-\\d{3}\\-\\d{3}-\\d{3}";

    @Id
    @Column(nullable = false)
    @TableGenerator(name = "name_table_generate",
            table = "table_generate_id",
            valueColumnName = "col_value",
            pkColumnName = "col_id",
            pkColumnValue = "nextDeviceId",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "name_table_generate")
    private Long id;

    @Length(min = 15, max =15, message = "The field must be 15 characters")
    @Column(nullable = false, length = 15)
    @Pattern(regexp = patternRegex, message="Invalid serialNumber pattern")
    private String serialNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id")
    @Setter @Getter private List<Location> locations;



    public Device(String serialNumber)
    {
        if(serialNumber.length() != 15) {
            throw new IllegalArgumentException("serialNumber must have 15 characters: "+ serialNumber);
        }
        if(!serialNumber.matches(patternRegex)) {
            throw new IllegalArgumentException("serialNumber bad regex: "+ serialNumber);
        }
        this.serialNumber = serialNumber;
    }


}
