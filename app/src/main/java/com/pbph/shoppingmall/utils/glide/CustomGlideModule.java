package com.pbph.shoppingmall.utils.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by Administrator on 2018/5/15.
 */

public class CustomGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
//      builder
//        .setMemoryCache(MemoryCache memoryCache)
//        .setBitmapPool(BitmapPool bitmapPool)
//        .setDiskCache(DiskCache.Factory diskCacheFactory)
//        .setDiskCacheService(ExecutorService service)
//        .setResizeService(ExecutorService service)
//        .setDecodeFormat(DecodeFormat decodeFormat)

        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context)//自定义为缓存空间大小，缓存位置在内部存储器
                //new ExternalCacheDiskCacheFactory(context )//自定义为缓存空间大小，缓存位置在外部存储器
        );
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // nothing to do here
    }
}
