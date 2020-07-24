<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="单菜品名称 外联单菜品" prop="singleName">
      <el-input v-model="dataForm.singleName" placeholder="单菜品名称 外联单菜品"></el-input>
    </el-form-item>
    <el-form-item label="" prop="salty">
      <el-input v-model="dataForm.salty" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="酸 1-5->表示程度" prop="sour">
      <el-input v-model="dataForm.sour" placeholder="酸 1-5->表示程度"></el-input>
    </el-form-item>
    <el-form-item label="苦 1-5->表示程度" prop="bitter">
      <el-input v-model="dataForm.bitter" placeholder="苦 1-5->表示程度"></el-input>
    </el-form-item>
    <el-form-item label="甜 1-5->表示程度" prop="sweet">
      <el-input v-model="dataForm.sweet" placeholder="甜 1-5->表示程度"></el-input>
    </el-form-item>
    <el-form-item label="辣 1-5->表示程度" prop="spicy">
      <el-input v-model="dataForm.spicy" placeholder="辣 1-5->表示程度"></el-input>
    </el-form-item>
    <el-form-item label="油炸 1-5->表示程度" prop="fry">
      <el-input v-model="dataForm.fry" placeholder="油炸 1-5->表示程度"></el-input>
    </el-form-item>
    <el-form-item label="软硬 1-5->表示程度" prop="taste">
      <el-input v-model="dataForm.taste" placeholder="软硬 1-5->表示程度"></el-input>
    </el-form-item>
    <el-form-item label="冷热 1-5->表示程度" prop="cool">
      <el-input v-model="dataForm.cool" placeholder="冷热 1-5->表示程度"></el-input>
    </el-form-item>
    <el-form-item label="鲜品 0->非鲜品；1->鲜品" prop="fresh">
      <el-input v-model="dataForm.fresh" placeholder="鲜品 0->非鲜品；1->鲜品"></el-input>
    </el-form-item>
    <el-form-item label="当前评分" prop="score">
      <el-input v-model="dataForm.score" placeholder="当前评分"></el-input>
    </el-form-item>
    <el-form-item label="菜品分类 外联菜品主分类" prop="categoryName">
      <el-input v-model="dataForm.categoryName" placeholder="菜品分类 外联菜品主分类"></el-input>
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
          singleName: '',
          salty: '',
          sour: '',
          bitter: '',
          sweet: '',
          spicy: '',
          fry: '',
          taste: '',
          cool: '',
          fresh: '',
          score: '',
          categoryName: '',
          status: ''
        },
        dataRule: {
          singleName: [
            { required: true, message: '单菜品名称 外联单菜品不能为空', trigger: 'blur' }
          ],
          salty: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          sour: [
            { required: true, message: '酸 1-5->表示程度不能为空', trigger: 'blur' }
          ],
          bitter: [
            { required: true, message: '苦 1-5->表示程度不能为空', trigger: 'blur' }
          ],
          sweet: [
            { required: true, message: '甜 1-5->表示程度不能为空', trigger: 'blur' }
          ],
          spicy: [
            { required: true, message: '辣 1-5->表示程度不能为空', trigger: 'blur' }
          ],
          fry: [
            { required: true, message: '油炸 1-5->表示程度不能为空', trigger: 'blur' }
          ],
          taste: [
            { required: true, message: '软硬 1-5->表示程度不能为空', trigger: 'blur' }
          ],
          cool: [
            { required: true, message: '冷热 1-5->表示程度不能为空', trigger: 'blur' }
          ],
          fresh: [
            { required: true, message: '鲜品 0->非鲜品；1->鲜品不能为空', trigger: 'blur' }
          ],
          score: [
            { required: true, message: '当前评分不能为空', trigger: 'blur' }
          ],
          categoryName: [
            { required: true, message: '菜品分类 外联菜品主分类不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/food/nature/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.singleName = data.nature.singleName
                this.dataForm.salty = data.nature.salty
                this.dataForm.sour = data.nature.sour
                this.dataForm.bitter = data.nature.bitter
                this.dataForm.sweet = data.nature.sweet
                this.dataForm.spicy = data.nature.spicy
                this.dataForm.fry = data.nature.fry
                this.dataForm.taste = data.nature.taste
                this.dataForm.cool = data.nature.cool
                this.dataForm.fresh = data.nature.fresh
                this.dataForm.score = data.nature.score
                this.dataForm.categoryName = data.nature.categoryName
                this.dataForm.status = data.nature.status
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
              url: this.$http.adornUrl(`/food/nature/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'singleName': this.dataForm.singleName,
                'salty': this.dataForm.salty,
                'sour': this.dataForm.sour,
                'bitter': this.dataForm.bitter,
                'sweet': this.dataForm.sweet,
                'spicy': this.dataForm.spicy,
                'fry': this.dataForm.fry,
                'taste': this.dataForm.taste,
                'cool': this.dataForm.cool,
                'fresh': this.dataForm.fresh,
                'score': this.dataForm.score,
                'categoryName': this.dataForm.categoryName,
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
