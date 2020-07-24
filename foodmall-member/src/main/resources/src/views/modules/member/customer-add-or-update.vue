<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="顾客编号" prop="uuid">
      <el-input v-model="dataForm.uuid" placeholder="顾客编号"></el-input>
    </el-form-item>
    <el-form-item label="用户名" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="用户名" prop="nickname">
      <el-input v-model="dataForm.nickname" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="手机号码" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="手机号码"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="性别 1->男；2->女" prop="gender">
      <el-input v-model="dataForm.gender" placeholder="性别 1->男；2->女"></el-input>
    </el-form-item>
    <el-form-item label="生日" prop="birthday">
      <el-input v-model="dataForm.birthday" placeholder="生日"></el-input>
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
          uuid: '',
          username: '',
          password: '',
          nickname: '',
          mobile: '',
          email: '',
          gender: '',
          birthday: '',
          updateTime: '',
          status: ''
        },
        dataRule: {
          uuid: [
            { required: true, message: '顾客编号不能为空', trigger: 'blur' }
          ],
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '手机号码不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          gender: [
            { required: true, message: '性别 1->男；2->女不能为空', trigger: 'blur' }
          ],
          birthday: [
            { required: true, message: '生日不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/customer/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.uuid = data.customer.uuid
                this.dataForm.username = data.customer.username
                this.dataForm.password = data.customer.password
                this.dataForm.nickname = data.customer.nickname
                this.dataForm.mobile = data.customer.mobile
                this.dataForm.email = data.customer.email
                this.dataForm.gender = data.customer.gender
                this.dataForm.birthday = data.customer.birthday
                this.dataForm.updateTime = data.customer.updateTime
                this.dataForm.status = data.customer.status
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
              url: this.$http.adornUrl(`/member/customer/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'uuid': this.dataForm.uuid,
                'username': this.dataForm.username,
                'password': this.dataForm.password,
                'nickname': this.dataForm.nickname,
                'mobile': this.dataForm.mobile,
                'email': this.dataForm.email,
                'gender': this.dataForm.gender,
                'birthday': this.dataForm.birthday,
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
