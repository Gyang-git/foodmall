<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="单菜品id" prop="singleId">
      <el-input v-model="dataForm.singleId" placeholder="单菜品id"></el-input>
    </el-form-item>
    <el-form-item label="原料名称 外联库存信息表" prop="rawName">
      <el-input v-model="dataForm.rawName" placeholder="原料名称 外联库存信息表"></el-input>
    </el-form-item>
    <el-form-item label="所需数量" prop="quantity">
      <el-input v-model="dataForm.quantity" placeholder="所需数量"></el-input>
    </el-form-item>
    <el-form-item label="库存状态" prop="stock">
      <el-input v-model="dataForm.stock" placeholder="库存状态"></el-input>
    </el-form-item>
    <el-form-item label="单价" prop="price">
      <el-input v-model="dataForm.price" placeholder="单价"></el-input>
    </el-form-item>
    <el-form-item label="所属代理商" prop="agentName">
      <el-input v-model="dataForm.agentName" placeholder="所属代理商"></el-input>
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
          singleId: '',
          rawName: '',
          quantity: '',
          stock: '',
          price: '',
          agentName: '',
          status: ''
        },
        dataRule: {
          singleId: [
            { required: true, message: '单菜品id不能为空', trigger: 'blur' }
          ],
          rawName: [
            { required: true, message: '原料名称 外联库存信息表不能为空', trigger: 'blur' }
          ],
          quantity: [
            { required: true, message: '所需数量不能为空', trigger: 'blur' }
          ],
          stock: [
            { required: true, message: '库存状态不能为空', trigger: 'blur' }
          ],
          price: [
            { required: true, message: '单价不能为空', trigger: 'blur' }
          ],
          agentName: [
            { required: true, message: '所属代理商不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/food/singleraw/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.singleId = data.singleRaw.singleId
                this.dataForm.rawName = data.singleRaw.rawName
                this.dataForm.quantity = data.singleRaw.quantity
                this.dataForm.stock = data.singleRaw.stock
                this.dataForm.price = data.singleRaw.price
                this.dataForm.agentName = data.singleRaw.agentName
                this.dataForm.status = data.singleRaw.status
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
              url: this.$http.adornUrl(`/food/singleraw/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'singleId': this.dataForm.singleId,
                'rawName': this.dataForm.rawName,
                'quantity': this.dataForm.quantity,
                'stock': this.dataForm.stock,
                'price': this.dataForm.price,
                'agentName': this.dataForm.agentName,
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
