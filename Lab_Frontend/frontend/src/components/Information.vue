<template>
  <div  class="text item">
    <el-tabs type="border-card">
      <el-tab-pane label="个人信息">
        <div  class="text item">
          <el-table  :data="tabledata" style="width: 100%">
              <el-table-column label="用户名"  width="130">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.username}}</span>
                </template>
              </el-table-column>

              <el-table-column label="密码"   width="130">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.password}}</span>
                </template>
              </el-table-column>

              <el-table-column label="姓名"   width="130">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.name}}</span>
                </template>
              </el-table-column>

              <el-table-column label="性别（0：男 1：女）"  width="130">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.sex}}</span>
                </template>
              </el-table-column>

              <el-table-column label="身份（0：主治医师 1：护士长 2：急诊护士 3：病房护士）"   width="130">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.identity}}</span>
                </template>
              </el-table-column>


              <el-table-column label="电话号码"   width="130">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.telephone}}</span>
                </template>
              </el-table-column>

              <el-table-column label="操作" width="130">
                  <el-button size="mini" @click="change()" >修改个人信息</el-button>

              </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>

</template>

<script>
  export default {
    name: "Information",
    data(){
      return{
        tabledata:[
          {
          username:'88',
          password:'',
          name:'',
          sex:1,
          identity:0,
          telephone:'',}]
      }
    },

    methods: {
      change:function () {
        this.$router.replace({path:'/changeinformation'})
      }},


    created:
      function () {
        this.$axios.post('/personalinformation', {
          userid: this.$store.state.userDetail.userid,
        })
          .then(resp => {
            if (resp.status === 200) {
              this.tabledata[0].username = resp.data.username;
              this.tabledata[0].password=resp.data.password;
              this.tabledata[0].name =resp.data.name;
              this.tabledata[0].sex =resp.data.sex;
              this.tabledata[0].identity =resp.data.identity;
              this.tabledata[0].telephone =resp.data.telephone;
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

</style>
