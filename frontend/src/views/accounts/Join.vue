<template>
  <div>
    <div>회원가입</div>
    <div class="line"></div>


     <div class="outer">
       <div class="inner">

        <vs-input class="interval" v-model="value1" placeholder="Email">
          <template v-if="validEmail" #message-success>
            가능한 이메일입니다!
          </template>
          <template v-if="(!validEmail && value1 !== '') || value1 == ''" #message-danger>
            이메일 형식으로 입력해주세요.
          </template>
        </vs-input>

        <vs-input class="interval" v-model="value2" placeholder="Name">
          <template v-if="validName" #message-success>
            멋진 이름이네요!
          </template>
          <template v-if="!validName || value2 === ''" #message-danger>
            이름을 입력해주세요.
          </template>
        </vs-input>

        <vs-input class="interval" type="password" v-model="value3" placeholder="Password">
          <template v-if="validPassword" #message-success>
            안전한 비밀번호입니다!
          </template>
          <template v-if="!validPassword || value3 === ''" #message-danger>
            6~20자의 영문 대소문자<br>
            최소 1개의 숫자 혹은 특수문자 포함
          </template>
        </vs-input>

        <vs-input class="interval" type="password" v-model="value4" placeholder="ConfirmPassword">
          <template v-if="!same" #message-danger>
            비밀번호가 일치하지 않습니다.
          </template>
        </vs-input>

        <vs-button class="intervalForButton" color="#7d33ff" flat>
          회원가입
        </vs-button>

       </div>
    </div>
  </div>
</template>

<script>
export default {

   data:() => ({
        value1: '',
        value2: '',
        value3: '',
        value4: '',
      }),
      computed: {
        validEmail() {
          return /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/.test(this.value1)
        },

        validName() {
          return /^[가-힣]+$/.test(this.value2)
        },

        validPassword() {
          return /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/.test(this.value3)
        },

        same() {
          if (this.value3 === this.value4 ) {
            return true
          }
          else {
            return false
          }
        }

      }

}
</script>

<style scoped>
  .outer {
    text-align: center;
    margin-top: 80px;
  }

  .inner {
    display: inline-block;
  }

  .interval {
    margin-top: 30px;
  }

  .intervalForButton {
    margin: 50px auto;
  }


</style>