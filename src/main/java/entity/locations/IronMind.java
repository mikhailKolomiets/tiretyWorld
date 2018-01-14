package entity.locations;

import javax.persistence.*;

/**
 * Created by mihail on 16.12.17.
 */
@Entity
public class IronMind {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private int ironOre;
    @Column
    private int silverOre;
    @Column
    private int goldOre;
    @Column
    private int position;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIronOre() {
        return ironOre;
    }

    public void setIronOre(int ironOre) {
        this.ironOre = ironOre;
    }

    public int getSilverOre() {
        return silverOre;
    }

    public void setSilverOre(int silverOre) {
        this.silverOre = silverOre;
    }

    public int getGoldOre() {
        return goldOre;
    }

    public void setGoldOre(int goldOre) {
        this.goldOre = goldOre;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
