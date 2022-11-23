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
        <user-my-page-info></user-my-page-info>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { removeUser } from "@/api/member.js";
import UserMyPageInfo from "@/components/user/UserMyPageInfo";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {
    UserMyPageInfo,
  },
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
  },
};
</script>

<style></style>
