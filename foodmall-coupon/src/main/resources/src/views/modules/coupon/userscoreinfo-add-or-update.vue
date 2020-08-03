<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户编号" prop="uuid">
      <el-input v-model="dataForm.uuid" placeholder="用户编号"></el-input>
    </el-form-item>
    <el-form-item label="当前积分" prop="score">
      <el-input v-model="dataForm.score" placeholder="当前积分"></el-input>
    </el-form-item>
    <el-form-item label="用户等级" prop="level">
      <el-input v-model="dataForm.level" placeholder="用户等级"></el-input>
    </el-form-item>
    <el-form-item label="可享折扣" prop="discount">
      <el-input v-model="dataForm.discount" placeholder="可享折扣"></el-input>
    </el-form-item>
    <el-form-item label="使用状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="使用状态"></el-input>
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
          score: '',
          level: '',
          discount: '',
          status: ''
        },
        dataRule: {
          uuid: [
            { required: true, message: '用户编号不能为空', trigger: 'blur' }
          ],
          score: [
            { required: true, message: '当前积分不能为空', trigger: 'blur' }
          ],
          level: [
            { required: true, message: '用户等级不能为空', trigger: 'blur' }
          ],
          discount: [
            { required: true, message: '可享折扣不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '使用状态不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/coupon/userscoreinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.uuid = data.userScoreInfo.uuid
                this.dataForm.score = data.userScoreInfo.score
                this.dataForm.level = data.userScoreInfo.level
                this.dataForm.discount = data.userScoreInfo.discount
                this.dataForm.status = data.userScoreInfo.status
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
              url: this.$http.adornUrl(`/coupon/userscoreinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'uuid': this.dataForm.uuid,
                'score': this.dataForm.score,
                'level': this.dataForm.level,
                'discount': this.dataForm.discount,
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
