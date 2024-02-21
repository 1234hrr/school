<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">编辑</div>
                <span></span>
                <a @click="close" class="window-close">
                    <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                </a>
            </div>
        </div>
        <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
            <FormItem label="学生" prop="stuId">
                <Select v-model="form.stuId" clearable placeholder="请选择学生..." style="width:570px">
                    <Option v-for="(item,index) in studentList" :key="index" :value="item.id">{{ item.name }}</Option>
                </Select>
            </FormItem>
            <FormItem label="作业名称" prop="title">
                <Input v-model="form.title" clearable maxlength="240" show-word-limit placeholder="请输入作业名称..." style="width:570px" />
            </FormItem>
            <FormItem label="作业文件" prop="fileUrl">
                <upload-pic-input v-model="form.fileUrl" placeholder="请上传作业文件..." style="width:570px"></upload-pic-input>
            </FormItem>
            <FormItem label="作业分数" prop="grade">
                <InputNumber v-model="form.grade" min="0" max="100" placeholder="请输入作业分数..." style="width:570px"></InputNumber>
            </FormItem>
            <FormItem label="作业日期" prop="date">
                <Input v-model="form.date" readonly style="width:570px" />
            </FormItem>
            <Form-item class="br">
                <Button @click="handleSubmit" :loading="submitLoading" type="primary">提交并保存</Button>
                <Button @click="handleReset">重置</Button>
                <Button type="dashed" @click="close">关闭</Button>
            </Form-item>
        </Form>
    </Card>
</div>
</template>

<script>
import {
    editAssignment
} from "./api.js";
import uploadPicInput from "@/views/template/upload-file-input";
export default {
    name: "edit",
    components: {
        uploadPicInput,
    },
    props: {
        data: Object
    },
    data() {
        return {
            submitLoading: false, // 表单提交状态
            form: { // 添加或编辑表单对象初始化数据
                stuId: "",
                stuName: "",
                title: "",
                fileUrl: "",
                grade: 0,
                date: "",
            },
            // 表单验证规则
            formValidate: {}
        };
    },
    methods: {
        init() {
            this.handleReset();
            this.form = this.data;
        },
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    editAssignment(this.form).then(res => {
                        this.submitLoading = false;
                        if (res.success) {
                            this.$Message.success("操作成功");
                            this.submited();
                        }
                    });
                }
            });
        },
        close() {
            this.$emit("close", true);
        },
        submited() {
            this.$emit("submited", true);
        }
    },
    mounted() {
        this.init();
    }
};
</script>

<style lang="less">
// 建议引入通用样式 具体路径自行修改 可删除下面样式代码
// @import "../../../styles/single-common.less";
.edit-head {
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;

    .back-title {
        color: #515a6e;
        display: flex;
        align-items: center;
    }

    .head-name {
        display: inline-block;
        height: 20px;
        line-height: 20px;
        font-size: 16px;
        color: #17233d;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .window-close {
        z-index: 1;
        font-size: 12px;
        position: absolute;
        right: 0px;
        top: -5px;
        overflow: hidden;
        cursor: pointer;

        .ivu-icon-ios-close {
            color: #999;
            transition: color .2s ease;
        }
    }

    .window-close .ivu-icon-ios-close:hover {
        color: #444;
    }
}
</style>
