<template>
    <div>
        <nav >
          
        </nav>
    </div>
  </template>
  <script >
  export default {
  name: 'TopBar',
  data() {
    return {
         navs: [
        {
          name: '首页',
          link: '/'
        },
        {
          name: '沸点',
          link: '/hot'
        },
        {
          name: '课程',
          link: '/course'
        },
        {
          name: '直播',
          link: '/live'
        }, 
        {
          name: '直播',
          link: '/live'
        },
        {
          name: '直播',
          link: '/live'
        },
        {
          name: '直播',
          link: '/live'
        }
      ],
    }
  },

  }
  </script >
  
  <style >
  nav{
  height: 3rem;
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
  
  </style>