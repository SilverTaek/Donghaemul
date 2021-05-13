<template>
  <div class="information">
    <p class="title">
      {{ ingredientName }}
    </p>

    <div class="message">
      오늘 {{ count | comma }}명의 사람들이 조회했어요!
      <Eyes />
    </div>
  </div>
</template>
<script>
import '@/components/css/detail/information.scss';
import { Eyes } from '@/assets/index.js';

export default {
  name: 'Information',
  components: {
    Eyes,
  },
  data() {
    return {
      ingredientId: this.$route.params.id, //  라우터에서 ingredientId 찾기
      ingredientName: '',
      count: '',
    };
  },
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  methods: {
    getIngredientInfo(ingredientId) {
      this.$axios({
        url: '/ingredients/detailId/' + ingredientId,
        method: 'GET',
      })
        .then((response) => {
          this.ingredientName = response.data.ingredientName;
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
  created() {
    this.getIngredientInfo(this.ingredientId);
    this.getIngredientWatchs(this.ingredientId);
  },
};
</script>
