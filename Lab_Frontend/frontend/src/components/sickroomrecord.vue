<template>
  <el-tabs type="border-card">
    <el-tab-pane label="病人状态记录">
      <div id="base_register">
        <el-form :model="recordForm" :rules="rules" class="demo-dynamic" label-position="left" v-loading="loading" :ref="recordForm" label-width="180px">
          <h3 class="register_title">记录病人状态</h3>

          <el-form-item prop="date"  label="记录日期 ：" class="left">
            <el-date-picker id="4" v-model="recordForm.date" type="date" class="input">
            </el-date-picker>
          </el-form-item>
          <el-form-item prop="lifestate" label="病人生命状态 ：" class="left">
            <el-radio-group v-model="recordForm.lifestate">
              <el-radio  :label="0" >康复</el-radio>
              <el-radio  :label="1" >在院治疗</el-radio>
              <el-radio  :label="2" >病亡</el-radio>
            </el-radio-group>
          </el-form-item>


          <el-form-item prop="type" label="病人病情等级 ：" class="left">
            <el-radio-group v-model="recordForm.type">
              <el-radio  :label="0" >轻症</el-radio>
              <el-radio  :label="1" >重症</el-radio>
              <el-radio  :label="2" >危重症</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item prop="symptom" label="症状 ：" class="left">
            <el-input id ="1" type="text"  v-model="recordForm.symptom" auto-complete="off" class="input"></el-input>
          </el-form-item>

          <el-form-item prop="examresult" label="检测结果 ：" class="left">
            <el-radio-group v-model="recordForm.examresult"  >
              <el-radio  :label="0" >阳性</el-radio>
              <el-radio  :label="1" >阴性</el-radio>
            </el-radio-group>
          </el-form-item>




          <el-form-item prop="temperature" label="病人温度 ：" class="left">
            <el-input id ="6"   v-model="recordForm.temperature"
                      oninput="value=value.replace(/[^0-9.]/g,'')" class="input"></el-input>
          </el-form-item>

          <el-form-item style="width: 100%">
            <el-button type="primary" icon="el-icon-thumb" v-on:click="submit(recordForm)">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-tab-pane>


  </el-tabs>


</template>

<script >
  export default {
    name: 'sickroomrecord',
    data() {
      const dataValid = (rule, value, callback) => {
        if( value === '') {
          return callback(new Error('Can\'t be empty'))
        }
        return callback()
      }
      return {
        recordForm: {
          lifestate:1,
          symptom: '',
          examresult:0,
          date: '',
          type:0,
          temperature:37.7,
        },

        rules: {
          symptom:[ {required: true, message: '请输入病人症状', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          lifestate:[{required: true, message: '请选择病人状态', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          examresult: [{required: true, message: '请选择检测结果', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          date: [{required: true, message: '请选择记录日期', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          type: [{required: true, message: '请选择病情等级', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          temperature: [{required: true, message: '请输入温度', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
        },

        loading: false
      }
    },
    methods: {
      submit(formName){
        if(document.getElementById("1").value==''||document.getElementById("1").value==undefined||document.getElementById("1").value==null||
          document.getElementById("4").value==''||document.getElementById("4").value==undefined||document.getElementById("4").value==null||
          document.getElementById("6").value==''||document.getElementById("6").value==undefined||document.getElementById("6").value==null
        )
          this.$message({
            message: '请输入完整信息',
            type: 'warning'
          });
        this.$refs[formName].validate(valid => {
          if(valid){
            this.$axios.post('/sickroomrecord',{
                patientid:this.$store.state.patientid,
                symptom:this.recordForm.symptom,
                lifestate:this.recordForm.lifestate,
                type:this.recordForm.type,
                date:this.recordForm.date,
                examresult:this.recordForm.examresult,
                temperature:this.recordForm.temperature,
              }
            )
              .then(resp => {
                // 根据后端的返回数据修改
                if(resp.status === 200 ) {
                  // 跳转到login
                  this.$message({
                    message: '病人信息记录成功',
                    type: 'success'
                  });
                  this.$router.replace('/sickroommypatient')
                }else
                  this.$message.error('提交失败');
              })
              .catch(error => {
                console.log(error);
                this.$message.error('捕捉到错误');
              })
          } else {
            this.$message({
              message: '请输入完整信息！',
              type: 'warning'
            });
          }
        })
      },

    },
  }

</script>

<style scoped>
  #base_register{
    height: 100%;
    width: 100%;
    background-size: cover;
    position: relative;
    padding:100px auto;
  }

  .register_title{
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .input{
    width:60%;
    float: left;
  }
  .deleatebutton{
    float: left;
    margin-left: 30px;
  }
  .topicbutton{
  }

  .left{
    margin-left: 50px;
  }
</style>

