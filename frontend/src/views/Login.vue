<template>

  <div>
    <div>로그인</div>
    <div class="line"></div>
    <div class="login">
      <vs-input
        class="id"
        color="#7d33ff"
        v-model="value1"
        placeholder="ID" />

      <vs-input
        color="#7d33ff"
        v-model="value2"
        placeholder="PASSWORD" />

    </div>
    <div class="purplebutton1">
      <vs-button
        class="purplebutton2"
        color="#7d33ff"
        relief
        :active="active == 5"
        @click="active = 5"
        >
        블록듀스 ID로 로그인
      </vs-button>
    </div>
    <img class="kakao" src="@/assets/kakao_login.jpg" @click="login()"/>
  </div>
</template>
<script>
// import { mapGetters } from 'vuex';
export default {

  methods: {
    //     intercepter() {
    //   if (this.$store.getters.getAccessToken == null) {
    //     alert('로그인이 필수 입니다.');
    //     this.$router.push('/');
    //   }
    // },

    login() {
      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=360325f103f39664cd6c418590ff659c&redirect_uri=http://localhost:3000/kakaologin&response_type=code"
      );
    },
  },


  data:() => ({
        value1: '',
        value2: '',
        active: 0
      })

}

</script>

<style>

.login {
  margin-left: 100px;
  margin-top: 50px;
  margin-bottom: 100px;
  
}

.id {
  margin-bottom: 10px;
}

.kakao {
  width: 15rem;
  margin-top: 1rem;
}

.purplebutton1 {
  /* margin-left: 200px;   */
  margin: 0 auto;
  text-align: -webkit-center;
}

.purplebutton2 {
  width: 15rem;
}

</style>
