package entity;

import javax.persistence.*;

/**
 * Created by mihail on 23.10.17.
 */
@Entity
public class Professions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private long id;
    @Column private long userId;
    @Column private int agronom;
    @Column private int woodmen;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setAgronom(int agronom) {
        this.agronom = agronom;
    }

    public int getAgronom() {
        return agronom;
    }

    public void setWoodmen(int woodmen) {
        this.woodmen = woodmen;
    }

    public int getWoodmen() {
        return woodmen;
    }

}
