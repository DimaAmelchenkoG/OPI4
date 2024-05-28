package com.example.lab3;

import jakarta.enterprise.context.ApplicationScoped;

import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class Area implements AreaMBean {

    @Override
    public Double getArea(float r) {
        double squareArea = Math.pow(r / 2, 2);
        double triangleArea = 0.5 * r * r;
        double circleArea = Math.PI * Math.pow(r / 2, 2) / 2;
        return squareArea + triangleArea + circleArea;
    }
}
