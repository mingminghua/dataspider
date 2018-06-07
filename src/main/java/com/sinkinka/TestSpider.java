package com.sinkinka;

import com.cv4j.netdiscovery.core.parser.Parser;
import com.cv4j.netdiscovery.core.Spider;
import com.sinkinka.parser.TestParser;

public class TestSpider {
    public static void main(String[] args){

        //目标任务的网页地址，可以拷贝到浏览器区看看
        String url = "http://www.szrc.cn/HrMarket/WLZP/ZP/0/%E6%95%B0%E6%8D%AE";

        //依靠NetDiscovery,我们只需要写一个parset类就可以实现基本到爬虫功能了
        Spider.create()
                .name("spider-1")
                .url(url)
                .parser((Parser) new TestParser())
                .run();

    }


}
