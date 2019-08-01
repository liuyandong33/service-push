package build.dream.push.listeners;

import build.dream.common.listeners.BasicServletContextListener;
import build.dream.common.mappers.CommonMapper;
import build.dream.common.utils.MQTTUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class PushServiceServletContextListener extends BasicServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        super.contextInitialized(servletContextEvent);
        super.previousInjectionBean(servletContextEvent.getServletContext(), CommonMapper.class);
        MQTTUtils.mqttConnect();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
