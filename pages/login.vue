<template>
   <div>
    <div class="title"><h2>校园趣闻</h2></div>
    <div class="login">
      <form>
        <div class="user-box">
          <el-input v-model="username" placeholder="Please input" @input="changeValue" class="B1"/>
          <label>账号</label>
        </div>
        <div class="user-box">
          <el-input v-model="password" type="password" placeholder="Please input password" show-password class="B2"/>
          <label>密码</label>
        </div>
        <div class="user-box">
          <el-button type="success" :plain="true" @click="login">登陆</el-button>
          <nuxt-link to="register"><el-button type="primary" plain>注册</el-button></nuxt-link>
        </div>
      </form>
    </div>
    <div>
      <ul>
        <li v-for="item in topics" :key="item.id"><a href="#">{{ item.title }}</a></li>
      </ul>
    </div>
  </div>

</template>
<script>
import axios from 'axios';
import Cookie from 'js-cookie';
  export default{
    layout:"login",
    data() {
    return {
      username: '', // 账号
      password: '',    // 密码
    }
  },
  async asyncData ({ $axios, $http}) {
      const {data:{ data:topics}} = await $axios.get('https://cnodejs.org/api/v1//topics');
      return {
        topics
      }
  },
  methods: {
    changeValue (e) {
      this.$forceUpdate()
    },
    login(){
      console.log("登陆"+this.role+this.username);
      axios.post('/api/common/login',{username:this.username,password:this.password})
      .then(Response=>{
        console.log(Response);
        console.log(Response.headers.token);
        if(Response.data.code==200){
          this.$message('登陆成功!');
          // this.$store.commit('updateUserId',Response.data.data.user_id);
          // this.$store.commit('updateRole',Response.data.data.role);
          // this.$store.commit('updateToken',Response.headers.token);
        //    设置token
        // this.updateToken(token);
        // 存储token到cookie
          Cookie.set('token',Response.headers.token) ;
          Cookie.set('userId',Response.data.data.user_id) ;
          Cookie.set('role',Response.data.data.role) ;
          console.log(Response.data.data.user_id+"&"+this.$store.state.userId+"&"+this.$store.state.role+this.$store.state.token)
          if(Response.data.data.role=='普通用户'||Response.data.data.role=='新闻发布者')this.$router.push('/')
          else{this.$router.push('admin')}
        }
        else{
          this.$message('登陆失败')
        }
      })
    },
  }
}
</script>

<style>
.login{
  width: 50%;
  height: 20rem;
   margin: 0 auto;
   background-color: rgb(255, 255, 255);
   box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
}
.title{
  text-align: center;
  margin-top: 5rem;
}
.B1{
  width: 50%;
}
.B2{
  width: 50%;
}
.user-box{
  padding-top: 2rem;
  padding-bottom: 0.5rem;
  text-align: center;
  /* background-color: aqua; */
  margin-top: 1rem;
}

</style>