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


