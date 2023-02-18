<template>
    <div>
        <div>
            <div class="edit">
            <div><h3>新闻类别</h3></div>
        </div>
        <div class="getnews" v-for="cate in navs" :key="cate" v-on:click="todeatil">
                <div class="gettext">{{ cate.name }}</div>
                <div class="getinfo">{{ cate.create_time }}</div>
        </div>
    </div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data(){
        return {
            navs: []
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
}
</script>

<style>

</style>,