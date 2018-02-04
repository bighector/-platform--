<template>
  <div class="index">
      <div class="index-slider">
           <swiper :list="swiperList"></swiper>
      </div>
  </div>
</template>

<script>
import { Swiper } from "vux";
export default {
  data() {
    return {
      swiperList: [
        {
          url: "javascript:",
          img: "https://static.vux.li/demo/1.jpg",
          title: "送你一朵fua"
        },
        {
          url: "javascript:",
          img: "https://static.vux.li/demo/2.jpg",
          title: "送你一辆车"
        },
        {
          url: "javascript:",
          img: "https://static.vux.li/demo/3.jpg",
          title: "送你一次旅行"
        }
      ]
    };
  },
  components: {
    Swiper
  }
};
</script>
