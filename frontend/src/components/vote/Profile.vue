<template>
  <div class="center">
    <div class="profile black">
      <div>
        <img class="image" :src="require(`@/assets/${profile.image}`)" alt="">
      </div>
      <div>
        <div class="description">{{ profile.name }}</div>
        <div class="description">{{ profile.agency }}</div>
        <div class="description">{{ profile.age }}</div>
      </div>
      <div class="input">
        <!-- <vs-input
        @keyup="onKeyUp"
        style="width:50px;"
        label-placeholder="단위: 십만 원"
        v-model="value"
      /> -->
      <!-- <label for="range-1">Example range with min and max</label> -->
    <b-form-input id="range-1" v-model="value" type="range" min="0" max="5"
        @change="onKeyUp"
        style="width:100px;"
        ></b-form-input>
    <div class="mt-2"> {{ value * 100000 }} 원</div>
      </div>

      <!-- <div class="heart blackheart" @click="onClickHeart">
        ♥
      </div> -->
    </div>
  </div>
</template>

<script>
export default {
  props:{
    profile:Object,
  },
  data() {
    return {
      value:'',

    }
  },
  methods:{
    onKeyUp(){
      this.$emit('onChange',this.value, this.profile);
    }
    // onClickHeart(e){
    //   // console.log(e);
    //   // console.log(e.target.parentNode.classList);


    //   e.target.parentNode.classList.toggle('red');
    //   e.target.parentNode.classList.toggle('purple');
    //   e.target.classList.toggle('blackheart');
    //   e.target.classList.toggle('purpleheart');
      
      
    //   this.$emit('onClickHeart')

    // },
    // inputMoney(profile) {

    // }
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

.input {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  margin-left: 50px;
}


</style>