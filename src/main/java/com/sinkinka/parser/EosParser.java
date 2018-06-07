package com.sinkinka.parser;

import com.cv4j.netdiscovery.core.domain.Page;
import com.cv4j.netdiscovery.core.parser.Parser;

public class EosParser implements Parser {

    @Override
    public void process(Page page) {

        String xpathStr = "//div[@class='coinprice']/text()";
        String marketPrice = page.getHtml().xpath(xpathStr).get();
        System.out.println("marketPrice=" + marketPrice);

    }
}