<template>
    <div class="mynews">
        <nav >
          <ul class="mybars">
          <nuxt-link v-for="item in navs" :key="item.id" :to="item.link" tag="li" class="mybar">{{item.name}}</nuxt-link>
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
          name: '动态',
          link: 'home'
        },
        {
          name: '关注',
          link: 'MyAttention'
        },
        {
          name: '点赞',
          link: 'MyCollection'
        }, 
      ],
    }
  },

  }
  </script >
  
  <style >
.mynews{
    position: relative;
    margin-left: 14vw;
    width: 52.3vw;
    background-color: rgb(255, 255, 255);
    box-shadow: 0 0.1rem 0.5rem  rgb(222, 218, 218);
}
  nav{
  height: 3rem;
  }
  .mybars{
  margin:0;padding:0;
  margin-top: 0rem;
  margin-top: auto;
  display: flex;
  list-style:none
  }
  .mybar :hover{
  color: #71777c;
  }
  .mybars li {
    padding: 1vw 1vw;
    cursor: pointer;
  }
  </style>