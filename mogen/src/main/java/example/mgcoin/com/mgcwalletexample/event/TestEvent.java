package example.mgcoin.com.mgcwalletexample.event;

import example.mgcoin.com.mgcwalletexample.modle.LogingBean;

/**
 * 事件传递类
 */

public class TestEvent {
    private LogingBean bean;

    public TestEvent() {
    }

    public LogingBean getBean() {
        return bean;
    }

    public void setBean(LogingBean bean) {
        this.bean = bean;
    }
}
