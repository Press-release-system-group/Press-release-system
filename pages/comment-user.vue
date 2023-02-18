<template>
    <div>
        <div class="admin_news">
         <div class="getstate">
            <label>评论我的：</label>
         </div>
            <div class="getnews" v-for="comments in navs" :key="comments" v-on:click="todeatil">
                <div class="gettext">{{ comments.user_name }}</div>
                <div class="gettext">{{ comments.content }}</div>
                <div class="getinfo">{{ comments.create_time }}</div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import { onMounted } from 'vue'

export default {
  data() {
    return {
        navs: []
    }
  },
  onMounted(){
    this.$axios({
        method: 'POST',
        url: '/api/ordinary/getAllCommentsByNewsId',
        params:{
          news_id : this.$store.state.news_id
        }
    }).then((result) => {
      this.navs = [];
      this.$message("查找成功！");
      this.navs = result.data.data;
    });
  }
}
</script>

<style>
.admin_news{
    display: inline-block; 
    margin-left: 14vw;
    height: 100%;
    width:52.5vw;
    background-color: rgb(255, 255, 255);    
    box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
}
.getstate{
    padding-left: 4rem;
    background-color: rgb(255, 255, 255);    
    box-shadow: 0 0.1rem 0.5rem  rgb(222, 218, 218);
}
.category_select{
    margin-bottom: 1rem;
    margin-top: 1rem;
    width: 40%;
}
.state_select{
    float: right;
    margin-top: 0.5rem;
    margin-right: 0.5rem;
    width: 12%;
}
.getnews{
    width: 50vw;
    display: inline-block; 
    margin-left: 1rem;
    margin-right: 1rem;
    margin-top: 1rem;
    background-color: rgb(255, 255, 255);    
    box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
}
.gettext{
  margin-bottom: 0.5rem;
  margin-top: 0.5rem;
  margin-left: 0.5rem;
  width:42vw;
  background-color: green;
}
.getinfo{
  float: left;
  margin-bottom: 1rem;
  margin-left: 0.5rem;
  margin-right: 0.5rem;
  width:42vw;
  background-color: rgb(127, 55, 86);
}
</style>