<template>
  <div id="nav">
    <nav
      class="navbar navbar-expand-md navbar-light navbar-bg-color d-flex justify-content-between"
    >
      <router-link class="navbar-brand" to="/">
        <img
          class="img-fluid logo-img"
          style="height: 50px"
          src="https://user-images.githubusercontent.com/70404643/113627029-dd31ea80-969d-11eb-9369-9e3c5760d330.png"
          alt="로고 이미지"
        />
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"> </span>
      </button>

      <div
        class="collapse navbar-collapse d-flex justify-content-between"
        id="navbarSupportedContent"
      >
        <div class="colum">
          <div class="home">
            <i
              class="lni-home size-md "
              :class="{ 'lni-tada-effect': showHome }"
              id="home"
              @click="showHome = !showHome"
            ></i>
            <router-link
              v-if="showHome"
              style="margin-top: 8px; color: #ffff"
              class="menus"
              to="/after/vote"
              >투표하기</router-link
            >
          </div>

          <div class="check">
            <i
              class="lni-check-mark-circle size-md"
              :class="{ 'lni-tada-effect': showCheck }"
              id="check"
              @click="showCheck = !showCheck"
            ></i>
            <router-link
              v-if="showCheck"
              style="margin-top: 8px; color: #ffff"
              class="menus"
              to="/after/now"
              >투표현황</router-link
            >
          </div>

          <div class="coin">
            <i
              class="lni-coin size-md"
              :class="{ 'lni-tada-effect': showCoin }"
              id="coin"
              @click="showCoin = !showCoin"
            ></i>
            <router-link
              v-if="showCoin"
              style="margin-top: 8px; color: #ffff"
              class="menus"
              to="/after/blockduce"
              >블록듀스</router-link
            >
          </div>

          <div class="user">
            <i
              class="lni-user size-md"
              :class="{ 'lni-tada-effect': showUser }"
              id="user"
              @click="showUser = !showUser"
            ></i>
            <router-link
              v-if="showUser"
              style="margin-top: 8px; color: #ffff"
              class="menus"
              to="/after/wallet"
              >내 지갑</router-link
            >
          </div>
          <div id="buttons mt-3">
            <div v-if="isToken">
              <button class="btn login-button" v-on:click.prevent="deleteToken">
                Logout
              </button>
            </div>
            <div v-else>
              <button class="btn login-button" @click="clickLogin">
                Login
              </button>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>
<script>
import router from '@/router';
export default {
  data() {
    return {
      showHome: false,
      showCheck: false,
      showCoin: false,
      showUser: false,
      effect: 'lni-tada-effect',
      isToken: null,
    };
  },
  computed: {
    findClubName() {
      return (clubId) => {
        {
          let name = '이름 미정';
          this.clubs.forEach((club) => {
            if (club.id === clubId) {
              return (name = club.name);
            }
          });
          return name;
        }
      };
    },
  },
  methods: {
    clickLogin() {
      router.push({ name: 'Login' });
    },
    deleteToken() {
      localStorage.clear();
      window.location.reload();
      alert('로그아웃 되었습니다.');
    },
    focusout() {
      setTimeout(this.isKeywordNull, 100);
    },
    isKeywordNull() {
      this.keyword = null;
      this.searchedUsers = null;
    },
    scrollToTop() {
      window.scrollTo(0, 0);
    },
  },
  created() {
    this.isToken = localStorage.getItem('token');
    this.fetchUsers();
  },
};
</script>

<style>
.navbar {
  position: fixed;
  top: 0;
  /* width: 100% */
  left: 0;
  right: 0;
  width: 100%;
  height: 60px;
  background-color: #4e6fc2;
  position: fixed;
  box-shadow: 0.1px 0.1px 15px 0.1px #273c75;
  border-radius: 10px;
  display: flex;
}
#home {
  margin-left: 0;
  margin-top: 2px;
  color: #ffff;
  transition: width 0.5s, background-color 0.5s, border-radius 0.5s;
}
#check {
  margin-left: 0;
  margin-top: 2px;
  color: #ffff;
  transition: width 0.5s, background-color 0.5s, border-radius 0.5s;
}
#coin {
  margin-left: 0;
  margin-top: 2px;
  color: #ffff;
  transition: width 0.5s, background-color 0.5s, border-radius 0.5s;
}
#user {
  margin-left: 0;
  color: #ffff;
  transition: width 0.5s, background-color 0.5s, border-radius 0.5s;
}
.colum {
  display: flex;
  width: 95%;
  height: 40px;
  margin-top: 10px;
  margin-left: 10px;
}
.home {
  width: 100px;
  height: 34px;
  margin-left: 20px;
  display: flex;
}
.check {
  width: 100px;
  height: 34px;
  margin-left: 20px;
  display: flex;
}
.coin {
  width: 100px;
  height: 34px;
  margin-left: 20px;
  display: flex;
}
.user {
  width: 100px;
  height: 34px;
  margin-left: 20px;
  display: flex;
}
.clicked {
  width: 100px;
  height: 34px;
  background-color: #feca57;
  border-radius: 10px;
  transition: background-color 0.3s, border-radius 0.5s, width 0.5s;
}
.login-button {
  float: right;
  color: #fcfcfd;
}

.login-button:hover {
  background-color: #0011ff;
  color: #f8f8f8;
}

.disabled,
.disabled:hover {
  background-color: #3700ff (136, 154, 152, 0.25);
  color: #f8f8f8;
  cursor: inherit;
}

.items:hover {
  cursor: pointer;
  color: #3700ff;
}

.menus {
  /* position: absolute; */

  text-decoration: none;

  width: 70px;
  height: 21px;
  left: 20px;
  top: 56px;

  font-family: Roboto;
  font-style: normal;
  font-weight: normal;
  font-size: 1em;
  line-height: 19px;

  color: #000000;
}
</style>
