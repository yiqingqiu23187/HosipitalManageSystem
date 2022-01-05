<template>
  <el-tabs type="border-card">
    <el-tab-pane label="病人状态更迭">
      <div id="base_register">
        <el-form :model="recordForm" :rules="rules" class="demo-dynamic" label-position="left" v-loading="loading" :ref="recordForm" label-width="180px">
          <h3 class="register_title">病人状态更迭</h3>

          <el-form-item prop="lifestate" label="病人生命状态 ：" class="left">
            <el-radio-group v-model="recordForm.lifestate">
              <el-radio  :label="0" >康复</el-radio>
              <el-radio  :label="1" >在院治疗</el-radio>
              <el-radio  :label="2" >病亡</el-radio>
            </el-radio-group>
          </el-form-item>


          <el-form-item prop="type" label="病人病情等级 ：" class="left">
            <el-radio-group v-model="recordForm.type">
              <el-radio  :label="0" >轻症</el-radio>
              <el-radio  :label="1" >重症</el-radio>
              <el-radio  :label="2" >危重症</el-radio>
            </el-radio-group>
          </el-form-item>


          <el-form-item style="width: 100%">
            <el-button type="primary" icon="el-icon-thumb" v-on:click="submit(recordForm)">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-tab-pane>


  </el-tabs>


</template>

<script >
  export default {
    name: 'doctorchangepatientinformation',
    data() {
      const dataValid = (rule, value, callback) => {
        if( value === '') {
          return callback(new Error('Can\'t be empty'))
        }
        return callback()
      }
      return {
        recordForm: {
          lifestate:0,
          type:0,
        },

        rules: {
          lifestate:[{required: true, message: '请选择病人状态', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          type: [{required: true, message: '请选择病情等级', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
        },

        loading: false
      }
    },
    methods: {
      submit(formName){
        this.$refs[formName].validate(valid => {
          if(valid){
            this.$axios.post('/doctorchangepatientinformation',{
                patientid:this.$store.state.patientid,
                lifestate:this.recordForm.lifestate,
                type:this.recordForm.type,
              }
            )
              .then(resp => {
                // 根据后端的返回数据修改
                if(resp.status === 200 ) {
                  // 跳转到login
                  this.$message({
                    message: '病人信息修改成功',
                    type: 'success'
                  });
                  this.$router.replace('/doctormypatient')
                }else
                  this.$message.error('提交失败');
              })
              .catch(error => {
                console.log(error);
                this.$message.error('捕捉到错误');
              })
          } else {
            this.$message({
              message: '请输入完整信息！',
              type: 'warning'
            });
          }
        })
      },

    },
  }

</script>

<style scoped>
  #base_register{
    height: 100%;
    width: 100%;
    background-size: cover;
    position: relative;
    padding:100px auto;
  }

  .register_title{
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .input{
    width:60%;
    float: left;
  }
  .deleatebutton{
    float: left;
    margin-left: 30px;
  }
  .topicbutton{
  }

  .left{
    margin-left: 50px;
  }
</style>
