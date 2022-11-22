<template>
  <div id="container">
  </div>
</template>

<script>

export default {
  name: "AppMain",
  components: {
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
