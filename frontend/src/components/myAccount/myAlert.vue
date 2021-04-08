<template>
  <div>
    <div class="page_Alert">
      <b-alert
        :show="dismissCountDown1"
        dismissible
        variant="warning"
        @dismissed="dismissCountDown = 0"
        @dismiss-count-down="countDownChanged"
      >
        경고! 지갑 비밀키를 잃어버리지 마세요! 한번 잃어버리면 복구 할 수
        없습니다.
      </b-alert>
      <b-alert
        :show="dismissCountDown2"
        dismissible
        variant="warning"
        @dismissed="dismissCountDown = 0"
        @dismiss-count-down="countDownChanged"
      >
        공유하지 마세요! 비밀키가 악위적인 사이트에 노출되면 당신의 자산이
        유실될 수 있습니다.
      </b-alert>
      <b-alert
        :show="dismissCountDown3"
        dismissible
        variant="warning"
        @dismissed="dismissCountDown = 0"
        @dismiss-count-down="countDownChanged"
      >
        백업을 만들어 두세요! 종이에 적어서 오프라인으로 관리하세요.
        {{ dismissCountDown }} seconds...
      </b-alert>
    </div>
  </div>
</template>
<script scoped>
export default {
  data() {
    return {
      dismissSecs: 5,
      dismissCountDown: 0,
    };
  },
  created() {
    this.dismissCountDown1 = this.dismissSecs;
    this.dismissCountDown2 = this.dismissSecs;
    this.dismissCountDown3 = this.dismissSecs;
  },
  methods: {
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown;
    },
  },
};
</script>

<style>
.page_Alert {
  position: absolute;
  z-index: 30;
}
</style>
