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
      level:0
    }
  },
  {
    name: 'index',
    path: '/index',
    meta: {
      requiresAuth: false,
      title: '首页',
      level:2
    },
    component: (resolve) => {
      require(['../view/index/index'], resolve)
    }
  },
  {
    name: 'special',
    path: '/special',
    meta: {
      requiresAuth: false,
      title: '专题',
      level:2
    },
    component: (resolve) => {
      require(['../view/special/index'], resolve)
    }
  },
  {
    name: 'category',
    path: '/category',
    meta: {
      requiresAuth: false,
      title: '分类',
      level:2
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
      level:2
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
      level:2
    },
    component: (resolve) => {
      require(['../view/center/index'], resolve)
    }
  },
  {
    name: 'search',
    path: '/search',
    meta: {
      requiresAuth: false,
      title: '搜索',
      level:0
    },
    component: (resolve) => {
      require(['../view/search'], resolve)
    }
  },
  {
    name: 'order-confirm',
    path: '/order/confirm',
    meta: {
      requiresAuth: false,
      title: '确认订单',
      level:1
    },
    component: (resolve) => {
      require(['../view/order/order-confirm'], resolve)
    }
  }
]

export const router = new VueRouter({
  mode: 'history',
  routes
})