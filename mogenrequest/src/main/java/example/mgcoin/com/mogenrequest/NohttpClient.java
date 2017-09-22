package example.mgcoin.com.mogenrequest;

import android.content.Context;

import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;


/**
 * 网络请求主框架
 */

public class NohttpClient {
    /**
     * 初始化
     */
    private static final int count = 3;

    public static void init(Context context) {
        InitializationConfig config = InitializationConfig.newBuilder(context)
                // 全局连接服务器超时时间，单位毫秒，默认10s。
                .connectionTimeout(30 * 1000)
                // 全局等待服务器响应超时时间，单位毫秒，默认10s。
                .readTimeout(30 * 1000)
                // 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。
                .cacheStore(
                        // 如果不使用缓存，setEnable(false)禁用。
                        new DBCacheStore(context).setEnable(true)
                )
                // 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现CookieStore接口。
                .cookieStore(
                        // 如果不维护cookie，setEnable(false)禁用。
                        new DBCookieStore(context).setEnable(true)
                )
                // 配置网络层，默认URLConnectionNetworkExecutor，如果想用OkHttp：OkHttpNetworkExecutor。
                // .networkExecutor(new OkHttpNetworkExecutor ())
                // 全局通用Header，add是添加，多次调用add不会覆盖上次add。
                //.addHeader()
                // 全局通用Param，add是添加，多次调用add不会覆盖上次add。
                //.addParam()
//                .sslSocketFactory() // 全局SSLSocketFactory。
//                .hostnameVerifier() // 全局HostnameVerifier。
                .retry(count) // 全局重试次数，配置后每个请求失败都会重试x次。
                .build();
        NoHttp.initialize(config);
        //true 代表打印日志信息
        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。NohttpConfig.IS_SHOWN_NOHTTP_LOG
        Logger.setTag(NohttpConfig.SHOWN_NOHTTP_LOG_TAG);// 设置NoHttp打印Log的tag。NohttpConfig.SHOWN_NOHTTP_LOG_TAG
    }
}
