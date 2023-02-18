<template>
    <div>
        <adminBar/>
        <div>
            <div class="edit">
            <div><h3>增加新闻类别</h3></div>
            <div>
                <label>新建新闻类别：</label><el-input placeholder="请输入内容" v-model="categoryName" clearable class="editInput"></el-input>
            </div>
            <div><el-button v-on:click="submit">确认</el-button></div>
        </div>
    </div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data(){
        return {
            categoryName: '',
        }
    },
    methods:{
        submit(){
            // const customAxios = axios.create({
            //     headers:{
            //         token : this.$store.state.token,
            //         'platform': 'web'
            //     },
            // })
            // console.log(this.categoryName)
            // customAxios.post('/api/admin/addCategory?', {categoryName: this.categoryName})
            let params={
                categoryName: this.categoryName
            };
            this.$axios({
                method: 'POST',
                url: '/api/admin/addCategory',
                params:params
            }).then((result) => {
                
            });
        }
    }
}
</script>

<style>

</style>,