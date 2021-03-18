<template>
  <div class="about">
    <img src="../assets/kakao_login_large_wide.png" @click="login()" />
    <br />
  </div>
</template>
<script>

export default {
  methods: {
    login() {
      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=360325f103f39664cd6c418590ff659c&redirect_uri=http://localhost:3000/kakaologin&response_type=code"
      );
    },
  },
};
</script>