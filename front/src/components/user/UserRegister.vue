<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input id="userid" v-model="user.userid" required placeholder="아이디 입력...." ref="userid">
              </b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input type="password" id="userpwd" v-model="user.userpwd" required placeholder="비밀번호 입력...."
                ref="userpwd"></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="email">
              <b-form-input type="email" id="email" v-model="user.email" required placeholder="example@ssafy.com"
                ref="email"></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input id="username" v-model="user.username" required placeholder="이름" ref="username">
              </b-form-input>
            </b-form-group>
            <b-button type="button" variant="success" class="m-1" @click="checkValue(user)">가입하기</b-button>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { joinUser } from "@/api/member";
export default {
  name: "UserRegister",
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        userpwd: null,
        username: null,
        email: null,
      },
    };
  },
  methods: {
    checkValue(user) {
      console.log(user.userid);
      if (user.userid == null || user.userid == "") {
        alert("아이디를 입력해주세요 !");
        this.$refs.userid.focus();
      } else if (user.userpwd == null || user.userpwd == "") {
        alert("비밀번호를 입력해주세요 !");
        this.$refs.userpwd.focus();
      } else if (user.email == null || user.email == "") {
        alert("이메일을 입력해주세요 !");
        this.$refs.email.focus();
      } else if (user.username == null || user.username == "") {
        alert("이름을 입력해주세요 !");
        this.$refs.username.focus();
      } else {
        this.registUser(user);
      }
    },
    registUser(user) {
      joinUser(
        user,
        ({ data }) => {
          let msg = "회원 가입에 실패하였습니다!";
          if (data === "success") {
            msg = "회원 등록이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push('/user/login');
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style>

</style>
