package com.nhz.mycode.completed.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

public class MyBloomFilter {
    public static void main(String[] args) {
        BloomFilter bloomFilter=BloomFilter.create
                (Funnels.stringFunnel(Charset.defaultCharset()),1000000,0.001); //1%，有个概率问题，布隆越大，占用的空间越多，但是错误概率减小了
        bloomFilter.put("ma");
        System.out.println(bloomFilter.mightContain("ma"));//为true表示在布隆过滤器里
        System.out.println(bloomFilter.mightContain("2"));//为true表示在布隆过滤器里
    }
}
