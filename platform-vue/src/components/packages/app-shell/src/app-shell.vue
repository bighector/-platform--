<template>
  <div class="app-shell">
      <header>
          <app-shell-header></app-shell-header>
      </header>
      <section class="app-shell-with-header app-shell-with-footer">
          <router-view></router-view>
      </section>
      <footer>
          <app-shell-footer></app-shell-footer>
      </footer>
  </div>
</template>

<script>
import appShellHeader from './app-shell-header.vue'
import appShellFooter from './app-shell-footer.vue'
export default {
  name:'app-shell',
  components:{
      appShellHeader,
      appShellFooter
  }
}
</script>

<style lang="less" scoped>
@import '../../../../assets/css/var.less';
.app-shell{
    position: absolute;
    top:0px;
    right:0px;
    bottom:0px;
    left:0px;
    display: flex;
    flex-direction: column;
    header{
        position: absolute;
        top:0px;
        left:0px;
        right:0px;
        z-index:9;
    }
    section{
        position: relative;
        flex:1;
        overflow-x: hidden;
        overflow-y: auto;
        background: @background-color;
    }
    .app-shell-with-header{
        padding-top:46px;
    }
    .app-shell-with-footer{
        padding-bottom:53px;
    }
    footer{
        position:absolute;
        bottom:0px;
        left:0px;
        right:0px;
        z-index:9
    }
}
</style>
