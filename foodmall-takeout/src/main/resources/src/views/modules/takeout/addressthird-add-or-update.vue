<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="一级分类id 外联外卖地址一级分类" prop="firstId">
      <el-input v-model="dataForm.firstId" placeholder="一级分类id 外联外卖地址一级分类"></el-input>
    </el-form-item>
    <el-form-item label="二级分类id 外联外卖地址二级分类" prop="secondId">
      <el-input v-model="dataForm.secondId" placeholder="二级分类id 外联外卖地址二级分类"></el-input>
    </el-form-item>
    <el-form-item label="三级地址" prop="thirdAddress">
      <el-input v-model="dataForm.thirdAddress" placeholder="三级地址"></el-input>
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
          firstId: '',
          secondId: '',
          thirdAddress: '',
          status: ''
        },
        dataRule: {
          firstId: [
            { required: true, message: '一级分类id 外联外卖地址一级分类不能为空', trigger: 'blur' }
          ],
          secondId: [
            { required: true, message: '二级分类id 外联外卖地址二级分类不能为空', trigger: 'blur' }
          ],
          thirdAddress: [
            { required: true, message: '三级地址不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/takeout/addressthird/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.firstId = data.addressThird.firstId
                this.dataForm.secondId = data.addressThird.secondId
                this.dataForm.thirdAddress = data.addressThird.thirdAddress
                this.dataForm.status = data.addressThird.status
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
              url: this.$http.adornUrl(`/takeout/addressthird/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'firstId': this.dataForm.firstId,
                'secondId': this.dataForm.secondId,
                'thirdAddress': this.dataForm.thirdAddress,
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
