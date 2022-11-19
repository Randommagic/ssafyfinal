<template>
  <div>
    <b-navbar toggleable="lg" variant="light">
      <b-navbar-brand href="#">
        <router-link :to="{ name: 'main' }">
          <b-img :src="require('@/assets/ssafy_logo.png')" id="logo" class="d-inline-block align-top" alt="logo">
          </b-img>
        </router-link>
      </b-navbar-brand>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#" variant="">
            <router-link :to="{ name: 'main' }" class="link">
              홈
            </router-link>
            <router-link :to="{ name: 'board' }" class="m-2 link">
              공지사항
            </router-link>
            <router-link :to="{ name: 'house' }" class="m-2 link">
              지도
            </router-link>
            <router-link :to="{ name: 'board' }" class="m-2 link">
              자유게시판
            </router-link>
          </b-nav-item>
        </b-navbar-nav>



        <!-- 관리자 -->
        <b-navbar-nav class="ml-auto" v-if="userInfo">        
          <b-nav-item-dropdown id="my-nav-dropdown" toggle-class="nav-link-custom" right>
            <template #button-content>{{userInfo.username}} 님</template>
            <b-dropdown-item v-if="userInfo.category == 1">
              <router-link :to="{ name: 'mypage' }" class="link align-self-center">회원 목록</router-link>
            </b-dropdown-item>
            <b-dropdown-item>
              <router-link :to="{ name: 'mypage' }" class="link align-self-center">내정보보기</router-link>
            </b-dropdown-item>
            <b-dropdown-divider></b-dropdown-divider>
            <b-dropdown-item variant="danger" @click.prevent="onClickLogout">로그아웃</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <!-- 일반 유저-->
        <!-- before login -->
        <b-navbar-nav class="ml-auto" v-else>
            <b-dropdown-item>
              <router-link :to="{ name: 'login' }" class="link">로그인</router-link>
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
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
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
