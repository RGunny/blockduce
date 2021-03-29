<template>
  <div id="ElectionContainer">
    <div>
      <electionHead />
    </div>
    <div>
      <electionContent />
    </div>
  </div>
</template>

<script>
import electionHead from '@/components/election/election_head.vue';
import electionContent from '@/components/election/election_content.vue';

export default {
  components: {
    electionHead,
    electionContent,
  },
};
</script>
