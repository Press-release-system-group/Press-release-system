<template>
  <div>
    <Bar />
    <newslist />
    <div>
      <!-- <nuxt-link :to="{name: 'about', params: {id: 3306 }}">params传参</nuxt-link> -->
    </div>

  </div>
 
</template>
<script >
export default {
  name: 'IndexPage',
  methods:{
       increment(step) {
          this.$store.commit('increment', step);
       },
       
       asyncIncrement (step) {
          this.$store.dispatch('asyncIncrement', step);
       }
    }
}
</script>
<style>   
</style>