<template>
  <div id="base_login" >
  <el-carousel indicator-position="none" >
    <el-carousel-item v-for="item in 6" :key="item" style="height:100%">
    </el-carousel-item>
  </el-carousel>

    <el-form :model="loginForm"
             :rules="rules"
             class="login_container"
             label-position="left"
             label-width="0px"
             v-loading="loading">
      <h3 class="login_title">Login</h3>
      <el-form-item prop="username">
        <el-input type="text"
                  v-model="loginForm.username"
                  auto-complete="off"
                  placeholder="username"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password"
                  v-model="loginForm.password"
                  auto-complete="off"
                  placeholder="password"></el-input>
      </el-form-item>


      <el-form-item style="width: 100%">
        <el-button type="primary"
                   :plain="true"
                   style="width: 40%;background: #afb4db;border: none"
                   v-on:click="login">login
        </el-button>
      </el-form-item>


    </el-form>

  </div>

</template>

<script>
  import ElFormItem from "element-ui/packages/form/src/form-item";

  export default {
    components: {ElFormItem},
    name: 'Login',
    data() {
      return {
        loginForm: {
          username: '',
          password: ''
        },
        rules: {
          username: [{required: true, message: '', trigger: 'blur'}],
          password: [{required: true, message: '', trigger: 'blur'}]
        },
        loading: false
      }
    },


    methods:
      {
     login()
     {
        this.$axios.post('/login', {
           username: this.loginForm.username,
           password: this.loginForm.password,
        })
          .then(resp => {
            if (resp.status === 200 && resp.data.hasOwnProperty("token"))
            {
                this.$store.commit('login', resp.data);
                this.$router.replace({path: '/Information'})
                this.$message({
                   message: '登陆成功',
                   type: 'success'});
            }
            else
              {
              this.$message.error('用户不存在或身份错误');
            }
          })
          .catch(error => {
            this.$message.error('登陆失败');
          })

      }
    },

  }
</script>

<style scoped>
  #base_login {
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }

  body {
    margin: 0px;
    padding: 0px;
  }

  .login_container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    position: absolute;
    z-index: 10;
    top:50px;
    right:100px;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #494e8f;
  }

  .el-carousel__item:nth-child(6n) {
    background: url("../assets/背景轮换/1.jpg");

    background-size: cover;
    background-position:center;
  }

  .el-carousel__item:nth-child(6n+1) {
    background: url("../assets/背景轮换/2.jpg");
    background-position:center;

    background-size: cover;
  }
  .el-carousel__item:nth-child(6n+2) {
    background: url("../assets/背景轮换/3.jpg");
    background-position:center;

    background-size: cover;
  }
  .el-carousel__item:nth-child(6n+3) {
    background: url("../assets/背景轮换/4.jpg");
    background-position:center;

    background-size: cover;
  }
  .el-carousel__item:nth-child(6n+4) {
    background: url("../assets/背景轮换/5.jpg");
    background-position:center;

    background-size: cover;
  }
  .el-carousel__item:nth-child(6n+5) {
    background: url("../assets/背景轮换/6.jpg");
    background-position:center;

    background-size: cover;
  }

  .el-carousel--horizontal >>>.el-carousel__container{
    height:700px;
  }


</style>
