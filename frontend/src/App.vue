<template>
  <div id="app">
    <div id="nav">
    </div>
    <!-- <tool-bar></tool-bar> -->

  <transition name="page">
  <router-view></router-view>
  </transition>
  <!-- <spinner :loading="LoadingStatus"></spinner> -->
  
  </div>
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;900&display=swap');
#app {
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 14px;
  letter-spacing: -0.05em;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 100%;
  margin: 0 auto;
}

#nav {
  padding: 20px;
}

/* #nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
} */


[v-cloak] {
    display: none;
}

</style>
