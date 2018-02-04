// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import App from './App'
import 'normalize.css';
import components from './components';
import plugin from './plugin';
import {router} from './router'
import './assets/icon/index.css';
import './assets/css/reset.css';

Vue.use(components);
Vue.use(plugin);

FastClick.attach(document.body)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app-box')
