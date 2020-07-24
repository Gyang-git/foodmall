<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="单菜品id 外联单菜品" prop="singleId">
      <el-input v-model="dataForm.singleId" placeholder="单菜品id 外联单菜品"></el-input>
    </el-form-item>
    <el-form-item label="套餐名称 外联套餐id" prop="setmealId">
      <el-input v-model="dataForm.setmealId" placeholder="套餐名称 外联套餐id"></el-input>
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
          singleId: '',
          setmealId: '',
          status: ''
        },
        dataRule: {
          singleId: [
            { required: true, message: '单菜品id 外联单菜品不能为空', trigger: 'blur' }
          ],
          setmealId: [
            { required: true, message: '套餐名称 外联套餐id不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/food/singlesetmeal/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.singleId = data.singleSetmeal.singleId
                this.dataForm.setmealId = data.singleSetmeal.setmealId
                this.dataForm.status = data.singleSetmeal.status
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
              url: this.$http.adornUrl(`/food/singlesetmeal/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'singleId': this.dataForm.singleId,
                'setmealId': this.dataForm.setmealId,
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
