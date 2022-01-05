<template>
  <div  class="text item">
    <el-tabs type="border-card">
      <el-tab-pane label="所有病房护士信息">
        <div  class="text item">
          <el-table  :data="sickrooms" style="width: 100%">
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


            <el-table-column label="操作1">
              <template slot-scope="scope">
                <el-button size="mini" @click="sickroomid=scope.row.userid;choose()" >
                  查看他/她的病人</el-button>
              </template>
            </el-table-column>

            <el-table-column label="操作2">
              <template slot-scope="scope">
                <el-button size="mini" @click="sickroomid=scope.row.userid;choose2()" >
                  删除该护士</el-button>
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
    inject:['reload'],
    name: "mainnurseallsickroom",
    data(){
      return{
        sickrooms:[
          {
            name:'',
            sex:1,
            identity:0,
            telephone:'',
            userid:0,
          }],
        sickroomid:0,
      }
    },

    created:
      function () {
        this.$axios.post('/mainnurseallsickroom', {
          userid: this.$store.state.userDetail.userid,
        })
          .then(resp => {
            if (resp.status === 200) {
              this.sickrooms= resp.data.sickrooms;
            } else {
              this.$message.error('后端响应不是200');
            }
          })
          .catch(error => {
            console.log(error);
            this.$message.error('获取病房护士信息时出错');
          })
      },
    methods: {
      choose:function () {
        this.$store.commit('sickroomid', this.sickroomid);
        this.$router.replace({path:'/doctorchecksickroompatient'})
      },
      choose2:function () {
        this.$axios.post('/mainnursedeletesickroom', {
          userid: this.sickroomid,
        })
          .then(resp => {
            if (resp.data.ok) {
              this.$message({
                message: '已删除该护士',
                type: 'success'
              });
              this.reload();
            }
            else
              this.$message.error('有病人的护士不能删除');
          })
          .catch(error => {
            this.$message.error('删除护士时出错');
          })
      },

    }


  }
</script>

<style scoped>

</style>
