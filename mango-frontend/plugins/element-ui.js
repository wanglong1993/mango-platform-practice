import Vue from 'vue'
import Element from 'element-ui'
import AvueRouter from './config/avue-route'
import Avue from '@smallwei/avue'
import '@smallwei/avue/lib/index.css'
import i18n from './lang/index.js'

Vue.use(Element, {
  i18n: (key, value) => {
    let d = i18n.t(key, value)
    if (d !== null && d !== undefined) {
      return d
    }
    return ''
  },
})
import Router from 'vue-router'

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location)
}

// AvueRouter.install(Vue, {
//   router: Router,
//   i18n: i18n,
//   keepAlive: false,
// })

Vue.use(Avue)
