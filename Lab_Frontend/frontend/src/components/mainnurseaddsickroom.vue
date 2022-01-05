<template>
  <el-tabs type="border-card">
    <el-tab-pane label="病房护士登记">
      <div id="base_register">
        <el-form :model="sickroomForm" :rules="rules" class="demo-dynamic" label-position="left" v-loading="loading" :ref="sickroomForm" label-width="180px">
          <h3 class="register_title">护士信息填写</h3>


          <el-form-item prop="username" label="用户名 ：" class="left">
            <el-input id ="1" type="text"  v-model="sickroomForm.username" auto-complete="off" class="input"></el-input>
          </el-form-item>

          <el-form-item prop="password" label="密码 ：" class="left">
            <el-input id ="3" type="text"  v-model="sickroomForm.password" auto-complete="off" class="input"></el-input>
          </el-form-item>


          <el-form-item prop="name" label="护士姓名 ：" class="left">
            <el-input id ="2" type="text"  v-model="sickroomForm.name" auto-complete="off" class="input"></el-input>
          </el-form-item>

          <el-form-item prop="sex" label="护士性别 ：" class="left">
            <el-radio-group v-model="sickroomForm.sex"  >
              <el-radio  :label="0" >男</el-radio>
              <el-radio  :label="1" >女</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item prop="telephone" label="电话 ：" class="left">
            <el-input id ="4" type="text"  v-model="sickroomForm.telephone" auto-complete="off" class="input"></el-input>
          </el-form-item>



          <el-form-item style="width: 100%">
            <el-button type="primary" icon="el-icon-thumb" v-on:click="submit(sickroomForm)">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-tab-pane>


  </el-tabs>


</template>

<script >
  export default {
    name: 'mainnurseaddsickroom',
    data() {
      const dataValid = (rule, value, callback) => {
        if( value === '') {
          return callback(new Error('Can\'t be empty'))
        }
        return callback()
      }
      return {
        username:"",
        sickroomForm: {
          sex:0,
          name: '',
          username:'',
          password:'',
          telephone:'',
        },

        rules: {
          name:[ {required: true, message: '请输入患者姓名', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          sex:[{required: true, message: '请选择患者性别', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          username: [{required: true, message: '请输入用户名', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          password: [{required: true, message: '请输入密码', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          telephone: [{required: true, message: '请输入电话号码', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
        },



        loading: false
      }
    },
    methods: {
      submit(formName){
        if(document.getElementById("1").value==''||document.getElementById("1").value==undefined||document.getElementById("1").value==null||
          document.getElementById("2").value==''||document.getElementById("2").value==undefined||document.getElementById("2").value==null||
          document.getElementById("3").value==''||document.getElementById("3").value==undefined||document.getElementById("3").value==null||
          document.getElementById("4").value==''||document.getElementById("4").value==undefined||document.getElementById("4").value==null
        )
          this.$message({
            message: '请输入完整信息',
            type: 'warning'
          });
        this.$refs[formName].validate(valid => {
          if(valid){
            this.$axios.post('/mainnurseaddsickroom',{
              userid:this.$store.state.userDetail.userid,
               name:this.sickroomForm.name,
                sex:this.sickroomForm.sex,
                telephone:this.sickroomForm.telephone,
               username:this.sickroomForm.username,
                password:this.sickroomForm.password,
              }
            )
              .then(resp => {
                // 根据后端的返回数据修改
                if(resp.status === 200 ) {
                  // 跳转到login
                  this.$message({
                    message: '护士信息提交成功',
                    type: 'success'
                  });
                  this.$router.replace('/mainnurseallsickroom')
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
      function(){
        this.username=JSON.parse(localStorage.getItem('userDetail')).username,
        this.sickroomForm.sex=0;
      }
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

