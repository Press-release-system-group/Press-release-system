<template>
    <div class="box-card">
    <div class="head">
        <span>最新</span>
        <hr/>
    </div>
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
        name: '掘友们新年快乐~2023年第一次更文挑战正式上线啦！相信大家已经调整好状态，蓄势待发了，2月与掘金一起在技术写作之路「兔飞猛进」吧！',
      },
      {
        name: '掘友们新年快乐~2023年第一次更文挑战正式上线啦！相信大家已经调整好状态，蓄势待发了，2月与掘金一起在技术写作之路「兔飞猛进」吧！',
      },
      {
        name: '掘友们新年快乐~2023年第一次更文挑战正式上线啦！相信大家已经调整好状态，蓄势待发了，2月与掘金一起在技术写作之路「兔飞猛进」吧！',
      },
      {
        name: '掘友们新年快乐~2023年第一次更文挑战正式上线啦！相信大家已经调整好状态，蓄势待发了，2月与掘金一起在技术写作之路「兔飞猛进」吧！',
      },
      {
        name: '掘友们新年快乐~2023年第一次更文挑战正式上线啦！相信大家已经调整好状态，蓄势待发了，2月与掘金一起在技术写作之路「兔飞猛进」吧！',
      },
    ],
  }
},

}
  </script >
  
  <style >
  .head{
    width: 100%;
    height: 5vh;
  }
  .head span{
    font-size: large;
    margin-left: 1rem;
  }
  .head hr{
    border-color: #dcdcdc;
  }
  .item{
    position:flex;
    width: 100%;
    margin-bottom: 1rem;
    padding: 1rem 1rem 1rem 1rem;
  }
  .box-card {
    padding: 0;
    margin: 0;
    height: 100vh;
    width: 55vw;
    background-color: rgb(255, 255, 255);
    margin-left: 14vw;
    box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
  }
  </style>