<template>
  <div
    :class="{
      header: 'header',
      headerGreen: ingredientCategory === '농산',
      headerBlue: ingredientCategory === '수산',
      headerRed: ingredientCategory === '축산',
    }"
  >
    <v-toolbar color="transparent">
      <v-app-bar-nav-icon>
        <v-btn icon @click="goBack">
          <v-icon color="white">mdi-chevron-left</v-icon>
        </v-btn>
      </v-app-bar-nav-icon>
      <v-toolbar-title class="title">{{ ingredientName }}</v-toolbar-title>
      <v-btn icon @click="onclickFavorite">
        <v-icon color="white" v-if="favorite">mdi-heart</v-icon>
        <v-icon color="white" v-else>mdi-heart-outline</v-icon>
      </v-btn>

      <v-spacer></v-spacer>

      <!-- 검색창 -->
      <v-toolbar class="search" dense max-width="283px">
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
    </v-toolbar>
  </div>
</template>
<script>
import '@/components/css/detail/header.scss';

export default {
  name: 'Header',
  data() {
    return {
      ingredientId: this.$route.params.id, //  라우터에서 ingredientId 찾기
      ingredientName: '',
      ingredientCategory: '',
      userId: this.$store.state.userId,
      favorite: false,
      // -- search 관련 state --
      loading: false,
      items: [], // 검색할 때마다 밑에 나오는 데이터
      searchIngredientName: [], // autocomplete 검색을 위해 이름만 있는 데이터
      search: null,
      select: null,
      ingredients: [
        {
          ingredientId: 0,
          ingredientName: '',
        },
      ],
      // --
    };
  },
  watch: {
    search(val) {
      val && val !== this.select && this.querySelections(val);
    },
    select: function() {
      // 검색한 카드 컴포넌트만 보여주기
      const selectId = this.getIngredientId(this.select);
      this.handleInsertWatch(selectId);
      this.$router.push({
        name: 'Detail',
        params: {
          id: selectId,
        },
      });
    },
  },
  methods: {
    goBack() {
      //  뒤로가기
      this.$router.push({ name: 'Main' });
    },
    getIngredientName() {
      // 상품 이름 불러오기
      this.$axios({
        url: '/ingredients/detailId/' + this.ingredientId,
        method: 'GET',
      })
        .then((response) => {
          let ingredientName = response.data.ingredientName;
          if (response.data.ingredientDetailName !== null) {
            ingredientName = response.data.ingredientDetailName + '(' + ingredientName + ')';
          }
          this.ingredientName = ingredientName;
          this.ingredientCategory = response.data.ingredientCategory;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    onclickFavorite() {
      // 즐겨찾기 toggle
      this.$axios({
        url: '/favorites',
        method: 'POST',
        data: {
          ingredientId: this.ingredientId,
          memberId: this.userId,
        },
      })
        .then(() => {
          this.$router.go(this.$router.currentRoute); // 페이지 새로고침
        })
        .catch((error) => {
          console.error(error);
        });
    },
    getFavorite() {
      // 즐겨찾기 여부 가져오기
      this.$axios({
        url: '/favorites/' + this.userId + '/' + this.ingredientId,
        method: 'GET',
      }).then((response) => {
        if (response.data == 1) {
          this.favorite = true;
        }
      });
    },
    // -- search 관련 method --
    querySelections(v) {
      this.loading = true;
      // Simulated ajax query
      setTimeout(() => {
        this.items = this.searchIngredientName.filter((e) => {
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

          this.searchIngredientName = this.ingredients.map((item) => {
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
    // --
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
    this.getIngredients(); // 검색 기능을 위한 데이터 로드
  },
  mounted() {
    this.getIngredientName(); // 찾은 ingredientId로 ingredientName 찾기
    this.getFavorite(); // 즐겨찾기 상품인지 표시
  },
};
</script>
