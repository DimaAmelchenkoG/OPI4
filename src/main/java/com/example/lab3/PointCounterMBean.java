package com.example.lab3;

public interface PointCounterMBean {
    long getTotalPoints();
    long getMissedPoints();
    void addPoint(boolean isHit);
}