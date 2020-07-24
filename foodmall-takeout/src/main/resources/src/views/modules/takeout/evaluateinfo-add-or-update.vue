<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="派送信息单 外联外卖派送信息" prop="outId">
      <el-input v-model="dataForm.outId" placeholder="派送信息单 外联外卖派送信息"></el-input>
    </el-form-item>
    <el-form-item label="订单编号" prop="orderSn">
      <el-input v-model="dataForm.orderSn" placeholder="订单编号"></el-input>
    </el-form-item>
    <el-form-item label="反馈菜品 外联菜品特性" prop="natureName">
      <el-input v-model="dataForm.natureName" placeholder="反馈菜品 外联菜品特性"></el-input>
    </el-form-item>
    <el-form-item label="反馈评分" prop="evaluateScore">
      <el-input v-model="dataForm.evaluateScore" placeholder="反馈评分"></el-input>
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
          outId: '',
          orderSn: '',
          natureName: '',
          evaluateScore: '',
          status: ''
        },
        dataRule: {
          outId: [
            { required: true, message: '派送信息单 外联外卖派送信息不能为空', trigger: 'blur' }
          ],
          orderSn: [
            { required: true, message: '订单编号不能为空', trigger: 'blur' }
          ],
          natureName: [
            { required: true, message: '反馈菜品 外联菜品特性不能为空', trigger: 'blur' }
          ],
          evaluateScore: [
            { required: true, message: '反馈评分不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/takeout/evaluateinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.outId = data.evaluateInfo.outId
                this.dataForm.orderSn = data.evaluateInfo.orderSn
                this.dataForm.natureName = data.evaluateInfo.natureName
                this.dataForm.evaluateScore = data.evaluateInfo.evaluateScore
                this.dataForm.status = data.evaluateInfo.status
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
              url: this.$http.adornUrl(`/takeout/evaluateinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'outId': this.dataForm.outId,
                'orderSn': this.dataForm.orderSn,
                'natureName': this.dataForm.natureName,
                'evaluateScore': this.dataForm.evaluateScore,
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
