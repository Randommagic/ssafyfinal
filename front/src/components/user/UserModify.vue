<template>
    <b-modal id="modify-form" hide-footer>
        <template #modal-title>
            회원정보수정
        </template>
        <div class="d-block text-center">
            <b-form id="join_form">
                <b-form-group label-cols="4" label-cols-lg="2" label-size="sm" label="아이디" label-for="userid">
                    <b-form-input type="text" id="userid" v-model="changeUser.userid" required readonly></b-form-input>
                </b-form-group>
                <b-form-group label-cols="4" label-cols-lg="2" label-size="sm" label="이름" label-for="username">
                    <b-form-input type="text" id="username" v-model="changeUser.username" required placeholder="이름 입력"
                        ref="username"></b-form-input>
                </b-form-group>
                <b-form-group label-cols="4" label-cols-lg="2" label-size="sm" label="이메일" label-for="email">
                    <b-form-input type="email" id="email" v-model="changeUser.email" required
                        placeholder="example@ssafy.com" ref="email"></b-form-input>
                </b-form-group>
            </b-form>
        </div>
        <div>
        <b-button-group>
            <b-button @click="checkValue(changeUser)">Sign Up</b-button>
            <b-button block @click="$bvModal.hide('modify-form')">Close Me</b-button>
        </b-button-group>
    </div>
    </b-modal>
</template>


<script>
import { mapState, mapGetters } from "vuex";
const memberStore = "memberStore";

export default {
    name: "UserModify",
    data() {
        return {
            changeUser: {
                userid: "",
                username: "",
                email: "",
            },
        }
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
        ...mapGetters(memberStore, {
            user: "checkUserInfo",
        }),
    },
    created() {
        this.changeUser.userid = this.userInfo.userid;
        this.changeUser.username = this.userInfo.username;
        this.changeUser.email = this.userInfo.email;
    }
}
</script>

<style>

</style>