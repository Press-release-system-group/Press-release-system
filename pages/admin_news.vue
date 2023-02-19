<template>
    <div>
        <adminBar/>
        <div class="admin_news">
         <div class="getstate">
            <label>新闻状态：</label>
          <el-select v-model="category" placeholder="请选择" class="category_select">
                   <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"> </el-option>
           </el-select>
           <el-button slot="append" icon="el-icon-search" v-on:click="getBriefNews"></el-button>
           <!-- <el-button v-on:click="changeNewsState"></el-button> -->
         </div>
            <div class="getnews" v-for="news in navs" :key="news" v-on:click="todeatil(news.news_id)">
                <el-select v-model="news.state" :key="news" class="state_select" @change="(status_id) => {changeNewsState(news.news_id, status_id)}">
                   <el-option v-for="item in options" :key="item" :label="item.label" :value="item.value"></el-option>
                </el-select>
                <div class="gettext">{{ news.title }}</div>
                <div class="getinfo">{{ news.update_time }}</div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import Cookie from 'js-cookie';
export default {
  data() {
return {
    state: true,
    category:'',
    navs: [
      // {
      //   "category_id": 0,
      //   "news_id": 0,
      //   "state": 0,
      //   "title": "string1",
      //   "update_time": "2023-02-18T11:51:13.181Z"
      // },
      // {
      //   "category_id": 1,
      //   "news_id": 1,
      //   "state": 1,
      //   "title": "string2",
      //   "update_time": "2023-02-18T11:51:13.181Z"
      // },
      // {
      //   "category_id": 2,
      //   "news_id": 2,
      //   "state": 1,
      //   "title": "string3",
      //   "update_time": "2023-02-18T11:51:13.181Z"
      // },
  ],
  options: [{
          value: 0,
          label: '保存中'
        }, {
          value: 1,
          label: '审核中'
        }, {
          value: 2,
          label: '已删除'
        }, {
          value: 3,
          label: '通过'
        }],
}
},

methods:{
  todeatil(n){
    console.log(n)
      Cookie.set('news_id',n);
      this.$router.push('news_detail')
    },
    getBriefNews(){
      this.$axios({
          method: 'POST',
          url: '/api/admin/getSimpleNewsByStatus',
          params:{
            list : this.category
          }
      }).then((result) => {
        this.navs = [];
        console.log(result)
        console.log(result.data.data[0].news_id)
        this.$message("查找成功！");
        this.navs = result.data.data;
      });
    },
    changeNewsState(news_id, status_id){
      this.$axios({
          method: 'POST',
          url: '/api/admin/changeNewsState',
          params:{
            news_id : news_id,
            state : status_id
          }
      }).then((result) => {
        this.$message("修改成功！");
      });
    }
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
</style>