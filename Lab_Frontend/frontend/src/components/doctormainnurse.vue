<template>
  <div  class="text item">
    <el-tabs type="border-card">
      <el-tab-pane label="护士长信息">
        <div  class="text item">
          <el-table  :data="mainnurses" style="width: 100%">
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
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>

</template>

<script>
  export default {
    name: "doctormainnurse",
    data(){
      return{
        mainnurses:[
          {
            name:'',
            sex:1,
            identity:0,
            telephone:'',}]
      }
    },

    created:
      function () {
        this.$axios.post('/doctormainnurse', {
          userid: this.$store.state.userDetail.userid,
        })
          .then(resp => {
            if (resp.status === 200) {
              this.mainnurses= resp.data.mainnurses;
            } else {
              this.$message.error('后端响应不是200');
            }
          })
          .catch(error => {
            console.log(error);
            this.$message.error('获取护士长信息时出错');
          })
      },



  }
</script>

<style scoped>

</style>
