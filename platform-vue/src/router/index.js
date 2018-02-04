import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

let routes = [
  {
    name: '/',
    path: '/',
    redirect: '/index',
    meta: {
      requiresAuth: false,
      title: '重定向',
    }
  },
  {
    name: 'index',
    path: '/index',
    meta: {
      requiresAuth: false,
      title: '首页',
    },
    component: (resolve) => {
      require(['../view/index/index'], resolve)
    }
  },
  {
    name: 'activity',
    path: '/activity',
    meta: {
      requiresAuth: false,
      title: '活动',
    },
    component: (resolve) => {
      require(['../view/activity/index'], resolve)
    }
  },
  {
    name: 'category',
    path: '/category',
    meta: {
      requiresAuth: false,
      title: '分类',
    },
    component: (resolve) => {
      require(['../view/category/index'], resolve)
    }
  },
  {
    name: 'cart',
    path: '/cart',
    meta: {
      requiresAuth: false,
      title: '购物车',
    },
    component: (resolve) => {
      require(['../view/cart/index'], resolve)
    }
  },
  {
    name: 'center',
    path: '/center',
    meta: {
      requiresAuth: false,
      title: '个人中心',
    },
    component: (resolve) => {
      require(['../view/center/index'], resolve)
    }
  }
]

export const router = new VueRouter({
  mode: 'history',
  routes
})