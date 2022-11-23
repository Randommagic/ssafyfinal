<template>
  <div id="container">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show>
          <h3>내정보</h3>
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="6">
        <user-my-page-info></user-my-page-info>
      </b-col>
      <b-col cols="6">
        <user-my-page-book-mark></user-my-page-book-mark>
      </b-col>
    </b-row>
  </div>
</template>

<script>
// import { mapState, mapActions } from "vuex";
// import { removeUser } from "@/api/member.js";
// const memberStore = "memberStore";
import UserMyPageInfo from "@/components/user/UserMyPageInfo";
import UserMyPageBookMark from "@/components/user/UserMyPageBookmark";


export default {
  name: "UserMyPage",
  components: {
    UserMyPageInfo,
    UserMyPageBookMark,
  },
};
</script>

<style>
</style>
