package entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by mihail on 23.10.17.
 */
@Entity
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String url;
    @Column
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getDate() {
        return LocalDate.parse(date);
    }

    public void setDate(LocalDate localDate) {
        this.date = localDate.toString();
    }
}
