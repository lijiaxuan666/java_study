package Service;

import java.sql.Timestamp;
import java.util.Date;

public class Movie {
    private Integer id;           //电影id
    private String name;          //电影名称
    private String show_time;       //上映时间
    private Integer duration;     //电影时长
    private Double price;         //电影票价格
    private Integer row_seat;     //座位所在行
    private Integer column_seat;  //座位所在列列
    private Integer all_seat=row_seat*column_seat; //电影总座位数
    private Timestamp buy_time;        //购买日期

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShow_time(String show_time) {
        this.show_time = show_time;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRow_seat(Integer row_seat) {
        this.row_seat = row_seat;
    }

    public void setColumn_seat(Integer column_seat) {
        this.column_seat = column_seat;
    }

    public void setAll_seat(Integer all_seat) {
        this.all_seat = all_seat;
    }

    public void setBuy_time(Timestamp buy_time) {
        this.buy_time = buy_time;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShow_time() {
        return show_time;
    }

    public Integer getDuration() {
        return duration;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getRow_seat() {
        return row_seat;
    }

    public Integer getColumn_seat() {
        return column_seat;
    }

    public Integer getAll_seat() {
        return all_seat;
    }

    public Timestamp getBuy_time() {
        return buy_time;
    }
}
