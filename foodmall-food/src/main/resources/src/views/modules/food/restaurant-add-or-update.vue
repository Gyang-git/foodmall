<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="饭店名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="饭店名称"></el-input>
    </el-form-item>
    <el-form-item label="负责人名称 外联餐饮负责人" prop="managerName">
      <el-input v-model="dataForm.managerName" placeholder="负责人名称 外联餐饮负责人"></el-input>
    </el-form-item>
    <el-form-item label="饭店月份评级 1-5->表示星级" prop="level">
      <el-input v-model="dataForm.level" placeholder="饭店月份评级 1-5->表示星级"></el-input>
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
          name: '',
          managerName: '',
          level: '',
          status: ''
        },
        dataRule: {
          name: [
            { required: true, message: '饭店名称不能为空', trigger: 'blur' }
          ],
          managerName: [
            { required: true, message: '负责人名称 外联餐饮负责人不能为空', trigger: 'blur' }
          ],
          level: [
            { required: true, message: '饭店月份评级 1-5->表示星级不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/food/restaurant/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.restaurant.name
                this.dataForm.managerName = data.restaurant.managerName
                this.dataForm.level = data.restaurant.level
                this.dataForm.status = data.restaurant.status
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
              url: this.$http.adornUrl(`/food/restaurant/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'managerName': this.dataForm.managerName,
                'level': this.dataForm.level,
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
