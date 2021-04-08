<template>
  <div class="container">
    <link
      rel="stylesheet"
      type="text/css"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/css/bootstrap-extended.min.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/fonts/simple-line-icons/style.min.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/css/colors.min.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/css/bootstrap.min.css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Montserrat&display=swap"
      rel="stylesheet"
    />
    <div class="grey-bg container-fluid">
      <section id="stats-subtitle">
        <div class="row">
          <div class="col-12 mt-3 mb-1">
            <h4 class="text-uppercase">Ethereum & Token</h4>
            <p>Statistics on ethereum and tokens</p>
          </div>
        </div>

        <div class="row">
          <div class="col-xl-6 col-md-12">
            <div class="card">
              <div class="card-content">
                <div class="card-body cleartfix">
                  <div class="media align-items-stretch">
                    <div class="align-self-center">
                      <h1 class="mr-2">{{ DBCbalance }} DBC</h1>
                    </div>
                    <div class="media-body">
                      <h4 class="content_infospan">Total DBC</h4>
                      <vs-button
                        transparent
                        :active="active == 1"
                        @click="[(active = 1), GetERCBalance()]"
                      >
                        refresh
                      </vs-button>
                    </div>

                    <div class="align-self-center_icon">
                      <i class="icon-heart danger font-large-2"> </i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="col-xl-6 col-md-12">
            <div class="card">
              <div class="card-content">
                <div class="card-body cleartfix">
                  <div class="media align-items-stretch">
                    <div class="align-self-center">
                      <h1 class="mr-2">{{ ETHbalance }} ETH</h1>
                    </div>
                    <div class="media-body">
                      <h4 class="content_infospan">Total Ethereum</h4>
                      <vs-button
                        transparent
                        :active="active == 1"
                        @click="[(active = 1), GetBalance()]"
                      >
                        refresh
                      </vs-button>
                    </div>

                    <div class="align-self-center_icon">
                      <i class="icon-wallet success font-large-2"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section id="minimal-statistics">
        <div class="row">
          <div class="col-12 mt-3 mb-1">
            <h4 class="text-uppercase">account information</h4>
            <p>Statistics for your account</p>
          </div>
        </div>
        <div class="row">
          <div class="col-xl-3 col-sm-6 col-12">
            <div class="card">
              <div class="card-content">
                <div class="card-body">
                  <div class="media d-flex">
                    <div class="align-self-center">
                      <i
                        class="icon-pencil primary font-large-2 float-left"
                      ></i>
                    </div>
                    <div class="media-body text-right">
                      <h3>{{ statisInfo.dbcTransactions }} 회</h3>
                      <span class="content_infospan"> 투표 횟수</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-xl-3 col-sm-6 col-12">
            <div class="card">
              <div class="card-content">
                <div class="card-body">
                  <div class="media d-flex">
                    <div class="align-self-center">
                      <i
                        class="icon-speech warning font-large-2 float-left"
                      ></i>
                    </div>
                    <div class="media-body text-right">
                      <h3>{{ statisInfo.receiveTransactionsDbc }} 회</h3>
                      <span class="content_infospan">DBC받은 횟수</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-xl-3 col-sm-6 col-12">
            <div class="card">
              <div class="card-content">
                <div class="card-body">
                  <div class="media d-flex">
                    <div class="align-self-center">
                      <i class="icon-graph success font-large-2 float-left"></i>
                    </div>
                    <div class="media-body text-right">
                      <h3>{{ statisInfo.totalDbc }} DBC</h3>
                      <span class="content_infospan">투표한 양</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-xl-3 col-sm-6 col-12">
            <div class="card">
              <div class="card-content">
                <div class="card-body">
                  <div class="media d-flex">
                    <div class="align-self-center">
                      <i
                        class="icon-book-open primary font-large-2 float-left"
                      ></i>
                    </div>
                    <div class="media-body text-right">
                      <h3>{{ statisInfo.receiveDbc }} DBC</h3>
                      <span class="content_infospan">DBC받은 양</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-xl-3 col-sm-6 col-12">
            <div class="card">
              <div class="card-content">
                <div class="card-body">
                  <div class="media d-flex">
                    <div class="media-body text-left">
                      <h3>{{ statisInfo.receivedEthTransactions }} 회</h3>
                      <span class="content_infospan">ETH받은 횟수</span>
                    </div>
                    <div class="align-self-center">
                      <i
                        class="icon-rocket danger font-large-2 float-right"
                      ></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

