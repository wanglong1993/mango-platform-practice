package cn.siques.mango.config;

import cn.siques.mango.controller.dto.DbConfig;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Validated
@ConfigurationProperties(prefix="spring")
public class DbProperty {
   @NotNull private List<DbConfig> multiplesource;
}
