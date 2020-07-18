import Vue from 'vue'
import Element from 'element-ui'
import locale from 'element-ui/lib/locale/lang/en'
import Avue from '@smallwei/avue'
import '@smallwei/avue/lib/index.css'

import Router from 'vue-router'

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location)
}
Vue.use(Element, { locale })
Vue.use(Avue)
