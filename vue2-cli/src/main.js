// 导入vue这个包，得到vue构造函数
import Vue from 'vue'
// 导入 app.vue 根租金啊
import App from './App.vue'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
