package entity.items;

import javax.persistence.*;

/**
 * Created by mihail on 15.10.17.
 */
@Entity
public class Culture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private int timeRisePerSecond;

    @Column
    private int baseRise;

    @Column
    private String sort;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBaseRise(int baseRise) {
        this.baseRise = baseRise;
    }

    public int getBaseRise() {
        return baseRise;
    }

    public int getTimeRisePerSecond() {
        return timeRisePerSecond;
    }

    public void setTimeRisePerSecond(int timeRisePerSecond) {
        this.timeRisePerSecond = timeRisePerSecond;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
