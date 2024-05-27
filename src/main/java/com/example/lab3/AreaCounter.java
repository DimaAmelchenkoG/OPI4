package com.example.lab3;

public class AreaCounter implements AreaMBean {
    public Double getArea(float r) {
        double squareArea = Math.pow(r / 2, 2);
        double triangleArea = 0.5 * r * r;
        double circleArea = Math.PI * Math.pow(r / 2, 2) / 2;
        return squareArea + triangleArea + circleArea;
    }
}
