<template>
  <div id="AccountContainer">
    <div>
      <myAlert />
    </div>
    <div>
      <myAccount />
    </div>
    <div><account_content /></div>
  </div>
</template>

<script>
import myAccount from '@/components/myAccount/myAccount.vue';
import myAlert from '@/components/myAccount/myAlert.vue';
import account_content from '@/components/myAccount/account_content.vue';

export default {
  components: {
    account_content,
    myAccount,
    myAlert,
  },
};
</script>

<style>
.AccountContainer {
  background-color: #310f19;
  overflow: auto;
}
</style>