//
<script>
let web3;
import * as Web3 from 'web3';
import axios from 'axios';
import DBCabi from './DBCabi.json';
const userId = localStorage.getItem('id');

export default {
  data() {
    return {
      active: 0,
      DBCbalance: 0,
      ETHbalance: 0,
      account: '',
      statisInfo: [
        {
          dbcTransactions: '',
          receiveDbc: '',
          receiveTransactionsDbc: '',
          receivedEth: '',
          receivedEthTransactions: '',
          totalDbc: '',
        },
      ],
    };
  },
  created() {
    console.log('ready!');
    if (typeof web3 !== 'undefined') {
      console.log('Web3 Detected! ' + web3.currentProvider.constructor.name);
      web3 = new Web3(web3.currentProvider);
    } else {
      console.log('No Web3 detected .. using HTTP provider');
      const projectId = 'b04025a46bb245b3bdb7c350a938dbe5';
      web3 = new Web3(
        new Web3.providers.HttpProvider(
          `https://ropsten.infura.io/v3/${projectId}`
        )
      );
    }
    axios
      .get('http://j4b107.p.ssafy.io/api/members/' + userId + '')
      .then((response) => {
        this.account = response.data.account;
        if (response.data.dbc === null) {
          this.DBCbalance = 0;
          this.ETHbalance = 0;
        } else {
          this.DBCbalance = response.data.dbc;
          this.ETHbalance = response.data.eth;
        }
      })
      .catch((error) => {
        console.log(error);
      });
    axios
      .get('http://j4b107.p.ssafy.io/api/election/accountInfo/' + userId)
      .then((response) => {
        this.statisInfo = response.data;
        if (this.statisInfo.receiveDbc === null) {
          this.statisInfo.receiveDbc = 0;
        }
        if (this.statisInfo.receivedEth === null) {
          this.statisInfo.receivedEth = 0;
        }
        if (this.statisInfo.totalDbc === null) {
          this.statisInfo.totalDbc = 0;
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    GetBalance: function() {
      var account = this.account;
      this.ETHbalance = 0;
      web3.eth.getBalance(account, (error, wei) => {
        if (!error) {
          this.ETHbalance = web3.utils.fromWei(wei, 'ether');
          axios
            .put(
              'http://j4b107.p.ssafy.io/api/members/refreshEth/' +
                userId +
                '/' +
                this.ETHbalance
            )
            .then(() => {})
            .catch((error) => {
              console.log(error);
            });
        }
      });
      this.active = 0;
    },
    GetERCBalance: function() {
      var account = this.account;
      var contractAddress = '0x9864bb32e02b1fae9eb875f7b169c5400b15efec';
      var TokenABI = DBCabi;
      var DBCcontract = new web3.eth.Contract(TokenABI, contractAddress);
      let DBCtoken;
      this.DBCbalance = 0;
      DBCcontract.methods
        .balanceOf(account)
        .call()
        .then((result) => {
          DBCtoken = result / 100000;
          this.DBCbalance = DBCtoken;
          axios
            .put(
              'http://j4b107.p.ssafy.io/api/members/refreshDbc/' +
                userId +
                '/' +
                this.DBCbalance
            )
            .then(() => {})
            .catch((error) => {
              console.log(error);
            });
        });
      this.active = 0;
    },
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
.container {
  overflow: auto;
}
.text-uppercase {
  font-family: 'account_font';
  font-size: xxx-large;
}
.content_infospan {
  font-family: 'account_font';
  font-size: x-large;
}
.grey-bg {
  background-color: #f5f7fa;
}
.align-self-center {
  width: 60%;
}
.align-self-center_icon {
  width: 30%;
}
.media-body {
  width: 60%;
}
</style>
