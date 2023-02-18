<template>
    <div class="box-card">
    <div class="head">
        <span>最新</span>
        <hr/>
    </div>
    <div class="item"  v-for="news in newslist" :key="o" v-on:click="todeatil">
          <img src="~/assets/images/头像 男孩.svg" class="item-img">
          <div class="item-text">{{ news.name }}</div>
        <div class="item-info">{{ news.name }}</div>
      </div>
    </div>
  </template>
  <script>
import axios from 'axios';
  export default {
  data() {
  return {
       newslist: [
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
    categorylist:[1]
  }
},
created(){
  this.load();
},
  methods:{
    todeatil(){
      this.$router.push('news_detail')
    },
    load(){
      axios.post('/api/ordinary/briefnews?',{categorylist:4},{headers:{token:this.$store.state.token,'platform': 'web'}})
      .then(Response=>{
        console.log(Response);
        console.log(Response.headers.token);
        if(Response.data.code==200){
          console.log(Response.data.data.user_id+"&"+this.$store.state.userId+"&"+this.$store.state.role+this.$store.state.token)
          this.$router.push('/')
        }
        else{
          this.$message(Response.data.msg)
        }
      })
    }}
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