<template>
  <div id="container">
    <house-search-bar></house-search-bar><b-button>test</b-button>
  </div>
</template>

<script>
import HouseSearchBar from '@/components/house/HouseSearchBar';

export default {
  name: "AppMain",
  components: {
    HouseSearchBar,
  },
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}

#container {
  width: 100vw;
  height: 40vh;
  background-color: gray;
}
</style>
