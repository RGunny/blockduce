<template>
  <div class="footer">
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
      ><a href="https://github.com/junjun0905" target="_blank"> @junjun0905</a>
    </p>
  </div>
</template>
<script>
export default {
  data() {
    return {
      showHome: false,
      showCheck: false,
      showCoin: false,
      showUser: false,
      effect: 'lni-tada-effect',
    };
  },
};
</script>

<style scope>
.footer {
  text-align: center;
  background-color: rgb(34, 34, 34);
  color: white;
  padding-top: 20px;
  height: 100px;
  left: 0;
  bottom: 0;
  width: 100%;
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
</style>
