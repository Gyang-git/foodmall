<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="订单编号 外联订单" prop="orderSn">
      <el-input v-model="dataForm.orderSn" placeholder="订单编号 外联订单"></el-input>
    </el-form-item>
    <el-form-item label="退单编号" prop="returnId">
      <el-input v-model="dataForm.returnId" placeholder="退单编号"></el-input>
    </el-form-item>
    <el-form-item label="退单理由" prop="reason">
      <el-input v-model="dataForm.reason" placeholder="退单理由"></el-input>
    </el-form-item>
    <el-form-item label="退单状态 1->批阅中；2->批准；3->强制驳回；" prop="returnStatus">
      <el-input v-model="dataForm.returnStatus" placeholder="退单状态 1->批阅中；2->批准；3->强制驳回；"></el-input>
    </el-form-item>
    <el-form-item label="驳回理由" prop="refuseReason">
      <el-input v-model="dataForm.refuseReason" placeholder="驳回理由"></el-input>
    </el-form-item>
    <el-form-item label="扣除积分" prop="deduct">
      <el-input v-model="dataForm.deduct" placeholder="扣除积分"></el-input>
    </el-form-item>
    <el-form-item label="启用状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="启用状态"></el-input>
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
          reason: '',
          returnStatus: '',
          refuseReason: '',
          deduct: '',
          status: ''
        },
        dataRule: {
          orderSn: [
            { required: true, message: '订单编号 外联订单不能为空', trigger: 'blur' }
          ],
          returnId: [
            { required: true, message: '退单编号不能为空', trigger: 'blur' }
          ],
          reason: [
            { required: true, message: '退单理由不能为空', trigger: 'blur' }
          ],
          returnStatus: [
            { required: true, message: '退单状态 1->批阅中；2->批准；3->强制驳回；不能为空', trigger: 'blur' }
          ],
          refuseReason: [
            { required: true, message: '驳回理由不能为空', trigger: 'blur' }
          ],
          deduct: [
            { required: true, message: '扣除积分不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '启用状态不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/orderreturnapply/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderSn = data.orderReturnApply.orderSn
                this.dataForm.returnId = data.orderReturnApply.returnId
                this.dataForm.reason = data.orderReturnApply.reason
                this.dataForm.returnStatus = data.orderReturnApply.returnStatus
                this.dataForm.refuseReason = data.orderReturnApply.refuseReason
                this.dataForm.deduct = data.orderReturnApply.deduct
                this.dataForm.status = data.orderReturnApply.status
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
              url: this.$http.adornUrl(`/order/orderreturnapply/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'orderSn': this.dataForm.orderSn,
                'returnId': this.dataForm.returnId,
                'reason': this.dataForm.reason,
                'returnStatus': this.dataForm.returnStatus,
                'refuseReason': this.dataForm.refuseReason,
                'deduct': this.dataForm.deduct,
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
