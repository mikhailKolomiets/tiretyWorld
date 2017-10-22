package entity.locations;

import javax.persistence.*;

/**
 * Created by mihail on 15.10.17.
 */
@Entity
public class Cropland {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private int position;

    @Column
    private String itemName;

    @Column
    private long cultureId;

    @Column
    private String timeDown;

    @Column
    private long userId;

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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTimeDown() {
        return timeDown;
    }

    public void setTimeDown(String timeDown) {
        this.timeDown = timeDown;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setCultureId(long cultureId) {
        this.cultureId = cultureId;
    }

    public long getCultureId() {
        return cultureId;
    }
}
