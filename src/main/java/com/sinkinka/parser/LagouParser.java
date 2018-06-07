package com.sinkinka.parser;

import com.cv4j.netdiscovery.core.domain.Page;
import com.cv4j.netdiscovery.core.domain.ResultItems;
import com.cv4j.netdiscovery.core.parser.Parser;
import com.cv4j.netdiscovery.core.parser.selector.Selectable;
import java.util.List;

public class LagouParser implements Parser {

    @Override
    public void process(Page page) {

        ResultItems resultItems = page.getResultItems();
        List<Selectable> liList = page.getHtml().xpath("//li[@class='nav-logo']").nodes();

        for(Selectable li : liList) {
            String logoUrl = li.xpath("//img/@src").get();
            String companyShortName = li.xpath("//div[@class='company-short-name']/text()").get();
            resultItems.put(companyShortName, logoUrl);
        }

    }
}