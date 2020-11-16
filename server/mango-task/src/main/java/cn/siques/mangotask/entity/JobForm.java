package cn.siques.mangotask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 定时任务详情
 * </p>
 *
 * @package: com.xkcoding.task.quartz.entity.form
 * @description: 定时任务详情
 * @date: Created in 2018-11-26 13:42
 * @copyright: Copyright (c) 2018
 * @version: V1.0

 */
@Data
@Accessors(chain = true)
public class JobForm  {

    public JobForm(){}

    public JobForm(@NotBlank(message = "类名不能为空") String jobClassName, @NotBlank(message = "任务组名不能为空") String jobGroupName) {
        this.jobClassName = jobClassName;
        this.jobGroupName = jobGroupName;
    }

    /**
     * 定时任务全类名
     */
    @NotBlank(message = "类名不能为空")
    private String jobClassName;
    /**
     * 任务组名
     */
    @NotBlank(message = "任务组名不能为空")
    private String jobGroupName;
    /**
     * 定时任务cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

    private String alias;
}
