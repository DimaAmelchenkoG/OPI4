package com.example.lab3;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named


@SessionScoped
public class TheirPointCollectionBean implements Serializable {

    private Point TheirNewPoint = new Point();
    private List<Point> points = new ArrayList<Point>();
    public Point getNewPoint(){
       // System.out.println("GET NEW POINT");
        return TheirNewPoint;
    }

    public List<Point> getPoints(){
        return points;
    }
    public void addPoint(){
        long t1 = System.nanoTime();
        PointCreater pointCreater = new PointCreater();
        TheirNewPoint = pointCreater.createPoint(TheirNewPoint, t1);
        CheckValid checkValid = new CheckValid();
        if (checkValid.checkAll(TheirNewPoint) == true) {
            points.add(TheirNewPoint);
        }
        //
        //
         //HibernateUtils.getSessionFactory();
        //HibernateUtils.buildSessionFactory();
        //MyDataBase.getInstance().makeBigAdd(TheirNewPoint);
        ConnectDB connectDB = new ConnectDB();
        connectDB.add(TheirNewPoint);
        TheirNewPoint = new Point();
    }

    private String name = "Tom";
    public String getName(){
        System.out.println("NAME");
        return name;
    }

    private ArrayList<Point> pointArrayList = new ArrayList<>();

    public TheirPointCollectionBean(){}

    public void clean(){
        ConnectDB connectDB = new ConnectDB();
        connectDB.clear();
        points.clear();
    }

    public Point getPoint(int id){
        return pointArrayList.get(id);
    }

    public Integer getLength(){
        return pointArrayList.size();
    }

    public void addPoint1(Point point){
        pointArrayList.add(point);
    }







}
