<template>
    <div>
        <div class="mycollection"  v-for="o in navs" :key="o">
            <img src="~/assets/images/头像 男孩.svg" class="AAimg">
            <div class="Atext">{{ o.name }}</div>
          <div class="Ainfo">{{ o.name }}</div>
          <!-- <div class="Aimg"><img src="~/assets/images/头像 男孩.svg" class="AAimg"></div> -->
        </div>
      </div>
  </template>
<script>
  export default {
    layout:"home",
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
</script>
  <style> 
  .mycollection{
    display: inline-block; 
    margin-bottom: 2rem;
    margin-left: 14vw;
    width: 52.3vw;
    background-color: rgb(255, 255, 255);
    box-shadow: 0 0.1rem 0.5rem  rgb(222, 218, 218);
  }
  .Atext{
    margin-bottom: 0.5rem;
    margin-top: 0.5rem;
    margin-left: 0.5rem;
    width:45vw;
    background-color: green;
  }
  .Ainfo{
    float: left;
    margin-bottom: 1rem;
    margin-left: 0.5rem;
    margin-right: 0.5rem;
    width:45vw;
    background-color: rgb(127, 55, 86);
  }
  /* .Aimg{
    margin-left: 0;
    background-color: rgb(92, 76, 146);
    float:left;
  } */
  .AAimg{
    margin-top: 0.5rem;
    margin-right: 0.5rem;
    float: right;
    width: 5rem;
  }
  </style>