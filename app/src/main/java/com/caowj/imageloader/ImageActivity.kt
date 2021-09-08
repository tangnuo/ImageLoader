package com.caowj.imageloader

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageLoader
import com.caowj.imageloader.cache.ImageCacheManager
import com.caowj.imageloader.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "三级缓存"


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
                    binding.progressLy.visibility = View.GONE
                    if (imageContainer.bitmap != null) {
                        binding.iv.setImageBitmap(imageContainer.bitmap)
                    }
                }

                override fun onErrorResponse(volleyError: VolleyError?) {
                    Log.d("caowj", "onErrorResponse")
                    binding.progressLy.visibility = View.GONE
                }
            })
    }

    private fun loadImage2() {
        //方法二：
        com.caowj.imageloader.cache2.ImageLoader.getInstance(this).display(
            "https://tva2.sinaimg.cn/large/0060lm7Tly1ftg6omusg9j31hc0u010h.jpg",
            binding.iv
        )
        binding.progressLy.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("caowj", "内存回收，需要优化")
    }
}