<template>
  <div>
    <Navbar/>
    <img class="crown" src="@/assets/crown.png" alt="">
    <div>
      <div class="realtime">
        실시간 순위
      </div>
      <div :class="'rank top'+(idx+1)" v-for="(person, idx) in top6" :key="idx">
        {{ idx + 1 }}등
        <img class="photo photoblock" :src="person.candidateImg"/>
        <div class="name">
          {{ person.candidateName }}
        </div>
      </div>
    </div>


    <div class="chart">
      <!-- <card> -->
        <h6 class="text-uppercase text-muted">
          순위 그래프
        </h6>
        <div>
          <canvas :height="350" :id="ordersChartID"></canvas>
        </div>



    </div>

  </div>
</template>

<script>
import Navbar from '@/components/Navbar.vue'
import axios from 'axios'
import Chart from "chart.js"

// let chart;

export default {
  components: {
    Navbar,
  },

  data() {
    return {

      ordersChartID: "ordersChart",

      candidates: [
        {
          img_url: require("@/assets/1.jpg"),
          name: '최주아',
          nov: 1235154300,
        },
        {
          img_url: require("@/assets/2.jpg"),
          name: '류건희',
          nov: 1235154353
        },
        {
          img_url: require("@/assets/3.jpg"),
          name: '박상우',
          nov: 1235154352
        },
        {
          img_url: require("@/assets/4.jpg"),
          name: '이아영',
          nov: 1235154351
        },
        {
          img_url: require("@/assets/5.jpg"),
          name: '황민현',
          nov: 1235154350
        },
        {
          img_url: require("@/assets/6.jpg"),
          name: '강다니엘',
          nov: 1235154999
        },
        {
          img_url: require("@/assets/7.jpg"),
          name: '배진영',
          nov: 1235153000
        },

      ],

      top6 : [],
      candidates2: []
    }
  },

  methods: {

    createChart(chartId) {
    const chartColor = "#FFFFFF";
    const fallBackColor = "#f96332";
    const color = this.color || fallBackColor;
    const ctx = document.getElementById(chartId).getContext("2d");
    const gradientStroke = ctx.createLinearGradient(500, 0, 100, 0);
    gradientStroke.addColorStop(0, color);
    gradientStroke.addColorStop(1, chartColor);

    new Chart(ctx, {
      type: "bar",
      data: {
        labels: ["최주아", "황영준", "류건희", "황호연", "박상우", "이상현"],
        datasets: [
          {
            label: "points",
            tension: 0.4,
            borderWidth: 0,
            pointRadius: 0,
            backgroundColor: "#962afa",
            data: [1000, 1500, 20, 1200, 1400, 1500],
            maxBarThickness: 10,
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
          display: false,
        },
        tooltips: {
          enabled: true,
          mode: "index",
          intersect: false,
        },
        scales: {
          yAxes: [
            {
              gridLines: {
                borderDash: [2],
                borderDashOffset: [2],
                drawBorder: false,
                drawTicks: false,
                lineWidth: 0,
                zeroLineWidth: 0,
                zeroLineBorderDash: [2],
                zeroLineBorderDashOffset: [2],
              },
              ticks: {
                beginAtZero: true,
                padding: 10,
                fontSize: 13,
                fontColor: "#8898aa",
                fontFamily: "Open Sans",
                callback: function (value) {
                  if (!(value % 10)) {
                    return value;
                  }
                },
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                drawBorder: false,
                drawOnChartArea: false,
                drawTicks: false,
              },
              ticks: {
                padding: 20,
                fontSize: 13,
                fontColor: "#8898aa",
                fontFamily: "Open Sans",
              },
            },
          ],
        },
      },
    });
  },



  },

  mounted() {

    this.createChart(this.ordersChartID);

    axios.get('http://j4b107.p.ssafy.io/api/candidates')
    .then((response) => {
    this.candidates2 = response.data.data

    this.candidates2.sort(function(a, b) {
      return parseFloat(b.age) - parseFloat(a.age)
    })

    for (let index = 0; index < 6; index++) {
      this.top6.push(this.candidates2[index]) 
    }

    console.log(this.top6)

    // this.createChart(this.ordersChartID);

    
    })
    .catch((err) => {
      console.log(err);
      })
  

    },

    

}
</script>

<style>

.photo {
  width: 20vw;
  border-radius: 10vw;
  
}

.photoblock {
  max-width: 100%;
  height: auto;
  border: 2px solid #eed5f7;
}

.rank {
  position: absolute;
  width: 30vw;
  height: 30vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 10px;
}

.top1 {

  margin-left: 35vw;
  margin-top: 2vh;

}
.top2 {
  margin-left: 17vw;
  margin-top: 21vh;

}
.top3 {
  margin-left: 50vw;
  margin-top: 21vh;

}
.top4 {
    margin-left: 5vw;
    margin-top: 42vh;
}
.top5 {
    margin-left: 35vw;
    margin-top: 42vh;
}
.top6 {
    margin-left: 65vw;
    margin-top: 42vh;
}

.name {
  margin-top: 1vh;
}

.crown {
  position: absolute;
  width: 6vw;
  transform: rotate(-25deg);
  margin-left: -11vw;
  margin-top: 9vh;
}

.realtime {
  
  font-size: 15px;
  margin-top: 4vh;
  margin-left: 5vw;
}

.chart {
  margin-top: 100px;
}

</style>