package com.sinkinka;

import com.cv4j.netdiscovery.core.Spider;
import com.cv4j.netdiscovery.core.SpiderEngine;
import com.cv4j.netdiscovery.extra.downloader.httpclient.HttpClientDownloader;
import com.sinkinka.parser.EosParser;

public class TestSpiderEngine {

    public static void main(String[] args) {
        //爬虫容器引擎
        SpiderEngine engine = SpiderEngine.create();

        //爬虫程序1
        String eosUrl = "https://www.feixiaohao.com/currencies/eos/";
        long periodTime1 = 1000 * 5;
        Spider spider1 = Spider.create()
                .name("EOS")
                .repeatRequest(periodTime1, eosUrl)
                .parser(new EosParser())
                .downloader(new HttpClientDownloader())
                .initialDelay(periodTime1);

        engine.addSpider(spider1);

        System.out.println("");

        //可以增加多个爬虫到容器中,
//        engine.addSpider(spider2);
//        ......

        engine.httpd(8088);     //这一行要注意，通过接口可以获取访问爬虫容器内的状态
        engine.runWithRepeat();
    }
}