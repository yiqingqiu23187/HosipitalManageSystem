<template>
  <div id="app">

    <LuffyHeader/>
    <Login/>


    <el-container class="none">
      <el-aside v-if="$route.meta.keepinitial"><asideheader/></el-aside>
      <el-main >  <router-view v-if ="isRouterAlive"></router-view></el-main>
    </el-container>




    <footer style="background-color:darkgray;height: 25px;color: white;font-size:medium">Copyright:最强3v4</footer>
  </div>
</template>

<script>
  import LuffyHeader from '@/components/LuffyHeader'
  import ElContainer from "element-ui/packages/container/src/main";
  import ElHeader from "element-ui/packages/header/src/main";
  import asideheader from '@/components/asideheader'
  import ElAside from "element-ui/packages/aside/src/main";
  import ElMain from "element-ui/packages/main/src/main";
  import ElFooter from "element-ui/packages/footer/src/main";



  export default {
    name: 'App',
    provide(){
      return{
        reload:this.reload
      }
    },
    data(){
      return{
        isRouterAlive:true
      }
    },
    methods:{
      reload(){
        this.isRouterAlive=false;
        this.$nextTick(function(){
          this.isRouterAlive=true
        })
      }
    },
    components:{
      ElFooter,
      ElMain,
      ElAside,
      ElHeader,
      ElContainer,
      LuffyHeader,
      asideheader,
    }
  }
</script>

<style>
  .none /deep/ .el-main {
    padding: 0;
  }

  .none /deep/ .el-aside {
    padding: 0;
  }


  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
  body{
    margin: 0px;
    padding: 0px;

  }
</style>
