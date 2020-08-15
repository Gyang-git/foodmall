<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="orderSn">
      <el-input v-model="dataForm.orderSn" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="对应表id" prop="orderId">
      <el-input v-model="dataForm.orderId" placeholder="对应表id"></el-input>
    </el-form-item>
    <el-form-item label="餐品id" prop="foodId">
      <el-input v-model="dataForm.foodId" placeholder="餐品id"></el-input>
    </el-form-item>
    <el-form-item label="餐品类型" prop="foodType">
      <el-input v-model="dataForm.foodType" placeholder="餐品类型"></el-input>
    </el-form-item>
    <el-form-item label="对应数量" prop="count">
      <el-input v-model="dataForm.count" placeholder="对应数量"></el-input>
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
          orderId: '',
          foodId: '',
          foodType: '',
          count: '',
          status: ''
        },
        dataRule: {
          orderSn: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          orderId: [
            { required: true, message: '对应表id不能为空', trigger: 'blur' }
          ],
          foodId: [
            { required: true, message: '餐品id不能为空', trigger: 'blur' }
          ],
          foodType: [
            { required: true, message: '餐品类型不能为空', trigger: 'blur' }
          ],
          count: [
            { required: true, message: '对应数量不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/orderitem/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderSn = data.orderItem.orderSn
                this.dataForm.orderId = data.orderItem.orderId
                this.dataForm.foodId = data.orderItem.foodId
                this.dataForm.foodType = data.orderItem.foodType
                this.dataForm.count = data.orderItem.count
                this.dataForm.status = data.orderItem.status
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
              url: this.$http.adornUrl(`/order/orderitem/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'orderSn': this.dataForm.orderSn,
                'orderId': this.dataForm.orderId,
                'foodId': this.dataForm.foodId,
                'foodType': this.dataForm.foodType,
                'count': this.dataForm.count,
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
