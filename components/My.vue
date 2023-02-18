<template>
    <div class="mycard">
    <el-avatar :size="50" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" class="avatar"/>
    <div>
      <div class="myinfo">
        <p>用户名:{{Info.username}}</p>
        <p>真实姓名:{{ Info.name}}</p>
        <p>手机号:{{ Info.phone }}</p>
        <p>邮箱:{{ Info.email}}</p>
        <p>角色:{{ Info.role}}</p>
      </div>
    </div>
    <div class="editbutton">
      <el-button plain @click="edit">编辑资料</el-button>
    </div>
    </div>
  </template>
  <script >
  import axios from 'axios'
  export default {
  data() {
  return {
       myInfo: [
      {
        user_id:'',
        username:'',
        password:'',
        role:'',
        email:'',
        phone:'',
        name:''      
      },
    ],
    Info: [
      {
        // user_id:'',
        // username:'',
        // password:'',
        // role:'',
        // email:'',
        // phone:'',
        // name:''      
      },
    ],
  }
},
   mounted(){
    console.log(this.role);
    this.$axios({
        method: 'get',
        url: '/api/common/getUserInfo',
        headers:{
          token:this.$store.state.token
         }}).then((result) => {
         console.log(result)
       if(result.data.code==200)
       this.Info=result.data.data

});
  },
   methods:{
    edit(){
      this.$router.push('editInfo')
    },
     getUserInfo(){
      console.log("获取个人信息");
      this.$axios({
        method: 'get',
        url: '/api/common/getUserInfo',
        headers:{
          token:this.$store.state.token
         }

}).then((result) => {

console.log(result)

if(result.data.code==200)

this.Info=result.data.data

});
    }
  }
}
  </script >
  <style >
  .avatar{
    margin-left: 1rem;
  }
  .myinfo{
    margin-bottom: 1rem;
    margin-left: 1rem;
   
  }
  .mycard {
    display: inline-block;
    position: relative;
    margin-top: 1.5%;
    height:16rem;
    width: 52.5vw;
   
    margin-left: 14vw;
    box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
  }
  .info{
    display: inline;
    background-color: #4e2b2b;
  }
  .editbutton{
    position: absolute;
    right: 1rem;
    bottom: 1rem;
  }
  </style>