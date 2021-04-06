<template>
  <div id="ElectionContainer">
  <div>
    <navbar />
  </div>
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
import navbar from "@/components/Navbar.vue";
export default {
  components: {
    electionHead,
    electionContent,
    navbar,
  },
};
</script>
