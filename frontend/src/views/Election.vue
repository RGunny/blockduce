<template>
  <div id="ElectionContainer">
    <div>
      <electionHead />
      <electionCalendar />
    </div>
    <div>
      <electionContent />
    </div>
  </div>
</template>

<script>
import electionHead from '@/components/election/election_head.vue';
import electionCalendar from '@/components/election/component/election_calendar_modal.vue';
import electionContent from '@/components/election/election_content.vue';

export default {
  components: {
    electionHead,
    electionContent,
    electionCalendar,
  },
};
</script>
