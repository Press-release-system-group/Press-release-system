<template>
    <div>
        <div class="darfs">
        <div><h3>草稿箱</h3></div>
        <div>
            <label>新闻标题：</label><el-input placeholder="请输入新闻标题" v-model="newsdetail.title" clearable class="editInput"></el-input>
        </div>
        <div>
          <label>新闻类别：</label>
          <el-select v-model="newsdetail.category_name" placeholder="请选择" class="edit_select">
                   <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
           </el-select>
        </div>
        <div>
          <label>新闻内容：</label><el-input type="textarea"  :autosize="{ minRows: 10, maxRows: 20}" placeholder="请输入新闻内容" v-model="newsdetail.content" class="edit_textarea">{{ newsdetail.content }}</el-input>
        </div>
        <div><el-button plain @click="Save">确认保存</el-button></div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
 export default{
   data(){
    return {
    newsdetail: [
      {
      }
    ],
    options: [{
          value: '学习',
          label: '学习'
        }, {
          value: '日常',
          label: '日常'
        }, {
          value: '美食',
          label: '美食'
        }, {
          value: '旅游',
          label: '旅游'
        }, {
          value: '时政',
          label: '时政'
        },{
          value: '体育',
          label: '体育'
        },
        {
          value: '竞赛',
          label: '竞赛'
        }],
   }
 },
 created(){
    this.pdetail()
 },
 methods:{
   Save(){
    let data={
        news_id:this.newsdetail.news_id,
        title:this.newsdetail.title,
        content:this.newsdetail.content,
        "category":this.newsdetail.category_name
      };
      this.$axios.put('/api/publisher/saveNews', data,{
        headers:{
          "token": this.$store.state.token
        }
        }
      )
        .then((response)=> {
          console.log(response);
          if(response.data.code==200)
          {
            this.$message('保存成功!');
            this.$router.push('home')
          }
        });
    },
    pdetail(){
      let data={
        news_id:this.$store.state.news_id
      };
      this.$axios.post('/api/publisher/getNews', data,{
        headers:{
          "token": this.$store.state.token
        }
        }
      )
        .then((response)=> {
          console.log(response);
          this.newsdetail=response.data.data
        });
    },
 }
}
</script>

<style>
.darfs{
    text-align: center;
    display: inline-block; 
    margin-left: 14vw;
    margin-top: 0.5rem;
    height: 35rem;
    width:52.5vw;
    background-color: aliceblue;    
    box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
}
.darfs div{
    margin-top: 0.8rem;
}
.edit div{
    text-align: center;
    margin-top: 0.5rem;
    /* background-color:aqua; */
}
.edit_textarea{
  width: 20rem;
  
}
.edit_select{
  width: 20rem;
}
.editInput{
    width:20rem;
    margin-top: 0.5rem;
}

</style>