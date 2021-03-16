<template>
  <button @click="loginWithKakao">카카오톡으로 로그인</button>
</template>

<script>
// import SERVER from "@/api/api";
// import Swal from "sweetalert2";
// import axios from "axios";

export default {

  methods: {


    loginWithKakao() {
      return new Promise((resolve, reject) => {
        window.Kakao.Auth.login({
          scope: "profile",
          success: (res) => {
            console.log("kakaoLogin", res);
            console.log(res.code)

            resolve();
          },
          fail: (err) => {
            alert(err);
            reject();
          },
        });
      });
            
    // },

    // kakaoLogin() {
    //   window.Kakao.Auth.login({
    //     scope: "profile, account_email",
    //     success: this.GetMe,
    //   });
    // },
    // GetMe() {
    //   window.Kakao.API.request({
    //     url: "/v2/user/me",
    //     success: (res) => {
    //       console.log(res);
    //       const kakao_account = res.kakao_account;
    //       console.log(res.kakao_account);
    //       const userInfo = {
    //         nickName: kakao_account.profile.nickname,
    //         email: kakao_account.email,
    //         platformType: "KAKAO",
    //       };
    //       if (userInfo.email === undefined) {
    //         this.$router.push({
    //           name: "SignupKakao",
    //           params: {
    //             nickName: userInfo.nickName,
    //             platformType: userInfo.platformType,
    //           },
    //         });
    //       } else {
    //         axios
    //           .post(SERVER.URL + SERVER.ROUTES.social, userInfo)
    //           .then((res) => {
    //             this.SET_TOKEN(res.data);
    //             const Toast = Swal.mixin({
    //               toast: true,
    //               position: "top-end",
    //               showConfirmButton: false,
    //               timer: 2000,
    //               timerProgressBar: true,
    //               onOpen: (toast) => {
    //                 toast.addEventListener("mouseenter", Swal.stopTimer);
    //                 toast.addEventListener("mouseleave", Swal.resumeTimer);
    //               },
    //             });
    //             Toast.fire({
    //               icon: "success",
    //               title: "로그인에 성공하였습니다.",
    //             });
    //             this.$router.push("/");
    //           })
    //           .catch((err) => {
    //             const Toast = Swal.mixin({
    //               toast: true,
    //               position: "top-end",
    //               showConfirmButton: false,
    //               timer: 2000,
    //               timerProgressBar: true,
    //               onOpen: (toast) => {
    //                 toast.addEventListener("mouseenter", Swal.stopTimer);
    //                 toast.addEventListener("mouseleave", Swal.resumeTimer);
    //               },
    //             });
    //             Toast.fire({
    //               icon: "error",
    //               title: err.response.data.message,
    //             });
    //             this.$router.push("/");
    //           });
    //       }
    //     },
    //   });
    // },
  },

}
}
</script>

<style>

</style>