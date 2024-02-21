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

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_news")
@TableName("a_news")
@ApiModel(value = "校园通知")
public class News extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通知标题")
    private String title;

    @ApiModelProperty(value = "通知内容")
    private String content;

    @ApiModelProperty(value = "发布状态")
    private String status;

    @ApiModelProperty(value = "附图")
    private String image;
}