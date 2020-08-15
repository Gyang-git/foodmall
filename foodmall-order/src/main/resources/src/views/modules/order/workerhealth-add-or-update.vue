<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="员工编号" prop="workId">
      <el-input v-model="dataForm.workId" placeholder="员工编号"></el-input>
    </el-form-item>
    <el-form-item label="员工种类" prop="workType">
      <el-input v-model="dataForm.workType" placeholder="员工种类"></el-input>
    </el-form-item>
    <el-form-item label="姓名" prop="name">
      <el-input v-model="dataForm.name" placeholder="姓名"></el-input>
    </el-form-item>
    <el-form-item label="健康情况" prop="heal">
      <el-input v-model="dataForm.heal" placeholder="健康情况"></el-input>
    </el-form-item>
    <el-form-item label="实时体温" prop="tem">
      <el-input v-model="dataForm.tem" placeholder="实时体温"></el-input>
    </el-form-item>
    <el-form-item label="保存日期" prop="saveDate">
      <el-input v-model="dataForm.saveDate" placeholder="保存日期"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态"></el-input>
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
          workId: '',
          workType: '',
          name: '',
          heal: '',
          tem: '',
          saveDate: '',
          status: ''
        },
        dataRule: {
          workId: [
            { required: true, message: '员工编号不能为空', trigger: 'blur' }
          ],
          workType: [
            { required: true, message: '员工种类不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '姓名不能为空', trigger: 'blur' }
          ],
          heal: [
            { required: true, message: '健康情况不能为空', trigger: 'blur' }
          ],
          tem: [
            { required: true, message: '实时体温不能为空', trigger: 'blur' }
          ],
          saveDate: [
            { required: true, message: '保存日期不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/workerhealth/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.workId = data.workerHealth.workId
                this.dataForm.workType = data.workerHealth.workType
                this.dataForm.name = data.workerHealth.name
                this.dataForm.heal = data.workerHealth.heal
                this.dataForm.tem = data.workerHealth.tem
                this.dataForm.saveDate = data.workerHealth.saveDate
                this.dataForm.status = data.workerHealth.status
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
              url: this.$http.adornUrl(`/order/workerhealth/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'workId': this.dataForm.workId,
                'workType': this.dataForm.workType,
                'name': this.dataForm.name,
                'heal': this.dataForm.heal,
                'tem': this.dataForm.tem,
                'saveDate': this.dataForm.saveDate,
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
