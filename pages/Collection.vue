<template>
    <div>
        <div class="mylike"  v-for="news in mynews" :key="news" v-on:click="todeatil(news.news_id)">
            <el-button type="info" plain class="CC" v-if="news.state==0">保存中</el-button>
            <el-button type="info" plain class="CC" v-if="news.state==1">待审核</el-button>
            <el-button type="info" plain class="CC" v-if="news.state==2">已审核</el-button>
          <div class="Ctext"><h4>{{ news.title }}</h4></div>
          <div class="Cinfo">
            <span>新闻Id:{{news.news_id}}</span>
            <span>类别Id:{{news.category_id}}</span>
            <span>更新时间:{{news.update_time}}</span>
          </div>
          <!-- <div class="Aimg"><img src="~/assets/images/头像 男孩.svg" class="AAimg"></div> -->
        </div>
      </div>
  </template>
  <script>
  import axios from 'axios';
  import Cookie from 'js-cookie';
  export default {
    data() {
  return {
       mynews: [
      {
        state:'',
        news_id:'',
        title:'',
        category_id:'',
        update_time:''
      }
    ],
  }
  },
  created(){
  this.getAll();
  axios.post('/api/ordinary/checklike?',{headers:{token:this.$store.state.token,'platform': 'web'}})
        .then(Response=>{
          console.log(Response);
          if(Response.data.code==200){
            this.mynews=Response.data.data
          }
        })
  },
     methods:{
      todeatil(n){
        Cookie.set('news_id',n);
        this.$router.push('news_detail')
      },
      getAll(){
        console.log("获取所有的新闻");
        console.log(this.$store.state.token);
        console.log(this.$store.state.role);
        console.log(this.$store.state.userId);
        
      }
    },
  
  }
  </script>
  <style> 
  .mylike{
    margin-top: 2rem;
    display: inline-block; 
    margin-bottom: 2rem;
    margin-left: 14vw;
    width: 52.3vw;
    background-color: rgb(255, 255, 255);
    box-shadow: 0 0.1rem 0.5rem  rgb(222, 218, 218);
  }
  .Ctext{
    margin-bottom: 0.5rem;
    margin-top: 0.5rem;
    margin-left: 0.5rem;
    width:45vw;
    /* background-color: green; */
  }
  .Cinfo{
    float: left;
    margin-bottom: 1rem;
    margin-left: 0.5rem;
    margin-right: 0.5rem;
    width:45vw;
    /* background-color: rgb(127, 55, 86); */
  }

  .CC{
    width: 5rem;
    float: right;
    margin-top: 0.5rem;
    margin-right: 0.5rem;
  }
  </style>