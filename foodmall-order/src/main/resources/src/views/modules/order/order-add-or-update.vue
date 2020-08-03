<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="订单编号" prop="orderSn">
      <el-input v-model="dataForm.orderSn" placeholder="订单编号"></el-input>
    </el-form-item>
    <el-form-item label="派送编号 外联外卖派送信息" prop="takeoutId">
      <el-input v-model="dataForm.takeoutId" placeholder="派送编号 外联外卖派送信息"></el-input>
    </el-form-item>
    <el-form-item label="健康监控id" prop="healthId">
      <el-input v-model="dataForm.healthId" placeholder="健康监控id"></el-input>
    </el-form-item>
    <el-form-item label="订单状态 1->待付款；2->待派送；3->派送中；4->待反馈；5->异常" prop="orderStatus">
      <el-input v-model="dataForm.orderStatus" placeholder="订单状态 1->待付款；2->待派送；3->派送中；4->待反馈；5->异常"></el-input>
    </el-form-item>
    <el-form-item label="下单顾客id 外联顾客" prop="customerId">
      <el-input v-model="dataForm.customerId" placeholder="下单顾客id 外联顾客"></el-input>
    </el-form-item>
    <el-form-item label="接收人姓名" prop="takeName">
      <el-input v-model="dataForm.takeName" placeholder="接收人姓名"></el-input>
    </el-form-item>
    <el-form-item label="接收人电话" prop="takePhone">
      <el-input v-model="dataForm.takePhone" placeholder="接收人电话"></el-input>
    </el-form-item>
    <el-form-item label="订单总额" prop="totalAmout">
      <el-input v-model="dataForm.totalAmout" placeholder="订单总额"></el-input>
    </el-form-item>
    <el-form-item label="实付金额" prop="payAmout">
      <el-input v-model="dataForm.payAmout" placeholder="实付金额"></el-input>
    </el-form-item>
    <el-form-item label="优惠金额" prop="discountAmout">
      <el-input v-model="dataForm.discountAmout" placeholder="优惠金额"></el-input>
    </el-form-item>
    <el-form-item label="外卖费用" prop="freightAmout">
      <el-input v-model="dataForm.freightAmout" placeholder="外卖费用"></el-input>
    </el-form-item>
    <el-form-item label="支付方式 1->支付宝；2->微信；3->校园一卡通；4->送达付款" prop="payWay">
      <el-input v-model="dataForm.payWay" placeholder="支付方式 1->支付宝；2->微信；3->校园一卡通；4->送达付款"></el-input>
    </el-form-item>
    <el-form-item label="地址一级分类" prop="addressFirst">
      <el-input v-model="dataForm.addressFirst" placeholder="地址一级分类"></el-input>
    </el-form-item>
    <el-form-item label="地址二级分类" prop="addressSecond">
      <el-input v-model="dataForm.addressSecond" placeholder="地址二级分类"></el-input>
    </el-form-item>
    <el-form-item label="地址三级分类" prop="addressThird">
      <el-input v-model="dataForm.addressThird" placeholder="地址三级分类"></el-input>
    </el-form-item>
    <el-form-item label="成交获得积分" prop="score">
      <el-input v-model="dataForm.score" placeholder="成交获得积分"></el-input>
    </el-form-item>
    <el-form-item label="启用状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="启用状态"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="note">
      <el-input v-model="dataForm.note" placeholder="备注"></el-input>
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
          takeoutId: '',
          healthId: '',
          orderStatus: '',
          customerId: '',
          takeName: '',
          takePhone: '',
          totalAmout: '',
          payAmout: '',
          discountAmout: '',
          freightAmout: '',
          payWay: '',
          addressFirst: '',
          addressSecond: '',
          addressThird: '',
          score: '',
          status: '',
          note: ''
        },
        dataRule: {
          orderSn: [
            { required: true, message: '订单编号不能为空', trigger: 'blur' }
          ],
          takeoutId: [
            { required: true, message: '派送编号 外联外卖派送信息不能为空', trigger: 'blur' }
          ],
          healthId: [
            { required: true, message: '健康监控id不能为空', trigger: 'blur' }
          ],
          orderStatus: [
            { required: true, message: '订单状态 1->待付款；2->待派送；3->派送中；4->待反馈；5->异常不能为空', trigger: 'blur' }
          ],
          customerId: [
            { required: true, message: '下单顾客id 外联顾客不能为空', trigger: 'blur' }
          ],
          takeName: [
            { required: true, message: '接收人姓名不能为空', trigger: 'blur' }
          ],
          takePhone: [
            { required: true, message: '接收人电话不能为空', trigger: 'blur' }
          ],
          totalAmout: [
            { required: true, message: '订单总额不能为空', trigger: 'blur' }
          ],
          payAmout: [
            { required: true, message: '实付金额不能为空', trigger: 'blur' }
          ],
          discountAmout: [
            { required: true, message: '优惠金额不能为空', trigger: 'blur' }
          ],
          freightAmout: [
            { required: true, message: '外卖费用不能为空', trigger: 'blur' }
          ],
          payWay: [
            { required: true, message: '支付方式 1->支付宝；2->微信；3->校园一卡通；4->送达付款不能为空', trigger: 'blur' }
          ],
          addressFirst: [
            { required: true, message: '地址一级分类不能为空', trigger: 'blur' }
          ],
          addressSecond: [
            { required: true, message: '地址二级分类不能为空', trigger: 'blur' }
          ],
          addressThird: [
            { required: true, message: '地址三级分类不能为空', trigger: 'blur' }
          ],
          score: [
            { required: true, message: '成交获得积分不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '启用状态不能为空', trigger: 'blur' }
          ],
          note: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/order/order/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.orderSn = data.order.orderSn
                this.dataForm.takeoutId = data.order.takeoutId
                this.dataForm.healthId = data.order.healthId
                this.dataForm.orderStatus = data.order.orderStatus
                this.dataForm.customerId = data.order.customerId
                this.dataForm.takeName = data.order.takeName
                this.dataForm.takePhone = data.order.takePhone
                this.dataForm.totalAmout = data.order.totalAmout
                this.dataForm.payAmout = data.order.payAmout
                this.dataForm.discountAmout = data.order.discountAmout
                this.dataForm.freightAmout = data.order.freightAmout
                this.dataForm.payWay = data.order.payWay
                this.dataForm.addressFirst = data.order.addressFirst
                this.dataForm.addressSecond = data.order.addressSecond
                this.dataForm.addressThird = data.order.addressThird
                this.dataForm.score = data.order.score
                this.dataForm.status = data.order.status
                this.dataForm.note = data.order.note
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
              url: this.$http.adornUrl(`/order/order/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'orderSn': this.dataForm.orderSn,
                'takeoutId': this.dataForm.takeoutId,
                'healthId': this.dataForm.healthId,
                'orderStatus': this.dataForm.orderStatus,
                'customerId': this.dataForm.customerId,
                'takeName': this.dataForm.takeName,
                'takePhone': this.dataForm.takePhone,
                'totalAmout': this.dataForm.totalAmout,
                'payAmout': this.dataForm.payAmout,
                'discountAmout': this.dataForm.discountAmout,
                'freightAmout': this.dataForm.freightAmout,
                'payWay': this.dataForm.payWay,
                'addressFirst': this.dataForm.addressFirst,
                'addressSecond': this.dataForm.addressSecond,
                'addressThird': this.dataForm.addressThird,
                'score': this.dataForm.score,
                'status': this.dataForm.status,
                'note': this.dataForm.note
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
