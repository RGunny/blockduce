<template>
  <div class="background">
    <div class="container p-3 mt-5 bg-light-ivory signup-form">
      <h3>회원가입</h3>

      <!-- 프로필 사진 위치 -->
      <div>
        <div v-if="signupData.imageUrl != null">
          <img
            :src="signupData.imageUrl"
            class="rounded mx-auto d-block"
            @click="onClickImageUpload"
            width="150px"
            height="150px"
          />
        </div>
        <div v-if="signupData.imageUrl == null">
          <img
            :src="defaultimg"
            class="rounded mx-auto d-block"
            @click="onClickImageUpload"
            width="150px"
            height="150px"
          />
        </div>
      </div>
      <input ref="imageInput" type="file" hidden @change="onChangeImages" />
      <p class="my-3">
        <span class="items" @click="onClickImageUpload">프로필 등록하기</span>
      </p>
      <div class="input-with-label">
        <input
          v-model="signupData.name"
          v-bind:class="{
            error: error.name,
            complete: !error.name && signupData.name.length !== 0,
          }"
          class="inputs"
          id="name"
          placeholder="이름"
          type="text"
          autocapitalize="none"
          autocorrect="none"
          style="text-transform:lowercase"
        />
        <label for="name"></label>
        <div class="error-text ml-3" v-if="error.name">
          {{ error.name }}
        </div>
      </div>
      <div class="input-with-label">
        <input
          v-model="signupData.nickname"
          v-bind:class="{
            error: error.nickname,
            complete: !error.nickname && signupData.nickname.length !== 0,
          }"
          class="inputs"
          id="nickname"
          placeholder="닉네임"
          type="text"
          autocapitalize="none"
          autocorrect="none"
          style="text-transform:lowercase"
        />
        <label for="nickname"></label>
        <div class="error-text ml-3" v-if="error.nickname">
          {{ error.nickname }}
        </div>
      </div>
      <div class="input-with-label">
        <input
          v-model="signupData.intro"
          v-bind:class="{
            error: error.intro,
            complete: !error.intro && signupData.intro.length !== 0,
          }"
          class="inputs"
          id="intro"
          placeholder="자기소개"
          type="text"
          autocapitalize="none"
          autocorrect="none"
          style="text-transform:lowercase"
        />
        <label for="intro"></label>
        <div class="error-text ml-3" v-if="error.intro">
          {{ error.intro }}
        </div>
      </div>
      <div class="input-with-label">
        <input
          v-model="signupData.email"
          v-bind:class="{
            error: error.email,
            complete: !error.email && signupData.email.length !== 0,
          }"
          class="inputs"
          id="email"
          placeholder="이메일"
          type="text"
          autocapitalize="none"
          autocorrect="none"
          style="text-transform:lowercase"
          required
        />
        <label for="email"></label>
        <div class="error-text ml-3" v-if="error.email">{{ error.email }}</div>
      </div>

      <div class="input-with-label">
        <input
          v-model="signupData.password"
          v-bind:class="{
            error: error.password,
            complete: !error.password && signupData.password.length !== 0,
          }"
          class="inputs"
          id="password"
          type="password"
          placeholder="비밀번호를 입력하세요."
          required
        />
        <label for="password"></label>
        <div class="error-text ml-3" v-if="error.password">
          {{ error.password }}
        </div>
      </div>

      <div class="input-with-label">
        <input
          v-model="signupData.passwordConfirm"
          type="password"
          id="password-confirm"
          v-bind:class="{
            error: error.passwordConfirm,
            complete:
              !error.passwordConfirm && signupData.passwordConfirm.length !== 0,
          }"
          placeholder="비밀번호를 다시 입력해주세요."
          class="inputs"
          required
          @keyup.enter="clickSignup"
        />
        <label for="password-confirm"></label>
        <div class="error-text ml-3" v-if="error.passwordConfirm">
          {{ error.passwordConfirm }}
        </div>
      </div>
      <div class="buttons mt-3">
        <button
          class="btn signup-button"
          :class="{ disabled: !isSubmit }"
          @click="clickSignup"
        >
          가입하기
        </button>
      </div>
      <p class="my-3">
        <span class="items" @click="toLogin">로그인하기</span>
      </p>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'Signup',
  data() {
    return {
      signupData: {
        email: '',
        password: '',
        passwordConfirm: '',
        name: '',
        nickname: '',
        intro: '',
        imageUrl: null,
      },
      error: {
        email: false,
        name: false,
        password: false,
        passwordConfirm: false,
        nickname: '',
        intro: '',
      },
      isSubmit: false,
      defaultimg: require('@/assets/user/defaultimg.png'),
    };
  },
  created() {
    this.component = this;
  },
  watch: {
    signupData: {
      deep: true,

      handler() {
        this.checknameForm();
        this.checkEmailForm();
        this.checkPasswordForm();
        this.checkNickName();
        this.checkIntro();
        this.checkPasswordConfirmationForm();
      },
    },
  },
  methods: {
    fileSelect() {
      console.log('file', this.$refs);
      this.signupData.imageUrl = this.$refs.imageInput.files[0];
    },
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
    onChangeImages(e) {
      console.log(e.target.files);
      const file = e.target.files[0]; // Get first index in files
      this.signupData.imageUrl = URL.createObjectURL(file); // Create File URL
    },
    checknameForm() {
      if (this.signupData.name.length > 0) {
        this.error.name = false;
      } else this.error.name = '이름을 입력해주세요.';
    },
    checkNickName() {
      if (this.signupData.nickname.length > 0) {
        this.error.nickname = false;
      } else this.error.nickname = '닉네임을 입력해주세요.';
    },
    checkIntro() {
      if (this.signupData.intro.length > 0) {
        this.error.intro = false;
      } else this.error.intro = '자기소개를 입력해주세요.';
    },
    checkEmailForm() {
      if (
        this.signupData.email.length > 0 &&
        !this.validEmail(this.signupData.email)
      ) {
        this.error.email = '올바른 이메일 형식이 아니에요';
      } else this.error.email = false;
    },
    validEmail(email) {
      // eslint-disable-next-line
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    checkPasswordForm() {
      if (
        this.signupData.password.length > 0 &&
        this.signupData.password.length < 8
      ) {
        this.error.password = '비밀번호가 너무 짧아요';
      } else if (
        this.signupData.password.length >= 8 &&
        !this.validPassword(this.signupData.password)
      ) {
        this.error.password = '영문, 숫자 포함 8 자리 이상이어야 해요.';
      } else this.error.password = false;
    },
    validPassword(password) {
      var va = /^(?=.*\d)(?=.*[a-z])(?=.*[a-zA-Z]).{8,}$/;
      return va.test(password);
    },
    checkPasswordConfirmationForm() {
      if (
        this.signupData.password.length >= 8 &&
        this.validPassword(this.signupData.password)
      ) {
        if (this.signupData.password !== this.signupData.passwordConfirm)
          this.error.passwordConfirm = '비밀번호가 일치하지 않아요.';
        else this.error.passwordConfirm = false;
      }

      // 버튼 활성화
      if (
        this.signupData.name.length > 0 &&
        this.signupData.email.length > 0 &&
        this.signupData.password.length > 0 &&
        this.signupData.passwordConfirm.length > 0 &&
        this.signupData.intro.length > 0 &&
        this.signupData.nickname.length > 0
      ) {
        let isSubmit = true;
        Object.values(this.error).map((v) => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      }
    },
    clickSignup() {
      if (this.isSubmit) {
        this.signup(this.signupData);
      }
    },
    toLogin() {
      this.$router.push({ name: 'Login' });
    },
    ...mapActions('accountStore', ['signup']),
  },
};
</script>

<style scoped>
.container {
  width: 30%;
  border-radius: 25px;
}

h3 {
  color: #3700ff;
  font-weight: 800;
}

.inputs {
  border-style: none;
  border-bottom: 1px solid #88a498;
  background-color: transparent;
  width: 80%;
  padding: 10px;
  padding-left: 10px;
  padding-right: 10px;
  margin-top: 20px;
}

.signup-button {
  background-color: #3743ac;
  color: #f8f8f8;
  width: 70%;
}

.divide {
  width: 10%;
  border-top: 1px solid #88a498;
  margin-left: auto;
  margin-right: auto;
}

.kakao {
  background-color: #ffe812;
  border-radius: 5px;
  width: 70%;
}

.google {
  background-color: #ffffff;
  border-radius: 5px;
  width: 70%;
}

.inputs:focus {
  border-style: none;
  border-bottom: 2px solid #d6cbbd;
  outline-style: none;
}

input[type='password'] {
  font-family: sans-serif;
}
.error,
.error:focus {
  border-bottom: 2px solid rgb(250, 25, 59, 0.7);
}

.error-text {
  color: rgb(250, 25, 59, 0.7);
  text-align: left;
  padding-left: 30px;
}

.signup-button:hover {
  background-color: #0011ff;
  color: #f8f8f8;
}

.disabled,
.disabled:hover {
  background-color: rgb(136, 154, 152, 0.25);
  color: #f8f8f8;
  cursor: inherit;
}

.background {
  overflow: scroll;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  height: 100vh;
  background-image: url('https://user-images.githubusercontent.com/70404643/113249815-55915800-92fa-11eb-989b-e7c56b3df63e.PNG');
  background-position: center;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  background: url('https://user-images.githubusercontent.com/70404643/113249815-55915800-92fa-11eb-989b-e7c56b3df63e.PNG')
    no-repeat center/cover;
}

.signup-form {
  margin-top: 20vh !important;
  opacity: 0.9;
}

.items:hover {
  cursor: pointer;
  color: #d6cbbd;
}
</style>
