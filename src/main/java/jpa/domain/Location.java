package jpa.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Entity
@Getter
public class Location
{
    @Id
    @Column(nullable = false)
    @TableGenerator(name = "table_generate_id", table = "generate_id",
            pkColumnValue = "locationId", initialValue = 0, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generate_id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date measTimestamp;

    private double latitude;
    private double longitude;

    public Location() {
        this.createTimestamp = getDate();
    }

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

        this.createTimestamp = getDate();
//        this.date = new Date();
//
//        this.timestamp = new Timestamp(date.getTime());
//        this.time = new Time(date.getTime());


//        System.out.println("date: " + this.date);
//        System.out.println("time: " + this.time);
//        System.out.println("timestamp: " + this.timestamp);
    }

    private Timestamp getDate()
    {
        return new Timestamp(new Date().getTime());
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", createTimestamp=" + createTimestamp +
                ", measTimestamp=" + measTimestamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }




}
