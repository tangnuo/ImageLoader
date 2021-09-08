package com.caowj.imageloader

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageLoader
import com.caowj.imageloader.cache.ImageCacheManager

class ImageActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var progressLy: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        title = "三级缓存"
        imageView = findViewById(R.id.iv)
        progressLy = findViewById(R.id.progress_ly)

//        高清壁纸：https://api.ixiaowai.cn/gqapi/gqapi.php
//        https://tva2.sinaimg.cn/large/0060lm7Tly1ftg6omusg9j31hc0u010h.jpg
//        https://tva2.sinaimg.cn/large/87c01ec7gy1frmrz8e3ytj21hc0u0wnn.jpg
//        https://tva4.sinaimg.cn/large/87c01ec7gy1frmrx49dasj21hc0u0wnm.jpg

//        loadImage1()
        loadImage2()
    }

    private fun loadImage1() {
        //方法一：
        ImageCacheManager.loadImage("https://tva4.sinaimg.cn/large/87c01ec7gy1frmr4o35kuj21kw0w0kjy.jpg",
            object : ImageLoader.ImageListener {
                override fun onResponse(imageContainer: ImageLoader.ImageContainer, b: Boolean) {
                    Log.d("caowj", "onResponse")
                    progressLy.visibility = View.GONE
                    if (imageContainer.bitmap != null) {
                        imageView.setImageBitmap(imageContainer.bitmap)
                    }
                }

                override fun onErrorResponse(volleyError: VolleyError?) {
                    Log.d("caowj", "onErrorResponse")
                    progressLy.visibility = View.GONE
                }
            })
    }

    private fun loadImage2() {
        //方法二：
        com.caowj.imageloader.cache2.ImageLoader.getInstance(this).display(
            "https://tva2.sinaimg.cn/large/0060lm7Tly1ftg6omusg9j31hc0u010h.jpg",
            imageView
        )
        progressLy.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("caowj", "内存回收，需要优化")
    }
}