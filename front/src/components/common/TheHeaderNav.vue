<!-- 자식이 없는 vue -> TheName.vue -->
<template>
    <div class="header">
      <!-- <router-link to="/">
        <img src="" alt="" />
      </router-link> -->
      <div>
        <router-link to="/">로그인</router-link>
        <router-link to="/search">게시판</router-link>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "TheHeaderNav",
  }
  </script>
  
  <style>
  .header {
    padding: 30px;
    text-align: center;
    box-shadow: 0px 1px 10px rgba(159, 157, 157, 0.3);
  }
  
  a {
    font-weight: bold;
    color: #2c3e50;
  }
  
  a:hover {
    color: #42b983;
  }
  </style>
  