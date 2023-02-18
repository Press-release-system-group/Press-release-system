<template>
    <div>
        <div class="getComments">
         <div class="getstate">
            <label>评论我的：</label>
         </div>
            <div class="getnews" v-for="comments in navs" :key="comments" v-on:click="todeatil">
                <el-button type="info" plain class="DD" @click="deleteC(comments.comment_id)">删除</el-button>
                <div class="gettext">{{ comments.user_name }}:</div>
                <div class="gettext">{{ comments.content }}</div>
                <div class="getinfo">{{ comments.create_time }}</div>
            </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
  data() {
    return {
        role:this.$store.state.role,
        navs: []
    }
  },
  mounted(){
    console.log(this.role);
    if(this.role=="普通用户"){
      this.$axios({
        method: 'POST',
        url: '/api/ordinary/getAllCommentsByNewsId',
        params:{
          news_id : this.$store.state.news_id
        }
    }).then((result) => {
      console.log(result)
      this.navs = [];
      if(result.data.code==200)
      {
      this.$message("查找成功！");
      this.navs = result.data.data;
      }
      else{
        this.$message("查找失败！");
      }
    });
    }
    else {
      this.$axios({
        method: 'POST',
        url: '/api/publisher/findAllCommentsOfMe',
        params:{
          news_id : this.$store.state.news_id
        },
        headers:{
          token:this.$store.state.token
        }
    }).then((result) => {
      console.log(result)
      this.navs = [];
      if(result.data.code==200)
      {
      this.$message("查找成功！");
      this.navs = result.data.data;
      }
      else{
        this.$message("查找失败！");
      }
    });
    }
  },
  methods:{
    deleteC(n){
      console.log(n);
      this.$axios({
        method: 'POST',
        url: '/api/publisher/deleteComment',
        params:{
          comment_id  :n
        },
        headers:{
          token:this.$store.state.token
        }
    }).then((result) => {
      console.log(result)
      if(result.data.code==200)
      {
        this.$message("删除成功！");
      }
      else{
        this.$message(result.data.msg+"，所以删除失败！");
      }
    });
    }
  }
}
</script>

<style>
.getComments{
    margin-top: 1rem;
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
  /* background-color: green; */
}
.getinfo{
  float: left;
  margin-bottom: 1rem;
  margin-left: 0.5rem;
  margin-right: 0.5rem;
  width:42vw;
  /* background-color: rgb(127, 55, 86); */
}
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