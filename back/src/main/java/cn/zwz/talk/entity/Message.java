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
@Table(name = "a_message")
@TableName("a_message")
@ApiModel(value = "家校留言")
public class Message extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "留言内容")
    private String content;

    @ApiModelProperty(value = "留言人ID")
    private String userId;

    @ApiModelProperty(value = "留言人")
    private String userName;

    @ApiModelProperty(value = "留言时间")
    private String time;

    @ApiModelProperty(value = "回复内容")
    private String replyContent;

    @ApiModelProperty(value = "回复人ID")
    private String replyId;

    @ApiModelProperty(value = "回复人")
    private String replyName;

    @ApiModelProperty(value = "回复时间")
    private String replyTime;
}