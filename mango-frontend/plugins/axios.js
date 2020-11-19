import Vue from 'vue'
import configs from './config/website.js'
export default ({ app, store, route, redirect }) => {
  const axios = app.$axios

  // 基本配置
  axios.defaults.timeout = 10000
  axios.defaults.headers.post['Content-Type'] = 'application/json'
  axios.defaults.withCredentials = true
  axios.defaults.baseURL = configs.API_URL

  // 请求回调
  axios.onRequest((config) => {
    console.log(config)
    const newUrl = config.baseURL.replace('*', config.prefix)
    config.baseURL = newUrl
    //设置token
    if (store.state.Auth && store.state.Auth.token) {
      config.headers.Authorization = store.state.Auth.token || ''
    }
  })

  // code返回回调
  axios.onResponse((res) => {
    //   if (res.headers.refreshtoken) {
    //     Cookie.set('token', res.headers.refreshtoken)
    //   }
    if (res.data.code == 403) {
      redirect('/sys/login')
      app.$auth.logout()
    }
  })

  // 内部错误回调
  axios.onError((error) => {
    console.log(error.response)
    switch (error.response.status) {
      case 403:
        Vue.prototype.$message({
          message: '未授权',
          type: 'info',
          showClose: true,
        })
        store.state.auth.loggedIn = false
        redirect('/sys/login')
      case 500:
        redirect('/sys/500')
        break
      case 404:
        redirect('/sys/404')
        break
    }
  })
  Vue.prototype.$http = axios
}
