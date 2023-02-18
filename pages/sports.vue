<template>
  <div>
    <Bar />
    <div class="box-card">
    <div class="head">
        <span>最新</span>
        <hr/>
    </div>
    <div class="item"  v-for="news in newslist" :key="o" v-on:click="todeatil(news.news_id)">
          <!-- <img src="~/assets/images/头像 男孩.svg" class="item-img"> -->
          <div class="item-text"><h4>{{ news.title}}</h4></div>
        <div class="item-info"> 
          <span>新闻Id:{{news.news_id}}</span>
          <span>类别Id:{{news.category_id}}</span>
          <span>更新时间:{{news.update_time}}</span>
        </div>
      </div>
    </div>
    <div>
      <!-- <nuxt-link :to="{name: 'about', params: {id: 3306 }}">params传参</nuxt-link> -->
    </div>

  </div>
 
</template>
<script>
import axios from 'axios';
import Cookie from 'js-cookie';
  export default {
  data() {
  return {
       newslist: [
      {
        news_id:'',
        title:'',
        category_id:'',
        update_time:''
      }
    ],
    categorylist:[1]
  }
},
created(){
  this.load()
},
  methods:{
    todeatil(n){
      Cookie.set('news_id',n);
      this.$router.push('news_detail')
    },
    load(){
      let params={categorylist: 4};
      this.$axios({
        method:'post',
        url:'/api/ordinary/briefnews',
        params:params
      }).then((result)=>{
        console.log(result);
        this.newslist=result.data.data;
      })
    }
    }
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
  /* background-color: green; */
}
.item-info{
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
.item-img{
  margin-top: 0.5rem;
  margin-right: 0.5rem;
  float: right;
  width: 5rem;
}
</style>