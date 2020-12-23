package com.qunar.fin;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.joda.time.LocalDateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2019/9/24 10:45
 */
public class GsonTest {

    private static Cache cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    System.out.println("remove" + notification.toString() +".........");
                }
            }).build();


    public static void main(String[] args) {
//        put();
//        Map<String, String> map = new HashMap<>();
//        for (int i = 10; i < 20; i++){
//            map.put("key" + i, "value" + i);
//        }
//        List<String> list = new ArrayList<>(map.keySet());
//        cache.invalidateAll(list);
//        Map<String, String> map1 = cache.asMap();
//        for(String key : map1.keySet()) {
//            System.out.println(key + "--------" + map1.get(key));
//        }

        Date dd=new Date();
        //格式化
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(dd);
        System.out.println(time);
    }

    public static void put(){
        for (int i = 0; i < 50; i++) {
            cache.put("key" + i, "value" + i);
        }
    }
}
