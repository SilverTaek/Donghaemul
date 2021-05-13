<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th>
            항목
          </th>
          <th>
            가격
          </th>
          <th>
            하락율
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in items" :key="index" @click="moveToRecommend(item.ingredientId)">
          <td v-if="item.ingredientDetailName == null">
            {{ item.ingredientName }}
          </td>
          <td v-else>{{ item.ingredientDetailName }}</td>
          <td>{{ item.ingredientAvgPrice | comma }}원</td>
          <td>{{ item.rate }}%</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>
<script>
export default {
  name: 'CheapRecommend',
  data() {
    return {
      items: [],
      ingredientId: this.$route.params.id,
    };
  },
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  methods: {
    getFluctuationRecommand: function() {
      this.$axios({
        url: '/fluctuationRates/rate/' + this.ingredientId,
        method: 'GET',
      })
        .then((response) => {
          this.items = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    moveToRecommend: function(index) {
      this.$axios({
        url: '/watches',
        method: 'POST',
        data: {
          ingredientId: index,
          memberId: this.$store.state.userId,
        },
      })
        .then(() => {
          this.$router.push({
            name: 'Detail',
            params: {
              id: index,
            },
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    this.getFluctuationRecommand();
  },
};
</script>
