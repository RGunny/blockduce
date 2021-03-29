<template>
  <div>
    <Navbar/>
    <img class="crown" src="@/assets/crown.png" alt="">
    <div>
      <div class="realtime">
        실시간 순위
      </div>
      <div :class="'rank top'+(idx+1)" v-for="(person, idx) in top6" :key="idx">
        {{ idx + 1 }}등
        <img class="photo photoblock" :src="person.img_url"/>
        <div class="name">
          {{ person.name }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar.vue'

export default {
  components: {
    Navbar,
  },

  data() {
    return {

      top6: [
        {
          img_url: require("@/assets/1.jpg"),
          name: '최주아',
          nov: 1235154354,
        },
        {
          img_url: require("@/assets/2.jpg"),
          name: '류건희',
          nov: 1235154353
        },
        {
          img_url: require("@/assets/3.jpg"),
          name: '박상우',
          nov: 1235154352
        },
        {
          img_url: require("@/assets/4.jpg"),
          name: '이아영',
          nov: 1235154351
        },
        {
          img_url: require("@/assets/5.jpg"),
          name: '황민현',
          nov: 1235154350
        },
        {
          img_url: require("@/assets/6.jpg"),
          name: '강다니엘',
          nov: 1235154349
        },

      ]
    }
  }


}
</script>

<style>

.photo {
  width: 20vw;
  border-radius: 10vw;
  
}

.photoblock {
  max-width: 100%;
  height: auto;
  border: 2px solid #eed5f7;
}

.rank {
  position: absolute;
  width: 30vw;
  height: 30vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 10px;
}

.top1 {

  margin-left: 35vw;
  margin-top: 2vh;

}
.top2 {
  margin-left: 17vw;
  margin-top: 21vh;

}
.top3 {
  margin-left: 50vw;
  margin-top: 21vh;

}
.top4 {
    margin-left: 5vw;
    margin-top: 42vh;
}
.top5 {
    margin-left: 35vw;
    margin-top: 42vh;
}
.top6 {
    margin-left: 65vw;
    margin-top: 42vh;
}

.name {
  margin-top: 1vh;
}

.crown {
  position: absolute;
  width: 6vw;
  transform: rotate(-25deg);
  margin-left: -11vw;
  margin-top: 9vh;
}

.realtime {
  
  font-size: 15px;
  margin-top: 4vh;
  margin-left: 5vw;
}

</style>