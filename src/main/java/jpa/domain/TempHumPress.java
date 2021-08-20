package jpa.domain;


import lombok.Getter;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
public class TempHumPress {
    @Id
    @Column(nullable = false)
    @TableGenerator(name = "table_generate_id", table = "generate_id", pkColumnValue = "humTempPressId",
            initialValue = 0, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generate_id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date measTimestamp;

    private double temperature;
    private double humidity;
    private double pressure;

    public TempHumPress() {
        this.createTimestamp = getDate();
    }

    public TempHumPress(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        this.createTimestamp = getDate();
    }

    private Timestamp getDate()
    {
        return new Timestamp(new Date().getTime());
    }

    @Override
    public String toString() {
        return "TempHumPress{" +
                "id=" + id +
                ", createTimestamp=" + createTimestamp +
                ", measTimestamp=" + measTimestamp +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}
