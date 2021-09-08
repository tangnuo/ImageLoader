

## 简易图片加载框架：

### cache1

- 1、三级缓存：LruCache + DiskLruCache + Volley
- 2、借助Volley框架中的ImageLoader加载网络图片；

[Volley框架中加载图片的3种方式](https://blog.csdn.net/little_shengsheng/article/details/51324293)
- ImageRequest
- ImageLoader
- NetworkImageView


### cache2
- 1、三级缓存：LruCache + SAF + HttpURLConnection
- 2、内存缓存中使用了强引用和软引用

