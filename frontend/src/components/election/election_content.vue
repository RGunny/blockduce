<template>
  <div class="election_container">
    <div class="cardwrap">
      <div class="candidate" v-for="c in candidateInfo" :key="c.id">
        <div class="imgbox">
          <img :src="c.img" />
        </div>
        <div class="specifies font">
          <h2>
            {{ c.name }} <br />
            <span>{{ c.age }} </span>
          </h2>
          <h2>{{ c.agency }} <br /></h2>
          <div class="center content-inputs">
            <vs-input v-model="voteValue" placeholder="1500DBC" />DBC
          </div>
          <div
            class="button_container"
            onclick="this.classList.toggle('active')"
            v-on:click="DBCContract(c.id)"
          >
            <span class="text">투표하기</span>
            <svg
              class="fingerprint fingerprint-base"
              xmlns="http://www.w3.org/2000/svg"
              width="100"
              height="100"
              viewBox="0 0 100 100"
            >
              <g
                class="fingerprint-out"
                fill="none"
                stroke-width="2"
                stroke-linecap="round"
              >
                <path
                  class="odd"
                  d="m 25.117139,57.142857 c 0,0 -1.968558,-7.660465 -0.643619,-13.149003 1.324939,-5.488538 4.659682,-8.994751 4.659682,-8.994751"
                />
                <path
                  class="odd"
                  d="m 31.925369,31.477584 c 0,0 2.153609,-2.934998 9.074971,-5.105078 6.921362,-2.17008 11.799844,-0.618718 11.799844,-0.618718"
                />
                <path
                  class="odd"
                  d="m 57.131213,26.814448 c 0,0 5.127709,1.731228 9.899495,7.513009 4.771786,5.781781 4.772971,12.109204 4.772971,12.109204"
                />
                <path
                  class="odd"
                  d="m 72.334009,50.76769 0.09597,2.298098 -0.09597,2.386485"
                />
                <path
                  class="even"
                  d="m 27.849282,62.75 c 0,0 1.286086,-1.279223 1.25,-4.25 -0.03609,-2.970777 -1.606117,-7.675266 -0.625,-12.75 0.981117,-5.074734 4.5,-9.5 4.5,-9.5"
                />
                <path
                  class="even"
                  d="m 36.224282,33.625 c 0,0 8.821171,-7.174484 19.3125,-2.8125 10.491329,4.361984 11.870558,14.952665 11.870558,14.952665"
                />
                <path
                  class="even"
                  d="m 68.349282,49.75 c 0,0 0.500124,3.82939 0.5625,5.8125 0.06238,1.98311 -0.1875,5.9375 -0.1875,5.9375"
                />
                <path
                  class="odd"
                  d="m 31.099282,65.625 c 0,0 1.764703,-4.224042 2,-7.375 0.235297,-3.150958 -1.943873,-9.276886 0.426777,-15.441942 2.370649,-6.165056 8.073223,-7.933058 8.073223,-7.933058"
                />
                <path
                  class="odd"
                  d="m 45.849282,33.625 c 0,0 12.805566,-1.968622 17,9.9375 4.194434,11.906122 1.125,24.0625 1.125,24.0625"
                />
                <path
                  class="even"
                  d="m 59.099282,70.25 c 0,0 0.870577,-2.956221 1.1875,-4.5625 0.316923,-1.606279 0.5625,-5.0625 0.5625,-5.0625"
                />
                <path
                  class="even"
                  d="m 60.901059,56.286612 c 0,0 0.903689,-9.415996 -3.801777,-14.849112 -3.03125,-3.5 -7.329245,-4.723939 -11.867187,-3.8125 -5.523438,1.109375 -7.570313,5.75 -7.570313,5.75"
                />
                <path
                  class="even"
                  d="m 34.072577,68.846248 c 0,0 2.274231,-4.165782 2.839205,-9.033748 0.443558,-3.821814 -0.49394,-5.649939 -0.714206,-8.05386 -0.220265,-2.403922 0.21421,-4.63364 0.21421,-4.63364"
                />
                <path
                  class="odd"
                  d="m 37.774165,70.831845 c 0,0 2.692139,-6.147592 3.223034,-11.251208 0.530895,-5.103616 -2.18372,-7.95562 -0.153491,-13.647655 2.030229,-5.692035 8.108442,-4.538898 8.108442,-4.538898"
                />
                <path
                  class="odd"
                  d="m 54.391174,71.715729 c 0,0 2.359472,-5.427681 2.519068,-16.175068 0.159595,-10.747388 -4.375223,-12.993087 -4.375223,-12.993087"
                />
                <path
                  class="even"
                  d="m 49.474282,73.625 c 0,0 3.730297,-8.451831 3.577665,-16.493718 -0.152632,-8.041887 -0.364805,-11.869326 -4.765165,-11.756282 -4.400364,0.113044 -3.875,4.875 -3.875,4.875"
                />
                <path
                  class="even"
                  d="m 41.132922,72.334447 c 0,0 2.49775,-5.267079 3.181981,-8.883029 0.68423,-3.61595 0.353553,-9.413359 0.353553,-9.413359"
                />
                <path
                  class="odd"
                  d="m 45.161782,73.75 c 0,0 1.534894,-3.679847 2.40625,-6.53125 0.871356,-2.851403 1.28125,-7.15625 1.28125,-7.15625"
                />
                <path
                  class="odd"
                  d="m 48.801947,56.125 c 0,0 0.234502,-1.809418 0.109835,-3.375 -0.124667,-1.565582 -0.5625,-3.1875 -0.5625,-3.1875"
                />
              </g>
            </svg>
            <svg
              class="fingerprint fingerprint-active"
              xmlns="http://www.w3.org/2000/svg"
              width="100"
              height="100"
              viewBox="0 0 100 100"
            >
              <g
                class="fingerprint-out"
                fill="none"
                stroke-width="2"
                stroke-linecap="round"
              >
                <path
                  class="odd"
                  d="m 25.117139,57.142857 c 0,0 -1.968558,-7.660465 -0.643619,-13.149003 1.324939,-5.488538 4.659682,-8.994751 4.659682,-8.994751"
                />
                <path
                  class="odd"
                  d="m 31.925369,31.477584 c 0,0 2.153609,-2.934998 9.074971,-5.105078 6.921362,-2.17008 11.799844,-0.618718 11.799844,-0.618718"
                />
                <path
                  class="odd"
                  d="m 57.131213,26.814448 c 0,0 5.127709,1.731228 9.899495,7.513009 4.771786,5.781781 4.772971,12.109204 4.772971,12.109204"
                />
                <path
                  class="odd"
                  d="m 72.334009,50.76769 0.09597,2.298098 -0.09597,2.386485"
                />
                <path
                  class="even"
                  d="m 27.849282,62.75 c 0,0 1.286086,-1.279223 1.25,-4.25 -0.03609,-2.970777 -1.606117,-7.675266 -0.625,-12.75 0.981117,-5.074734 4.5,-9.5 4.5,-9.5"
                />
                <path
                  class="even"
                  d="m 36.224282,33.625 c 0,0 8.821171,-7.174484 19.3125,-2.8125 10.491329,4.361984 11.870558,14.952665 11.870558,14.952665"
                />
                <path
                  class="even"
                  d="m 68.349282,49.75 c 0,0 0.500124,3.82939 0.5625,5.8125 0.06238,1.98311 -0.1875,5.9375 -0.1875,5.9375"
                />
                <path
                  class="odd"
                  d="m 31.099282,65.625 c 0,0 1.764703,-4.224042 2,-7.375 0.235297,-3.150958 -1.943873,-9.276886 0.426777,-15.441942 2.370649,-6.165056 8.073223,-7.933058 8.073223,-7.933058"
                />
                <path
                  class="odd"
                  d="m 45.849282,33.625 c 0,0 12.805566,-1.968622 17,9.9375 4.194434,11.906122 1.125,24.0625 1.125,24.0625"
                />
                <path
                  class="even"
                  d="m 59.099282,70.25 c 0,0 0.870577,-2.956221 1.1875,-4.5625 0.316923,-1.606279 0.5625,-5.0625 0.5625,-5.0625"
                />
                <path
                  class="even"
                  d="m 60.901059,56.286612 c 0,0 0.903689,-9.415996 -3.801777,-14.849112 -3.03125,-3.5 -7.329245,-4.723939 -11.867187,-3.8125 -5.523438,1.109375 -7.570313,5.75 -7.570313,5.75"
                />
                <path
                  class="even"
                  d="m 34.072577,68.846248 c 0,0 2.274231,-4.165782 2.839205,-9.033748 0.443558,-3.821814 -0.49394,-5.649939 -0.714206,-8.05386 -0.220265,-2.403922 0.21421,-4.63364 0.21421,-4.63364"
                />
                <path
                  class="odd"
                  d="m 37.774165,70.831845 c 0,0 2.692139,-6.147592 3.223034,-11.251208 0.530895,-5.103616 -2.18372,-7.95562 -0.153491,-13.647655 2.030229,-5.692035 8.108442,-4.538898 8.108442,-4.538898"
                />
                <path
                  class="odd"
                  d="m 54.391174,71.715729 c 0,0 2.359472,-5.427681 2.519068,-16.175068 0.159595,-10.747388 -4.375223,-12.993087 -4.375223,-12.993087"
                />
                <path
                  class="even"
                  d="m 49.474282,73.625 c 0,0 3.730297,-8.451831 3.577665,-16.493718 -0.152632,-8.041887 -0.364805,-11.869326 -4.765165,-11.756282 -4.400364,0.113044 -3.875,4.875 -3.875,4.875"
                />
                <path
                  class="even"
                  d="m 41.132922,72.334447 c 0,0 2.49775,-5.267079 3.181981,-8.883029 0.68423,-3.61595 0.353553,-9.413359 0.353553,-9.413359"
                />
                <path
                  class="odd"
                  d="m 45.161782,73.75 c 0,0 1.534894,-3.679847 2.40625,-6.53125 0.871356,-2.851403 1.28125,-7.15625 1.28125,-7.15625"
                />
                <path
                  class="odd"
                  d="m 48.801947,56.125 c 0,0 0.234502,-1.809418 0.109835,-3.375 -0.124667,-1.565582 -0.5625,-3.1875 -0.5625,-3.1875"
                />
              </g>
            </svg>
            <svg
              class="ok"
              xmlns="http://www.w3.org/2000/svg"
              width="100"
              height="100"
              viewBox="0 0 100 100"
            >
              <path
                d="M34.912 50.75l10.89 10.125L67 36.75"
                fill="none"
                stroke="#fff"
                stroke-width="6"
              />
            </svg>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
