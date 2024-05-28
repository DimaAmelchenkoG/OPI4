package com.example.lab3;

import jakarta.enterprise.context.ApplicationScoped;

import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class Interval implements IntervalMBean {
    @Override
    public long getInterval() {
        return 1;
    }
}
