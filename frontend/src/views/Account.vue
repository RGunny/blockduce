<template>
  <div>
    <div>
      <Navbar />
    </div>
    <div id="AccountContainer">
      <div>
        <myAlert />
      </div>
      <div>
        <myAccount />
      </div>
      <div><account_content /></div>
    </div>
    <div>
      <Footer />
    </div>
  </div>
</template>

<script>
import myAccount from '@/components/myAccount/myAccount.vue';
import myAlert from '@/components/myAccount/myAlert.vue';
import account_content from '@/components/myAccount/account_content.vue';
import Navbar from '@/components/common/nav.vue';
import Footer from '@/components/common/footer.vue';
export default {
  components: {
    Navbar,
    Footer,
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
