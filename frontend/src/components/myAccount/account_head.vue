<template>
  <div>
    <h1 class="rgb-text">My account</h1>
  </div>
</template>

<script>
export default {
  mounted() {
    let rgbText, nodes, hues;

    rgbText = document.querySelector('.rgb-text');

    rgbText.innerHTML = [].slice
      .call(rgbText.innerHTML)
      .map((c) => `<span>${c}</span>`)
      .join('');

    nodes = document.querySelectorAll('.rgb-text span');
    hues = [];

    nodes.forEach((c, i) => {
      hues.push(Math.round(i * (360 / nodes.length)));
    });

    (function loop() {
      hues.forEach((h, i, _this) => {
        _this[i]--;
        nodes[i].style.color = `hsl(${_this[i]},100%,50%)`;
      });
      window.requestAnimationFrame(loop);
    })();
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Libre+Barcode+39+Text');

.rgb-text {
  position: fixed;
  top: 5%;
  left: 50%;
  font-family: 'Libre Barcode 39 Text';
  font-size: 3em;
  text-align: center;
  color: white;
  transform: translateX(-50%) translateY(-50%);
}
</style>
