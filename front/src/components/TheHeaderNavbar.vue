<template>
  <div>
    <b-navbar toggleable="lg" variant="light">
      <b-navbar-brand href="#">
        <router-link :to="{ name: 'main' }">
          <b-img
            :src="require('@/assets/logo.png')"
            id="logo"
            class="d-inline-block align-top"
            alt="logo"
          >
          </b-img>
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item class="px-2" :to="'/'"> 홈</b-nav-item>
          <b-nav-item class="px-2" :to="'/notice'"> 공지사항</b-nav-item>
          <b-nav-item class="px-2" :to="'/house'"> 지도</b-nav-item>
          <b-nav-item class="px-2" :to="'/board'"> 자유게시판</b-nav-item>
          <b-nav-item class="px-2" :to="'/news'"> 뉴스</b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item-dropdown
            id="my-nav-dropdown"
            toggle-class="nav-link-custom"
            right
          >
            <template #button-content>{{ userInfo.username }} 님</template>
            <b-dropdown-item v-if="userInfo.category == 1">
              <router-link
                :to="{ name: 'mypage' }"
                class="link align-self-center"
                >회원 목록</router-link
              >
            </b-dropdown-item>
            <b-dropdown-item>
              <router-link
                :to="{ name: 'mypage' }"
                class="link align-self-center"
                >내정보보기</router-link
              >
            </b-dropdown-item>
            <b-dropdown-divider></b-dropdown-divider>
            <b-dropdown-item variant="danger" @click.prevent="onClickLogout"
              >로그아웃</b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-dropdown-item>
            <router-link :to="{ name: 'login' }" class="link"
              >로그인</router-link
            >
          </b-dropdown-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      console.log(this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped>
#logo {
  width: 120px;
}

.link {
  text-decoration: none;
}
</style>
