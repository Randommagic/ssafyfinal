<template>
    <div>
        <div v-for="(item, index) in userInfo.aptBookmark" :key="index">
            {{item.aptcode}}
            </div>
    </div>
  
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
    name: "UserMyPageBookMark",
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    }
}
</script>

<style>

</style>