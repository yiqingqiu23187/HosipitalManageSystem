<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="新转入患者">
        <div  class="text item">
          <el-table :data="news" style="width: 100%">
            <el-table-column label="患者名字" width="180">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.patientname }}</span>
              </template>
            </el-table-column>

            <el-table-column label="原来住院区" width="180">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.originalarea }}</span>
              </template>
            </el-table-column>

            <el-table-column label="现在住院区" width="180">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.area }}</span>
              </template>
            </el-table-column>

            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini"   @click="newsid=scope.row.newsid,read()" >
                  阅读</el-button>
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
    name: "mainnursenewpatient",
    data() {
      return {
        news: [
          {
            patientname: '',
            originalarea:0,
            area:0,
            newsid:0,
          },
        ],
        newsid:0,
      }
    },
    created:
      function () {
        this.$axios.post('/mainnursenewpatient', {
          userid: this.$store.state.userDetail.userid,
        })
          .then(resp => {
            if (resp.status === 200) {
              this.news = resp.data.news;
              console.log(resp.data);
            } else {
              this.$message.error('后端响应不是200');
            }
          })
          .catch(error => {
            console.log(error);
            this.$message.error('获取新转入病人列表时出错');
          })
      },
    methods: {
      read: function () {
        this.$axios.post('/mainnurseread', {
          newsid: this.newsid,
        })
          .then(resp => {
            if (resp.status === 200) {
              this.$message({
                message: '信息已阅读',
                type: 'success'
              });
              this.reload();
            } else {
              this.$message.error('后端响应不是200');
            }
          })
          .catch(error => {
            console.log(error);
            this.$message.error('出错');
          })
      },
    }

  }


</script>

<style scoped>
</style>
