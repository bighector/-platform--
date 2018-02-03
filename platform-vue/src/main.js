// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import {router} from './router'
import YDUI from 'vue-ydui';
import 'vue-ydui/dist/ydui.px.css';
import 'normalize.css';
import components from './components';
import plugin from './plugin';

Vue.config.productionTip = false
Vue.use(YDUI);
Vue.use(components);
Vue.use(plugin);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
