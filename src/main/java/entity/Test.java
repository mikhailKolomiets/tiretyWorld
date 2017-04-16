package entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by mihail on 16.04.17.
 */
@Entity
public class Test {
    @Column
    private int some;

    public int getSome() {
        return some;
    }

    public void setSome(int some) {
        this.some = some;
    }
}
