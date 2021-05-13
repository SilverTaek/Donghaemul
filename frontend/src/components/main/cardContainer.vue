<template>
  <div class="cardContainer" id="cardContainer"></div>
</template>
<script>
import { Login, BookmarkGreen, BookmarkBlue, BookmarkRed, Hot, Warning } from '@/assets/index.js';
import CMRotate from '@/components/main/CMRotate.js';

export default {
  name: 'CardContainer',
  data() {
    return {
      userId: this.$store.state.userId,
      item: [],
    };
  },
  methods: {
    callIngredients() {
      // 비로그인시 전체 목록 조회
      this.$axios({
        url: '/ingredients/',
        method: 'GET',
      })
        .then((response) => {
          this.item = response.data.map((item) => {
            // 숫자 3자리 수에 맞춰 comma 표시
            item.ingredientAvg.ingredientAvgPrice = item.ingredientAvg.ingredientAvgPrice
              .toString()
              .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',');

            // 상품 이미지 추가
            item.imageURL = `https://j4a301.p.ssafy.io/ingredients/ingredients_${item.ingredientId}.png`;

            // 유형에 따라 다른 색상으로 북마크 표시
            if (item.ingredientCategory === '농산') {
              item.bookmark = BookmarkGreen;
            } else if (item.ingredientCategory === '수산') {
              item.bookmark = BookmarkBlue;
            } else if (item.ingredientCategory === '축산') {
              item.bookmark = BookmarkRed;
            }

            // status 표시 (1: 인기상품, 2: 비추천상품)
            if (item.status === 1) {
              item.status = Hot;
            } else if (item.status === 2) {
              item.status = Warning;
            }
            return item;
          });
          this.addLoginCard(); // 로그인 카드 추가
          this.renderCards(this.item);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    callMemberIngredients() {
      // 로그인시 전체 목록 조회
      this.$axios({
        url: '/ingredients/' + this.$store.state.userId,
        method: 'GET',
      })
        .then((response) => {
          this.item = response.data.map((item) => {
            // 숫자 3자리 수에 맞춰 comma 표시
            item.ingredientAvg.ingredientAvgPrice = item.ingredientAvg.ingredientAvgPrice
              .toString()
              .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',');

            // 상품 이미지 추가
            item.imageURL = `https://j4a301.p.ssafy.io/ingredients/ingredients_${item.ingredientId}.png`;

            // 유형에 따라 다른 색상으로 북마크 표시
            if (item.ingredientCategory === '농산') {
              item.bookmark = BookmarkGreen;
            } else if (item.ingredientCategory === '수산') {
              item.bookmark = BookmarkBlue;
            } else if (item.ingredientCategory === '축산') {
              item.bookmark = BookmarkRed;
            }

            // status 표시 (1: 인기상품, 2: 비추천상품)
            if (item.status === 1) {
              item.status = Hot;
            } else if (item.status === 2) {
              item.status = Warning;
            }
            return item;
          });
          this.addLogoutCard(); // 로그아웃 카드 추가
          this.renderCards(this.item);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    renderCards(items) {
      // api로 불러온 목록을 card로 그리기(카드의 개수는 최소 13개)
      const clickCard = (id) => {
        if (id === -1) {
          // 로그인 페이지로 이동
          this.$router.push({ name: 'Login' });
        } else if (id === -2) {
          // 로그아웃
          this.$store.commit('LOGOUT');
          this.$router.go(this.$router.currentRoute); // 페이지 새로고침
        } else {
          // 비로그인 시 로그인 화면으로 분기
          if (this.userId === '') {
            this.$router.push({ name: 'Login' });
          } else {
            this.handleInsertWatch(id);
            // 상세 페이지로 이동
            this.$router.push({
              name: 'Detail',
              params: {
                id: id,
              },
            });
          }
        }
      };
      const radius = window.innerHeight >= 900 ? 1400 : 1200;
      CMRotate.init('cardContainer', 240, 320, 700, 8, radius, items, clickCard);
    },
    addLoginCard() {
      // 로그인 카드 추가
      const loginCard = {
        ingredientId: -1,
        title: '로그인',
        status: Login,
      };
      this.item.push(loginCard);
    },
    addLogoutCard() {
      // 로그아웃 카드 추가
      const logoutCard = {
        ingredientId: -2,
        title: '로그아웃',
        status: Login,
      };
      this.item.push(logoutCard);
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
    if (this.$store.state.userId === '') {
      // 비로그인 시 데이터 조회
      this.callIngredients();
    } else {
      // 로그인 시 데이터 조회
      this.callMemberIngredients();
    }
  },
};
</script>
