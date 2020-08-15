<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="member_id" prop="uuif">
      <el-input v-model="dataForm.uuif" placeholder="member_id"></el-input>
    </el-form-item>
    <el-form-item label="food_id" prop="foodId">
      <el-input v-model="dataForm.foodId" placeholder="food_id"></el-input>
    </el-form-item>
    <el-form-item label="活动场次id" prop="sessionId">
      <el-input v-model="dataForm.sessionId" placeholder="活动场次id"></el-input>
    </el-form-item>
    <el-form-item label="订阅时间" prop="subcribeTime">
      <el-input v-model="dataForm.subcribeTime" placeholder="订阅时间"></el-input>
    </el-form-item>
    <el-form-item label="发送时间" prop="sendTime">
      <el-input v-model="dataForm.sendTime" placeholder="发送时间"></el-input>
    </el-form-item>
    <el-form-item label="通知方式[0-短信，1-邮件]" prop="noticeType">
      <el-input v-model="dataForm.noticeType" placeholder="通知方式[0-短信，1-邮件]"></el-input>
    </el-form-item>
    <el-form-item label="" prop="status">
      <el-input v-model="dataForm.status" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          uuif: '',
          foodId: '',
          sessionId: '',
          subcribeTime: '',
          sendTime: '',
          noticeType: '',
          status: ''
        },
        dataRule: {
          uuif: [
            { required: true, message: 'member_id不能为空', trigger: 'blur' }
          ],
          foodId: [
            { required: true, message: 'food_id不能为空', trigger: 'blur' }
          ],
          sessionId: [
            { required: true, message: '活动场次id不能为空', trigger: 'blur' }
          ],
          subcribeTime: [
            { required: true, message: '订阅时间不能为空', trigger: 'blur' }
          ],
          sendTime: [
            { required: true, message: '发送时间不能为空', trigger: 'blur' }
          ],
          noticeType: [
            { required: true, message: '通知方式[0-短信，1-邮件]不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/coupon/seckillfoodnotice/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.uuif = data.seckillFoodNotice.uuif
                this.dataForm.foodId = data.seckillFoodNotice.foodId
                this.dataForm.sessionId = data.seckillFoodNotice.sessionId
                this.dataForm.subcribeTime = data.seckillFoodNotice.subcribeTime
                this.dataForm.sendTime = data.seckillFoodNotice.sendTime
                this.dataForm.noticeType = data.seckillFoodNotice.noticeType
                this.dataForm.status = data.seckillFoodNotice.status
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/coupon/seckillfoodnotice/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'uuif': this.dataForm.uuif,
                'foodId': this.dataForm.foodId,
                'sessionId': this.dataForm.sessionId,
                'subcribeTime': this.dataForm.subcribeTime,
                'sendTime': this.dataForm.sendTime,
                'noticeType': this.dataForm.noticeType,
                'status': this.dataForm.status
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
