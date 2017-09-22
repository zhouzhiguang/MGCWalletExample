package example.mgcoin.com.mogenrequest.protocol;


import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.Map;

import example.mgcoin.com.mogenrequest.nohttp.JavaBeanRequest;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


/**
 * 请求网络返回Java实体类
 */

public class BeanRequestProtocol {

    public BeanRequestProtocol() {

    }


    /**
     * @param url           请求地址
     * @param params        请求参数
     * @param requestMethod 请求方式
     * @param modeurl       请求 缓存模式
     * @param clazz         最后解析成的累i
     * @param <T>           泛行
     * @return 观察者
     */
    public <T> Observable<T> createObservable(final String url, final Map<String, Object> params, final RequestMethod requestMethod, final CacheMode modeurl, final Class<T> clazz) {

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception {

                Request request = new JavaBeanRequest(url, requestMethod, clazz);
                request.set(params);
                request.setCacheMode(modeurl);//默认就是DEFAULT，所以这里可以不用设置，DEFAULT代表走Http标准协议。
                Response<T> response = NoHttp.startRequestSync(request);
                if (response.isSucceed()) {
                    T clazz = response.get();
                    setData(emitter, clazz);

                } else {
                    setData(emitter, null);
                }
            }
        }).subscribeOn(Schedulers.io());

    }

    /**
     * 为观察者（订阅者）设置返回数据
     */
    protected <T> void setData(ObservableEmitter<T> emitter, T clazz) {
        if (clazz == null) {
          //  emitter.onError(new Throwable("not data"));
            return;
        }

        emitter.onNext(clazz);
        emitter.onComplete();
    }

}
