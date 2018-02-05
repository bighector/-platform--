<template>
  <div class="app-shell-header">
      <x-header :left-options="{showBack: false}">{{this.$route.meta.title}}</x-header>
  </div>
</template>

<script>
import {XHeader} from 'vux'
export default {
  components:{
    XHeader
  }
}
</script>

<style lang="less">
@import '../../../../assets/css/var.less';
.app-shell-header{
  .vux-header{
    background: white;
    .vux-header-title{
        color:@title-color;
    }
  }
}
</style>


