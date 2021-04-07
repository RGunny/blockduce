<template>
  <div>
    <Navbar />
    <div class="text">
      <div class="section1 purpleline">
        <img
          class="picture1"
          src="https://blockduce-image.s3.ap-northeast-2.amazonaws.com/main1.jpg"
          alt=""
        />
        <div class="description1_1">
          Blockduce 프로젝트는 <br />
          ERC20 토큰 기반의<br />
          블록체인 투표 시스템 입니다.
        </div>
      </div>

      <div class="section2 purpleline">
        <img
          class="picture2"
          src="https://blockduce-image.s3.ap-northeast-2.amazonaws.com/main2.jpg"
          alt=""
        />
        <div class="description2">
          여러분들의 투표는<br />
          이더리움의 네트워크 안에서 <br />
          안전하게 관리되고 있습니다.
        </div>
      </div>

      <div class="section3 purpleline">
        <img
          class="picture3"
          src="https://blockduce-image.s3.ap-northeast-2.amazonaws.com/main3.jpg"
          alt=""
        />
        <div class="description3">
          더 이상 중앙 집권화된 기관을 통해<br />
          투표할 필요가 없습니다.<br />
          여러분들의 투표 데이터를<br />
          블록체인으로 직접 확인해 보세요<br />
        </div>
      </div>

      <div class="section4 purpleline">
        <div class="description4">
          당신의 진짜 소년에게 투표하세요! <br />
          안전하고 투명한 투표 <br />
          BLOCKDUCE 입니다.
        </div>
        <img
          class="picture4"
          src="https://blockduce-image.s3.ap-northeast-2.amazonaws.com/mainpage.jpg"
          alt=""
        />
      </div>
    </div>

    <Footer />
  </div>

  <!--   
        <div v-if="this.$cookies.get(accesstoken)">
          <div @click="onClickLogout">로그아웃</div>
        </div>

  <router-link v-else to="/login">Login</router-link> -->
</template>

<script>
// import { mapGetters } from 'vuex';
// import VueCookies from 'vue-cookies';
import Navbar from '@/components/common/nav.vue';
import Footer from '@/components/common/footer.vue';
export default {
  components: {
    Navbar,
    Footer,
  },
};
</script>

<style scoped>
@font-face {
  font-family: 'account_font';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
.text {
  font-family: 'account_font';
  font-size: x-large;
}
.blockduce {
  margin-top: 200px;
  margin-bottom: 200px;
}

.section1 {
  height: 300px;
  display: flex;
  flex-direction: row;
}

.picture1 {
  position: absolute;
  height: 150px;
  margin-top: 45px;
  margin-left: 20px;
}

.description1_1 {
  text-align: left;
  position: absolute;
  margin-left: 200px;
  margin-top: 80px;
  font-size: 20px;
}

.section2 {
  display: flex;
  flex-direction: row;
  height: 300px;
}

.picture2 {
  position: absolute;
  height: 250px;
  margin-left: 170px;
}

.description2 {
  text-align: left;
  position: absolute;
  margin-left: 50px;
  margin-top: 80px;
  font-size: 20px;
  font-weight: 500;
}

.section3 {
  display: flex;
  flex-direction: row;
  height: 300px;
}

.picture3 {
  position: absolute;
  height: 200px;
  margin-top: 20px;
  margin-left: 30px;
}

.description3 {
  text-align: left;
  position: absolute;
  margin-left: 190px;
  margin-top: 50px;
  font-size: 20px;
  font-weight: 500;
}

.section4 {
  display: flex;
  flex-direction: row;
  height: 300px;
}

.picture4 {
  position: absolute;
  height: 250px;
  margin-left: 200px;
}

.description4 {
  text-align: left;
  position: absolute;
  margin-top: 60px;
  margin-left: 30px;
  font-size: 20px;
  font-weight: 500;
  z-index: 1;
}

.purpleline {
  /* border: 3px solid rebeccapurple; */
  position: relative;
}

.main-message {
  display: flex;
  align-items: center;
  justify-content: center;
  top: 35vh;
  margin: 5px 0;
  height: 3em;
  font-size: 2.5rem;
  opacity: 0;
}
.main-message p {
  font-weight: bold;
  text-align: center;
  line-height: 1.2;
}
.main-message small {
  display: block;
  margin-bottom: 0.5em;
  font-size: 1.2rem;
}

.loading {
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 100;
  background: white;
  opacity: 0;
  transition: 0.5s;
}
.before-load .container {
  display: none;
}
.before-load .loading {
  opacity: 1;
}

.loading-circle {
  width: 54px;
  height: 54px;
  animation: loading-spin 3s infinite;
}
.loading-circle circle {
  stroke: black;
  stroke-width: 4;
  /* getTotalLength()로 stroke의 길이를 얻어올 수 있음 */
  stroke-dasharray: 157, 157;
  stroke-dashoffset: 0;
  fill: transparent;
  animation: loading-circle-ani 1s infinite;
  /* transition: 1s; */
}
/* .loading-circle:hover circle {
	stroke-dashoffset: -157;
} */

.normal-content {
  margin-bottom: 10rem;
}

.footer {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 7rem;
  color: #939296;
}
</style>
