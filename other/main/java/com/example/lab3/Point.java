package com.example.lab3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.*;

import java.io.Serializable;


/**
 * Entity class for tablepoint
 * Contains x, y, r, target, date, time
 */
@SessionScoped
@Entity
@Table(name = "tablepoint")
public class Point implements Serializable {
 //kdghjkjnlj
    /**
     * Default constructor
     * @param x
     * @param y
     * @param r
     * @param target
     * @param date
     * @param time
     * @param id
     */
    public Point(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id", nullable = false)
    private Long id;
    
    @Column(name = "point_x", nullable = false)
    private String x;
    @Column(name = "point_y", nullable = false)
    private Double y;
    @Column(name = "point_r", nullable = false)
    private String r;


    @Column(name = "point_target", nullable = false)
    private String target;
    @Column(name = "point_time_now", nullable = false)
    private String date;
    @Column(name = "point_time", nullable = false)
    private String  time;

    /**
     * @param s
     * @param s1
     * @param number
     */
    public Point(String s, String s1, String number) {
        this.x = s;
        this.y = Double.parseDouble(s1);
        this.r = number;
    }


    /**
     * @return x
     */
    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    /**
     * @return r
     */
    @Override
    public String toString() {
        return "Point{x=" + x + '\'' +
                ", y=" + y +
                '}';
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}