<template>
    <div class="mycard">
    <el-avatar :size="50" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" class="avatar"/>
    <div v-for="my in Info" :key="my" class="info">
      <div v-if="my.user_id==myInfo.user_id">
        {{my.username}}{{ my.phone }}
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
        user_id:'',
        username:'',
        password:'',
        role:'',
        email:'',
        phone:'',
        name:''      
      },
    ],
  }
},
 async fetch() {
    this.getUserInfo();
    //  const {data} = await this.$axios.$get('/api/admin/findAllUser');
   },
   methods:{
    edit(){
      this.$router.push('editInfo')
    },
    getUserInfo(){
      console.log("获取个人信息");
      console.log(this.$store.state.token);
      axios.get('/api/admin/findAllUser?',{headers:{token:this.$store.state.token,'platform': 'web'}})
      .then(Response=>{
        console.log(Response);
        if(Response.data.code==200){
          
        }
   
      })
    }
  }
}
  </script >
  <style >
  .avatar{
    margin-left: 1rem;
  }
  .mycard {
    position: relative;
    margin-top: 1.5%;
    height:15rem;
    width: 52.5vw;
    background-color: rgb(255, 255, 255);
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