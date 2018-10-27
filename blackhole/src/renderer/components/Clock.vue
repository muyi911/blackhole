<template>
    <div>
        <div class="clock">123</div>
    </div>
</template>


<script type="module">
import FlipClock from "flipclock/dist/flipclock";
import "flipclock/dist/flipclock.css";

export default {
  data: () => ({
    options: {
      clockFace: "TwentyFourHourClock"
    }
  }),
  components: {},
  created: function() {},
  mounted: function() {
    var clock = new FlipClock($(".clock")[0], {
      clockFace: "TwentyFourHourClock"
    });
  }
};
</script>
