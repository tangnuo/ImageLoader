package com.caowj.imageloader.cache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.caowj.imageloader.MyApplication;

/**
 * Created by yanru.zhang on 16/8/18.
 * Email:yanru.zhang@renren-inc.com
 * <p>
 * Volley需要我们声明一个RequesQueuetManager来维持请求队列，因此，我们首先定义RequesQueuetManager类来管理
 */
public class RequestQueueManager {
    public static RequestQueue mRequestQueue = Volley.newRequestQueue(MyApplication.Companion.getContext());

    public static void addRequest(Request<?> request, Object object) {
        if (object != null) {
            request.setTag(object);
        }
        mRequestQueue.add(request);
    }

    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
