package com.sinkinka;

import com.cv4j.netdiscovery.core.Spider;
import com.cv4j.netdiscovery.extra.downloader.httpclient.HttpClientDownloader;
import com.sinkinka.parser.EosParser;

public class EosSpider {

    public static void main(String[] args) {

        String eosUrl = "https://www.feixiaohao.com/currencies/eos/";

        long periodTime = 1000 * 600;

        Spider.create()
                .name("EOS")
                .repeatRequest(periodTime, eosUrl)
        //下面这行代码要注意，要设置>=periodTime，想了解具体作用，可以去分析源代码
                .initialDelay(periodTime)
                .parser(new EosParser())
                .downloader(new HttpClientDownloader())
                .run();
    }
}