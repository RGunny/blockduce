<template>
  <div v-cloak >

    <div>회원가입</div>
    <div class="line"></div>

      <b-form
              class="form"
              @submit="onSubmit"
              v-if="show"
            >
     <div class="outer">
       <div class="inner">

        <vs-input
                  type="text"
                  v-text="form.name"
                  v-model="form.name"
                  placeholder="Password"
                  class="form-control"
                  required
                  disabled>
        </vs-input>

        <vs-input 
                  type="text"
                  v-text="form.email"
                  v-model="form.email"
                  placeholder="form.name"
                  class="form-control"
                  required
                  disabled>
        </vs-input>


        <vs-input class="interval" type="password" v-model="form.password" placeholder="Password">
          <template v-if="validPassword" #message-success>
            안전한 비밀번호입니다!
          </template>
          <template v-if="!validPassword || form.password === ''" #message-danger>
            6~20자의 영문 대소문자<br>
            최소 1개의 숫자 혹은 특수문자 포함
          </template>
        </vs-input>

        <vs-input class="interval" type="password" v-model="form.passwordValid" placeholder="ConfirmPassword">
          <template v-if="!same" #message-danger>
            비밀번호가 일치하지 않습니다.
          </template>
        </vs-input>

        <vs-button type="submit" class="intervalForButton" color="#7d33ff" flat>
          회원가입
        </vs-button>
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
    create() {
      this.codes = this.$route.query.code;
      this.getToken();
    },
    login() { //서비스의 회원임 -> 토큰 받아서 로그인
      axios.post("api/members/login", this.form).then((res) => {
        console.log("토큰: "+res.data);
        if (res.data != null) {
          document.cookie = `accessToken=${res.data}`;
          // this.$cookie.set("accesstoken", res.data, 60 * 60 * 12);
          axios.defaults.headers.common["x-access-token"] = res.data;
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
          this.form.password = res.data.password;
          if (this.form.password == undefined) { 
            alert(" 회원가입을 진행하여 소셜계정과 연결해주세요");
            this.$router.push("/kakaologin");
            this.form.kid = res.data.kid; //카카오 아이디 넣어줌
          } else { 
            this.login();
          }
        });
    },
    onSubmit(event) {
      event.preventDefault();
      
      // alert(JSON.stringify(this.form));
      axios.post("api/members/join", this.form).then((res) => {
        console.log(res.status);
        this.login();
      });
    },

  },
}

</script>