<template>
  <el-tabs type="border-card">
    <el-tab-pane label="退出登录">
      <el-button v-on:click="logout" >注销用户</el-button>
    </el-tab-pane>
    <el-tab-pane label="更多功能">配置管理</el-tab-pane>
  </el-tabs>
</template>

<script>
    export default {
        name: "logout",
      methods:{
        logout() {
          this.$store.commit('logout');
          this.$message({
            message: '注销成功！',
            type: 'success'
          });
          if(this.$route.path !== '/Login') {
            this.$router.replace('/Login')
          }
        }
      },
    }
</script>

<style scoped>

</style>
