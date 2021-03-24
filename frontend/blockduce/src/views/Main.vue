<template>


<!-- 
        <div v-if="this.$cookies.get(accesstoken)">
          <div @click="onClickLogout">로그아웃</div>
        </div>

  <router-link v-else to="/login">Login</router-link> -->

<a v-if="isCookie" v-on:click.prevent="deleteCookie"><button>로그아웃</button></a>  
      <a v-else  href="/login"><button>login</button></a>
  
</template>

<script>
// import { mapGetters } from 'vuex';
// import VueCookies from 'vue-cookies';

export default {
    data() {
    return {
      isCookie: null,
    };
  },
    created() {
    this.isCookie = document.cookie;
  },
    computed: {
    // ...mapGetters(['getAccessToken']),
  },
    methods: {
    deleteCookie() {
      this.$cookie.delete("accesstoken");
      this.isCookie = null;
      alert("로그아웃 되었습니다.");
      this.$router.push("/")
    },
  },
//   methods: {
//         intercepter() {
//       if (this.$store.getters.getAccessToken == null) {
//         alert('로그인이 필수 입니다.');
//         this.$router.push('/');
//       }
//     },
// }
}
</script>

<style>

</style>