<template>
  <div class="card mt-50 mb-50">
    <div class="title mx-auto text">투표 내역</div>
    <div class="main">
      <span id="sub-title text">
        <p>
          <b>{{ clickday }}</b>
        </p>
      </span>
      <div class="row row-main " v-for="d in dateList" :key="d.localDateTime">
        <div class="col-3">
          <img class="img-fluid" :src="d.candidateImg" />
        </div>
        <div class="col-6">
          <div class="row d-flex text">
            <a v-bind:href="d.transactionHash">{{ d.candidateName }}</a>
          </div>
          <div class="row d-flex text ">
            <p class="text-muted">{{ d.agency }}</p>
          </div>
        </div>
        <div class="col-3 d-flex justify-content-end text">
          <div class="valueText">
            <p>
              <b>{{ d.value }}</b>
            </p>
          </div>
        </div>
      </div>
      <hr />
      <div class="total">
        <div class="row">
          <div class="col text"><b> Total:</b></div>
          <div class="col d-flex justify-content-end text">
            <b>{{ totalValue }} DBC</b>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
const userId = localStorage.getItem('id');

export default {
  props: ['propsDate'],
  data() {
    return {
      dateList: [],
      clickday: '',
      totalValue: '0',
      block: [],
    };
  },
  created() {
    console.log('content start!');
    this.clickday = this.propsDate;
    var splitDate = this.propsDate.split('-');
    var day = splitDate[2];
    var month = splitDate[1];
    axios
      .get(
        'http://j4b107.p.ssafy.io/api/elections/' +
          userId +
          '/' +
          month +
          '/' +
          day
      )
      .then((response) => {
        this.dateList = response.data;
        for (var d in this.dateList) {
          if (this.dateList[d].transactionHash) {
            this.dateList[d].transactionHash =
              'https://ropsten.etherscan.io/tx/' +
              this.dateList[d].transactionHash;
          }
        }
        this.totalValue = response.data[0].totalValue;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
@font-face {
  font-family: 'account_font';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
.text {
  font-family: 'account_font';
  font-size: x-large;
}
.valueText {
  font-family: 'account_font';
  font-size: large;
}
.text .b-flex {
  text-align: center;
}
.title {
  display: flex;
  text-align: center;
  font-size: 2rem;
  font-weight: bold;
  padding: 12%;
}

.main {
  padding: 0 2rem;
}

.main img {
  border-radius: 7px;
}

.main p {
  margin-bottom: 0;
  font-size: 0.75rem;
}

#sub-title p {
  margin: 1vh 0 2vh 0;
  font-size: 1rem;
}

.row-main {
  padding: 1.5vh 0;
  align-items: center;
}

hr {
  margin: 1rem -1vh;
  border-top: 1px solid rgb(214, 214, 214);
}

.total {
  font-size: 1rem;
}

@media (height: 1366px) {
  .main p {
    margin-bottom: 0;
    font-size: 1.2rem;
  }

  .total {
    font-size: 1.5rem;
  }
}

.btn {
  background-color: #0c3267;
  border-color: #0c3267;
  color: white;
  margin: 7vh 0;
  border-radius: 7px;
  width: 60%;
  font-size: 0.8rem;
  padding: 0.8rem;
  justify-content: center;
}

.btn:focus {
  box-shadow: none;
  outline: none;
  box-shadow: none;
  color: white;
  -webkit-box-shadow: none;
  -webkit-user-select: none;
  transition: none;
}

.btn:hover {
  color: white;
}
</style>
