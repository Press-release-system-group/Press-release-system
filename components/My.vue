<template>
    <div class="mycard">
    <el-avatar :size="50" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" class="avatar"/>
    <div v-for="o in navs" :key="o" class="info">
    {{o.name}}
    </div>
    <div class="editbutton">
      <el-button plain >编辑资料</el-button>
    </div>
    </div>
  </template>
  <script >
  export default {

  data() {
  return {
       navs: [
      {
        name: '我的用户名',
      },
      {
        name: '我的职业',
      },
    ],
  }
},
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
  }
  .editbutton{
    position: absolute;
    right: 1rem;
    bottom: 1rem;
  }
  </style>