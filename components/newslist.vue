<template>
    <div class="box-card">
    <div class="head">
        <span>最新</span>
        <hr/>
    </div>
    <div class="item"  v-for="o in navs" :key="o">
          <img src="~/assets/images/头像 男孩.svg" class="item-img">
          <div class="item-text">{{ o.name }}</div>
        <div class="item-info">{{ o.name }}</div>
        <!-- <div class="Aimg"><img src="~/assets/images/头像 男孩.svg" class="AAimg"></div> -->
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
  .box-card {
    padding: 0;
    margin: 0;
    height: 100vh;
    width: 55vw;
    background-color: rgb(255, 255, 255);
    margin-left: 14vw;
    box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
  }
  .item{
  margin-left: 0.5rem;
  display: inline-block; 
  margin-bottom: 2rem;
  width: 54vw;
  background-color: rgb(255, 255, 255);
  box-shadow: 0 0.1rem 0.5rem  rgb(222, 218, 218);
}
.item-text{
  margin-bottom: 0.5rem;
  margin-top: 0.5rem;
  margin-left: 0.5rem;
  width:45vw;
  background-color: green;
}
.item-info{
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
.item-img{
  margin-top: 0.5rem;
  margin-right: 0.5rem;
  float: right;
  width: 5rem;
}
</style>