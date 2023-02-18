<template>
    <div>
        <div class="edit">
        <div><h3>新闻编辑</h3></div>
        <div>
            <label>新闻标题：</label><el-input placeholder="请输入新闻标题" v-model="title" clearable class="editInput"></el-input>
        </div>
        <div>
          <label>新闻类别：</label>
          <el-select v-model="category" placeholder="请选择" class="edit_select">
                   <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"> </el-option>
           </el-select>
        </div>
        <div>
          <label>新闻内容：</label><el-input type="textarea"  :autosize="{ minRows: 10, maxRows: 20}" placeholder="请输入新闻内容" v-model="content" class="edit_textarea"></el-input>
        </div>
        <div><el-button plain @click="todrafts">草稿箱</el-button></div>
        <div><el-button plain @click="Upadate">确认保存</el-button></div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
 export default{
   data(){
    return {
    title:'',
    category:'',
    content:'',
    options: [{
          value: '学习',
          label: '学习'
        }, {
          value: '生活',
          label: '生活'
        }, {
          value: '娱乐',
          label: '娱乐'
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
 methods:{
    Upadate(){
      console.log("发布新闻"+this.$store.state.userId+"&"+this.category);
      axios.post('/api/publisher/createNews?',{category:this.category,title:this.title,content:this.content},{headers:{token:this.$store.state.token,'platform': 'web'}})
      .then(Response=>{
        console.log(Response);
        if(Response.status==200){
          this.$message('保存成功!');
          this.$router.push('home')
        }
        else{
          this.$message('保存失败')
        }
      })
    },
    todrafts(){
      this.$router.push('drafts')
    }
 }
}
</script>

<style>
.edit{
    display: inline-block; 
    margin-left: 14vw;
    margin-top: 0.5rem;
    height: 35rem;
    width:52.5vw;
    background-color: aliceblue;    
    box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
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