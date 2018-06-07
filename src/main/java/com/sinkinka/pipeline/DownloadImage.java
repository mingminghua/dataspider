package com.sinkinka.pipeline;

import com.cv4j.netdiscovery.core.domain.ResultItems;
import com.cv4j.netdiscovery.core.pipeline.Pipeline;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class DownloadImage implements Pipeline {
    @Override
    public void process(ResultItems resultItems) {
        Map<String, Object> map = resultItems.getAll();
        for(String key : map.keySet()) {
            String filePath = "./temp/" + key + ".png";
            saveRemoteImage(map.get(key).toString(), filePath);
        }
    }

    private boolean saveRemoteImage(String imgUrl, String filePath) {
        InputStream in = null;
        OutputStream out = null;
        try {
            URL url = new URL(imgUrl);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);

            in = connection.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            out = new FileOutputStream(filePath);
            while ((len = in.read(bs)) != -1) {
                out.write(bs, 0, len);
            }
        } catch(Exception e) {
            return false;
        } finally {
            try {
                out.flush();
                out.close();
                in.close();
            } catch(IOException e) {
                return false;
            }
        }
        return true;
    }
}