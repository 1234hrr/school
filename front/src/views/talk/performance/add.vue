<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">添加</div>
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
            <FormItem label="行为类型" prop="title">
                <Select v-model="form.title" clearable style="width:570px">
                    <Option value="行为类型A">行为类型A</Option>
                    <Option value="行为类型B">行为类型B</Option>
                    <Option value="行为类型C">行为类型C</Option>
                </Select>
            </FormItem>
            <FormItem label="行为详情" prop="content">
                <Input v-model="form.content" clearable type="textarea" :rows="4" maxlength="240" show-word-limit placeholder="请输入行为详情..." style="width:570px" />
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
    addPerformance,
    getMyStudentList
} from "./api.js";
export default {
    name: "add",
    components: {},
    data() {
        return {
            submitLoading: false, // 表单提交状态
            form: { // 添加或编辑表单对象初始化数据
                stuId: "",
                stuName: "",
                title: "",
                content: "",
                date: "",
            },
            // 表单验证规则
            formValidate: {},
            studentList: []
        };
    },
    methods: {
        init() {
            this.getMyStudentListFx();
        },
        getMyStudentListFx() {
            var that = this;
            that.studentList = [];
            getMyStudentList().then(res => {
                if (res.success) {
                    that.studentList = res.result;
                }
            })
        },
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    addPerformance(this.form).then(res => {
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
