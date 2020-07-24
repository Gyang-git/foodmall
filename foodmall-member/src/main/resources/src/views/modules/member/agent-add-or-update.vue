<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="供应商" prop="name">
      <el-input v-model="dataForm.name" placeholder="供应商"></el-input>
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
    <el-form-item label="最近更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="最近更新时间"></el-input>
    </el-form-item>
    <el-form-item label="代理级别 1->初级供应；2->中级供应；3->高级供应；4->特级供应" prop="agentLevel">
      <el-input v-model="dataForm.agentLevel" placeholder="代理级别 1->初级供应；2->中级供应；3->高级供应；4->特级供应"></el-input>
    </el-form-item>
    <el-form-item label="供应执照图片地址" prop="agentImgUrl">
      <el-input v-model="dataForm.agentImgUrl" placeholder="供应执照图片地址"></el-input>
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
          mobile: '',
          email: '',
          gender: '',
          updateTime: '',
          agentLevel: '',
          agentImgUrl: '',
          status: ''
        },
        dataRule: {
          name: [
            { required: true, message: '供应商不能为空', trigger: 'blur' }
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
          updateTime: [
            { required: true, message: '最近更新时间不能为空', trigger: 'blur' }
          ],
          agentLevel: [
            { required: true, message: '代理级别 1->初级供应；2->中级供应；3->高级供应；4->特级供应不能为空', trigger: 'blur' }
          ],
          agentImgUrl: [
            { required: true, message: '供应执照图片地址不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/agent/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.agent.name
                this.dataForm.mobile = data.agent.mobile
                this.dataForm.email = data.agent.email
                this.dataForm.gender = data.agent.gender
                this.dataForm.updateTime = data.agent.updateTime
                this.dataForm.agentLevel = data.agent.agentLevel
                this.dataForm.agentImgUrl = data.agent.agentImgUrl
                this.dataForm.status = data.agent.status
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
              url: this.$http.adornUrl(`/member/agent/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'mobile': this.dataForm.mobile,
                'email': this.dataForm.email,
                'gender': this.dataForm.gender,
                'updateTime': this.dataForm.updateTime,
                'agentLevel': this.dataForm.agentLevel,
                'agentImgUrl': this.dataForm.agentImgUrl,
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
