package example.mgcoin.com.mgcwalletexample.framework;

import android.app.Application;

import com.threshold.rxbus2.RxBus;

import example.mgcoin.com.mogenrequest.NohttpClient;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/9/22.
 */

public class MogenWalletApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化网络请求
        NohttpClient.init(this);
        //这里rxbus 回调在主线程里面

        RxBus.setMainScheduler(AndroidSchedulers.mainThread());
    }
}