let web3;
const userId = localStorage.getItem('id');

import * as Web3 from 'web3';
import DBCabi from '../myAccount/DBCabi.json';
import axios from 'axios';

export default {
  data() {
    return {
      candidateInfo: [],
      send_account: '0x9DFD19acAc0c523D19F9B50B4640a0dD74E092E6',
      receive_account: '0xD9Cba24c647faE6fB0b971487F75d78dCBB6FBb3',
      voteValue: 0,
      privateKey: Buffer.from(
        '91b40449775898b8c31c8cb914f5408bc4e2a619cab888fcf1b0f823b8905ffd',
        'hex'
      ),
      contract_result: '',
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
      .get('http://j4b107.p.ssafy.io/api/candidate/findAll')
      .then((response) => {
        this.candidateInfo = response.data.data;
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    DBCContract: function(candidate_id) {
      console.log('DBC contract start!.....');
      // 토큰 어드레스
      var contractAddress = '0x9864bb32e02b1fae9eb875f7b169c5400b15efec';
      // 토큰 abi
      var TokenABI = DBCabi;
      // 컨트랙트 만들기
      var DBCcontract = new web3.eth.Contract(TokenABI, contractAddress, {
        from: this.receive_account,
      });
      // 1인가
      var amount = web3.utils.toHex(100000 * this.voteValue);
      // ethereumjs-tx npm install  끝에 .Transaction 까지 해서 TX 만들기
      var Tx = require('ethereumjs-tx').Transaction;
      // 이더리움 컨트랙트 만들기
      web3.eth.getTransactionCount(this.send_account, (err, txCount) => {
        const rawTx = {
          from: this.send_account,
          nonce: web3.utils.toHex(txCount),
          value: '0x0',
          to: contractAddress,
          gasLimit: web3.utils.toHex(210000),
          gasPrice: web3.utils.toHex(web3.utils.toWei('20', 'gwei')),
          // 여기가 중요!! 위에서 만든 DBC컨트렉트의 주소로 보내야함
          data: DBCcontract.methods
            .transfer(this.receive_account, amount)
            .encodeABI(),
        };

        console.log('data : ', rawTx.data);

        // tx chain : ropsten 설정
        var tx = new Tx(rawTx, { chain: 'ropsten' });

        // 트랜잭션 서명
        tx.sign(this.privateKey);
        if (tx.verifySignature()) {
          console.log('서명 완료!');
          console.log(
            '서명에서 추적한 발신자 주소: ' +
              tx.getSenderAddress().toString('hex')
          );
        }

        const serializedTx = tx.serialize();
        const raw = '0x' + serializedTx.toString('hex');

        // 보내고 돌아오는거 로그에 찍고 좀 기다리면 롭슨으로 옴
        // 가스비가 작아서 그런가 좀 늦게 되는 감이있다
        web3.eth
          .sendSignedTransaction(raw)
          .once('transactionHash', (hash) => {
            console.info(
              'transactionHash',
              'https://ropsten.etherscan.io/tx/' + hash
            );
          })
          .once('receipt', (receipt) => {
            console.info('receipt', receipt);
            console.log('save transaction!');
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            month = month >= 10 ? month : '0' + month;
            var day = date.getDate();
            day = day >= 10 ? day : '0' + day;
            var hour = date.getHours();
            var minutes = date.getMinutes();
            minutes = minutes >= 10 ? minutes : '0' + minutes;
            var second = date.getSeconds();
            second = second >= 10 ? second : '0' + second;
            var today =
              month +
              '/' +
              day +
              '/' +
              year +
              ' ' +
              hour +
              ':' +
              minutes +
              ':' +
              second;

            console.log(userId);
            console.log(candidate_id);
            console.log(receipt.blockHash);
            console.log(receipt.blockNumber);
            console.log(this.voteValue);
            console.log(receipt.gasUsed);
            console.log(today);

            axios
              .post('http://j4b107.p.ssafy.io/election/dbc', {
                senderId: userId,
                receiverId: candidate_id,
                blockHash: receipt.blockHash,
                blockNumber: receipt.blockNumber,
                value: this.voteValue,
                gasUsed: receipt.cumulativeGasUsed,
                transactionFee: receipt.gasUsed,
                timeStamp: today,
                isDbcEth: 1,
              })
              .then((response) => {
                this.candidateInfo = response.data.data;
                console.log(this.candidateInfo);
              })
              .catch((error) => {
                console.log(error);
              });

            this.voteValue = 0;

            console.log('reward transaction start!');

            // axios
            //   .get('http://127.0.0.1:8080/test/web3')
            //   .then((response) => {
            //     console.log(response);
            //     console.log('reward transaction end!');
            //   })
            //   .catch((error) => {
            //     console.log(error);
            //   });
          })
          .on('error', console.error);
      });
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
.font {
  font-family: 'account_font';
  font-size: x-large;
}
@import url('https://fonts.googleapis.com/css?family=Open+Sans&display=swap');
.election_container {
  text-align: -webkit-center;
  width: 100vw;
  height: 100vh;
  /* border: #4a148c 5px solid; */
}
.cardwrap {
  display: flex;
  flex-flow: wrap;
  justify-content: center;
  width: 80vw;
  height: 100vh;
  /* border: #148c6e 5px solid; */
}
.candidate {
  /* border: #8c8a14 5px solid; */

  margin: 1%;
  transform: translate(0%, 0%);
  width: 30%;
  height: 50%;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  background: #0e0e0f;
  overflow: hidden;
}

.candidate .imgbox {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  background-image: url('./img/candidate_background.jpeg');
  background-size: 100%;
}

.candidate .imgbox img {
  display: block;
  width: 60%;
  height: 60%;
  margin: 2px auto 0;
}

.specifies {
  position: absolute;
  width: 100%;
  bottom: -130px;
  background: #fff;
  padding: 10px;
  box-sizing: border-box;
  transition: 0.5s;
}

.candidate:hover .specifies {
  bottom: 0;
}

.specifies h2 {
  margin: 0;
  padding: 0;
  font-size: 20px;
  width: 100%;
}

.specifies h2 span {
  font-size: 15px;
  color: #ccc;
  font-weight: normal;
}

.specifies .price {
  position: absolute;
  top: 12px;
  right: 25px;
  font-weight: bold;
  color: #000;
  font-size: 30px;
}

label {
  display: block;
  margin-top: 5px;
  font-weight: bold;
  font-size: 15px;
}

ul {
  display: flex;
  margin: 0;
  padding: 0;
}

ul li {
  list-style: none;
  margin: 5px 5px 0;
  font-size: 15px;
  font-style: normal;
  color: #ccc;
}

ul li:first-child {
  margin-left: 0;
}

ul.colors li {
  width: 15px;
  height: 15px;
}

ul.colors li:nth-child(1) {
  background: #4a148c;
}

ul.colors li:nth-child(2) {
  background: #f50057;
}

ul.colors li:nth-child(3) {
  background: #536dfe;
}

ul.colors li:nth-child(4) {
  background: #388e3c;
}

ul.colors li:nth-child(5) {
  background: #ff6d00;
}

.btn {
  display: block;
  padding: 5px;
  color: #fff;
  margin: 10px 0 0;
  width: 100%;
  font-size: 13px;
  border-radius: 2px;
}
.button_container {
  align-items: center;
  background: #0c3267;
  border-radius: 40px;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  display: flex;
  height: 80px;
  justify-content: center;
  position: relative;
  width: 200px;
}
.text {
  color: #eeca99;
  position: absolute;
  transition: opacity 300ms;
  user-select: none;
  -moz-user-select: none;
}
.fingerprint {
  /* height: 80px; */
  left: -8px;
  opacity: 0;
  position: absolute;
  stroke: #eeca99;
  top: -9px;
  transition: opacity 1ms;
}
.fingerprint-active {
  stroke: #eeca99;
}
.fingerprint-out {
  opacity: 1;
}
.odd {
  stroke-dasharray: 0px 50px;
  stroke-dashoffset: 1px;
  transition: stroke-dasharray 1ms;
}
.even {
  stroke-dasharray: 50px 50px;
  stroke-dashoffset: -41px;
  transition: stroke-dashoffset 1ms;
}
.ok {
  opacity: 0;
}
.active.button_container {
  animation: 6s Container;
}
.active .text {
  opacity: 0;
  animation: 6s Text forwards;
}
.active .fingerprint {
  opacity: 1;
  transition: opacity 300ms 200ms;
}
.active .fingerprint-base .odd {
  stroke-dasharray: 50px 50px;
  transition: stroke-dasharray 800ms 100ms;
}
.active .fingerprint-base .even {
  stroke-dashoffset: 0px;
  transition: stroke-dashoffset 800ms;
}
.active .fingerprint-active .odd {
  stroke-dasharray: 50px 50px;
  transition: stroke-dasharray 2000ms 1500ms;
}
.active .fingerprint-active .even {
  stroke-dashoffset: 0px;
  transition: stroke-dashoffset 2000ms 1300ms;
}
.active .fingerprint-out {
  opacity: 0;
  transition: opacity 300ms 4100ms;
}
.active .ok {
  opacity: 1;
  animation: 6s Ok forwards;
}
@keyframes Container {
  0% {
    width: 200px;
  }
  6% {
    width: 80px;
  }
  71% {
    transform: scale(1);
  }
  75% {
    transform: scale(1.2);
  }
  77% {
    transform: scale(1);
  }

  94% {
    width: 80px;
  }
  100% {
    width: 200px;
  }
}
@keyframes Text {
  0% {
    opacity: 1;
    transform: scale(1);
  }
  6% {
    opacity: 0;
    transform: scale(0.5);
  }

  94% {
    opacity: 0;
    transform: scale(0.5);
  }
  100% {
    opacity: 1;
    transform: scale(1);
  }
}
@keyframes Ok {
  0% {
    opacity: 0;
  }
  70% {
    opacity: 0;
    transform: scale(0);
  }
  75% {
    opacity: 1;
    transform: scale(1.1);
  }
  77% {
    opacity: 1;
    transform: scale(1);
  }
  92% {
    opacity: 1;
    transform: scale(1);
  }
  96% {
    opacity: 0;
    transform: scale(0.5);
  }
  100% {
    opacity: 0;
  }
}
</style>
