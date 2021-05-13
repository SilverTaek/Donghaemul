<template>
  <div class="CFRecommend">
    <h1 class="title">
      이런 상품은 어때요?
      <v-tooltip bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-icon v-bind="attrs" v-on="on">mdi-help-circle-outline</v-icon>
        </template>
        <span>회원 정보를 기반으로 추천하는 상품입니다.</span>
      </v-tooltip>
    </h1>
    <v-progress-circular indeterminate class="loading" v-if="items.length === 0" />
    <div class="cardContainer" v-else>
      <CFRecommendCard v-for="(item, index) in items" :key="index" :ingredient="item" />
    </div>
  </div>
</template>
<script>
import CFRecommendCard from './CFRecommendCard';
import axios from 'axios';

export default {
  name: 'CFRecommend',
  components: {
    CFRecommendCard,
  },
  data() {
    return {
      items: [],
    };
  },
  methods: {
    getCFRecommand: function() {
      // 해당 axios만 python에서 호출하기에 baseURL변경을 위해 instance를 생성했습니다.
      const instance = axios.create({
        baseURL: 'https://j4a301.p.ssafy.io',
      });
      instance
        .get('/cf/' + this.$store.state.userId)
        .then((response) => {
          this.items = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  created() {
    this.getCFRecommand();
  },
};
</script>
