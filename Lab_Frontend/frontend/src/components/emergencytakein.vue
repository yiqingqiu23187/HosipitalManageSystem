<template>
  <el-tabs type="border-card">
    <el-tab-pane label="患者登记">
      <div id="base_register">
        <el-form :model="patientForm" :rules="rules" class="demo-dynamic" label-position="left" v-loading="loading" :ref="patientForm" label-width="180px">
          <h3 class="register_title">患者信息填写</h3>

          <el-form-item prop="patientname" label="患者姓名 ：" class="left">
            <el-input id ="1" type="text"  v-model="patientForm.patientname" auto-complete="off" class="input"></el-input>
          </el-form-item>

          <el-form-item prop="patientsex" label="患者性别 ：" class="left">
            <el-radio-group v-model="patientForm.patientsex"  >
              <el-radio  :label="0" >男</el-radio>
              <el-radio  :label="1" >女</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item prop="examresult" label="检测结果 ：" class="left">
            <el-radio-group v-model="patientForm.examresult"  >
              <el-radio  :label="0" >阳性</el-radio>
              <el-radio  :label="1" >阴性</el-radio>
            </el-radio-group>
          </el-form-item>


          <el-form-item prop="date"  label="收诊日期 ：" class="left">
            <el-date-picker id="4" v-model="patientForm.date" type="date" class="input">
            </el-date-picker>
          </el-form-item>

          <el-form-item prop="type" label="患者病情等级 ：" class="left">
            <el-radio-group v-model="patientForm.type">
              <el-radio  :label="0" >轻症</el-radio>
              <el-radio  :label="1" >重症</el-radio>
              <el-radio  :label="2" >危重症</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item prop="temperature" label="患者温度 ：" class="left">
            <el-input id ="6"   v-model="patientForm.temperature"
                      oninput="value=value.replace(/[^0-9.]/g,'')" class="input"></el-input>
          </el-form-item>


          <el-form-item style="width: 100%">
            <el-button type="primary" icon="el-icon-thumb" v-on:click="submit(patientForm)">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-tab-pane>


  </el-tabs>


</template>

<script >
  export default {
    name: 'emergencytakein',
    data() {
      const dataValid = (rule, value, callback) => {
        if( value === '') {
          return callback(new Error('Can\'t be empty'))
        }
        return callback()
      }
      return {
        username:"",
        patientForm: {
          patientsex:0,
          patientname: '',
          examresult:0,
          date: '',
          type:0,
          temperature:37.7,
        },

        rules: {
          patientname:[ {required: true, message: '请输入患者姓名', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          patientsex:[{required: true, message: '请输入患者性别', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          examresult: [{required: true, message: '请选择检测结果', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
          date: [{required: true, message: '请选择收诊日期', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}],
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
            this.$axios.post('/emergencytakein',{
                patientname:this.patientForm.patientname,
                patientsex:this.patientForm.patientsex,
                type:this.patientForm.type,
                date:this.patientForm.date,
                examresult:this.patientForm.examresult,
                temperature:this.patientForm.temperature,
              }
            )
              .then(resp => {
                // 根据后端的返回数据修改
                if(resp.status === 200 ) {
                  // 跳转到login
                  this.$message({
                    message: '患者信息提交成功',
                    type: 'success'
                  });
                  this.$router.replace('/allpatients')
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

    created:
      function(){
        this.username=JSON.parse(localStorage.getItem('userDetail')).username,
          this.patientForm.temperature=37.6;
        this.patientForm.sex=0;
        this.patientForm.type=0;
        this.patientForm.examresult=0;
      }
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
