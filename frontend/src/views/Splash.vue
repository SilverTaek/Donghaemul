<template>
  <div>
    <div class="splash" v-if="!showOnboard">
      <img src="@/assets/logoWname.png" class="logo" />
    </div>

    <!-- on boarding 화면 -->
    <Onboard v-if="showOnboard" v-on:endOnboard="endOnboarding" />
  </div>
</template>
<script>
import '@/components/css/main/splash.scss';
import Onboard from '@/components/main/onboard';

export default {
  name: 'Splash',
  components: {
    Onboard,
  },
  data() {
    return {
      showOnboard: false,
    };
  },
  methods: {
    startOnboarding() {
      this.showOnboard = true;
    },
    endOnboarding() {
      // 메인페이지로 이동
      this.$router.push('/');
    },
  },
  mounted() {
    setTimeout(this.startOnboarding, 2500);
    this.$store.commit('READSPLASH');
  },
};
</script>
