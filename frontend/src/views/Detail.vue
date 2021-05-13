<template>
  <div class="detail">
    <!-- header -->
    <Header />

    <!-- body -->
    <v-container class="justify-center align-center contents">
      <v-row class="row row-1">
        <v-col :cols="4">
          <v-card class="pa-2 infoCard" :style="cssVars">
            <Information />
          </v-card>
        </v-col>
        <v-col :cols="4">
          <v-card class="pa-2">
            <CurrentPrice />
          </v-card>
        </v-col>
        <v-col :cols="4">
          <v-card class="pa-2">
            <PredictPrice />
          </v-card>
        </v-col>
      </v-row>
      <v-row class="row row-2">
        <v-col :cols="12">
          <v-card class="pa-2">
            <div class="lineChart">
              <p class="title">
                1년 가격 추이
                <v-tooltip top>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon v-bind="attrs" v-on="on">mdi-help-circle-outline</v-icon>
                  </template>
                  <span
                    >1년동안의 시세를 시각화했습니다. 범례를 클릭하면 각각의 그래프를 볼 수
                    있습니다.</span
                  >
                </v-tooltip>
              </p>
              <LineChart :height="270" />
            </div>
          </v-card>
        </v-col>
      </v-row>
      <v-row class="row row-3">
        <v-col :cols="6">
          <v-card class="pa-2">
            <Shopping />
          </v-card>
        </v-col>
        <v-col :cols="6">
          <v-card class="pa-2">
            <Recommend />
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import '@/components/css/detail/style.scss';
import Header from '@/components/detail/header';
import Information from '@/components/detail/information';
import CurrentPrice from '@/components/detail/currentPrice';
import PredictPrice from '@/components/detail/predictPrice';
import LineChart from '@/components/detail/lineChart';
import Shopping from '@/components/detail/shopping';
import Recommend from '@/components/detail/recommend';

export default {
  name: 'Detail',
  components: {
    Header,
    Information,
    CurrentPrice,
    PredictPrice,
    LineChart,
    Shopping,
    Recommend,
  },
  computed: {
    // 상품 이미지 출력
    cssVars() {
      return {
        '--bg-url': `url('https://j4a301.p.ssafy.io/ingredients/ingredients_${this.$route.params.id}.png')`,
      };
    },
  },
};
</script>
