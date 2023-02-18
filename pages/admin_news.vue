<template>
    <div>
        <adminBar/>
        <div class="admin_news">
         <div class="getstate">
            <label>新闻类别：</label>
          <el-select v-model="category" placeholder="请选择" class="category_select">
                   <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"> </el-option>
           </el-select>
           <el-button slot="append" icon="el-icon-search"></el-button>
         </div>
            <div class="getnews"  v-for="news in navs" :key="news" v-on:click="todeatil">
                <el-select v-model="state" placeholder="审核" class="state_select">
                   <el-option v-for="item in statelist" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                </el-select>
                <div class="gettext">{{ news.name }}</div>
                <div class="getinfo">{{ news.name }}</div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
return {
    state:'',
    category:'',
     navs: [
    {
      name: '掘友们新年快乐~2023年第一次更文挑战正式上线啦！相信大家已经调整好状态，蓄势待发了，2月与掘金一起在技术写作之路「兔飞猛进」吧！',
    }
  ],
  options: [{
          value: '',
          label: '学习'
        }, {
          value: '',
          label: '日常'
        }, {
          value: '',
          label: '美食'
        }, {
          value: '',
          label: '旅游'
        }, {
          value: '1',
          label: '时政'
        },{
          value: '4',
          label: '体育'
        },
        {
          value: '',
          label: '竞赛'
        }],
        statelist:[{
          value: '0',
          label: '待审核'
        }, {
          value: '1',
          label: '审核通过'
        }],
}
},
methods:{
    todeatil(){
      console.log("身份"+this.$store.state.role);
      this.$router.push('news_detail')
    },

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