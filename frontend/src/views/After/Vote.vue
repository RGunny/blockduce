<template>
  <div class="main">
    <Navbar />
    <Profile
      v-for="(profile, idx) in profiles"
      :key="idx"
      :profile="profile"
      @onChange="onChange"
    />
    <div class="remain">
      <p v-if="this.remain >= 0">잔액 : {{ remain }} 원</p>
      <p v-if="this.remain < 0" class="remainRed">잔액 : {{ remain }} 원</p>
      <button class="myButton" @click="calculate">계산</button>
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
          투표
        </vs-button>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar.vue';
import Profile from '@/components/vote/Profile.vue';

export default {
  components: {
    Navbar,
    Profile,
  },
  data: () => ({
    remain: 1000000,
    active: 0,
    cnt: 0,

    moneyList: [0, 0, 0, 0, 0, 0, 0],
    profiles: [
      {
        id: 0,
        image: '1.jpg',
        name: '최주아',
        agency: 'YG',
        age: 18,
        money: 0,
      },
      {
        id: 1,
        image: '2.jpg',
        name: '류건희',
        agency: 'JYP',
        age: 21,
        money: 0,
      },
      {
        id: 2,
        image: '3.jpg',
        name: '박상우',
        agency: 'SM',
        age: 19,
        money: 0,
      },
      {
        id: 3,
        image: '4.jpg',
        name: '이아영',
        agency: 'CUBE',
        age: 15,
        money: 0,
      },
      {
        id: 4,
        image: '5.jpg',
        name: '윤은철',
        agency: '위',
        age: 24,
        money: 0,
      },
      {
        id: 5,
        image: '6.jpg',
        name: '서범석',
        agency: '개인 연습생',
        age: 22,
        money: 0,
      },
      {
        id: 6,
        image: '7.jpg',
        name: '김민지',
        agency: '플레디스',
        age: 18,
        money: 0,
      },
    ],
  }),
  methods: {
    onChange(val, profile) {
      // console.log(val);
      // console.log(profile);
      // console.log(this.moneyList[0]);
      this.moneyList[`${profile.id}`] = Number(val);
      // console.log(this.moneyList);
    },
    calculate() {
      this.remain = 1000000;
      const sum = this.moneyList.reduce((a, b) => a + b);
      this.remain -= sum * 100000;
    },
    // onClickHeart(cnt){
    //   this.cnt=cnt
    // }
  },
};
</script>

<style scoped>
.main {
  width: 100%;
  height: 100%;
}

.footer {
  position: sticky;

  width: 100vw;
  height: 5rem;

  bottom: 0;
  border-top: 1px solid #6b05a9;
  background: white;
}

.button {
  position: absolute;
  margin-top: 1rem;
  margin-left: 11rem;
}

.remainRed {
  color: red;
}

.remain {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-top: 20px;
}

.myButton {
  border: 1px solid plum;
  background-color: white;
  border-top-right-radius: 7px;
  border-top-left-radius: 7px;
  border-bottom-right-radius: 7px;
  border-bottom-left-radius: 7px;
  margin-left: 10px;
  color: plum;
}
</style>
