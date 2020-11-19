package cn.central.config;


import cn.central.controller.dto.DbConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Validated
@ConfigurationProperties(prefix="spring")
public class DbProperty {
   @NotNull
   private List<DbConfig> multiplesource;
}
