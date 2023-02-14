<template>
    <div class="box-card">
    <div v-for="o in navs" :key="o" class="item">
    {{o.name}}
    </div>
    </div>
  </template>
  <script >
  export default {

  data() {
  return {
       navs: [
      {
        name: '我的个人资料！',
      },
      
    ],
  }
},
}
  </script >
  
  <style >
  .box-card {
    margin-top: 1.5%;
    padding: 0;
    height: 30vh;
    width: 52.5vw;
    background-color: rgb(255, 255, 255);
    margin-left: 14vw;
    box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
  }
  </style>