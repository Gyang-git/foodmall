<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="采购数量" prop="inQuantity">
      <el-input v-model="dataForm.inQuantity" placeholder="采购数量"></el-input>
    </el-form-item>
    <el-form-item label="入库时间" prop="time">
      <el-input v-model="dataForm.time" placeholder="入库时间"></el-input>
    </el-form-item>
    <el-form-item label="原料名称 外联供应商供应原料" prop="rawName">
      <el-input v-model="dataForm.rawName" placeholder="原料名称 外联供应商供应原料"></el-input>
    </el-form-item>
    <el-form-item label="防疫标准 1->一级防疫；2->二级防疫；3->三级防疫；4->红色防疫" prop="quarantineStandard">
      <el-input v-model="dataForm.quarantineStandard" placeholder="防疫标准 1->一级防疫；2->二级防疫；3->三级防疫；4->红色防疫"></el-input>
    </el-form-item>
    <el-form-item label="1->生鲜肉；2->冷冻肉；3->密封物；4->野味；5->配料 " prop="sort">
      <el-input v-model="dataForm.sort" placeholder="1->生鲜肉；2->冷冻肉；3->密封物；4->野味；5->配料 "></el-input>
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
          rawId: 0,
          inQuantity: '',
          time: '',
          rawName: '',
          quarantineStandard: '',
          sort: '',
          status: ''
        },
        dataRule: {
          inQuantity: [
            { required: true, message: '采购数量不能为空', trigger: 'blur' }
          ],
          time: [
            { required: true, message: '入库时间不能为空', trigger: 'blur' }
          ],
          rawName: [
            { required: true, message: '原料名称 外联供应商供应原料不能为空', trigger: 'blur' }
          ],
          quarantineStandard: [
            { required: true, message: '防疫标准 1->一级防疫；2->二级防疫；3->三级防疫；4->红色防疫不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '1->生鲜肉；2->冷冻肉；3->密封物；4->野味；5->配料 不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '启用状态不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.rawId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.rawId) {
            this.$http({
              url: this.$http.adornUrl(`/ware/wareininfo/info/${this.dataForm.rawId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.inQuantity = data.wareInInfo.inQuantity
                this.dataForm.time = data.wareInInfo.time
                this.dataForm.rawName = data.wareInInfo.rawName
                this.dataForm.quarantineStandard = data.wareInInfo.quarantineStandard
                this.dataForm.sort = data.wareInInfo.sort
                this.dataForm.status = data.wareInInfo.status
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
              url: this.$http.adornUrl(`/ware/wareininfo/${!this.dataForm.rawId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'rawId': this.dataForm.rawId || undefined,
                'inQuantity': this.dataForm.inQuantity,
                'time': this.dataForm.time,
                'rawName': this.dataForm.rawName,
                'quarantineStandard': this.dataForm.quarantineStandard,
                'sort': this.dataForm.sort,
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
