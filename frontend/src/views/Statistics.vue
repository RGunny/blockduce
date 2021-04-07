<template>
  <div>
    <div>
      <Navbar />
    </div>
    <div>
      <Statistic />
    </div>
  </div>
</template>

<script>
import Statistic from '@/components/statistic/statistic.vue';
import Navbar from '@/components/common/nav.vue';
export default {
  components: {
    Statistic,
    Navbar,
  },
};
</script>
