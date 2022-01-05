<template>
    <el-row class="tac" v-if="$route.meta.keepaside">
      <el-col :span="12">
        <!--<h5>用户导航</h5>-->
        <div class="block"><el-avatar :size="80" :src="circleUrl"></el-avatar></div>
        <span>{{username}}</span>
        <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"  >
          <el-menu-item >
            <i class="el-icon-user-solid"></i>
            <router-link to="Information">个人信息</router-link>
          </el-menu-item>
          <el-menu-item >
            <i class="el-icon-search"></i>
            <router-link to="changeinformation">修改个人信息</router-link>
          </el-menu-item>

          <el-menu-item v-if="identity===2">
            <i class="el-icon-edit"></i>
            <router-link to="emergencytakein">登记患者信息</router-link>
          </el-menu-item>

          <el-menu-item v-if="identity===2">
            <i class="el-icon-menu" ></i>
            <router-link to="allpatients">查看所有患者</router-link>
          </el-menu-item>

          <el-menu-item v-if="identity===0">
            <i class="el-icon-menu"></i>
            <router-link to="doctormainnurse">查看护士长信息</router-link>
          </el-menu-item>

          <el-menu-item v-if="identity===0">
            <i class="el-icon-me nu"></i>
            <router-link to="doctorallsickroom">查看所有病房护士信息</router-link>
          </el-menu-item>


          <el-menu-item v-if="identity===1">
            <i class="el-icon-menu"></i>
            <router-link to="mainnurseallsickroom">查看所有病房护士信息</router-link>
          </el-menu-item>

          <el-menu-item v-if="identity===1">
            <i class="el-icon-menu"></i>
            <router-link to="mainnurseaddsickroom">增加病房护士</router-link>
          </el-menu-item>

          <el-menu-item v-if="identity===1">
            <i class="el-icon-menu"></i>
            <router-link to="mainnursebed">查看病床</router-link>
          </el-menu-item>

          <el-menu-item v-if="identity===1">
            <i class="el-icon-menu"></i>
            <router-link to="mainnursenewpatient">查看新转入患者</router-link>
          </el-menu-item>

          <el-menu-item v-if="identity===0">
            <i class="el-icon-menu"></i>
            <router-link to="doctormypatient">查看我的患者</router-link>
          </el-menu-item>

          <el-menu-item v-if="identity===1">
            <i class="el-icon-menu"></i>
            <router-link to="mainnursemypatient">查看我的患者</router-link>
          </el-menu-item>

          <el-menu-item v-if="identity===3">
            <i class="el-icon-menu"></i>
            <router-link to="sickroommypatient">查看病房患者</router-link>
          </el-menu-item>
          <el-menu-item v-if="identity===0">
            <i class="el-icon-menu"></i>
            <router-link to="doctormygoodpatient">查看可出院患者</router-link>
          </el-menu-item>


          <el-menu-item >
            <i class="el-icon-s-check"></i>
            <router-link to="logout">注销</router-link>
          </el-menu-item>

        </el-menu>
      </el-col>
    </el-row>


</template>

<script>

    export default {
      name: "asideheader",
      data(){
        return{
          circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
          username:this.$store.state.userDetail.username,
          identity:0,
        }
      },

      created:
        function(){
          this.identity=this.$store.state.userDetail.identity;
          // alert(this.$store.state.userDetail.identity);
        },



      methods: {
        handleOpen(key, keyPath) {
          console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
          console.log(key, keyPath);
        }
      }
    }
</script>

<style scoped>
  .tac{
    height:100%;
    width:100%;
  }

  .el-col-12{
    width:100%;
    padding-top: 50px;
  }

  .el-menu-item{
    width:60%;
    /*padding-left: 20%;*/
    margin-left: 20%;
  }
</style>
