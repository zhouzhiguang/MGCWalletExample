package example.mgcoin.com.mogenrequest.protocol;


import android.text.TextUtils;
import android.util.Log;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.reactivestreams.Subscriber;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


/**
 * 请求网络返回String 的解析
 */
public class BaseStringProtocol {

    private String result;

    public BaseStringProtocol() {

    }


    /**
     * 创建一个工作在IO线程的被观察者(被订阅者)对象
     *
     * @param url
     * @param method
     * @param params
     */
    public Observable<String> createObservable(final String url, final Map<String, Object> params, final RequestMethod requestMethod, final CacheMode mode) {


        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {


                //这里就是要请求了
                // Request<String> request = NohttpClientFactory.getClient().getRequest(url, params, requestMethod, mode);
//                request.
//                Request request = XgoHttpClient.getClient().getRequest(url, method, params);    //  (3)
//                String json = XgoHttpClient.getClient().execute2String(request);
                Request<String> stringRequest = NoHttp.createStringRequest(url, requestMethod);
                stringRequest.add(params);
                //stringRequest.setCacheKey("CacheKeyDefaultString");// 这里的key是缓存数据的主键，默认是url，使用的时候要保证全局唯一，否则会被其他相同url数据覆盖。
                stringRequest.setCacheMode(mode);//默认就是DEFAULT，所以这里可以不用设置，DEFAULT代表走Http标准协议。
                Response<String> response = NoHttp.startRequestSync(stringRequest);

                if (response.isSucceed()) {
                    result = response.get();
                    setData(emitter, result);

                } else {
                    setData(emitter, null);
                }
            }

        }).subscribeOn(Schedulers.io());
    }


    /**
     * 为观察者（订阅者）设置返回数据
     */
    protected void setData(ObservableEmitter<String> emitter, String json) {
        if (TextUtils.isEmpty(json)) {                          //  (6)
           // emitter.onError(new Throwable("not data"));
            return;
        }
        emitter.onNext(json);                                //  (7)
        emitter.onComplete();
    }
}
