<template>
  <div>
    <div class="box">
      <div id="vote">
        <router-link class="menus" to="/after/vote">투표하기</router-link>
      </div>
      <div id="now">
        <router-link class="menus" to="/after/now">투표현황</router-link>
      </div>
      <div id="blockduce">
        <router-link class="menus" to="/after/blockduce">블록듀스</router-link>
      </div>
      <div id="wallet">
        <router-link class="menus" to="/after/wallet">내 지갑</router-link>
      </div>
    </div>
    <div class="line"></div>
  </div>
</template>

<script>
export default {
  mounted() {
    const routerName = this.$route.name;
    const tagName = routerName.toLowerCase();
    const tag = document.querySelector(`#${tagName}`);
    tag.classList.toggle('purple');
  },
  methods: {  
    },
};
</script>

<style>
.line {
  /* position: fixed; */

  width: 100vw;
  height: 0.01vh;

  border-bottom: 1px solid #6b05a9;
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

.box {
  position: relative;

  margin-left: 3vw;
  margin-right: 3vw;

  margin-top: 5vh;

  display: flex;
  justify-content: space-between;
}
.purple {
  /* width: 20vw; */
  border-bottom: 7px solid #6b05a9;
}
</style>
