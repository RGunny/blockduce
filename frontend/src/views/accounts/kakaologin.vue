<template>
  <div >

    <div>회원가입</div>
    <div class="line"></div>
      <b-form
              class="form"
              @submit="onSubmit"
              v-if="show"
            >
     <div class="outer">
       <div class="inner">

<div id="app"> 

    <div class="img">
          <img v-bind:src="form.img"/>
    </div>
    <div class = "content">
        <vs-input
                  type="text"
                  v-text="form.name"
                  v-model="form.name"
                  placeholder="Password"
                  class="form-control input"
                  required
                  disabled>
        </vs-input>

        <vs-input 
                  type="text"
                  v-text="form.email"
                  v-model="form.email"
                  placeholder="form.name"
                  class="form-control input"
                  required
                  disabled>
        </vs-input>
        <vs-input class="interval input" type="nickname" v-model="form.nickname" placeholder="닉네임">
        </vs-input>

        <vs-input class="interval input" type="intro" v-model="form.intro" placeholder="자기소개">
          
        </vs-input>

        <vs-input class="interval input" type="password" v-model="form.password" placeholder="비밀번호">
          <template v-if="validPassword" #message-success>
            안전한 비밀번호입니다!
          </template>
          <template v-if="!validPassword || form.password === ''" #message-danger>
            6~20자의 영문 대소문자<br>
            최소 1개의 숫자 혹은 특수문자 포함
          </template>
        </vs-input>

        <vs-input class="interval input" type="password" v-model="form.passwordValid" placeholder="비밀번호 확인">
          <template v-if="!same" #message-danger>
            비밀번호가 일치하지 않습니다.
          </template>
        </vs-input>

        <vs-button type="submit" class="intervalForButton" color="#7d33ff" flat>
          회원가입
        </vs-button>
        </div>
    </div>
</div>

    </div>
    </b-form>
  </div>

</template>

<script>
import Vue from 'vue'
import axios from "axios";
import VueCookie from 'vue-cookie'
// import { mapState } from 'vuex';
Vue.use(VueCookie)

export default {
  created() {
    this.create();
  },

   data:() => ({
      codes: "",
      form: {
        password: "",
        passwordValid: "",
        email: "",
        name: "",
        kid: "",
        token:"",
        ismem:"",
        img:"",
        nickname:"",
        intro:"",
        flag:"",
        id:""
      },

      show: true,
      value3: '',
      value4: ''
      }),

      computed: {

// ...mapState(['getAccessToken']),

        validPassword() {
          return /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/.test(this.form.password)
        },

        same() {
          if (this.form.password === this.form.passwordValid ) {
            return true
          }
          else {
            return false
          }
        }

      },
//       state: {
//   // getAccessToken: this.form.token
// },
  methods: {
    create() {  //여기서 토큰 받았을때 
    if(this.flag==undefined){
      this.codes = this.$route.query.code;
      this.getToken();
    }
    },
    login() { //서비스의 회원임 -> 토큰 받아서 로그인
      axios.post("api/members/login", this.form).then((res) => {
        console.log("로그인 시 토큰: "+res.data.token);
        console.log("로그인 시 아이디: "+res.data.id);

        this.form.token = res.data.token;
        this.form.id = res.data.id;

        if (res.data.token != null) {
          localStorage.setItem('token', res.data.token);
          localStorage.setItem('id', res.data.id);
          // this.$cookie.set("accesstoken", res.data, 60 * 60 * 12);
          // axios.defaults.headers.common["x-access-token"] = res.data.token; 이 부분 보완
          // this.form.token = res.data;
          this.$router.push("/");
        }
      });
    },
    //getToken - create
    getToken() { 
      axios
        .get("api/members/klogin?authorize_code=" + this.codes)  //인가코드를 통한 프로필로 우리 사이트 회원인지 검사(벡에서) 회원 아니면 회원가입으로 회원이면 억세스 토큰받고 홈으로
        .then((res) => {
          console.log("kid: "+res.data.kid);
          this.form.email = res.data.email;
          this.form.name = res.data.name;
          this.form.ismem = res.data.ismem;
          this.form.kid = res.data.kid; //카카오 아이디 넣어줌
          this.form.img = res.data.img;//이미지 넣어줌
          this.flag=1;
          if (this.form.ismem == undefined) {
            alert(" 회원가입을 진행하여 소셜계정과 연결해주세요");
            //this.$router.push("/kakaologin");
          } else {
            this.login();
          }
        });

      // axios
      //   .get("http://j4b107.p.ssafy.io:8080/api/members/klogin?authorize_code=" + this.codes)
      //   .then((res) => {
      //     console.log("res : " + res);
      //     console.log("res.status : " + res.status);
      //     console.log("res.statusText : " + res.statusText);
      //     console.log("res.headers : " + res.headers);
      //     console.log("res.config : " + res.config);
      //     console.log("res.data : " + res.data);
      //     console.log("kid: "+res.data.kid);
      //     console.log("res.data.email : " + res.data.email);
      //     console.log("res.data.name : " + res.data.name);
      //   });
    },
    onSubmit(event) {
      event.preventDefault();
      this.form.ismem=true;
      console.log("한마디: "+this.form.intro);
      console.log("닉네임: "+this.form.nickname);
      // alert(JSON.stringify(this.form));
      axios.post("api/members/join", this.form).then((res) => {
        console.log(res.status);
        this.login();
      });
    },

  },
}

</script>
<style scoped>
.content{
  text-align: -webkit-center;
}

.vs-input-content{
  align-self: center;
}
.input{
  width: 80%;
  align-self: center;
  align-items: center;
}
</style>
