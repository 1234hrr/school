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
@Table(name = "a_performance")
@TableName("a_performance")
@ApiModel(value = "课堂表现")
public class Performance extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生ID")
    private String stuId;

    @ApiModelProperty(value = "学生")
    private String stuName;

    @ApiModelProperty(value = "行为类型")
    private String title;

    @ApiModelProperty(value = "行为详情")
    private String content;

    @ApiModelProperty(value = "行为日期")
    private String date;
}