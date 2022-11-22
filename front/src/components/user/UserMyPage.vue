<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show>
          <h3>내정보</h3>
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead> 내 정보 확인페이지입니다. </template>

          <hr class="my-4" />

          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col>
              <b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col>
              <b-col cols="4" align-self="start">{{ userInfo.username }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col>
              <b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col>
              <b-col cols="4" align-self="start">{{ userInfo.joindate }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button variant="primary" to="/user/" class="mr-1">정보수정</b-button>
          <b-button variant="danger" @click="checkMsg()">회원탈퇴</b-button>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { removeUser } from "@/api/member.js";
const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    checkMsg() {
      if (!confirm("정말 탈퇴하시겠습니까?")) return;

      this.deleteUser();
    },
    deleteUser() {
      removeUser(
        this.userInfo.userid,
        ({ data }) => {
          let msg = "회원 탈퇴 중 오류가 발생했습니다 !";
          if (data === "success") {
            msg = "회원 탈퇴가 완료되었습니다.";
          }
          alert(msg);
          this.userLogout(this.userInfo.userid);
          sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
          sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
          if (this.$route.path != "/") this.$router.push({ name: "main" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
  }
};
</script>

<style>

</style>
