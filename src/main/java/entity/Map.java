package entity;

import javax.persistence.*;

/**
 * Created by mihail on 13.10.17.
 */
@Entity
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private int position;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private int secondGo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getSecondGo() {
        return secondGo;
    }

    public void setSecondGo(int secondGo) {
        this.secondGo = secondGo;
    }
}
