package jpa.domain;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Device {
    @Id
    @TableGenerator(name = "name_GenDevId",
            table = "table_GenDevId",
            valueColumnName = "col_value",
            pkColumnName = "col_id",
            pkColumnValue = "nextDeviceId",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "name_GenDevId")
    private Long id;

    //@Length(min = 14, max =16, message = "The field must be 15 characters")
    private String serialNumber;

    public Device(String serialNumber) {
        this.serialNumber = serialNumber;
    }


}
