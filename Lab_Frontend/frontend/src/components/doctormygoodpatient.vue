<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="可出院患者">
        <div  class="text item">
          <el-table :data="patients" style="width: 100%">
              <el-table-column label="患者名字" width="180">
                <template slot-scope="scope">
                  <i class="el-icon-time"></i>
                  <span style="margin-left: 10px">{{ scope.row.patientname }}</span>
                </template>
              </el-table-column>
              <el-table-column label="性别(0:男性 1：女性)" width="180">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.patientsex}}</span>
                </template>
              </el-table-column>
              <el-table-column label="病情等级(0：轻症 1:重症 2：危重症)" width="180">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.type}}</span>
                </template>
              </el-table-column>
              <el-table-column label="住院区(3：隔离区)" width="180">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.area}}</span>
                </template>
              </el-table-column>
              <el-table-column label="生命状态(0:康复 1：治疗中 2：死亡 3：隔离区等待" width="180">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.lifestate}}</span>
                </template>
              </el-table-column>

              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button size="mini" @click="patientid=scope.row.patientid;choose()" >
                    允许出院</el-button>
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
    name: "doctormygoodpatient",
    data() {
      return {
        patients: [
          {
            patientname: '',
            patientsex:0,
            patientid:0,
            type: 0,
            lifestate: 1,
            area:0,
          },
        ],
        patientid:0,
      }
    },
    created:
      function () {
        this.$axios.post('/doctormygoodpatient', {
          userid: this.$store.state.userDetail.userid,
        })
          .then(resp => {
            if (resp.status === 200) {
              this.patients = resp.data.patients;
            } else {
              this.$message.error('后端响应不是200');
            }
          })
          .catch(error => {
            console.log(error);
            this.$message.error('获取病人列表时出错');
          })
      },


    methods: {
      choose:function () {
        this.$axios.post('/doctoragree', {
          patientid: this.patientid,
        })
          .then(resp => {
            if (resp.status === 200) {
              this.$message({
                message: '患者已出院',
                type: 'success'
              });
              this.reload();
            }
          })
          .catch(error => {
            console.log(error);
            this.$message.error('同意病人出院时出错');
          })
      },
      },

  }


</script>

<style scoped>
</style>

