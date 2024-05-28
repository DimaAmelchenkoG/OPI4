package com.example.lab3;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;


@Named
@ApplicationScoped
public class MBeanSessionLocalAgent {

    @Inject
    private PointCounter countMBean;

    @Inject
    private Interval intervalMBean;

    private static final MBeanServer server;

    static {
        server = ManagementFactory.getPlatformMBeanServer();
    }

    @PostConstruct
    public void initAgentCount() {
        ObjectName countmBean;
        ObjectName areamBean;
        try {
            countmBean = new ObjectName("mbeans:name=PointCounterMBean");
            areamBean = new ObjectName("mbeans:name=IntervalMBean");
            if (!server.isRegistered(countmBean)) {
                server.registerMBean(countMBean, countmBean);
            }
            if (!server.isRegistered(areamBean)) {
                server.registerMBean(intervalMBean, areamBean);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void logSimpleAgentStarted() {
        System.out.println("CountMBean.logSimpleAgentStarted");
        System.out.println("IntervalMBean.logSimpleAgentStarted");
    }

    public void startupCount(@Observes @Initialized(ApplicationScoped.class) Object context) {
        MBeanSessionLocalAgent a = new MBeanSessionLocalAgent();
        a.logSimpleAgentStarted();
    }


}