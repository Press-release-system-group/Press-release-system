<template>
    <div class="infoB">
        <nav >
          <ul class="infobars">
          <nuxt-link v-for="item in navs" :key="item.id" :to="item.link" tag="li" class="infobar">{{item.name}}</nuxt-link>
        </ul>
        </nav>
    </div>
  </template>
  <script>
  export default {
  name: 'TopBar',
  data() {
    return {
         navs: [
        {
          name: '评论',
          link: 'comment'
        },
        {
          name: '点赞',
          link: 'Collection'
        },
        {
          name: '关注',
          link: 'attention'
        },
        {
          name: '私信',
          link: 'letter'
        }, 
        {
          name: '系统消息',
          link: 'SystemMessages'
        }
      ],
    }
  },

  }
  </script >
  
  <style >
.infoB{
    position: relative;
    width: 101.3vw;
    background-color: rgb(255, 255, 255);
    box-shadow: 0 0.1rem 0.5rem  rgb(222, 218, 218);
}
  nav{
  height: 3rem;
  }
  .infobars{
  margin:0;padding:0;
  margin-top: 0rem;
  margin-top: auto;
  display: flex;
  list-style:none
  }
  .infobar :hover{
  color: #71777c;
  }
  .infobars li {
    padding: 1vw 1vw;
    cursor: pointer;
  }

  </style>