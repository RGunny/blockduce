<template>
  <div class="background" style="padding-top:10px;">
    <div class="container py-5 mt-5 bg-light-ivory formatting">
      <h5 class="mt-3">회원가입 인증메일이 발송되었습니다.</h5>
      <h5>이메일을 확인해주세요.</h5>
      <h5 class="bold">{{ email }}</h5>
      <div class="button-container d-flex justify-content-center mt-5 mx-auto">
        <button
          class="btn-green btn"
          type="button"
          @click="resendSignupEmail(signupData, type)"
        >
          인증 메일 재발송하기
        </button>
      </div>
      <div class="button-container d-flex justify-content-center mt-3 mx-auto">
        <button class="btn-beige btn" type="button" @click="goToSite()">
          메일함가기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  name: 'SignupEmail',
  data() {
    return {
      email: this.$route.params.signupEmail.email,
      signupData: {
        data: this.$route.params.signupEmail,
        type: 'signup',
      },
    };
  },
  methods: {
    ...mapActions('accountStore', ['resendSignupEmail']),
    goToSite() {
      let idx = this.email.indexOf('@');
      let site = this.email.slice(idx);
      window.open('https://' + site, '_blank');
    },
  },
};
</script>

<style scoped>
.container {
  width: 30%;
  border-radius: 25px;
  margin-top: 20vh !important;
}

.button-container {
  width: 70%;
}

button {
  width: 100%;
  color: black;
}

.btn-beige {
  color: black !important;
}

.btn-beige {
  color: black !important;
}

.bold {
  font-weight: 600;
}

.background {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  height: 100vh;
  background-image: url('https://user-images.githubusercontent.com/70404643/113249815-55915800-92fa-11eb-989b-e7c56b3df63e.PNG');
  background-position: center;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  background: url('https://user-images.githubusercontent.com/70404643/113249815-55915800-92fa-11eb-989b-e7c56b3df63e.PNG')
    no-repeat center/cover;
}

.formatting {
  margin-top: 20vh !important;
  opacity: 0.9;
}
</style>
