import Vue from 'vue'
Vue.directive('loaded-callback', {
  inserted: function (el, binding, vnode) {
    binding.value(el, binding, vnode)
  },
})
