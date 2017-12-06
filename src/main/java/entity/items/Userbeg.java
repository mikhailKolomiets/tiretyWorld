package entity.items;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mihail on 18.10.17.
 */
@Entity
public class Userbeg {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private TreeMap<String, Integer> items;

    @Column
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TreeMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(TreeMap<String, Integer> items) {
        this.items = items;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
