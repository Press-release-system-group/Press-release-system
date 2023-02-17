<template>
    <div>
        <div><my/></div>
        <div class="edit">
        <div><h3>编辑用户资料</h3></div>
        <div>
            <label>用户名称：</label><el-input placeholder="请输入内容" v-model="username" clearable class="editInput"></el-input>
        </div>
        <div>
            <label>真实姓名：</label><el-input placeholder="请输入内容" v-model="name" clearable class="editInput"></el-input>
        </div>
        <div>
            <label>我的邮箱：</label><el-input placeholder="请输入内容" v-model="email" clearable class="editInput"></el-input>
        </div>
        <div>
            <label>我的电话：</label><el-input placeholder="请输入内容" v-model="phone" clearable class="editInput"></el-input>
        </div>
        <div><el-button plain @click="Upadate">确认更新</el-button></div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
 export default{
   data(){
    return {
    username:'',
    name:'',
    email:'',
    phone:''
   }
 },
 methods:{
    Upadate(){
      console.log("编辑个人资料"+this.$store.state.userId);
      axios.put('/api/common/updateUserInfo?',{user_id:this.$store.state.userId,username:this.username,email:this.email,phone:this.phone,name:this.name},
      {headers:{token:this.$store.state.token,'platform': 'web'}})
      .then(Response=>{
        console.log(Response);
        if(Response.data.code==200){
          this.$message('修改成功!');
          this.$router.push('home')
        }
        else{
          this.$message('修改失败')
        }
      })
    }
 }
}
</script>

<style>
.edit{
    display: inline-block; 
    margin-left: 14vw;
    margin-top: 2rem;
    height: 22rem;
    width:52.5vw;
    background-color: aliceblue;    
    box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
}
.edit div{
    text-align: center;
    margin-top: 0.5rem;
    /* background-color:aqua; */
}
.editInput{
    width:20rem;
    margin-top: 0.5rem;
}

</style>