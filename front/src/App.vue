<template>
  <div id="app">
    <the-header-navbar></the-header-navbar>
    <div>
      <router-view></router-view>
    </div>
    <b-container class="bv-example-row">
      <b-row class="text-center mt-4">
        <b-col cols="5">
          <main-thumb-nail></main-thumb-nail>
        </b-col>
        <b-col></b-col>
        <b-col cols="5">
          <main-thumb-nail></main-thumb-nail>
        </b-col>
      </b-row>
    </b-container>
      <!-- <div> -->
      <!-- <b-row class="mt-4">
        <the-thumb-nail></the-thumb-nail>
        <b-col>23</b-col>
      </b-row> -->
      <!-- </div> -->
  </div>
</template>

<script>
import TheHeaderNavbar from "@/components/TheHeaderNavbar";
import MainThumbNail from "@/components/main/MainThumbNail";

export default {
  name: "App",
  components: {
    TheHeaderNavbar,
    MainThumbNail,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

a.router-link-exact-active {
  color: #89bfef;
}

a.router-link-exact-active:hover {
  text-decoration: none;
}
</style>
