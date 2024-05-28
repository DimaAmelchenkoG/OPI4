package com.example.lab3;

import javax.faces.bean.ManagedBean;
import javax.management.*;
import jakarta.inject.Inject;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.context.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMBean, NotificationEmitter {
    private long totalPoints = 0;
    private long missedPoints = 0;
    private long consecutiveMisses = 0;
    private final NotificationBroadcasterSupport notificationSupport = new NotificationBroadcasterSupport();

    //@Inject
    //private SimpleAgent simpleAgent;

    //public PointCounter(){
    //}

    //public void init(@Observes @Initialized(ApplicationScoped.class) Object unused){
    //    simpleAgent.registerBean(this, "point counter");
    //}

    @Inject
    private Interval interval;

    @Override
    public long getTotalPoints() {
        return totalPoints;
    }

    @Override
    public long getMissedPoints() {
        return missedPoints;
    }

    @Override
    public void addPoint(boolean isHit) {
        if (totalPoints == 0){
            interval.setStart();
        } else {
            interval.setInterval(totalPoints);
        }

        totalPoints++;
        if (totalPoints % 15 == 0){
            Notification notification = new Notification("hits.five.times",
                    getClass().getSimpleName(), totalPoints, "15 hits");
            sendNotification(notification);
        }
        if (!isHit) {
            missedPoints++;
            consecutiveMisses++;
        } else {
            consecutiveMisses = 0;
        }
    }

}