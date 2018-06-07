package com.sinkinka.pipeline;

import com.cv4j.netdiscovery.core.domain.ResultItems;
import com.cv4j.netdiscovery.core.pipeline.Pipeline;
import com.safframework.tony.common.utils.Preconditions;

import java.sql.*;
import java.util.Map;

/**
 * @author Administrator
 */
public class SaveImage implements Pipeline {

    @Override
    public void process(ResultItems resultItems) {
        Map<String, Object> map = resultItems.getAll();
        for(String key : map.keySet()) {
            System.out.println("2"+key);
            saveCompanyInfo(key, map.get(key).toString());
        }
    }

    private boolean saveCompanyInfo(String shortName, String logoUrl) {
        int insertCount = 0;
        Connection conn = getMySqlConnection();
        Statement statement = null;
        if(Preconditions.isNotBlank(conn)) {
            try {
                statement = conn.createStatement();
                String insertSQL = "INSERT INTO company(shortname, logourl) VALUES('"+shortName+"', '"+logoUrl+"')";
                insertCount = statement.executeUpdate(insertSQL);

                statement.close();
                conn.close();
            } catch(SQLException e) {
                return false;
            } finally {
                try{
                    if(statement!=null) {
                        statement.close();
                    }
                }catch(SQLException e){
                }
                try{
                    if(conn!=null) {
                        conn.close();
                    }
                }catch(SQLException e){
                }
            }
        }

        return insertCount > 0;
    }

    //演示代码，不建议用于生产环境
    private Connection getMySqlConnection() {
        //使用的是mysql connector 5
        //数据库：test   账号/密码： root/123456
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/test";
        final String USER = "root";
        final String PASS = "123456";

        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch(SQLException e) {
            return null;
        } catch(Exception e) {
            return null;
        }
        return conn;
    }

}