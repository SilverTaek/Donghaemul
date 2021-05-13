<template>
  <div class="onboard">
    <img src="@/assets/logoWname2.png" class="logo" />

    <!-- onboarding page 1 -->
    <div v-show="page === 1" class="page">
      <OnboardImg1 class="onboardImg1" />
      <div class="textContainer">
        <h1>똑똑하게 살림하세요!</h1>
        <p>
          혹시 지금 당근<img src="@/assets/carrot.png" class="carrot" />이 얼마인지 아시나요?
          <br />
          물가를 모르면 평소보다 비싸더라도
          <br />
          잘 모르고 사게 됩니다.
        </p>
        <v-btn color="#608F58" large class="nextButton" @click="nextPage">
          농수산물 물가, 이젠 쉽게 알 수 있습니다!
        </v-btn>
      </div>
    </div>

    <!-- onboarding page 2 -->
    <div v-show="page === 2" class="page tutorial">
      <img src="@/assets/onboard_img2.png" class="onboardImg2" />
      <v-btn color="#4B6EB2" large class="nextButton" @click="nextPage">
        상세정보를 알고싶다면? 클릭!
      </v-btn>
    </div>

    <!-- onboarding page 3 -->
    <div v-show="page === 3" class="page">
      <img src="@/assets/onboard_img3.png" class="onboardImg3" />
      <div class="textContainer">
        <h1>빅데이터로 정확하게!</h1>
        <p>
          지자체 농수축산물 물가 정보를
          <br />
          날짜별로 분석하여
          <br />
          평균가격과 예측가격을 알려드려요!
        </p>
        <v-btn color="#D73F32" large class="nextButton" @click="endOnboard">
          서비스 시작하기
        </v-btn>
      </div>
    </div>

    <div class="progressContainer">
      <div :class="{ progress: 'progress', on: page === 1 }" />
      <div :class="{ progress: 'progress', on: page === 2 }" />
      <div :class="{ progress: 'progress', on: page === 3 }" />
    </div>
  </div>
</template>
<script>
import '@/components/css/main/onbaord.scss';
import { OnboardImg1 } from '@/assets/index.js';

export default {
  name: 'Onboard',
  components: {
    OnboardImg1,
  },
  data() {
    return {
      page: 1,
    };
  },
  methods: {
    nextPage() {
      // 다음 온보딩 페이지로 이동
      this.page += 1;
    },
    endOnboard() {
      // 온보딩 페이지 끝
      this.$emit('endOnboard');
    },
  },
};
</script>
