<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="原料供应商 外联供应商" prop="agentName">
      <el-input v-model="dataForm.agentName" placeholder="原料供应商 外联供应商"></el-input>
    </el-form-item>
    <el-form-item label="原料名" prop="name">
      <el-input v-model="dataForm.name" placeholder="原料名"></el-input>
    </el-form-item>
    <el-form-item label="库存量" prop="quantity">
      <el-input v-model="dataForm.quantity" placeholder="库存量"></el-input>
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
          agentName: '',
          name: '',
          quantity: '',
          status: ''
        },
        dataRule: {
          agentName: [
            { required: true, message: '原料供应商 外联供应商不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '原料名不能为空', trigger: 'blur' }
          ],
          quantity: [
            { required: true, message: '库存量不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/ware/waresku/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.agentName = data.wareSku.agentName
                this.dataForm.name = data.wareSku.name
                this.dataForm.quantity = data.wareSku.quantity
                this.dataForm.status = data.wareSku.status
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
              url: this.$http.adornUrl(`/ware/waresku/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'agentName': this.dataForm.agentName,
                'name': this.dataForm.name,
                'quantity': this.dataForm.quantity,
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
