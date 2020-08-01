package cn.siques.mango.utils;



import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Component
public class DbUtil {


    @Value("${spring.datasource.druid.url}")
     String jdbcUrl;

    @Value("${spring.datasource.druid.username}")
    String username;

    @Value("${spring.datasource.druid.password}")
    String password;

    public HikariDataSource buildDb()  {

        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
