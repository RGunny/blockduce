<template>
  <div>
    <link
      href="https://fonts.googleapis.com/css?family=Montserrat&display=swap"
      rel="stylesheet"
    />
    <div class="user_Info">
      <b-img
        v-bind:src="myInfo.profile_img"
        rounded="circle"
        alt="Circle image"
        width="80"
        height="80,"
      ></b-img>
      <div>
        <h4>{{ myInfo.name }}</h4>
      </div>
      <div>
        <h4>{{ myInfo.nick }}</h4>
      </div>
      <div v-show="myInfo.account === null">
        <b-button v-b-toggle.collapse-1 variant="outline-info"
          >지갑 생성</b-button
        >
        <b-collapse id="collapse-1" class="mt-2">
          <p class="card-text">
            경고! 지갑 비밀키를 잃어버리지 마세요! 한번 잃어버리면 복구 할 수
            없습니다.
          </p>
          <p class="card-text">
            공유하지 마세요! 비밀키가 악위적인 사이트에 노출되면 당신의 자산이
            유실될 수 있습니다.
          </p>
          <p class="card-text">
            백업을 만들어 두세요! 종이에 적어서 오프라인으로 관리하세요.
          </p>
          <b-button variant="warning" v-on:click="GetNewAccount">확인</b-button>
        </b-collapse>
      </div>
      <div v-show="myInfo.account != null">
        <div>
          <h4 v-show="show_account === true">
            My Account : {{ myInfo.account }}
          </h4>
          <button
            v-show="show_account === false"
            @click="show_account = true"
            class="corner-button"
          >
            <span> Account verification</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
let web3;
import * as Web3 from 'web3';
import axios from 'axios';
import DBCabi from './DBCabi.json';

