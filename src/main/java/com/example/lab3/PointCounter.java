package com.example.lab3;

import javax.faces.bean.ManagedBean;
import javax.management.*;

import jakarta.inject.Inject;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.context.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMBean {
    private long totalPoints = 0;
    private long missedPoints = 0;
    private long consecutiveMisses = 0;

//    @Inject
//    private SimpleAgent simpleAgent;
//
//    public PointCounter(){
//    }
//
//    public void init(@Observes @Initialized(ApplicationScoped.class) Object unused){
//        simpleAgent.registerBean(this, "point counter");
//    }

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
        System.out.println("total" + totalPoints);
        if (totalPoints % 15 == 0) {
            Notification notification = new Notification(
                    "Total dots count is multiple of 15",
                    getClass().getSimpleName(),
                    totalPoints++,
                    "The total count of user-set dots is now multiple of 15!"
            );
            sendNotification(notification);
        }
        if (!isHit) {
            missedPoints++;
            consecutiveMisses++;
            if (consecutiveMisses == 4) {
                Notification notification = new Notification(
                        "Consecutive misses count is 4",
                        getClass().getSimpleName(),
                        consecutiveMisses++,
                        "Consecutive misses count is 4!"
                );
                sendNotification(notification);
                consecutiveMisses = 0;
            }
        } else {
            consecutiveMisses = 0;
        }
    }


    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{AttributeChangeNotification.ATTRIBUTE_CHANGE};
        String name = AttributeChangeNotification.class.getName();
        String description = "Miss notification";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }

}