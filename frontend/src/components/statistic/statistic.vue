<template>
  <div class="wrapper text">
    <div class="wrapper__header">
      <div class="b_caption">
        <p>leaderboard <span>Blockduce</span></p>
      </div>
    </div>
    <div class="wrapper__content">
      <ul>
        <li v-for="c in candidates" :key="c.id" v-show="c.id != 1">
          {{ c.rank }}.
          <div class="graphic">
            <a v-bind:href="c.href"><img :src="c.img" alt=""/></a>
          </div>
          <div class="name">
            <span class="header"> {{ c.name }} </span
            ><span class="stat">{{ c.account.dbc }}</span
            ><span class="sub">DBC</span>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
export default {
  data() {
    return {
      candidates: [],
    };
  },
  created() {
    axios
      .get('http://j4b107.p.ssafy.io/api/candidates')
      .then((response) => {
        this.candidates = response.data.data;

        this.candidates.sort(function(a, b) {
          return parseFloat(b.account.dbc) - parseFloat(a.account.dbc);
        });
        for (var d in this.candidates) {
          if (this.candidates[d].account.account) {
            this.candidates[d].href =
              'https://ropsten.etherscan.io/address/' +
              this.candidates[d].account.account;
            this.candidates[d].rank = d;
          }
        }
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
@import url('https://fonts.googleapis.com/css?family=Montserrat:400,700');

.wrapper .wrapper__header {
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  background: #ffffff;
  padding: 27px 30px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.wrapper .wrapper__header .b_caption p {
  font-size: 40px;
  text-transform: uppercase;
  font-weight: 700;
  letter-spacing: 2px;
}
.wrapper .wrapper__header .b_caption p span {
  display: block;
  text-transform: uppercase;
  letter-spacing: 1.5px;
  font-size: 20px;
}
.wrapper .wrapper__content {
  background: #fff;
  padding: 10px;
  margin-top: 30px;
  margin-left: 40px;
  width: 85%;
  height: 100%;
}
.wrapper .wrapper__content ul {
  list-style: none;
}
.wrapper .wrapper__content ul li {
  height: 100px;
  cursor: pointer;
  background: #fefefe;
  padding: 10px 5px;
  border-radius: 5px;
  box-shadow: 0px 0px 10px 1px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: space-between;
  align-items: center;
  transition: transform 0.1s ease-in-out;
  z-index: 4;
}
.wrapper .wrapper__content ul li:hover {
  z-index: 5;
  transform: scale(1.1);
  box-shadow: 0px 0px 10px 10px rgba(0, 0, 0, 0.1);
}
.wrapper .wrapper__content ul li:not(:last-child) {
  margin-bottom: 10px;
}
.wrapper .wrapper__content ul li .graphic {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background: #ebeef3;
  overflow: hidden;
  padding: 5px;
}
.wrapper .wrapper__content ul li .graphic img {
  width: 100%;
  border-radius: 50%;
}
.wrapper .wrapper__content ul li .name span {
  vertical-align: middle;
}
.wrapper .wrapper__content ul li .name span.header {
  font-weight: 700;
  margin-right: 15px;
  vertical-align: middle;
}
.wrapper .wrapper__content ul li .name span.stat {
  color: #0c3267;
  font-weight: 700;
  font-size: 25px;
}
.wrapper .wrapper__content ul li .name span.sub {
  color: #0c3267;
  font-weight: 700;
  font-size: 10px;
  vertical-align: sub;
}
</style>
