package springpack.event;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat dateFormat) {
        this.id = new Random().nextInt(10)+1;
        this.date = date;
        this.df = dateFormat;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Event:" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                "\n}";
    }
}
