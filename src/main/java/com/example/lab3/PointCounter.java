package com.example.lab3;

import javax.faces.bean.ManagedBean;
import javax.management.*;
import jakarta.inject.Inject;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.context.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class PointCounter implements PointCounterMBean, NotificationEmitter {
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
        totalPoints++;
        System.out.println("total" + totalPoints);
        if (totalPoints % 15 == 0){
            sendNotification(new Notification("hits.five.times", this, totalPoints, "15 hits"));
        }
        if (!isHit) {
            missedPoints++;
            consecutiveMisses++;
            if (consecutiveMisses == 4) {
                sendNotification(new Notification("missed.four.times", this, totalPoints, "User missed 4 times in a row"));
                consecutiveMisses = 0;
            }
        } else {
            consecutiveMisses = 0;
        }
    }

    @Override
    public void addNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback) {
        notificationSupport.addNotificationListener(listener, filter, handback);
    }

    @Override
    public void removeNotificationListener(NotificationListener listener) throws ListenerNotFoundException {
        notificationSupport.removeNotificationListener(listener);
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        return new MBeanNotificationInfo[] {
            new MBeanNotificationInfo(new String[] {"missed.four.times"}, Notification.class.getName(), "User missed 4 times in a row")
        };
    }

    private void sendNotification(Notification notification) {
        notificationSupport.sendNotification(notification);
    }

    @Override
    public void removeNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback) throws ListenerNotFoundException {
        notificationSupport.removeNotificationListener(listener, filter, handback);
    }
}