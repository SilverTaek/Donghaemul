<template>
  <div class="currentPrice">
    <p class="title">
      3일 후 예측 가격
      <v-tooltip top>
        <template v-slot:activator="{ on, attrs }">
          <v-icon v-bind="attrs" v-on="on">mdi-help-circle-outline</v-icon>
        </template>
        <span>지자체 농수축산물 물가 정보를 바탕으로 예측한 3일 후 예측 가격 입니다.</span>
      </v-tooltip>
    </p>
    <div class="priceContainer">
      <p class="price">
        {{ price | comma }}원
        <span class="unit">({{ unit }})</span>
      </p>
      <div :class="{ range: 'range', up: status === 1, down: status === 2 }">
        <span v-if="status === 1">+{{ rangePrice | comma }}원 ({{ rangePercent }}%)</span>
        <span v-else-if="status === 2">{{ rangePrice | comma }}원 ({{ rangePercent }}%)</span>
        <span v-else>변동사항 없음</span>

        <Up v-if="status === 1" class="arrow" />
        <Down v-else-if="status === 2" class="arrow" />
      </div>
    </div>

    <div class="message">
      <p v-if="status === 1">
        오늘보다 비싸질 것 같아요.<br />
        빨리 구매해야겠어요!
      </p>
      <p v-else-if="status === 2">
        오늘보다 저렴해질 것 같아요.<br />
        조금 더 있다 구매하는 건 어떠세요?
      </p>
    </div>
  </div>
</template>
<script>
import '@/components/css/detail/priceComponent.scss';
import { Up, Down } from '@/assets/index.js';

export default {
  name: 'PredictPrice',
  components: {
    Up,
    Down,
  },
  mounted() {
    this.getIngredientPrice(this.ingredientId);
    this.getIngredientWatchs(this.ingredientId);
  },
  data() {
    return {
      price: '', // 예측가격
      unit: '', // 단위
      rangePrice: '', // 등락 가격
      rangePercent: '', // 등락률
      status: 0, // 상승, 하락에 따른 스타일 적용을 위한 state(0: 변동없음, 1: 상승, 2: 하락)
      count: '',
      previousPrice: '',
      ingredientId: this.$route.params.id,
    };
  },
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  methods: {
    getIngredientPrice(ingredientId) {
      this.$axios({
        url: '/ingredients/detailId/' + ingredientId,
        method: 'GET',
      })
        .then((response) => {
          this.price = response.data.ingredientAvg.ingredientAvgPredictPrice;
          this.unit = response.data.ingredientUnit;
          this.previousPrice = response.data.ingredientAvg.ingredientAvgPrice;
          this.rangePercent = (
            ((this.price - this.previousPrice) / this.previousPrice) *
            100
          ).toFixed(2);
          this.rangePrice = this.price - this.previousPrice;
          if (this.rangePrice > 0) {
            this.status = 1; // 상승
          } else if (this.rangePrice < 0) {
            this.status = 2; // 하락
          } else {
            this.status = 0; // 변동 없음
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    getIngredientPriceInterval(ingredientId) {
      this.$axios({
        url: '/ingredientAvg/price/interval/' + ingredientId,
        method: 'GET',
      })
        .then((response) => {
          this.rangePrice = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    getIngredientPriceRate(ingredientId) {
      this.$axios({
        url: '/ingredientAvg/rate/' + ingredientId,
        method: 'GET',
      })
        .then((response) => {
          this.rangePercent = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    getIngredientWatchs(ingredientId) {
      this.$axios({
        url: '/watches/' + ingredientId,
        method: 'GET',
      })
        .then((response) => {
          this.count = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
