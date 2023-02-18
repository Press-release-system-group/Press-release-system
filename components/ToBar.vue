<template>
  <div>
      <nav class="tobarnav">
        <ul class="bars">
        <li><img src="~/assets/images/宠物.svg" class="logo" /></li>
        <li class="logo_font">校园趣闻</li>
        <nuxt-link v-for="item in navs" :key="item.id" :to="item.link" tag="li" class="bar">{{item.name}}</nuxt-link>
        <li> <div class="container"><input class="search"/><button  class="searchbutton"><img src="~/assets/images/搜索框 .svg"  class="searchimg"/></button></div></li>
        <li>
          <el-dropdown size="large" split-button type="primary" v-if="role=='新闻发布者'">
                               创作者中心
            <template #dropdown>
             <el-dropdown-menu>
              <nuxt-link to="news_edit">
              <el-dropdown-item>
              <span style="display:block;">新闻编辑</span>
              </el-dropdown-item>
              </nuxt-link>
              <nuxt-link to="news_push">
              <el-dropdown-item>
              <span style="display:block;">新闻发布</span>
              </el-dropdown-item>
              </nuxt-link>
              <nuxt-link to="news_edit">
              <el-dropdown-item>
              <span style="display:block;">日常发布</span>
              </el-dropdown-item>
              </nuxt-link>
              <nuxt-link to="drafts">
              <el-dropdown-item>
              <span style="display:block;">草稿箱</span>
              </el-dropdown-item>
              </nuxt-link>
             </el-dropdown-menu>
           </template>
           </el-dropdown>
        </li>
        <li><img src="~/assets/images/会员.svg"  class="tubiao"/></li>
        <li>
          <el-dropdown class="avatar-container">
           <div>
            <img src="~/assets/images/消息.svg" class="tubiao"/>
           </div>
           <el-dropdown-menu slot="dropdown">
           <nuxt-link to="/Info">
            <el-dropdown-item>
               评论
             </el-dropdown-item>
              </nuxt-link>
              <nuxt-link to="/Info">
              <el-dropdown-item>
              <span style="display:block;">点赞</span>
              </el-dropdown-item>
              </nuxt-link>
              <nuxt-link to="/Info">
              <el-dropdown-item>
              <span style="display:block;">关注</span>
              </el-dropdown-item>
              </nuxt-link>
              <nuxt-link to="/Info">
              <el-dropdown-item>
              <span style="display:block;">私信</span>
              </el-dropdown-item>
              </nuxt-link>
              <nuxt-link to="/Info">
              <el-dropdown-item>
              <span style="display:block;">系统消息</span>
              </el-dropdown-item>
              </nuxt-link>
           </el-dropdown-menu>
          </el-dropdown>
        </li>
        <li class="menu">
        <el-dropdown class="avatar-container">
           <div>
            <img src="~/assets/images/头像 女孩.svg" class="tubiao"/>
           </div>
           <el-dropdown-menu slot="dropdown">
           <nuxt-link to="home">
            <el-dropdown-item>
               我的主页
             </el-dropdown-item>
              </nuxt-link>
              <nuxt-link to="login">
              <el-dropdown-item>
              <span style="display:block;"> 登录注册</span>
              </el-dropdown-item>
              </nuxt-link>
           </el-dropdown-menu>
          </el-dropdown>
        </li>
      </ul>
      </nav>
  </div>
</template>

<script>
export default {
name: 'TopBar',
data() {
  return {
      role:this.$store.state.role,
       navs: [
      {
        name: '首页',
        link: '/'
      },
      {
        name: '时政',
        link: '/'
      },
      {
        name: '体育',
        link: '/sports'
      },
      {
        name: '娱乐',
        link: '/game'
      }, 
      {
        name: '学习',
        link: '/study'
      },
      {
        name: '生活',
        link: '/live'
      },
      {
        name: '更多',
        link: '/catch_category'
      }
    ],
  }
},
}
</script >

<style >
.tobarnav{
height: 3rem;
}
.logo_font{
  margin-right: 0.5vw;
  font-size:1.8vw;
  font-weight: bolder;
  font-family:"黑体";
  margin: auto 0;
}
.logo{
  width: 3rem;
}
.searchbutton{
  position: absolute;
  height: 100%;
  margin-left: 6.9rem;
  top:0.02rem
}
.searchimg{
  width: 0.8rem;
  height:100%
}
.search{
  height: 3vh
}
.container{
margin-left: 1rem;
position: relative;
width: 8rem;

background-color: #f1f1f1;
}
.search{
width: 100%;
margin: auto 0;
}
.bars{
margin:0;padding:0;
margin-top: 0rem;
margin-top: auto;
display: flex;
list-style:none
}
.bar :hover{
color: #71777c;
}
.bars li {
  padding: 0 1vw;
  cursor: pointer;
  margin: auto 0;
}
.bars li div{
font-size:0
}
.tubiao{
  margin-left: 1rem;
  height: 2rem;
  width: 3rem;
}
</style>