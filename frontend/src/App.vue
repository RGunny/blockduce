<template>
  <div>
    <div id="app">
      <div id="nav" v-if="authToken != null">
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
            <span class="navbar-toggler-icon"></span>
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
                <h4 v-if="showHome" style="margin-top: 8px; color: #ffff;">
                  Home
                </h4>
              </div>

              <div class="check">
                <i
                  class="lni-check-mark-circle size-md"
                  :class="{ 'lni-tada-effect': showCheck }"
                  id="check"
                  @click="showCheck = !showCheck"
                ></i>
                <h4 v-if="showCheck" style="margin-top: 8px; color: #ffff;">
                  Check
                </h4>
              </div>

              <div class="coin">
                <i
                  class="lni-coin size-md"
                  :class="{ 'lni-tada-effect': showCoin }"
                  id="coin"
                  @click="showCoin = !showCoin"
                ></i>
                <h4 v-if="showCoin" style="margin-top: 8px; color: #ffff;">
                  Coin
                </h4>
              </div>

              <div class="user">
                <i
                  class="lni-user size-md"
                  :class="{ 'lni-tada-effect': showUser }"
                  id="user"
                  @click="showUser = !showUser"
                ></i>
                <h4 v-if="showUser" style="margin-top: 8px; color: #ffff;">
                  User
                </h4>
              </div>
            </div>
          </div>
        </nav>
      </div>
      <router-view class="router-view" />
    </div>
    <!-- footer -->
    <div
      class="footer"
      v-if="
        this.$route.name !== 'PostDetail' &&
          this.$route.name !== 'BookDetail' &&
          authToken != null
      "
    >
      <p class="footer-p">© 2021 Copyright: BLOCKDUCE</p>
      <p class="m-0 pb-2">
        <i class="fab fa-github github-color"></i
        ><a href="https://github.com/HoYeonHwang" target="_blank">
          @HoYeonHwang</a
        >
        | <i class="fab fa-github github-color"></i>
        <a href="https://github.com/RGunny" target="_blank"> @RGunny</a>
        | <i class="fab fa-github github-color"></i
        ><a href="https://github.com/upswp" target="_blank"> @upswp</a> |
        <i class="fab fa-github github-color"></i
        ><a href="https://github.com/bourzua" target="_blank"> @bourzua</a> |
        <i class="fab fa-github github-color"></i
        ><a href="https://github.com/junjun0905" target="_blank">
          @junjun0905</a
        >
      </p>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'App',
  data() {
    return {
      showHome: false,
      showCheck: false,
      showCoin: false,
      showUser: false,
      effect: 'lni-tada-effect',
    };
  },
  computed: {
    ...mapState(['myaccount', 'users', 'authToken']),
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
    ...mapActions(['findMyAccount', 'fetchUsers', 'logout']),
    ...mapActions('clubStore', ['fetchClubs']),
    searchUser() {
      if (!this.keyword) {
        this.isActive = false;
        this.searchedUsers = null;
      } else {
        this.isActive = true;
        this.searchedUsers = this.users.filter((user) => {
          return user.nickName.match(this.keyword);
        });
        if (this.searchedUsers.length < 1) {
          this.searchedUsers = null;
        }
      }
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
    this.fetchUsers();
  },
};
</script>

<style scoped>
@media (max-width: 960px) {
  #app {
    display: none;
  }
}

@media (max-width: 960px) {
  .footer {
    display: none;
  }
}

@media (min-width: 960px) {
  #app2 {
    display: none;
  }
}

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  min-height: 100vh;
  position: relative;
}

.footer {
  text-align: center;
  background-color: rgb(34, 34, 34);
  color: white;
  padding-top: 20px;
  height: 100px;
  left: 0;
  bottom: 0;
  width: 100%;
  z-index: 99;
  position: relative;
}

.footer a,
.footer a:link {
  color: white;
  text-decoration: none;
}

.github-color {
  color: white;
}

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
</style>
