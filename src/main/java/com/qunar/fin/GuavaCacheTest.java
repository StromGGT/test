package com.qunar.fin;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/1/6 15:39
 */
public class GuavaCacheTest {

    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .build();
        //向缓存中添加 数据 K V 形式
        cache.put("hello","where are you");
        cache.put("hehe","where are you");

        System.out.println(cache.asMap());
        List<String> list = Lists.newArrayList("hi", "hello", "ha");
        cache.invalidateAll(list);
        System.out.println(cache.asMap());
    }
}
