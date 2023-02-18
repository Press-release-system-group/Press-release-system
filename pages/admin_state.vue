<template>
    <div>
        <adminBar/>
        <div>
            <div class="edit">
            <div class="getnews" v-for="cate in navs" :key="cate" v-on:click="todeatil">
                <div class="gettext">{{ cate.category_id }} : {{ cate.name }}</div>
                <div class="getinfo">{{ cate.create_time }}</div>
            </div>

            <div><h3>增加新闻类别</h3></div>
            <div>
                <label>新建新闻类别：</label><input placeholder="请输入内容" v-model="categoryName" clearable class="editInput"/>
            </div>
            <div><el-button v-on:click="submitCreate">确认</el-button></div>

            <div><h3>删除新闻类别</h3></div>
            <div>
                <label>删除新闻类别：</label><input placeholder="请输入删除序列，如: 0,1,2,3,..." v-model="delList" clearable class="editInput"/>
            </div>
            <div><el-button v-on:click="submitDelete">确认</el-button></div>
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
            delList: '',
            navs: [],
        }
    },
    mounted(){
        this.$axios.post('/api/ordinary/newscategory', {
            news_id:this.$store.state.news_id
        },{
            headers:{ "token": this.$store.state.token}
        }).then((response)=> {
            this.navs = response.data.data;
        });
    },
    methods:{
        submitCreate(){
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
        },
        submitDelete(){
            let array = this.delList.split(',');
            for (let id in array){
                if (array[id] == '') continue;
                this.$axios({
                    method: 'POST',
                    url: '/api/admin/changeCagegory',
                    params:{
                        category_id: array[id]
                    }
                }).then((result) => {
                    console.log(result);
                    this.$message("成功删除类别" + id);
                });
            }
        }
    }
}
</script>

<style>

</style>,