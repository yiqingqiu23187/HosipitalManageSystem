<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="患者列表">
        <div  class="text item">
          <el-table :data="patients" style="width: 100%">
              <el-table-column label="患者名字" width="180">
                <template slot-scope="scope">
                  <i class="el-icon-time"></i>
                  <span style="margin-left: 10px">{{ scope.row.patientname }}</span>
                </template>
              </el-table-column>

              <el-table-column prop="patientsex" label="性别(0:男性 1：女性)"    width="180"  column-key="patientsex"
                               :filters="[{text: '男性', value: 0}, {text: '女性', value: 1}]"
                               :filter-method="filterHandler">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.patientsex}}</span>
                </template>
              </el-table-column>



              <el-table-column prop="type"  label="病情等级(0：轻症 1:重症 2：危重症)" width="180" column-key="type"
                               :filters="[{text: '轻症', value: 0}, {text: '重症', value: 1},{text: '危重症', value: 2}]"
                               :filter-method="filterHandler">
              >
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.type}}</span>
                </template>
              </el-table-column>


              <el-table-column   prop="area"  label="住院区(3：隔离区)" width="180" column-key="area"
                                 :filters="[{text: '区域0', value: 0}, {text: '区域1', value: 1},{text: '区域2', value: 2},{text: '隔离区', value: 3},]"
                                 :filter-method="filterHandler">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.area}}</span>
                </template>
              </el-table-column>




              <el-table-column  prop="lifestate"   label="生命状态(0:康复 1：治疗中 2：死亡 3：隔离区等待" width="180" column-key="lifestate"
                                :filters="[{text: '康复', value: 0}, {text: '治疗中', value: 1},{text: '死亡', value: 2},{text: '隔离区', value: 3},]"
                                :filter-method="filterHandler"
              >
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.lifestate}}</span>
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
      name: "allpatients",
      data() {
        return {
          patients: [
            {
              patientname: '',
              patientsex:0,
              type: 0,
              lifestate: 0,
              area:0,
            },
          ],
        }
      },
    created:
    function () {
        this.$axios.get('/emergencyallpatient')
          .then(resp => {
            if (resp.status === 200) {
              this.patients = resp.data.patients;
            } else {
              this.$message.error('后端响应不是200');
            }
          })
          .catch(error => {
            console.log(error);
            this.$message.error('获取患者列表时出错');
          })
    },
      methods: {
        filterHandler(value, row, column) {
          const property = column['property'];
          return row[property] === value;
        }
      }

    }


</script>

<style scoped>
</style>
