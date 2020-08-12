package cn.siques.mangosound.config.property;

import com.alibaba.druid.pool.DruidDataSource;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

//@Data
//@Validated
//@ConfigurationProperties(prefix="spring.oss")
public class OssProperty {

    @NotNull private  String endpoint;
    @NotNull private  String accessKeyId;
    @NotNull private  String accessKeySecret;

}
