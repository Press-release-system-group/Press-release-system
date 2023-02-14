<template>
    <div>
     <div class="login">
       <h2>校园趣闻</h2>
       <el-form>
         <div class="user-box">
           <el-input v-model="username" placeholder="Please input" @input="changeValue" class="B1"/>
           <label>用户名</label>
         </div>
         <div class="user-box">
           <el-input v-model="password" type="password" placeholder="Please input password" show-password class="B2" @input="changeValue"/>
           <label>密码</label>
         </div>
         <div class="user-box">
            <el-radio-group v-model="role" class="ml-4" >
            <el-radio label="1" size="large" >普通用户</el-radio>
            <el-radio label="2" size="large">管理员</el-radio>
            </el-radio-group>  
         </div>
         <div class="user-box">
         <el-button type="primary" plain  @click="register">注册</el-button>
         <el-button type="success" plain>前往登陆</el-button>
        </div>
        </el-form>
     </div>
     <div>
       <!-- <ul>
         <li v-for="item in topics" :key="item.id"><a href="#">{{ item.title }}</a></li>
       </ul> -->
       {{ data }}
     </div>
   </div>
 
 </template>
 
 <script>
   export default{
     layout:"login",
     data() {
     return {
        Info:{
        username: '', // 账号
       password: '',    // 密码
       role:''
     }
     }
   },
  //  async asyncData ({ $axios, $http}) {
  //      const {data:{ data:topics}} = await $axios.get('https://cnodejs.org/api/v1//topics');
  //      
  //     //  const {data:topics} = await $http.$get('https://cnodejs.org/api/v1//topics');
  //      return {
  //        topics
  //      }
  //  },

   methods: {
    changeValue (e) {
      this.$forceUpdate()
    }
   }
 }
 </script>
 
 <style>
 .login{
   width: 50%;
   margin: 0 auto;
 }
 .title{
   text-align: center;
 }
 .B1{
   width: 50%;
 }
 .B2{
   width: 50%;
 }
 .user-box{
  margin-top: 0.5rem;
}
 </style>