export default {
  data() {
    return {
      apiKey: 'CKWK2KN8MGRQD6DQ4I39G5NYGPTQFKWGNM',
      balance: 0,
      show_account: false,
      myInfo: {
        name: '',
        nick: '',
        profile_img: '',
        account: '',
        balance: '0.00 ETH',
        ERCbalance: '0.00 DBC',
      },
      send_account: '',
      receive_account: '',
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
      .get('http://127.0.0.1:8080/test/user/1')
      .then((response) => {
        this.myInfo.name = response.data.data.name;
        this.myInfo.nick = response.data.data.nick;
        this.myInfo.profile_img = response.data.data.profile_img;
        this.myInfo.account = response.data.data.account;
      })
      .catch((e) => {
        console.log('error: ', e);
      });
  },
  methods: {
    GetBalance: function() {
      let address, getbalance;
      address = this.myInfo.account;
      web3.eth.getBalance(address, (error, wei) => {
        if (!error) {
          getbalance = web3.utils.fromWei(wei, 'ether');
          this.myInfo.balance = getbalance + ' ETH';
        }
      });
    },
    GetERCBalance: function() {
      var account = this.myInfo.account;
      var contractAddress = '0x9864bb32e02b1fae9eb875f7b169c5400b15efec';
      var TokenABI = DBCabi;
      var DBCcontract = new web3.eth.Contract(TokenABI, contractAddress);
      console.log(DBCcontract);
      let DBCtoken;
      DBCcontract.methods
        .balanceOf(account)
        .call()
        .then((result) => {
          DBCtoken = result / 100000;
          this.myInfo.ERCbalance = DBCtoken + ' DBC';
        });
      DBCcontract.methods
        .totalSupply()
        .call()
        .then((result) => {
          result = result / 100000;
          console.log(result);
        });
      //   var DBCtoken = new web3.eth.Contract(TokenABI, contractAddress);
      //   if (DBCtoken != null) {
      //     console.log(DBCtoken);
      //     DBCtoken.balanceOf(account, function(error, result) {
      //       if (!error) {
      //         console.log(result);
      //       } else {
      //         console.log('balance 호출 실패!', error);
      //       }
      //     });
      //   }
    },
    GetNewAccount: function() {
      let result = web3.eth.accounts.create('newAccount');
      this.myInfo.account = result.address;
    },
    getERCContract: function() {
      console.log('DBC contract start!.....');
      // 토큰 어드레스
      var contractAddress = '0x9864bb32e02b1fae9eb875f7b169c5400b15efec';
      // 토큰 abi
      var TokenABI = DBCabi;
      // 컨트랙트 만들기
      var DBCcontract = new web3.eth.Contract(TokenABI, contractAddress, {
        from: this.receive_account,
      });
      // 0.0001인가
      var amount = web3.utils.toHex(1);
      // ethereumjs-tx npm install 하셔야 됩니다. 끝에 .Transaction 까지 해주셔서 TX 만들어 주시고
      var Tx = require('ethereumjs-tx').Transaction;
      // 이더리움 컨트랙트도 만들어 주세요
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

        // tx 만들어 주실때 chain : ropsten 해주시고
        var tx = new Tx(rawTx, { chain: 'ropsten' });

        // 트랜잭션 서명 해주시고
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

        // 보내주시고 돌아오는거 로그에 찍고 좀 기다리면 롭슨으로 바로 확인가능합니다.!
        // 가스비가 작아서 그런가 좀 늦게 되는 감이있네요
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
          })
          .on('error', console.error);
      });
    },
    getContract: function() {
      console.log('ETH contract start!.....');
      var Tx = require('ethereumjs-tx').Transaction;

      //   var contractAddress = '0x9864bb32e02b1fae9eb875f7b169c5400b15efec';
      //   var nonce = web3.eth.getTransactionCount(this.receive_account);
      //   var gasPrice = web3.eth.gasPrice;
      //   var value = '0x2386f26fc10000';
      //   var gasLimit = web3.eth.estimateGas({
      //     to: this.send_account,
      //     from: this.receive_account,
      //     value: value,
      //   });
      // 트렌잭션 데이터 생성
      web3.eth.getTransactionCount(this.send_account, (err, txCount) => {
        const rawTx = {
          nonce: web3.utils.toHex(txCount),
          value: web3.utils.toHex(web3.utils.toWei('0.01', 'ether')),
          to: this.receive_account,
          gasLimit: web3.utils.toHex(21000),
          gasPrice: web3.utils.toHex(web3.utils.toWei('10', 'gwei')),
          //   data: web3.transfer.getData(contractAddress, 10, {
          //     from: this.send_account,
          //   }),
          //   data: web3.eth.methods.transfer(contractAddress, amount).encodeABI(),
        };
        console.log(rawTx.nonce);
        console.log(rawTx.value);
        console.log(rawTx.gasLimit);
        console.log(rawTx.gasPrice);
        console.log(rawTx.to);

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
          })
          .on('error', console.error);
      });
    },
  },
};
</script>

<style scoped>
.corner-button {
  font-family: 'Lato', sans-serif;
  letter-spacing: 0.2rem;
  cursor: pointer;
  background: transparent;
  border: 0.2rem solid currentColor;
  padding: 0.6rem 0.1rem;
  font-size: 1rem;
  color: #0c3267;
  position: relative;
  transition: color 0.3s;
}
.corner-button:hover {
  color: #eeca99;
}
.corner-button:hover::before {
  width: 0;
}
.corner-button:hover::after {
  height: 0;
}
.corner-button:active {
  border-width: 0.25rem;
}
.corner-button span {
  position: relative;
  z-index: 2;
}
.corner-button::before,
.corner-button::after {
  content: '';
  position: absolute;
  background: #f5f7fa;
  z-index: 1;
  transition: all 1s;
}
.corner-button::before {
  width: calc(100% - 2rem);
  height: calc(101% + 1rem);
  top: -0.5rem;
  left: 50%;
  transform: translateX(-50%);
}
.corner-button::after {
  height: calc(100% - 2rem);
  width: calc(101% + 1rem);
  left: -0.5rem;
  top: 50%;
  transform: translateY(-50%);
}
</style>
