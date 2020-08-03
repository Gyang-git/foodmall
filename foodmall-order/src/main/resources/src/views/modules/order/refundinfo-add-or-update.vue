<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="订单编号 外联订单" prop="orderSn">
      <el-input v-model="dataForm.orderSn" placeholder="订单编号 外联订单"></el-input>
    </el-form-item>
    <el-form-item label="退单申请id " prop="returnId">
      <el-input v-model="dataForm.returnId" placeholder="退单申请id "></el-input>
    </el-form-item>
    <el-form-item label=" 启用状态" prop="status">
      <el-input v-model="dataForm.status" placeholder=" 启用状态"></el-input>
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
          orderSn: '',
          returnId: '',
          status: ''
        },
        dataRule: {
          orderSn: [
            { required: true, message: '订单编号 外联订单不能为空', trigger: 'blur' }
          ],
          returnId: [
            { required: true, message: '退单申请id 不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: ' 启用状态不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/refundinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderSn = data.refundInfo.orderSn
                this.dataForm.returnId = data.refundInfo.returnId
                this.dataForm.status = data.refundInfo.status
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
              url: this.$http.adornUrl(`/order/refundinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'orderSn': this.dataForm.orderSn,
                'returnId': this.dataForm.returnId,
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
