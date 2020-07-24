<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="车辆名称" prop="vehicleName">
      <el-input v-model="dataForm.vehicleName" placeholder="车辆名称"></el-input>
    </el-form-item>
    <el-form-item label="车辆类别" prop="sort">
      <el-input v-model="dataForm.sort" placeholder="车辆类别"></el-input>
    </el-form-item>
    <el-form-item label="车辆价值" prop="value">
      <el-input v-model="dataForm.value" placeholder="车辆价值"></el-input>
    </el-form-item>
    <el-form-item label="购入时间" prop="buyTime">
      <el-input v-model="dataForm.buyTime" placeholder="购入时间"></el-input>
    </el-form-item>
    <el-form-item label="最近维修时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="最近维修时间"></el-input>
    </el-form-item>
    <el-form-item label="最近检修时间" prop="overhaulTime">
      <el-input v-model="dataForm.overhaulTime" placeholder="最近检修时间"></el-input>
    </el-form-item>
    <el-form-item label="当前使用状态" prop="useStatus">
      <el-input v-model="dataForm.useStatus" placeholder="当前使用状态"></el-input>
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
          vehicleName: '',
          sort: '',
          value: '',
          buyTime: '',
          updateTime: '',
          overhaulTime: '',
          useStatus: '',
          status: ''
        },
        dataRule: {
          vehicleName: [
            { required: true, message: '车辆名称不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '车辆类别不能为空', trigger: 'blur' }
          ],
          value: [
            { required: true, message: '车辆价值不能为空', trigger: 'blur' }
          ],
          buyTime: [
            { required: true, message: '购入时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '最近维修时间不能为空', trigger: 'blur' }
          ],
          overhaulTime: [
            { required: true, message: '最近检修时间不能为空', trigger: 'blur' }
          ],
          useStatus: [
            { required: true, message: '当前使用状态不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/takeout/vehicle/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.vehicleName = data.vehicle.vehicleName
                this.dataForm.sort = data.vehicle.sort
                this.dataForm.value = data.vehicle.value
                this.dataForm.buyTime = data.vehicle.buyTime
                this.dataForm.updateTime = data.vehicle.updateTime
                this.dataForm.overhaulTime = data.vehicle.overhaulTime
                this.dataForm.useStatus = data.vehicle.useStatus
                this.dataForm.status = data.vehicle.status
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
              url: this.$http.adornUrl(`/takeout/vehicle/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'vehicleName': this.dataForm.vehicleName,
                'sort': this.dataForm.sort,
                'value': this.dataForm.value,
                'buyTime': this.dataForm.buyTime,
                'updateTime': this.dataForm.updateTime,
                'overhaulTime': this.dataForm.overhaulTime,
                'useStatus': this.dataForm.useStatus,
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
