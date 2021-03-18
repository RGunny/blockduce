<template>
  <div class="center">
    <div class="profile black" v-for="(profile, idx) in profiles" :key="idx">
      <div>
        <img class="image" :src="require(`@/assets/${profile.image}`)" alt="">
      </div>
      <div>
        <div class="description">{{ profile.name }}</div>
        <div class="description">{{ profile.agency }}</div>
        <div class="description">{{ profile.age }}</div>
      </div>
      <div class="heart blackheart" @click="onClickHeart">
        ♥
      </div>
    </div>
  </div>
</template>

<script>
export default {

  data() {
    return {
      profiles: [
        {
          image: "1.jpg",
          name: "최주아",
          agency: "YG",
          age: 18,
          click: 0,       
        }
        ,
        {
          image: "2.jpg",
          name: "류건희",
          agency: "JYP",
          age: 21,
          click: 0,       
        }
        ,
        {
          image: "3.jpg",
          name: "박상우",
          agency: "SM",
          age: 19,
          click: 0,       
        }
        ,
        {
          image: "4.jpg",
          name: "이아영",
          agency: "CUBE",
          age: 15,
          click: 0,      
        }
        ,
        {
          image: "5.jpg",
          name: "윤은철",
          agency: "위",
          age: 24,
          click: 0,      
        }
        ,
        {
          image: "6.jpg",
          name: "서범석",
          agency: "개인 연습생",
          age: 22,
          click: 0,      
        }
        ,
        {
          image: "7.jpg",
          name: "김민지",
          agency: "플레디스",
          age: 18,
          click: 0,      
        }
        ,
  
      ]
    }
  },
  methods:{
    onClickHeart(e){
      // console.log(e);
      // console.log(e.target.parentNode.classList);
      e.target.parentNode.classList.toggle('red');
      e.target.parentNode.classList.toggle('purple');
      e.target.classList.toggle('blackheart');
      e.target.classList.toggle('purpleheart');
      
      this.$emit('onClickHeart')

    }
  }
}
</script>

<style scoped>
.black{
  border: 1px solid #C4C4C4;;
}
.purple {
  border: 1px solid #6B05A9;
}
.blackheart {
  color: #C4C4C4;
}
.purpleheart {
  color: #6B05A9;
}

.profile {
  
  width: 331px;
  height: 116px;
  left: 42px;
  top: 148px;

  box-sizing: border-box;
  border-radius: 10px;

  display: flex;
  margin-left: 2.8rem;
  margin-top: 2rem;
}

.image {
  margin-top: 1.5rem;
  margin-left: 1.5rem;
  width: 15vw;
  border-radius: 70%;
}

.description {
  margin-left: 2rem;
  margin-top: 1rem;
  font-size: 9px;
}

.heart {
  margin-left: 9rem;
  margin-top: 0.7rem;
  font-size: 1.3rem;
}

.center {
  
}


</style>