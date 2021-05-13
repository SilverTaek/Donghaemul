<template>
  <td class="table">
    <div
      :class="['CFRecommendCard', 'border', ingredient.ingredientCategory]"
      v-if="ingredient.ingredientDetailName === null"
      @click="moveToIngredient"
    >
      <GreenIcon v-if="ingredient.ingredientCategory === '농산'" class="icon" />
      <BlueIcon v-if="ingredient.ingredientCategory === '수산'" class="icon" />
      <RedIcon v-if="ingredient.ingredientCategory === '축산'" class="icon" />
      {{ ingredient.ingredientName }}
    </div>
    <div
      :class="['CFRecommendCard', 'border', ingredient.ingredientCategory]"
      v-else
      @click="moveToIngredient"
    >
      <GreenIcon v-if="ingredient.ingredientCategory === '농산'" class="icon" />
      <BlueIcon v-if="ingredient.ingredientCategory === '수산'" class="icon" />
      <RedIcon v-if="ingredient.ingredientCategory === '축산'" class="icon" />
      {{ ingredient.ingredientDetailName }}
    </div>
  </td>
</template>
<script>
import { RedIcon, BlueIcon, GreenIcon } from '@/assets/index.js';

export default {
  name: 'CFRecommendCard',
  components: {
    RedIcon,
    BlueIcon,
    GreenIcon,
  },
  props: {
    ingredient: Object,
  },
  methods: {
    moveToIngredient: function() {
      this.$axios({
        url: '/watches',
        method: 'POST',
        data: {
          ingredientId: this.ingredient.ingredientId,
          memberId: this.$store.state.userId,
        },
      })
        .then(() => {
          this.$router.push({
            name: 'Detail',
            params: {
              id: this.ingredient.ingredientId,
            },
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
