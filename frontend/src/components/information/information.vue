<template>
  <div>
    <header>
      <div class="text-center">
        <!-- Logo -->
        <div class="logo">
          <h1><b>Blockduce Award 2021 by SSAFY</b></h1>
        </div>

        <h1>Vote for your one pick now!</h1>
      </div>
    </header>

    <div class="col-md-4">
      <h4 class="text-center"><strong>황민현</strong></h4>
      <hr />
      <div class="profile-card-6">
        <img
          src="https://cdn.sporbiz.co.kr/news/photo/201904/333476_248785_1050.png"
          class="img img-responsive"
        />
        <div class="profile-name">JOHN <br />DOE</div>
        <div class="profile-position">Lorem Ipsum Donor</div>
        <div class="profile-overview">
          <div class="profile-overview">
            <div class="row text-center">
              <div class="col-xs-4">
                <h3>1 |</h3>
                <p>Rank |</p>
              </div>
              <div class="col-xs-4">
                <h3>159 |</h3>
                <p>votes |</p>
              </div>
              <div class="col-xs-4">
                <h3>1,590,000 |</h3>
                <p>DBC</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
header {
  position: relative;
  left: 0;
  top: 0;
  width: 100%;
  min-height: 120px;
  padding: 50px 0;
  color: #fff;
  background: #383838
    url(https://www.athenadesignstudio.com/plugins/switch/images/bg.jpg)
    no-repeat center center;
  margin-bottom: 30px;
}

/* Logo */
header .logo {
  clear: both;
  display: block;
  text-align: center;
  padding-bottom: 10px;
}

/* Title */
header h1 {
  font-weight: 300;
  font-size: 24px;
  color: #eee;
  letter-spacing: 2px;
  text-align: center;
  text-transform: uppercase;
  margin: 0 !important;
  padding-bottom: 25px;
}
a {
  font-family: 'Open Sans';
  margin: 0px;
}
.container-fluid,
.container {
  max-width: 1200px;
}

.card-container {
  padding: 100px 0px;
  -webkit-perspective: 1000;
  perspective: 1000;
}
a:hover,
a:focus {
  color: inherit;
}

body {
  background-color: #f1f2f3;
}
.profile-card-6 {
  max-width: 600px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0px 0px 25px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  position: relative;
  margin: 10px auto;
  cursor: pointer;
}

.profile-card-6 img {
  transition: all 0.15s linear;
}

.profile-card-6 .profile-name {
  position: absolute;
  top: 10px;
  left: 10px;
  font-size: 25px;
  font-weight: bold;
  color: #fff;
  padding: 15px 20px;
  background: linear-gradient(
    140deg,
    rgba(0, 0, 0, 0.4) 50%,
    rgba(255, 255, 0, 0) 50%
  );
  transition: all 0.15s linear;
}

.profile-card-6 .profile-position {
  position: absolute;
  color: rgba(255, 255, 255, 0.4);
  left: 30px;
  top: 100px;
  transition: all 0.15s linear;
}

.profile-card-6 .profile-overview {
  position: absolute;
  bottom: 0px;
  left: 0px;
  right: 0px;
  background: linear-gradient(
    0deg,
    rgba(0, 0, 0, 0.4) 50%,
    rgba(255, 255, 0, 0)
  );
  color: #fff;
  padding: 50px 0px 20px 0px;
  transition: all 0.15s linear;
}

.profile-card-6 .profile-overview h3 {
  font-weight: bold;
}

.profile-card-6 .profile-overview p {
  color: rgba(255, 255, 255, 0.7);
}

.profile-card-6:hover img {
  filter: brightness(80%);
}

.profile-card-6:hover .profile-name {
  padding-left: 25px;
  padding-top: 20px;
}

.profile-card-6:hover .profile-position {
  left: 40px;
}

.profile-card-6:hover .profile-overview {
  padding-bottom: 25px;
}
</style>
