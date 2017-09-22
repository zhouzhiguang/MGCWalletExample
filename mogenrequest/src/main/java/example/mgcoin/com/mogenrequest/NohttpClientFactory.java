package example.mgcoin.com.mogenrequest;

import android.content.Context;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.Map;

/**
 * 网络请求主要工厂
 */

public class NohttpClientFactory {
    public static String METHOD_GET = "GET";
    public static String METHOD_POST = "POST";
    public static String METHOD_PUT = "PUT";
    public static String METHOD_DELETE = "DELETE";
    private static NohttpClientFactory mNohttpClientFactory;
    private Context context;

    public NohttpClientFactory(Context context) {
          this.context=context;
    }

    public static NohttpClientFactory getClient(Context context) {
        if (mNohttpClientFactory == null) {
            mNohttpClientFactory = new NohttpClientFactory(context);
        }
        return mNohttpClientFactory;

    }

    /**
     * 通过http请求的基本信息，创建一个Request对象
     * url 请求地址
     * method 请求方式
     * params 请求参数
     */
    public Request<String> getRequest(String url, Map<String, Object> params, RequestMethod requestMethod, CacheMode mode) {
        Request<String> stringRequest = NoHttp.createStringRequest(url, requestMethod);
        stringRequest.add(params);
        //stringRequest.setCacheKey("CacheKeyDefaultString");// 这里的key是缓存数据的主键，默认是url，使用的时候要保证全局唯一，否则会被其他相同url数据覆盖。
        stringRequest.setCacheMode(mode);//默认就是DEFAULT，所以这里可以不用设置，DEFAULT代表走Http标准协议。
        Response<String> response = NoHttp.startRequestSync(stringRequest);


//        RxNoHttp.request(context, stringRequest, new SimpleSubscriber<Response<String>>() {
//            @Override
//            public void onNext(Response<String> stringResponse) {
//                showMessageDialog(getText(R.string.request_succeed), stringResponse.get());
//            }
//        });
        return stringRequest;

    }
}