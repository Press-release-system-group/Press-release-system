<template>
   <div>
    <div class="login">
      <h2>校园趣闻</h2>
      <form>
        <div class="user-box">
          <el-input v-model="username" placeholder="Please input" @input="changeValue" class="B1"/>
          <label>用户名</label>
        </div>
        <div class="user-box">
          <el-input v-model="password" type="password" placeholder="Please input password" show-password class="B2"/>
          <label>密码</label>
        </div>
        <div class="user-box">
          <el-button type="success" plain>登陆</el-button>
          <el-button type="primary" plain><nuxt-link to="register">注册</nuxt-link></el-button>
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
      // const {data:topics} = await $axios.$get('https://cnodejs.org/api/v1//topics');
     //  const {data:topics} = await $http.$get('https://cnodejs.org/api/v1//topics');
      return {
        topics
      }
  },
  methods: {
    changeValue (e) {
      this.$forceUpdate()
    }
  }
}
</script>

<style>
.login{
  width: 50%;
  margin: 0 auto;
}
.title{
  text-align: center;
}
.B1{
  width: 50%;
}
.B2{
  width: 50%;
}
.user-box{
  margin-top: 0.5rem;
}

</style>