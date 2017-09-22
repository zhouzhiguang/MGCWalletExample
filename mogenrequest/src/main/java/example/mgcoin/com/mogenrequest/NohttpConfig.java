package example.mgcoin.com.mogenrequest;


import com.yanzhenjie.nohttp.rest.CacheMode;

/**
 * http 配置信息
 */

public class NohttpConfig {
    //链接超时时间
    public static final int CONNECT_TIME_OUT = 10 * 1000;
    //服务器响应超时时间
    public static final int READ_TIME_OUT = 15 * 1000;
    //默认是显示log是
    public static final boolean IS_SHOWN_NOHTTP_LOG = true;

    //logcat 信息头
    public static final String SHOWN_NOHTTP_LOG_TAG = "网络请求信息";
    //缓存策略包含5种缓存
    //默认
    public static final CacheMode NOHTTP_CACHEMODE_DEFAULT = CacheMode.DEFAULT;
    //请求网络失败后返回上次的缓存设置为REQUEST_NETWORK_FAILED_READ_CACHE表示请求服务器失败，就返回上次的缓存，如果缓存为空才会请求失败。
    public static final CacheMode NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE = CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE;
    //没有缓存才去请求网络。置为NONE_CACHE_REQUEST_NETWORK表示先去读缓存，如果没有缓存才请求服务器。
    public static final CacheMode NOHTTP_CACHEMODE_NONE_CACHE_REQUEST_NETWORK = CacheMode.NONE_CACHE_REQUEST_NETWORK;
    //仅仅请求缓存。ONLY_READ_CACHE表示仅仅读取缓存，无论如何都不会请求网络。
    public static final CacheMode NOHTTP_CACHEMODE_ONLY_READ_CACHE = CacheMode.ONLY_READ_CACHE;
    //只请求网络ONLY_REQUEST_NETWORK表示仅仅请求网络，不会读取缓存，但是数据可能被缓存
    public static final CacheMode NOHTTP_CACHEMODE_ONLY_REQUEST_NETWORK = CacheMode.ONLY_REQUEST_NETWORK;
}
