import Vue from 'vue'
import Element from 'element-ui'

import '@smallwei/avue/lib/index.css'

// Vue.use(Element, {
//   i18n: (key, value) => app.i18n.t(key, value),
// })

import Router from 'vue-router'

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location)
}
Vue.use(Element)

// Vue.use(Avue)
