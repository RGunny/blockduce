<template>
  <div class="klogin">
    <div class="container d-flex justify-content-center align-items-center">
      <div class="card">
        <div class="row">
          <div class="col-md-6">
            <b-form
              class="form"
              @submit="onSubmit"
              v-if="show"
            >
              <h2>회원가입</h2>

              <div class="inputbox mt-3">
                <span>이름</span>
                <input
                  type="text"
                  placeholder="실명을 입력해주세요"
                  class="form-control"
                  v-model="form.uname"
                  required
                />
              </div>
              <div class="inputbox mt-3">
                <span>이메일</span>
                <input
                  type="text"
                  v-text="form.email"
                  v-model="form.email"
                  class="form-control"
                  required
                  disabled
                />
              </div>
              <div class="inputbox mt-3">
                <span>핸드폰 번호</span>
                <input
                  type="text"
                  placeholder="010-0000-0000"
                  name=""
                  class="form-control"
                />
              </div>
              <div class="d-flex justify-content-between align-items-center">
                <div class="text-right">
                  <button
                    type="submit"
                    class="btn btn-success register btn-block"
                  >
                    Register
                  </button>
                </div>

              </div>
            </b-form>
          </div>
          <div class="col-md-6">
            <div class="text-center mt-5">
              <img src="https://i.imgur.com/98GXnDD.png" width="400" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  created() {
    this.create();
  },
  data() {
    return {
      codes: "",
      form: {
        password: "",
        email: "",
        uname: "",
      },
      show: true,
    };
  },
  methods: {
    create() {
      this.codes = this.$route.query.code;
      this.getToken();
    },
    login() { //서비스의 회원임 -> 로그인
      axios.post("api/members/login", this.form).then((res) => {
        if (res.data != null) {
          document.cookie = `accessToken=${res.data}`;
          axios.defaults.headers.common["x-access-token"] = res.data;
          this.$router.push("/");
        }
      });
    },
    //getToken - create
    getToken() { 
      axios
        .get("api/members/klogin?authorize_code=" + this.codes)
        .then((res) => {
          this.form.email = res.data.email;
          this.form.password = res.data.id;
          if (this.form.password == undefined) { //인허코드 -> 토큰 을 통해 가져온 정보가 정의 되지 않았다 -> 카카오로그인 실패 잘못된 접근
            alert("올바르지 못한 접근입니다.");
            this.$router.push("/");
          } else { // 인허코드 -> 토큰 을 통해 가져온 정보가 있다 -> 바로 우리 서비스 기반 - 로그인 진행
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
};
</script>