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
    private int some;

    public int getSome() {
        return some;
    }

    public void setSome(int some) {
        this.some = some;
    }
}
