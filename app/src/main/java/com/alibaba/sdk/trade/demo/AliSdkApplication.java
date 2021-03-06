package com.alibaba.sdk.trade.demo;

import android.app.Application;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.alibaba.baichuan.trade.biz.AlibcTradeBiz;
import com.alibaba.baichuan.trade.common.AlibcTradeCommon;
import com.alibaba.baichuan.trade.common.adapter.ut.AlibcUserTracker;
import com.ut.mini.internal.UTTeamWork;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里百川电商
 * 项目名称：阿里巴巴电商SDK
 * 开发团队：阿里巴巴百川商业化团队
 * 阿里巴巴电商SDK答疑群号：1488705339  2071154343  
 * <p/>
 * 功能说明：全局application
 */
public class AliSdkApplication extends Application {

    public static AliSdkApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        AlibcTradeCommon.turnOnDebug();
        AlibcTradeBiz.turnOnDebug();

        //电商SDK初始化
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(AliSdkApplication.this, "初始化成功", Toast.LENGTH_SHORT).show();

                Map utMap = new HashMap<>();
                utMap.put("debug_api_url","http://muvp.alibaba-inc.com/online/UploadRecords.do");
                utMap.put("debug_key","baichuan_sdk_utDetection");
                UTTeamWork.getInstance().turnOnRealTimeDebug(utMap);
                AlibcUserTracker.getInstance().sendInitHit4DAU("19","3.1.1.100");

            }

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(AliSdkApplication.this, "初始化失败,错误码="+code+" / 错误消息="+msg, Toast.LENGTH_SHORT).show();
            }
        });

    }
}