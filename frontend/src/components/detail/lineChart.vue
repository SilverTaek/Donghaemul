<script>
import { Line } from 'vue-chartjs';
import zoom from 'chartjs-plugin-zoom';

export default {
  extends: Line,
  data() {
    return {
      ingredientId: this.$route.params.id,
      chartdata: {
        labels: [],
        items: [],
        datasets: [
          {
            label: '가격 그래프',
            borderColor: '#e55572',
            pointBorderColor: 'transparent',
            pointBackgroundColor: 'transparent',
            backgroundColor: 'transparent',
            data: [],
            pointRadius: 3,
            borderWidth: 1,
            fill: false,
          },
          {
            label: '가격 예측 그래프',
            borderColor: ' #488cde',
            pointBorderColor: 'transparent',
            pointBackgroundColor: 'transparent',
            backgroundColor: 'transparent',
            data: [],
            pointRadius: 2,
            borderWidth: 1,
            fill: false,
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          xAxes: [
            {
              ticks: {
                callback: function(item, index, items) {
                  return items[index].toString().slice(2, 7);
                },
              },
              gridLines: {
                color: 'lightgray',
                borderDash: [2, 5],
              },
              scaleLabel: {
                display: true,
                labelString: '일',
                fontColor: 'red',
              },
            },
          ],
          yAxes: [
            {
              gridLines: {
                color: 'lightgray',
                borderDash: [2, 5],
              },
              scaleLabel: {
                display: true,
                labelString: '단위: 원',
                fontColor: 'green',
              },
            },
          ],
        },
      },
    };
  },
  methods: {
    getIngredientPriceYear(ingredientId) {
      this.$axios({
        url: '/transition/' + ingredientId,
        method: 'GET',
      })
        .then((response) => {
          this.items = response.data;

          this.chartdata.labels = this.items.map((item) => {
            item = item.ingredientAvgDate;
            return item;
          });
          this.chartdata.datasets[0].data = this.items.map((price) => {
            price = price.ingredientAvgPrice;
            return price;
          });
          this.chartdata.datasets[1].data = this.items.map((PredictPrice) => {
            PredictPrice = PredictPrice.ingredientAvgPredictPrice;
            return PredictPrice;
          });
          this.renderChart(this.chartdata, this.options);
        })
        .catch(() => {});
    },
  },
  mounted() {
    this.getIngredientPriceYear(this.ingredientId);
    this.addPlugin(zoom);
  },
};
</script>
