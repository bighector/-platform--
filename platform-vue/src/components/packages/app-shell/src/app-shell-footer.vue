<template>
  <div class="app-shell-footer">
        <yd-tabbar>
            <yd-tabbar-item title="首页" link="/index" :active="this.$route.path === '/index'">
                <yd-icon name="home" slot="icon" size="20px"></yd-icon>
            </yd-tabbar-item>
            <yd-tabbar-item title="活动" link="/activity" :active="this.$route.path === '/activity'">
                <yd-icon name="discount" slot="icon" size="20px"></yd-icon>
            </yd-tabbar-item>
            <yd-tabbar-item title="分类" link="category" :active="this.$route.path === '/category'">
                <yd-icon name="type" slot="icon" size="20px"></yd-icon>
            </yd-tabbar-item>
            <yd-tabbar-item title="购物车" link="/cart" :active="this.$route.path === '/cart'">
                <yd-icon name="shopcart" slot="icon" size="20px"></yd-icon>
            </yd-tabbar-item>
            <yd-tabbar-item title="个人中心" link="/center" :active="this.$route.path === '/center'">
                <yd-icon name="ucenter" slot="icon" size="20px"></yd-icon>
            </yd-tabbar-item>
        </yd-tabbar>
  </div>
</template>

<script>
export default {
  data(){
      return {
          active:true
      }
  }
}
</script>

