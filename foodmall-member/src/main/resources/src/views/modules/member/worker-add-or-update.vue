<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="name">
      <el-input v-model="dataForm.name" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="手机号码" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="手机号码"></el-input>
    </el-form-item>
    <el-form-item label="性别 1->男；2->女" prop="gender">
      <el-input v-model="dataForm.gender" placeholder="性别 1->男；2->女"></el-input>
    </el-form-item>
    <el-form-item label="生日" prop="birthday">
      <el-input v-model="dataForm.birthday" placeholder="生日"></el-input>
    </el-form-item>
    <el-form-item label="" prop="workType">
      <el-input v-model="dataForm.workType" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="实时体温" prop="tem">
      <el-input v-model="dataForm.tem" placeholder="实时体温"></el-input>
    </el-form-item>
    <el-form-item label="健康状况" prop="health">
      <el-input v-model="dataForm.health" placeholder="健康状况"></el-input>
    </el-form-item>
    <el-form-item label="生成时间" prop="saveTime">
      <el-input v-model="dataForm.saveTime" placeholder="生成时间"></el-input>
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
          gender: '',
          birthday: '',
          workType: '',
          tem: '',
          health: '',
          saveTime: '',
          status: ''
        },
        dataRule: {
          name: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '手机号码不能为空', trigger: 'blur' }
          ],
          gender: [
            { required: true, message: '性别 1->男；2->女不能为空', trigger: 'blur' }
          ],
          birthday: [
            { required: true, message: '生日不能为空', trigger: 'blur' }
          ],
          workType: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          tem: [
            { required: true, message: '实时体温不能为空', trigger: 'blur' }
          ],
          health: [
            { required: true, message: '健康状况不能为空', trigger: 'blur' }
          ],
          saveTime: [
            { required: true, message: '生成时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/member/worker/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.worker.name
                this.dataForm.mobile = data.worker.mobile
                this.dataForm.gender = data.worker.gender
                this.dataForm.birthday = data.worker.birthday
                this.dataForm.workType = data.worker.workType
                this.dataForm.tem = data.worker.tem
                this.dataForm.health = data.worker.health
                this.dataForm.saveTime = data.worker.saveTime
                this.dataForm.status = data.worker.status
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
              url: this.$http.adornUrl(`/member/worker/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'mobile': this.dataForm.mobile,
                'gender': this.dataForm.gender,
                'birthday': this.dataForm.birthday,
                'workType': this.dataForm.workType,
                'tem': this.dataForm.tem,
                'health': this.dataForm.health,
                'saveTime': this.dataForm.saveTime,
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
