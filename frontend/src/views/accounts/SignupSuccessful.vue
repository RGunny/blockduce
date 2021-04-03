<template>
  <div class="background">
    <div class="container py-5 mt-5 bg-light-ivory formatting">
      <h3>회원가입 완료</h3>
      <h5 class="mt-3">
        회원가입이 완료되었습니다.<br />
        로그인해주세요.
      </h5>
      <div class="buttons mt-5">
        <button class="btn login-button" @click="clickLogin">로그인하기</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SignupSuccessful',
  methods: {
    clickLogin() {
      this.$router.push({ name: 'Login' });
    },
  },
};
</script>

<style scoped>
.container {
  width: 30%;
  border-radius: 25px;
}

h3 {
  color: #3700ff;
  font-weight: 800;
}

h5 {
  color: black;
}

.login-button {
  background-color: #3743ac;
  color: #f8f8f8;
  width: 70%;
  /* font-size: 10px; */
}

.login-button:hover {
  background-color: #0011ff;
  color: #f8f8f8;
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
