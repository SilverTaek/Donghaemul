<template>
  <div class="recommendContainer">
    <v-tabs v-model="tab" grow height="70">
      <v-tab>
        <img src="@/assets/recommend.png" alt="추천상품" width="29" height="29" />
        추천상품
      </v-tab>
      <v-tab>
        <img src="@/assets/hot.png" alt="인기상품" width="29" height="29" />
        인기상품
      </v-tab>
      <v-tab>
        <img src="@/assets/cheap.png" alt="저렴한상품" width="29" height="29" />
        저렴한 상품
      </v-tab>
    </v-tabs>

    <CFRecommend v-show="tab === 0" class="recommend" />
    <PopularRecommend v-show="tab === 1" class="recommend" />
    <CheapRecommend v-show="tab === 2" class="recommend" />
  </div>
</template>
<script>
import '@/components/css/detail/recommend.scss';
import PopularRecommend from './popularRecommend';
import CheapRecommend from './cheapRecommend';
import CFRecommend from './CFRecommend';

export default {
  name: 'Recommend',
  components: {
    PopularRecommend,
    CheapRecommend,
    CFRecommend,
  },
  data() {
    return {
      tab: 0,
    };
  },
};
</script>
