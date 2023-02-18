<template>
    <div>
        <div class="sidebar">
         <div class="side"> <img src="~/assets/images/点赞.svg" class="tubiao"/></div>
         <div class="side"> <img src="~/assets/images/收藏 .svg" class="tubiao"/></div>
         <div class="side"> <img src="~/assets/images/评论.svg" class="tubiao"/></div>
         <div class="side"> <img src="~/assets/images/转发.svg" class="tubiao"/></div>
         <hr>
         <div class="side"> <img src="~/assets/images/举报.svg" class="tubiao"/></div>
        </div>
        <div class="detail">
        <div class="mytitle">
           <h2>新闻标题</h2>  
           <h6>新闻发布者——新闻时间</h6>
         </div>
         <div class="mydetail">
            {{name}}
         </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
 export default{
   data(){
    return {
        name: '掘友们新年快乐~2023年第一次更文挑战正式上线啦！相信大家已经调整好状态，蓄势待发了，2月与掘金一起在技术写作之路「兔飞猛进」吧！',
   }
 },
 created(){

 },
  
}
</script>

<style>
.sidebar{
    margin-top: 10rem;
    margin-left: 10vw;
    float: left;
    width: 5rem;
    background-color: antiquewhite;
}
.side{
    margin-top: 1rem;
    margin-bottom: 0.5rem;
    display:inline-block;
}
.detail{
   
    display: inline-block; 
    margin-left: 2vw;
    margin-top: 1rem;
    height: 45rem;
    width:52.5vw;
    background-color: rgb(255, 255, 255);    
    box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
}
.mytitle{
    /* text-align: center; */
    margin-right: 1rem;
    margin-left: 1rem;
    margin-top: 0.5rem;
    background-color:aqua;
}
.mydetail{
    margin-right: 1rem;
    margin-left: 1rem;
    margin-top: 0.5rem;
    background-color:aquamarine
}
</style>