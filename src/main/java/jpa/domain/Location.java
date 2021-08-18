package jpa.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
public class Location
{
    @Id
    @Column(nullable = false)
    @TableGenerator(name = "name_table_generate",
            table = "table_generate_id",
            valueColumnName = "col_value",
            pkColumnName = "col_id",
            pkColumnValue = "nextLocationId",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "name_table_generate")
    private Long id;

    private double latitude;
    private double longitude;


    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
