<template>
  <div class="myNav">
    <nav role="navigation">
      <div id="menuToggle">
        <input type="checkbox" />
        <span></span>
        <span></span>
        <span></span>
        <ul id="menu">
          <div class="box" id="account">
            <router-link class="router" id="Home" to="/">홈</router-link>
          </div>
          <div class="box" id="election">
            <router-link class="router" id="menus" to="/election"
              >투표하기</router-link
            >
          </div>
          <div class="box" id="now">
            <router-link class="router" id="menus" to="/statistic"
              >투표현황</router-link
            >
          </div>
          <div class="box" id="account">
            <router-link class="router" id="menus" to="/account"
              >내 지갑</router-link
            >
          </div>
          <div class="box" id="login">
            <div v-if="isToken">
              <a v-on:click.prevent="deleteToken">로그아웃 </a>
            </div>
            <div v-else>
              <a @click="clickLogin">로그인 </a>
            </div>
          </div>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>

import router from '@/router';


export default {
  data() {
    return {
      isToken: null,
    };
  },
  created() {
    this.isToken = localStorage.getItem('token');
  },
  methods: {

    clickLogin() {
      router.push({ name: 'Login' });
    },
    deleteToken() {
      localStorage.clear();
      // alert(this.$cookies);
      // this.$cookies.remove("auth-token");
      deleteCookie('auth-token');
      window.location.href = 'http://j4b107.p.ssafy.io/';
      alert('로그아웃 되었습니다.');
    },
  },
};

var deleteCookie = function(name) {
document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}

</script>

<style scope>
@font-face {
  font-family: 'account_font';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}

.myNav {
  position: fixed;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
  padding: 0.5rem 1rem;
  z-index: 100;
}
nav {
  height: 65px;
}
.box {
  margin-top: 20px;
}
.router {
  font-family: 'account_font';
  font-size: xx-large;
}

#menuToggle {
  display: flex;
  flex-direction: column;
  position: relative;
  top: 25px;
  left: 25px;
  z-index: 1;
  -webkit-user-select: none;
  user-select: none;
}

#menuToggle input {
  display: flex;
  width: 40px;
  height: 32px;
  position: absolute;
  cursor: pointer;
  opacity: 0;
  z-index: 2;
}

#menuToggle span {
  display: flex;
  width: 29px;
  height: 2px;
  margin-bottom: 5px;
  position: relative;
  background: #000000;
  border-radius: 3px;
  z-index: 1;
  transform-origin: 5px 0px;
  transition: transform 0.5s cubic-bezier(0.77, 0.2, 0.05, 1),
    background 0.5s cubic-bezier(0.77, 0.2, 0.05, 1), opacity 0.55s ease;
}

#menuToggle span:first-child {
  transform-origin: 0% 0%;
}

#menuToggle span:nth-last-child(2) {
  transform-origin: 0% 100%;
}

#menuToggle input:checked ~ span {
  opacity: 1;
  transform: rotate(45deg) translate(-3px, -1px);
  background: #36383f;
}
#menuToggle input:checked ~ span:nth-last-child(3) {
  opacity: 0;
  transform: rotate(0deg) scale(0.2, 0.2);
}

#menuToggle input:checked ~ span:nth-last-child(2) {
  transform: rotate(-45deg) translate(0, -1px);
}

#menu {
  position: absolute;
  width: 250px;
  height: 500px;
  box-shadow: 0 0 10px #85888c;
  margin: -50px 0 0 -50px;
  padding: 50px;
  padding-top: 125px;
  background-color: #f5f6fa;
  -webkit-font-smoothing: antialiased;
  transform-origin: 0% 0%;
  transform: translate(-100%, 0);
  transition: transform 0.5s cubic-bezier(0.77, 0.2, 0.05, 1);
}

#menu li {
  padding: 10px 0;
  transition-delay: 2s;
}

#menuToggle input:checked ~ ul {
  transform: none;
}
</style>
