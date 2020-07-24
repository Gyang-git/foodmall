<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="饭店名称 外联饭店" prop="restaurantName">
      <el-input v-model="dataForm.restaurantName" placeholder="饭店名称 外联饭店"></el-input>
    </el-form-item>
    <el-form-item label="套餐名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="套餐名称"></el-input>
    </el-form-item>
    <el-form-item label="库存剩余" prop="quantity">
      <el-input v-model="dataForm.quantity" placeholder="库存剩余"></el-input>
    </el-form-item>
    <el-form-item label="锁定库存" prop="quantityLock">
      <el-input v-model="dataForm.quantityLock" placeholder="锁定库存"></el-input>
    </el-form-item>
    <el-form-item label="图片地址" prop="imgUrl">
      <el-input v-model="dataForm.imgUrl" placeholder="图片地址"></el-input>
    </el-form-item>
    <el-form-item label="上架状态" prop="useStatus">
      <el-input v-model="dataForm.useStatus" placeholder="上架状态"></el-input>
    </el-form-item>
    <el-form-item label="启用状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="启用状态"></el-input>
    </el-form-item>
    <el-form-item label="价格" prop="price">
      <el-input v-model="dataForm.price" placeholder="价格"></el-input>
    </el-form-item>
    <el-form-item label="主展示标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="主展示标题"></el-input>
    </el-form-item>
    <el-form-item label="副展示标题" prop="subtitle">
      <el-input v-model="dataForm.subtitle" placeholder="副展示标题"></el-input>
    </el-form-item>
    <el-form-item label="描述" prop="desc">
      <el-input v-model="dataForm.desc" placeholder="描述"></el-input>
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
          restaurantName: '',
          name: '',
          quantity: '',
          quantityLock: '',
          imgUrl: '',
          useStatus: '',
          status: '',
          price: '',
          title: '',
          subtitle: '',
          desc: ''
        },
        dataRule: {
          restaurantName: [
            { required: true, message: '饭店名称 外联饭店不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '套餐名称不能为空', trigger: 'blur' }
          ],
          quantity: [
            { required: true, message: '库存剩余不能为空', trigger: 'blur' }
          ],
          quantityLock: [
            { required: true, message: '锁定库存不能为空', trigger: 'blur' }
          ],
          imgUrl: [
            { required: true, message: '图片地址不能为空', trigger: 'blur' }
          ],
          useStatus: [
            { required: true, message: '上架状态不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '启用状态不能为空', trigger: 'blur' }
          ],
          price: [
            { required: true, message: '价格不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '主展示标题不能为空', trigger: 'blur' }
          ],
          subtitle: [
            { required: true, message: '副展示标题不能为空', trigger: 'blur' }
          ],
          desc: [
            { required: true, message: '描述不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/food/setmeal/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.restaurantName = data.setmeal.restaurantName
                this.dataForm.name = data.setmeal.name
                this.dataForm.quantity = data.setmeal.quantity
                this.dataForm.quantityLock = data.setmeal.quantityLock
                this.dataForm.imgUrl = data.setmeal.imgUrl
                this.dataForm.useStatus = data.setmeal.useStatus
                this.dataForm.status = data.setmeal.status
                this.dataForm.price = data.setmeal.price
                this.dataForm.title = data.setmeal.title
                this.dataForm.subtitle = data.setmeal.subtitle
                this.dataForm.desc = data.setmeal.desc
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
              url: this.$http.adornUrl(`/food/setmeal/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'restaurantName': this.dataForm.restaurantName,
                'name': this.dataForm.name,
                'quantity': this.dataForm.quantity,
                'quantityLock': this.dataForm.quantityLock,
                'imgUrl': this.dataForm.imgUrl,
                'useStatus': this.dataForm.useStatus,
                'status': this.dataForm.status,
                'price': this.dataForm.price,
                'title': this.dataForm.title,
                'subtitle': this.dataForm.subtitle,
                'desc': this.dataForm.desc
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
