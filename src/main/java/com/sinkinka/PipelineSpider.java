package com.sinkinka;

import com.cv4j.netdiscovery.core.Spider;
import com.sinkinka.parser.LagouParser;
import com.sinkinka.pipeline.DownloadImage;
import com.sinkinka.pipeline.SaveImage;

/**
 * @author Administrator
 */
public class PipelineSpider{

    public static void main(String[] args) {

        String url = "https://xiaoyuan.lagou.com/";

        Spider.create()
                .name("lagou")
                .url(url)
                .parser(new LagouParser())
                .pipeline(new DownloadImage())    //1. 首先，下载图片到本地目录
                .pipeline(new SaveImage())        //2. 然后，把图片信息存储到数据库
                .run();
    }
}