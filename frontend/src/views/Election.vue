<template>
  <div>
    <div class="page_Alert">
      <b-alert
        :show="confirm"
        dismissible
        variant="primary"
        @dismissed="confirm = 0"
        @dismiss-count-down="countDownChanged"
      >
        투표가 완료될 때까지 잠시만 기다려 주세요!
      </b-alert>
      <b-alert
        :show="wating"
        dismissible
        variant="primary"
        @dismissed="wating = 0"
        @dismiss-count-down="countDownChanged"
      >
        투표가 완료되었습니다.!
      </b-alert>
      <b-alert
        :show="fail"
        dismissible
        variant="danger"
        @dismissed="fail = 0"
        @dismiss-count-down="countDownChanged"
      >
        투표에 실패했습니다.
      </b-alert>
    </div>
    <div>
      <Navbar />
    </div>
    <div id="ElectionContainer">
      <div>
        <electionHead />
      </div>
      <div>
        <electionContent @child="parents" />
      </div>
    </div>
  </div>
</template>

<script>
import electionHead from '@/components/election/election_head.vue';
import electionContent from '@/components/election/election_content.vue';
import Navbar from '@/components/common/nav.vue';
export default {
  components: {
    electionHead,
    electionContent,
    Navbar,
  },
  data() {
    return {
      dismissSecs: 5,
      confirm: 0,
      wating: 0,
      fail: 0,
    };
  },
  methods: {
    parents(emitKey) {
      if (emitKey === 1) {
        this.confirm = this.dismissSecs;
      } else if (emitKey === 2) {
        window.location.reload();
        this.wating = this.dismissSecs;
      } else if (emitKey === 3) {
        this.fail = this.dismissSecs;
      }
    },
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown;
    },
  },
};
</script>
<style scoped>
.page_Alert {
  width: 100%;
  position: absolute;
  z-index: 30;
}
</style>
