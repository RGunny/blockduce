<template>
  <div class="main">
    <Navbar/>
    <!-- <Profile @onClickHeart="onClickHeart"/> -->
    <div>

    ㅤ
    </div>
    <div class="footer">
      <!-- <button class="button">투표</button> -->
      <div class="button">
        <vs-button
          color="#6B05A9"
          gradient
          :active="active == 6"
          @click="active = 6"
        >
        {{cnt}}  투표
        </vs-button>
      </div>
    </div>


  </div>
</template>

<script>
import Navbar from '@/components/Navbar.vue'
// import Profile from '@/components/vote/Profile.vue'


export default {
  components: {
    Navbar,
    // Profile,
  },
  data:() => ({
        active: 0,
        cnt:0,
      }),
  methods:{
    onClickHeart(cnt){
      this.cnt=cnt
    }
  }
}
</script>

<style>
/* .main {
  width: 100%;
  height: 100%;
} */

.footer {
  position: sticky;

  width: 100vw;
  height: 5rem;

  bottom: 0;
  border-top: 1px solid #6B05A9;
  background: white;
}

.button {
  position: absolute;
  margin-top: 1rem;
  margin-left: 11rem;

}


</style>