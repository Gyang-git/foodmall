<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="出货物品 外联库存信息表" prop="wareName">
      <el-input v-model="dataForm.wareName" placeholder="出货物品 外联库存信息表"></el-input>
    </el-form-item>
    <el-form-item label="出库数量" prop="outQuantity">
      <el-input v-model="dataForm.outQuantity" placeholder="出库数量"></el-input>
    </el-form-item>
    <el-form-item label="出库申请人名称 外联餐饮负责人" prop="managerName">
      <el-input v-model="dataForm.managerName" placeholder="出库申请人名称 外联餐饮负责人"></el-input>
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
          wareName: '',
          outQuantity: '',
          managerName: '',
          status: ''
        },
        dataRule: {
          wareName: [
            { required: true, message: '出货物品 外联库存信息表不能为空', trigger: 'blur' }
          ],
          outQuantity: [
            { required: true, message: '出库数量不能为空', trigger: 'blur' }
          ],
          managerName: [
            { required: true, message: '出库申请人名称 外联餐饮负责人不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/ware/wareoutinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.wareName = data.wareOutInfo.wareName
                this.dataForm.outQuantity = data.wareOutInfo.outQuantity
                this.dataForm.managerName = data.wareOutInfo.managerName
                this.dataForm.status = data.wareOutInfo.status
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
              url: this.$http.adornUrl(`/ware/wareoutinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'wareName': this.dataForm.wareName,
                'outQuantity': this.dataForm.outQuantity,
                'managerName': this.dataForm.managerName,
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
