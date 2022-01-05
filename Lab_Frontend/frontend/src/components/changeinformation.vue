<template>
  <el-tabs type="border-card">
    <el-tab-pane label="个人信息">
      <div id="base_register">
        <el-form :model="personalForm" :rules="rules" class="demo-dynamic" label-position="left" v-loading="loading" :ref="personalForm" label-width="180px">
          <h3 class="register_title">个人信息修改</h3>
          <el-form-item prop="password"  label="密码 ：" class="left">
            <el-input type="password"
                      v-model="personalForm.password"
                      auto-complete="off"
                      placeholder="password"></el-input>
          </el-form-item>

          <el-form-item prop="name" label="姓名 ：" class="left">
            <el-input  type="text"  v-model="personalForm.name" auto-complete="off" class="input"></el-input>
          </el-form-item>



          <el-form-item prop="sex" label="性别 ：" class="left">
            <el-radio-group v-model="personalForm.sex"  >
              <el-radio  :label="0" >男</el-radio>
              <el-radio  :label="1" >女</el-radio>
            </el-radio-group>
          </el-form-item>


          <el-form-item prop="telephone" label="手机号码 ：" class="left">
            <el-input   v-model="personalForm.telephone"
                      type="number" class="input"></el-input>
          </el-form-item>

          <el-form-item style="width: 100%">
            <el-button type="primary" icon="el-icon-thumb" v-on:click="submit(personalForm)">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-tab-pane>


  </el-tabs>


</template>

<script >
  export default {
    name: 'changeinformation',
    data() {
      const dataValid = (rule, value, callback) => {
        if( value === '') {
          return callback(new Error('Can\'t be empty'))
        }
        return callback()
      }
      return {
        username:"",
        personalForm: {
          password:'',
          sex:0,
          name: '',
          telephone:'',
        },

        rules: {
          password:[ {required: true, message: '请输入密码', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          name:[ {required: true, message: '请输入姓名', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          sex:[{required: true, message: '请选择性别', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
         telephone:[{required: true, message: '请输入手机号', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
        },
        loading: false
      }
    },
    methods: {
      submit(formName){
        this.$refs[formName].validate(valid => {
          if(valid){
            this.$axios.post('/changepersonalinformation',{
              userid: this.$store.state.userDetail.userid,
              name:this.personalForm.name,
              sex:this.personalForm.sex,
              password:this.personalForm.password,
              telephone:this.personalForm.telephone,
              }
            )
              .then(resp => {
                // 根据后端的返回数据修改
                if(resp.status === 200 ) {
                  // 跳转到login
                  this.$message({
                    message: '信息修改成功',
                    type: 'success'
                  });
                  this.$router.replace('/information')
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

    created:
      function () {
        this.$axios.post('/personalinformation', {
          userid: this.$store.state.userDetail.userid,
        })
          .then(resp => {
            if (resp.status === 200) {
              this.personalForm.password=resp.data.password;
              this.personalForm.name =resp.data.name;
              this.personalForm.sex =resp.data.sex;
              this.personalForm.telephone =resp.data.telephone;
            } else {
              this.$message.error('后端响应不是200');
            }
          })
          .catch(error => {
            console.log(error);
            this.$message.error('获取个人信息时出错');
          })
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
