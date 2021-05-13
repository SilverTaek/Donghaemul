<template>
  <div class="main">
    <!-- 배경 그라데이션을 위한 div -->
    <div class="background" />

    <!-- 커서 -->
    <div class="cursor" id="cursor" />

    <!-- 로고 -->
    <div class="logo">
      <Logo />
    </div>

    <!-- 로그인 -->
    <div id="login">
      <router-link to="/login">로그인</router-link>
    </div>

    <!-- 검색창 -->
    <v-toolbar class="search">
      <v-toolbar-title>
        <v-icon>mdi-magnify</v-icon>
      </v-toolbar-title>
      <v-autocomplete
        v-model="select"
        :loading="loading"
        :items="items"
        :search-input.sync="search"
        cache-items
        class="mx-4"
        flat
        hide-no-data
        hide-details
        label="상품 검색"
        solo
      ></v-autocomplete>
    </v-toolbar>

    <!-- 카드 컴포넌트 -->
    <CardContainer />
  </div>
</template>
<script>
import '@/components/css/main/style.scss';
import '@/components/css/main/card.scss';
import { Logo } from '@/assets/index.js';
import CardContainer from '@/components/main/cardContainer';

export default {
  name: 'Main',
  components: {
    Logo,
    CardContainer,
  },
  data() {
    return {
      userId: this.$store.state.userId,
      loading: false,
      items: [], // 검색할 때마다 밑에 나오는 데이터
      ingredientName: [], // autocomplete 검색을 위해 이름만 있는 데이터
      search: null,
      select: null,
      ingredients: [
        {
          ingredientId: 0,
          ingredientName: '',
        },
      ],
    };
  },
  watch: {
    search(val) {
      val && val !== this.select && this.querySelections(val);
    },
    select: function() {
      // 검색한 카드 컴포넌트만 보여주기
      const selectId = this.getIngredientId(this.select);

      // 비로그인 시 로그인 화면으로 분기
      if (this.userId === '') {
        this.$router.push({ name: 'Login' });
      } else {
        this.handleInsertWatch(selectId);
        this.$router.push({
          name: 'Detail',
          params: {
            id: selectId,
          },
        });
      }
    },
  },
  methods: {
    querySelections(v) {
      this.loading = true;
      // Simulated ajax query
      setTimeout(() => {
        this.items = this.ingredientName.filter((e) => {
          return (e || '').toLowerCase().indexOf((v || '').toLowerCase()) > -1;
        });
        this.loading = false;
      }, 500);
    },
    getIngredients() {
      // ingredients 받아오기
      this.$axios({
        url: '/ingredients/ingredientName',
        method: 'GET',
      })
        .then((response) => {
          this.ingredients = response.data.map((item) => {
            let ingredientName = item.ingredientName;
            if (item.ingredientDetailName !== null) {
              ingredientName += `(${item.ingredientDetailName})`;
            }
            item = {
              ingredientId: item.ingredientId,
              ingredientName: ingredientName,
            };
            return item;
          });

          this.ingredientName = this.ingredients.map((item) => {
            return item.ingredientName;
          });
        })
        .catch((error) => {
          console.error(error);
        });
    },
    getIngredientId(name) {
      return this.ingredients.filter((item) => {
        return item.ingredientName === name;
      })[0].ingredientId;
    },
    // 조회수 카운트를 위한 api 호출
    handleInsertWatch(ingredientId) {
      this.$axios({
        url: '/watches',
        method: 'POST',
        data: {
          ingredientId: ingredientId,
          memberId: this.userId,
        },
      })
        .then(() => {})
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    // 첫 방문인 경우 -> splash 화면으로 분기
    if (this.$store.state.splash) {
      this.$router.push({ name: 'Splash' });
    }

    this.getIngredients();
  },
};
</script>
