<template>
  <div class="card mt-50 mb-50">
    <div class="col b-flex">
      <span class="text-muted" id="orderno">Block #546924</span>
    </div>

    <div class="title mx-auto text">Thank you for your vote!</div>
    <div class="main">
      <span id="sub-title text">
        <p>
          <b>{{ propsDate }}</b>
        </p>
      </span>
      <div class="row row-main">
        <div class="col-3">
          <img class="img-fluid" src="https://i.imgur.com/qSnCFIS.png" />
        </div>
        <div class="col-6">
          <div class="row d-flex text">
            황호연
          </div>
          <div class="row d-flex text ">
            <p class="text-muted">SSAFY</p>
          </div>
        </div>
        <div class="col-3 d-flex justify-content-end text">
          <p><b>100DBC</b></p>
        </div>
      </div>
      <div class="row row-main">
        <div class="col-3">
          <img class="img-fluid" src="https://i.imgur.com/WuJwAJD.jpg" />
        </div>
        <div class="col-6">
          <div class="row d-flex text">
            류건희
          </div>
          <div class="row d-flex text">
            <p class="text-muted">SSAFY</p>
          </div>
        </div>
        <div class="col-3 d-flex justify-content-end text">
          <p><b>100DBC</b></p>
        </div>
      </div>
      <div class="row row-main">
        <div class="col-3 ">
          <img class="img-fluid" src="https://i.imgur.com/hOsIes2.png" />
        </div>
        <div class="col-6">
          <div class="row d-flex text">
            황영준
          </div>
          <div class="row d-flex text">
            <p class="text-muted">SSAFY</p>
          </div>
        </div>
        <div class="col-3 d-flex justify-content-end text">
          <p><b>100DBC</b></p>
        </div>
      </div>
      <hr />
      <div class="total">
        <div class="row">
          <div class="col text"><b> Total:</b></div>
          <div class="col d-flex justify-content-end text"><b>300DBC</b></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: ['propsDate'],
  data() {
    return {};
  },
  computed: {},
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
