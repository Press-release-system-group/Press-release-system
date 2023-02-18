<template>
  <div>
      <div class="myHOME"  v-for="news in mynews" :key="news">
          <!-- <img src="~/assets/images/头像 男孩.svg" class="CCimg"> -->
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
import axios from 'axios'
export default {
  layout:"home",
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
},
   methods:{
    getAll(){
      console.log("获取所有的新闻");
      console.log(this.$store.state.token);
      console.log(this.$store.state.role);
      console.log(this.$store.state.userId);
      axios.get('/api/publisher/getAllSimpleNews?',{headers:{token:this.$store.state.token,'platform': 'web'}})
      .then(Response=>{
        console.log(Response);
        if(Response.data.code==200){
          this.mynews=Response.data.data
        }
   
      })
    }
  }
}
</script>
<style> 
.myHOME{
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
  background-color: green;
}
.Cinfo{
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
.CCimg{
  margin-top: 0.5rem;
  margin-right: 0.5rem;
  float: right;
  width: 5rem;
}
</style>