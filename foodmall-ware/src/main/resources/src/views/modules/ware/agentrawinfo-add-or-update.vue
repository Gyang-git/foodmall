<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="供应商名称 外联供应商" prop="agentName">
      <el-input v-model="dataForm.agentName" placeholder="供应商名称 外联供应商"></el-input>
    </el-form-item>
    <el-form-item label="原料名称" prop="rawName">
      <el-input v-model="dataForm.rawName" placeholder="原料名称"></el-input>
    </el-form-item>
    <el-form-item label="计量单位" prop="unitOfMeasurement">
      <el-input v-model="dataForm.unitOfMeasurement" placeholder="计量单位"></el-input>
    </el-form-item>
    <el-form-item label="单价" prop="price">
      <el-input v-model="dataForm.price" placeholder="单价"></el-input>
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
          rawName: '',
          unitOfMeasurement: '',
          price: '',
          status: ''
        },
        dataRule: {
          agentName: [
            { required: true, message: '供应商名称 外联供应商不能为空', trigger: 'blur' }
          ],
          rawName: [
            { required: true, message: '原料名称不能为空', trigger: 'blur' }
          ],
          unitOfMeasurement: [
            { required: true, message: '计量单位不能为空', trigger: 'blur' }
          ],
          price: [
            { required: true, message: '单价不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/ware/agentrawinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.agentName = data.agentRawInfo.agentName
                this.dataForm.rawName = data.agentRawInfo.rawName
                this.dataForm.unitOfMeasurement = data.agentRawInfo.unitOfMeasurement
                this.dataForm.price = data.agentRawInfo.price
                this.dataForm.status = data.agentRawInfo.status
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
              url: this.$http.adornUrl(`/ware/agentrawinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'agentName': this.dataForm.agentName,
                'rawName': this.dataForm.rawName,
                'unitOfMeasurement': this.dataForm.unitOfMeasurement,
                'price': this.dataForm.price,
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
