<template>
  <div>
    <p class="title">
      쇼핑몰별 최저가
    </p>

    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th width="25%">
              판매처
            </th>
            <th>
              상품명
            </th>
            <th width="15%">
              가격
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in items" :key="index">
            <td>{{ item.shoppingApiStore }}</td>
            <td>
              <a :href="item.shoppingApiLink" target="item.place">{{ item.shoppingApiTitle }}</a>
            </td>
            <td>{{ item.shoppingApiPrice | comma }}원</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>
<script>
import '@/components/css/detail/shopping.scss';

export default {
  name: 'Shopping',
  data() {
    return {
      ingredientId: this.$route.params.id,
      items: [],
      place: '_blank',
    };
  },
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  created: function() {
    // 쇼핑몰 API 호출
    this.$axios({
      url: `/shoppings/${this.ingredientId}`,
      method: 'GET',
    })
      .then((response) => {
        this.items = response.data.slice(0, 5); // 데이터 5개만 잘라서 표로 그림
      })
      .catch((error) => {
        console.error(error);
      });
  },
};
</script>
