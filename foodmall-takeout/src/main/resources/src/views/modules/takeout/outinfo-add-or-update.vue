<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="派送编号" prop="orderSn">
      <el-input v-model="dataForm.orderSn" placeholder="派送编号"></el-input>
    </el-form-item>
    <el-form-item label="开始派送时间" prop="startTime">
      <el-input v-model="dataForm.startTime" placeholder="开始派送时间"></el-input>
    </el-form-item>
    <el-form-item label="送达派送时间" prop="arriveTime">
      <el-input v-model="dataForm.arriveTime" placeholder="送达派送时间"></el-input>
    </el-form-item>
    <el-form-item label="所用车辆 外联外卖车辆" prop="vehicleId">
      <el-input v-model="dataForm.vehicleId" placeholder="所用车辆 外联外卖车辆"></el-input>
    </el-form-item>
    <el-form-item label="外卖员名字" prop="takemanName">
      <el-input v-model="dataForm.takemanName" placeholder="外卖员名字"></el-input>
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
          orderSn: '',
          startTime: '',
          arriveTime: '',
          vehicleId: '',
          takemanName: '',
          status: ''
        },
        dataRule: {
          orderSn: [
            { required: true, message: '派送编号不能为空', trigger: 'blur' }
          ],
          startTime: [
            { required: true, message: '开始派送时间不能为空', trigger: 'blur' }
          ],
          arriveTime: [
            { required: true, message: '送达派送时间不能为空', trigger: 'blur' }
          ],
          vehicleId: [
            { required: true, message: '所用车辆 外联外卖车辆不能为空', trigger: 'blur' }
          ],
          takemanName: [
            { required: true, message: '外卖员名字不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/takeout/outinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderSn = data.outInfo.orderSn
                this.dataForm.startTime = data.outInfo.startTime
                this.dataForm.arriveTime = data.outInfo.arriveTime
                this.dataForm.vehicleId = data.outInfo.vehicleId
                this.dataForm.takemanName = data.outInfo.takemanName
                this.dataForm.status = data.outInfo.status
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
              url: this.$http.adornUrl(`/takeout/outinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'orderSn': this.dataForm.orderSn,
                'startTime': this.dataForm.startTime,
                'arriveTime': this.dataForm.arriveTime,
                'vehicleId': this.dataForm.vehicleId,
                'takemanName': this.dataForm.takemanName,
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
