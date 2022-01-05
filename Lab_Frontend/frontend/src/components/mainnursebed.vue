<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="所有病床">
        <div  class="text item">
          <el-table :data="beds" style="width: 100%">
            <el-table-column  label="房间号" width="150">
              <template slot-scope="scope">
                <span style="margin-left: 10px">{{scope.row.roomnum}}</span>
              </template>
            </el-table-column>

            <el-table-column  label="床号" width="150">
              <template slot-scope="scope">
                <span style="margin-left: 10px">{{scope.row.bednum}}</span>
              </template>
            </el-table-column>


            <el-table-column label="患者名字" width="180">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ patients[scope.$index].patientname }}</span>
              </template>
            </el-table-column>

            <el-table-column  label="性别(0:男性 1：女性)"    width="180">
              <template slot-scope="scope">
                <span style="margin-left: 10px">{{  patients[scope.$index].patientsex }}</span>
              </template>
            </el-table-column>

            <el-table-column   label="病情等级(0：轻症 1:重症 2：危重症)" width="180" >
              <template slot-scope="scope">
                <span style="margin-left: 10px">{{ patients[scope.$index].type}}</span>
              </template>
            </el-table-column>

            <el-table-column    label="生命状态(0:康复 1：治疗中 2：死亡 3：隔离区等待" width="180"
            >
              <template slot-scope="scope">
                <span style="margin-left: 10px">{{ patients[scope.$index].lifestate}}</span>
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
    name: "mainnursemypatient",
    data() {
      return {
        patients: [
          {
            patientname: '',
            patientsex:0,
            patientid:0,
            type: 0,
            lifestate: 0,
            area:0,
          },
        ],
        beds:[
          {
            roomnum:0,
            bednum:0,
          }

        ],
      }
    },
    created:
      function () {
        this.$axios.post('/doctormypatient', {
          userid: this.$store.state.userDetail.userid,
        })
          .then(resp => {
            if (resp.status === 200) {
              this.patients = resp.data.patients;
              this.beds = resp.data.beds;
            } else {
              this.$message.error('后端响应不是200');
            }
          })
          .catch(error => {
            console.log(error);
            this.$message.error('获取病床列表时出错');
          })
      },


    methods: {
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      }
    },

  }


</script>

<style scoped>
</style>
