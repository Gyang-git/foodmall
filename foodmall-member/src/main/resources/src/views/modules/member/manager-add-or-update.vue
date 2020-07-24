<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="饭店id" prop="restaurantId">
      <el-input v-model="dataForm.restaurantId" placeholder="饭店id"></el-input>
    </el-form-item>
    <el-form-item label="昵称" prop="name">
      <el-input v-model="dataForm.name" placeholder="昵称"></el-input>
    </el-form-item>
    <el-form-item label="手机号码" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="手机号码"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="gender">
      <el-input v-model="dataForm.gender" placeholder="性别"></el-input>
    </el-form-item>
    <el-form-item label="企业营业执照图片" prop="busineseImgUrl">
      <el-input v-model="dataForm.busineseImgUrl" placeholder="企业营业执照图片"></el-input>
    </el-form-item>
    <el-form-item label="卫生检测执照图片" prop="sanitationImgUrl">
      <el-input v-model="dataForm.sanitationImgUrl" placeholder="卫生检测执照图片"></el-input>
    </el-form-item>
    <el-form-item label="最近更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="最近更新时间"></el-input>
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
          restaurantId: '',
          name: '',
          mobile: '',
          email: '',
          gender: '',
          busineseImgUrl: '',
          sanitationImgUrl: '',
          updateTime: '',
          status: ''
        },
        dataRule: {
          restaurantId: [
            { required: true, message: '饭店id不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '昵称不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '手机号码不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          gender: [
            { required: true, message: '性别不能为空', trigger: 'blur' }
          ],
          busineseImgUrl: [
            { required: true, message: '企业营业执照图片不能为空', trigger: 'blur' }
          ],
          sanitationImgUrl: [
            { required: true, message: '卫生检测执照图片不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '最近更新时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/manager/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.restaurantId = data.manager.restaurantId
                this.dataForm.name = data.manager.name
                this.dataForm.mobile = data.manager.mobile
                this.dataForm.email = data.manager.email
                this.dataForm.gender = data.manager.gender
                this.dataForm.busineseImgUrl = data.manager.busineseImgUrl
                this.dataForm.sanitationImgUrl = data.manager.sanitationImgUrl
                this.dataForm.updateTime = data.manager.updateTime
                this.dataForm.status = data.manager.status
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
              url: this.$http.adornUrl(`/member/manager/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'restaurantId': this.dataForm.restaurantId,
                'name': this.dataForm.name,
                'mobile': this.dataForm.mobile,
                'email': this.dataForm.email,
                'gender': this.dataForm.gender,
                'busineseImgUrl': this.dataForm.busineseImgUrl,
                'sanitationImgUrl': this.dataForm.sanitationImgUrl,
                'updateTime': this.dataForm.updateTime,
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
