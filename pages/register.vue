<template>
    <div>
      <div class="title"><h2>校园趣闻</h2></div>
     <div class="register">
       <el-form>
         <div class="user-box">
           <el-input v-model="username" placeholder="Please input" @input="changeValue" class="B1"/>
           <label>账号</label>
         </div>
         <div class="user-box">
           <el-input v-model="password" type="password" placeholder="Please input password" show-password class="B2" @input="changeValue"/>
           <label>密码</label>
         </div>
         <div class="user-box">
            <el-radio-group v-model="role" class="ml-4" >
            <el-radio label="普通用户" size="large" >普通用户</el-radio>
            <el-radio label="新闻发布者" size="large" >新闻发布者</el-radio>
            <el-radio label="管理员" size="large">管理员</el-radio>
            </el-radio-group> 
         </div>
         <div class="user-box">
         <el-button type="primary" :plain="true" @click="register">注册</el-button>
         <nuxt-link to="login"><el-button type="success" plain>前往登陆</el-button></nuxt-link>
        </div>
        </el-form>
     </div>
     <div>
       <!-- <ul>
         <li v-for="item in topics" :key="item.id"><a href="#">{{ item.title }}</a></li>
       </ul> -->
     </div>
   </div>
 
 </template>
 
 <script>
   import axios from 'axios'
   export default{
     layout:"login",
     data() {
     return {
        username: '', // 账号
        password: '',    // 密码
        role:''
     }
   },

   methods: {
    changeValue (e) {
      this.$forceUpdate()
    },
    register(){
      console.log("注册"+this.role+this.username+this.password);
      axios.post('/api/common/signup',{username:this.username,password:this.password,role:this.role})
      .then(Response=>{
        console.log(Response);
        if(Response.data.code==200){
          this.$message('注册成功');
        }
        else{
          this.$message('注册失败')
        }
      })
    },
    }
 }
 </script>
 
 <style>
 .register{
   /* background-color: aquamarine; */
   width: 50%;
   height: 25rem;
   margin: 0 auto;
   background-color: rgb(255, 255, 255);
   box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
 }

 .title{
   text-align: center;
   margin-top: 5rem;
 }
 .B1{
   width: 50%;
 }
 .B2{
   width: 50%;
 }
 .user-box{
  padding-top: 2rem;
  padding-bottom: 0.5rem;
  text-align: center;
  /* background-color: aqua; */
  margin-top: 0.5rem;
}
 </style>