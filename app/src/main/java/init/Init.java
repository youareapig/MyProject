package init;

import android.app.Application;
import android.content.Context;

import com.myproject.R;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import org.xutils.x;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Init extends Application {
    public ImageLoaderConfiguration config;
    private String popImg;
    private String popPrice;

    public void onCreate() {
        popImg="";
        popPrice="";
        super.onCreate();
        initImageLoader(getApplicationContext());
        x.Ext.init(this);
    }

    public void initImageLoader(Context context) {
        config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(1000)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(new DisplayImageOptions.Builder()
                        .cacheInMemory(true)//开启内存缓存
                        .cacheOnDisk(true)//开启磁盘缓存
                        .showImageOnLoading(R.mipmap.loading)//加载过程中显示的图片
                        .showImageOnFail(R.mipmap.fail)//加载失败显示的图片
                        .build()) // default
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }
    public String getPopPrice() {
        return popPrice;
    }

    public void setPopPrice(String popPrice) {
        this.popPrice = popPrice;
    }

    public String getPopImg() {
        return popImg;
    }

    public void setPopImg(String popImg) {
        this.popImg = popImg;
    }

}
