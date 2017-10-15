package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mihail on 16.04.17.
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private int position;

    @Column
    private int positionToGo;

    @Column
    private String wentTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getPositionToGo() {
        return positionToGo;
    }

    public void setPositionToGo(int positionToGo) {
        this.positionToGo = positionToGo;
    }

    public String getWentTime() {
        return wentTime;
    }

    public void setWentTime(String wentTime) {
        this.wentTime = wentTime;
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User() {

    }

}
