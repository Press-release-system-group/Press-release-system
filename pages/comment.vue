<template>
    <div>
        <div class="mycomment"  v-for="news in  mycomments" :key="news" >
            <el-button type="info" plain class="DD" @click="deleteC(news.comment_id)">删除</el-button>
            <el-button type="info" plain class="CC" v-if="news.state==0">保存中</el-button>
            <el-button type="info" plain class="CC" v-if="news.state==1">已删除</el-button>
          <div class="Ctext"><h4>{{ news.content }}</h4></div>
          <div class="Cinfo">
            <p>新闻名称:{{news.news_title}}Id:{{news.news_id}}</p>
            <p>更新时间:{{news.create_time}}</p>
          </div>
        </div>
      </div>
  </template>
  <script>
  import axios from 'axios';
  import Cookie from 'js-cookie';
  export default {
    layout:"Info",
    data() {
  return {
       mycomments: [
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
      this.$axios.get('/api/ordinary/findMyAllComments', {
        headers:{
          "token": this.$store.state.token
        }
        }
      )
        .then((response)=> {
          console.log(response);
          if(response.data.code==200)
          this.mycomments=response.data.data
        });
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
        
      },
      deleteC(n){
        
        let params={comments_id:n};
        this.$axios({
        method:'post',
        url:'/api/ordinary/deleteMyComments',
        params:params,
        headers:{
          token:this.$store.state.token
        }
      }).then((result)=>{
        console.log(n);
        console.log(result);
        if(result.data.code==200)
        {
            this.$message('删除成功!');
        }
      })
      }
    },
  
  }
  </script>
  <style> 
  .mycomment{
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
  /* .Aimg{
    margin-left: 0;
    background-color: rgb(92, 76, 146);
    float:left;
  } */
  /* .CCimg{
    margin-top: 0.5rem;
    margin-right: 0.5rem;
    float: right;
    width: 5rem;
  } */
  .CC{
    width: 5rem;
    float: right;
    margin-top: 0.5rem;
    margin-right: 0.5rem;
  }
  .DD{
    width: 5rem;
    float: right;
    margin-top: 0.5rem;
    margin-right: 0.5rem;
  }
  </style>