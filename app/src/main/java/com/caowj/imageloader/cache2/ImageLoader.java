package com.caowj.imageloader.cache2;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

/**
 * Copyright (c) 2018 All Rights Reserved
 * <p>
 * 作者：马彦虎  Email：1184265546@qq.com
 * 创建时间： 2019/3/18.
 * 修改历史:
 * 修改日期         作者        版本        描述说明
 * </p>
 */
public class ImageLoader {
    public static final String TAG = "ImageLoader------>";
    private static ImageLoader mImageLoader;
    private final Context mContext;
    private final ImageMemoryCache mImageMemoryCache;
    private final ImageFileCache mImageFileCache;
    private final Handler mHandler = new Handler();

    private ImageLoader(Context context) {
        mImageMemoryCache = new ImageMemoryCache(context);
        mImageFileCache = new ImageFileCache(context);
        mContext = context;
    }

    public static ImageLoader getInstance(Context context) {
        if (mImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (mImageLoader == null) {
                    mImageLoader = new ImageLoader(context);
                }
            }
        }
        return mImageLoader;

    }

    public void display(final String url, final ImageView imageView) {
        Bitmap bitmap;
        bitmap = mImageMemoryCache.getBitmapFromCache(url);
        if (bitmap != null) {
            Log.e(TAG, "1、从内存加载");
            imageView.setImageBitmap(bitmap);
            return;
        }
        if (bitmap == null) {
            bitmap = mImageFileCache.getImage(url);
            if (bitmap != null) {
                Log.e(TAG, "2、从文件加载");
                mImageMemoryCache.addBitmapToCache(url, bitmap);
                imageView.setImageBitmap(bitmap);
                return;
            }
            if (bitmap == null) {
                new Thread() {
                    @Override
                    public void run() {
                        final Bitmap bitmap1 = ImageGetFromHttp.downloadBitmap(url, mContext);
                        mImageMemoryCache.addBitmapToCache(url, bitmap1);
//                        mImageFileCache.saveBitmap(bitmap1,url);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                //主线程执行
                                imageView.setImageBitmap(bitmap1);
                                Log.e(TAG, "3、从网络加载");

                            }
                        });
                    }
                }.start();
            }
        }
    }
}
