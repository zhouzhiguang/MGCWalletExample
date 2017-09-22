package example.mgcoin.com.mgcwalletexample;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.threshold.rxbus2.RxBus;
import com.threshold.rxbus2.annotation.RxSubscribe;
import com.threshold.rxbus2.util.EventThread;

import example.mgcoin.com.mgcwalletexample.event.TestEvent;
import example.mgcoin.com.mgcwalletexample.framework.activities.BaseActivity;
import example.mgcoin.com.mgcwalletexample.modle.LogingBean;

public class MainTestActivity extends BaseActivity {

    private TextView mTestText;
    private RelativeLayout mActivityMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        initView();
        RxBus.getDefault().register(this);
    }


    @RxSubscribe(observeOnThread = EventThread.MAIN, isSticky = true)
    public void event(TestEvent event) {
        LogingBean bean = event.getBean();
        mTestText.setText(bean.toString());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
    }

    private void initView() {
        mTestText = (TextView) findViewById(R.id.testText);
        mActivityMainLayout = (RelativeLayout) findViewById(R.id.activity_main_layout);
    }
}
