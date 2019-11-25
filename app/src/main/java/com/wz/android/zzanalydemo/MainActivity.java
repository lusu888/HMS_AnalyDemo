package com.wz.android.zzanalydemo;



import android.app.Activity;
import android.os.Bundle;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.analytics.HiAnalytics;
import com.huawei.hms.analytics.HiAnalyticsInstance;
import com.huawei.hms.analytics.HiAnalyticsTools;
import static com.huawei.hms.analytics.type.HAEventType.*;
import static com.huawei.hms.analytics.type.HAParamType.*;


public class MainActivity extends Activity {

    HiAnalyticsInstance instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 打开analytics-kit的调试日志，便于问题定位
         * open analytics-kit logcat，facilitates problem locating.
         */
        HiAnalyticsTools.enableLog();

        /**
         * 初始化Analytics-kit
         * analytics kit init
         */
        instance = HiAnalytics.getInstance(this);
        // set you userid
        instance.setUserId("you userid");
        /**
         * set push token
         */
        instance.setPushToken("you push token");
        /**
         * 设置用户属性
         * set user property
         */
        instance.setUserProperty("propKey", "propValue");
        instance.setUserProperty("propKey2", "propValue2");
        /**
         * 设置session的刷新时间间隔，主要针对应用在后台停留的时长。默认30s
         * Set the refresh interval of the session, mainly for the duration of
         * the application staying in the background.Default 30s
         */
        instance.setMinimumSessionDuration(30 * 1000L);
        /**
         * 设置session的刷新时间间隔，当两个事件的间隔时间超过此阀值时刷新session。
         * Set the session refresh interval to refresh the session when the
         * interval between two events exceeds this threshold.
         */
        instance.setSessionTimeoutDuration(30 * 60 * 1000L);
        /**
         * 设置自动采集的开关，默认打开
         * Set the automatic acquisition switch, which is turned on by default.
         */
        instance.setAutoCollectionEnabled(true);
        /**
         * 设置Analytics-Kit的全局事件采集开关.默认打开
         * Set the global event collection switch for Analytics-Kit.Default open
         * Set this parameter to false when there is no need to collect events.
         */
        instance.setAnalyticsCollectionEnabled(true);
        logEvent();

    }

    /**
     * 在合适的地方使用 instance.logEvent，采集你需要的信息
     * Use the instance.logEvent where appropriate to gather the information you need
     */
    public void logEvent(){
        Bundle bundle = new Bundle();
        // 示例： 采集预定义参数
        // Example: Collect predefined parameters
        bundle.putString(HA_ITEM_ID, "item_ID");
        bundle.putString(HA_ITEM_NAME, "name");
        bundle.putString(HA_ITEM_CATEGORY, "category");
        bundle.putLong(HA_QUANTITY, 100L);
        bundle.putDouble(HA_PRICE, 10.01);
        bundle.putDouble(HA_VALUE, 10);
        bundle.putString(HA_CURRENCY, "currency");
        bundle.putString(HA_ITEM_LOCATION_ID, "location_ID");
        // 采集自定义的事件信息
        // Collect custom event information
        instance.logEvent(HA_ADD_TO_WISHLIST,bundle);
    }

    /**
     * 在合适的地方使用 instance.resetAnalyticsData()
     * Use instance.resetAnalyticsData() where appropriate
     */
    public void clearData(){
        // 清除本地缓存
        // clear cache data
        instance.resetAnalyticsData();
    }
    // get task
    public Task<String> getAppInstanceId(){
        Task<String> task = instance.getAppInstanceId();
        return  task;
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 设置页面事件的自定义页面名称
         * Set a custom page name
         */
        instance.setCurrentScreen(this, "youScreenName", "com.huawei.analyticskitdemo.MainActivity");
    }
}