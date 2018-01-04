import app.App;
import client.Client;
import event.Event;
import event.EventType;
import interfaces.IEventLogger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Vasil on 13.09.2016.
 */
public class SimpleTest {

    private Client client;
    private IEventLogger eventlogger;
    private Event event;
    private Map<EventType, IEventLogger> loggers;
    private ConfigurableApplicationContext ctx;

    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Test
    public void testInstanse() {

        //        App app=mock(App.class);
        App app = (App) ctx.getBean("app");
        assert (app instanceof App);
        assertTrue(app instanceof App);
    }

    @Test
    public void testLog() {
        //        App app=mock(App.class);
        App app = (App) ctx.getBean("app");
        app.logEvent(EventType.INFO, "86590");
    }

    @Test

    public void mockTest() {
        App mockApp = mock(App.class);
        System.out.println("Mock test");
        verify(mockApp).logEvent(EventType.ERROR, "12");

    }

}
