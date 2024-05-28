package com.example.lab3;

import jakarta.enterprise.context.ApplicationScoped;

import javax.faces.bean.ManagedBean;
import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@ManagedBean
@ApplicationScoped
public class Interval implements IntervalMBean {
    private long interval;
    private LocalTime start_time;

    @Override
    public long getInterval() {
        return interval;
    }

    @Override
    public void setInterval(long points) {
        interval = start_time.until(LocalTime.now(), ChronoUnit.MILLIS) / points;
    }

    public void setStart(){
        start_time = LocalTime.now();
    }
}
