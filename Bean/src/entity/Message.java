package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MSG")
public class Message implements Serializable {
    @Id
    @Column(name = "TIME")
    private Date time;

    @Column(name = "MESSAGE")
    private String message;

    public Message() {
    }

    public Message(Date time, String message) {
        this.time = time;
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
