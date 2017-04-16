package entity;

import javax.persistence.*;

/**
 * Created by mihail on 16.04.17.
 */
@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String some;

    public String getSome() {
        return some;
    }

    public void setSome(String some) {
        this.some = some;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
