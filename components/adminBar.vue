<template>
    <div class="adminBar">
        <nav >
          <ul class="adminbars">
          <nuxt-link v-for="item in adminbars" :key="item.id" :to="item.link" tag="li" class="adminB">{{item.name}}</nuxt-link>
        </ul>
        </nav>
    </div>
  </template>
  <script>
  export default {
  name: 'TopBar',
  data() {
    return {
         adminbars: [
        {
          name: '新闻管理',
          link: 'admin_news'
        },
        {
          name: '类别管理',
          link: 'admin_state'
        },
      ],
    }
  },

  }
  </script >
  
  <style >
.adminBar{
    position: relative;
    width: 101.3vw;
    background-color: rgb(255, 255, 255);
    box-shadow: 0 0.1rem 0.5rem  rgb(222, 218, 218);
}
  nav{
  height: 3rem;
  }
  .adminbars{
  margin:0;padding:0;
  margin-top: 0rem;
  margin-top: auto;
  display: flex;
  list-style:none
  }
  .adminB :hover{
  color: #71777c;
  }
  .adminbars li {
    padding: 1vw 1vw;
    cursor: pointer;
  }

  </style>