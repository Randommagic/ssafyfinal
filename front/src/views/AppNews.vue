<template>
  <div>
    <li
      class="list__item"
      v-for="(news, index) in newsList"
      :key="index"
    >{{news}}
    </li>
</div>
</template>
<script>
import { mapState, mapActions, mapMutations } from "vuex";

const newsStore = "newsStore";

export default {
  name: "AppNews",
  data() {
    return {
    }
  },
  computed: {
    ...mapState(newsStore, ["newsList"]),
  },
  created() {
    // methods/getNewsList()
    this.getNewsList();
  },
  methods: {
    ...mapActions(newsStore, ["getNews"]),
    ...mapMutations(newsStore, ["SET_NEWS_LIST"]),
    getNewsList() {
      if (this.newsList) this.getNews("부동산"); // store/getNewsList
    },
  }
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 27, 139, 0.3) 30%);
}
</style>
  