<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="厨师id" prop="chefId">
      <el-input v-model="dataForm.chefId" placeholder="厨师id"></el-input>
    </el-form-item>
    <el-form-item label="厨师体温" prop="chefTem">
      <el-input v-model="dataForm.chefTem" placeholder="厨师体温"></el-input>
    </el-form-item>
    <el-form-item label="厨师健康情况 0->健康;1->感冒;2->咳嗽;3->发烧;" prop="chefHeal">
      <el-input v-model="dataForm.chefHeal" placeholder="厨师健康情况 0->健康;1->感冒;2->咳嗽;3->发烧;"></el-input>
    </el-form-item>
    <el-form-item label="包装员id" prop="packerId">
      <el-input v-model="dataForm.packerId" placeholder="包装员id"></el-input>
    </el-form-item>
    <el-form-item label="包装员体温" prop="packerTem">
      <el-input v-model="dataForm.packerTem" placeholder="包装员体温"></el-input>
    </el-form-item>
    <el-form-item label="包装员健康情况" prop="packerHeal">
      <el-input v-model="dataForm.packerHeal" placeholder="包装员健康情况"></el-input>
    </el-form-item>
    <el-form-item label="配送员id" prop="takemanId">
      <el-input v-model="dataForm.takemanId" placeholder="配送员id"></el-input>
    </el-form-item>
    <el-form-item label="配送员体温" prop="takemanTem">
      <el-input v-model="dataForm.takemanTem" placeholder="配送员体温"></el-input>
    </el-form-item>
    <el-form-item label="配送员健康情况" prop="takemanHeal">
      <el-input v-model="dataForm.takemanHeal" placeholder="配送员健康情况"></el-input>
    </el-form-item>
    <el-form-item label="上报时间" prop="updatetime">
      <el-input v-model="dataForm.updatetime" placeholder="上报时间"></el-input>
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
          chefId: '',
          chefTem: '',
          chefHeal: '',
          packerId: '',
          packerTem: '',
          packerHeal: '',
          takemanId: '',
          takemanTem: '',
          takemanHeal: '',
          updatetime: '',
          status: ''
        },
        dataRule: {
          chefId: [
            { required: true, message: '厨师id不能为空', trigger: 'blur' }
          ],
          chefTem: [
            { required: true, message: '厨师体温不能为空', trigger: 'blur' }
          ],
          chefHeal: [
            { required: true, message: '厨师健康情况 0->健康;1->感冒;2->咳嗽;3->发烧;不能为空', trigger: 'blur' }
          ],
          packerId: [
            { required: true, message: '包装员id不能为空', trigger: 'blur' }
          ],
          packerTem: [
            { required: true, message: '包装员体温不能为空', trigger: 'blur' }
          ],
          packerHeal: [
            { required: true, message: '包装员健康情况不能为空', trigger: 'blur' }
          ],
          takemanId: [
            { required: true, message: '配送员id不能为空', trigger: 'blur' }
          ],
          takemanTem: [
            { required: true, message: '配送员体温不能为空', trigger: 'blur' }
          ],
          takemanHeal: [
            { required: true, message: '配送员健康情况不能为空', trigger: 'blur' }
          ],
          updatetime: [
            { required: true, message: '上报时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/healthmonitoring/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.chefId = data.healthMonitoring.chefId
                this.dataForm.chefTem = data.healthMonitoring.chefTem
                this.dataForm.chefHeal = data.healthMonitoring.chefHeal
                this.dataForm.packerId = data.healthMonitoring.packerId
                this.dataForm.packerTem = data.healthMonitoring.packerTem
                this.dataForm.packerHeal = data.healthMonitoring.packerHeal
                this.dataForm.takemanId = data.healthMonitoring.takemanId
                this.dataForm.takemanTem = data.healthMonitoring.takemanTem
                this.dataForm.takemanHeal = data.healthMonitoring.takemanHeal
                this.dataForm.updatetime = data.healthMonitoring.updatetime
                this.dataForm.status = data.healthMonitoring.status
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
              url: this.$http.adornUrl(`/order/healthmonitoring/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'chefId': this.dataForm.chefId,
                'chefTem': this.dataForm.chefTem,
                'chefHeal': this.dataForm.chefHeal,
                'packerId': this.dataForm.packerId,
                'packerTem': this.dataForm.packerTem,
                'packerHeal': this.dataForm.packerHeal,
                'takemanId': this.dataForm.takemanId,
                'takemanTem': this.dataForm.takemanTem,
                'takemanHeal': this.dataForm.takemanHeal,
                'updatetime': this.dataForm.updatetime,
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
