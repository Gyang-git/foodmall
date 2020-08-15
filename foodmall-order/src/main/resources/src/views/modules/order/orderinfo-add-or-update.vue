<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="订单号" prop="orderSn">
      <el-input v-model="dataForm.orderSn" placeholder="订单号"></el-input>
    </el-form-item>
    <el-form-item label="详情号" prop="takeSn">
      <el-input v-model="dataForm.takeSn" placeholder="详情号"></el-input>
    </el-form-item>
    <el-form-item label="登记时间" prop="time">
      <el-input v-model="dataForm.time" placeholder="登记时间"></el-input>
    </el-form-item>
    <el-form-item label="登记信息" prop="info">
      <el-input v-model="dataForm.info" placeholder="登记信息"></el-input>
    </el-form-item>
    <el-form-item label="所处位置" prop="address">
      <el-input v-model="dataForm.address" placeholder="所处位置"></el-input>
    </el-form-item>
    <el-form-item label="使用状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="使用状态"></el-input>
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
          takeSn: '',
          time: '',
          info: '',
          address: '',
          status: ''
        },
        dataRule: {
          orderSn: [
            { required: true, message: '订单号不能为空', trigger: 'blur' }
          ],
          takeSn: [
            { required: true, message: '详情号不能为空', trigger: 'blur' }
          ],
          time: [
            { required: true, message: '登记时间不能为空', trigger: 'blur' }
          ],
          info: [
            { required: true, message: '登记信息不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '所处位置不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '使用状态不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/orderinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderSn = data.orderInfo.orderSn
                this.dataForm.takeSn = data.orderInfo.takeSn
                this.dataForm.time = data.orderInfo.time
                this.dataForm.info = data.orderInfo.info
                this.dataForm.address = data.orderInfo.address
                this.dataForm.status = data.orderInfo.status
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
              url: this.$http.adornUrl(`/order/orderinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'orderSn': this.dataForm.orderSn,
                'takeSn': this.dataForm.takeSn,
                'time': this.dataForm.time,
                'info': this.dataForm.info,
                'address': this.dataForm.address,
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
