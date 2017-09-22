package example.mgcoin.com.mgcwalletexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.threshold.rxbus2.RxBus;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.HashMap;
import java.util.Map;

import example.mgcoin.com.mgcwalletexample.event.TestEvent;
import example.mgcoin.com.mgcwalletexample.framework.activities.BaseActivity;
import example.mgcoin.com.mgcwalletexample.modle.LogingBean;
import example.mgcoin.com.mogenrequest.NohttpConfig;
import example.mgcoin.com.mogenrequest.protocol.BaseStringProtocol;
import example.mgcoin.com.mogenrequest.protocol.BeanRequestProtocol;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 请求返回string
     */
    private Button mTest;
    /**
     * 请求返回实体类
     */
    private Button mTest1;
    private TextView mResult;
    private LinearLayout mActivityMain;
    /**
     * 请求返回实体类
     */
    private Button mTestRxbus;
    private LogingBean Bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        RxBus.getDefault().register(this);
    }

    private void initView() {
        mTest = (Button) findViewById(R.id.test);
        mTest.setOnClickListener(this);
        mTest1 = (Button) findViewById(R.id.test1);
        mTest1.setOnClickListener(this);
        mResult = (TextView) findViewById(R.id.result);
        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);
        mTestRxbus = (Button) findViewById(R.id.testRxbus);
        mTestRxbus.setOnClickListener(this);
        mResult.setOnClickListener(this);
        mActivityMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test:
                RequestString();
                break;
            case R.id.test1:
                RequestBean();
                break;
            case R.id.testRxbus:
                //在这里测试事件Rxbus
                if (Bean != null) {
                    TestEvent event = new TestEvent();
                    event.setBean(Bean);
                    //这里区分粘性事件 可以接收的到
                    RxBus.getDefault().postSticky(event);
                    //MainTestActivity 接收不到事件
                    // RxBus.getDefault().post(event);
                    Intent intent = new Intent(MainActivity.this, MainTestActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.result:
                break;
            case R.id.activity_main:
                break;
        }
    }

    /**
     * 请求返回实体类了哦
     */
    private void RequestBean() {

        BeanRequestProtocol baseStringProtocol = new BeanRequestProtocol();
        String URL = "http://ly-test-admin.cengfan7.com/Session/login";
        Map<String, Object> params = new HashMap<>();
        params.put("username", "18175861350");
        params.put("password", "123456");
        Observable<LogingBean> observable = baseStringProtocol.createObservable(URL, params, RequestMethod.POST, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE, LogingBean.class);
        observable.compose(MainActivity.this.<LogingBean>bindToLifecycle())    //  (2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogingBean>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // textView.setText(e.getMessage().toString());
                        e.printStackTrace();

                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(LogingBean entity) {
                        if (entity != null) {
                            Log.e("测试 返回", entity.toString());
                            Bean = entity;
                            mResult.setText(entity.toString());
                        }
                    }

                });
    }

    /**
     * 请求网络返回String
     */
    private void RequestString() {
        BaseStringProtocol baseStringProtocol = new BaseStringProtocol();
        String URL = "http://ly-test-admin.cengfan7.com/Session/login";
        Map<String, Object> params = new HashMap<>();
        params.put("username", "18175861350");
        params.put("password", "123456");
        //这里参数第三个是请求方式 NohttpConfig 参数 缓存策略后面有详细注解
        Observable<String> observable = baseStringProtocol.createObservable(URL, params, RequestMethod.POST, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE);
        observable.compose(MainActivity.this.<String>bindToLifecycle())    //  (2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // textView.setText(e.getMessage().toString());
                        e.printStackTrace();

                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(String entity) {
                        if (entity != null) {
                            Log.e("测试返回String", entity);
                            mResult.setText(entity);

                        }

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
    }
}
