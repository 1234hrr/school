package cn.zwz.talk.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_assignment")
@TableName("a_assignment")
@ApiModel(value = "作业提交")
public class Assignment extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生ID")
    private String stuId;

    @ApiModelProperty(value = "学生")
    private String stuName;

    @ApiModelProperty(value = "作业名称")
    private String title;

    @ApiModelProperty(value = "作业文件")
    private String fileUrl;

    @ApiModelProperty(value = "作业分数")
    private BigDecimal grade;

    @ApiModelProperty(value = "作业日期")
    private String date;
}