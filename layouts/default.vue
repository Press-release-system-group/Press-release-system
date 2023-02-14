<template>
    <div>
      <header>
        <ToBar />
      </header>
       <Nuxt />
    </div>
  </template>
  <script>
  export default {
  }
</script>
  <style>
  header{
    top: 0;
    position: relative;
    width: 100%;
    height: 2.5rem;
    background-color: rgb(255, 255, 255);
    padding: 1rem;
    box-shadow: 0 0.1rem 1rem  rgb(222, 218, 218);
  }

  a {
    text-decoration: none;
  }

  a.nuxt-link-active{
    font-weight:lighter;
  }
  a.nuxt-link-exact-active {
    color: #000000;
  }    
  </style>