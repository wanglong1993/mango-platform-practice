package cn.siques.mangosound.entity.dto;

import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangosound.entity.SysSoundfile;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 2 * @Author: HE
 * 3 * @Date: 2020/8/12 0:05
 * 4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPage {

    public  SysSoundfile sysSoundfile;
    public  PageRequest pageRequest;
}
