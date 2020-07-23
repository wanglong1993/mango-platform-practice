import Vue from 'vue'

export default ({ app, store, route, redirect }) => {
  const axios = app.$axios

  // 基本配置
  axios.defaults.timeout = 10000
  axios.defaults.headers.post['Content-Type'] = 'application/json'
  axios.defaults.withCredentials = true

  // 请求回调
  axios.onRequest((config) => {
    const newUrl = config.baseURL.replace('*', config.prefix)
    config.baseURL = newUrl
    //设置token
    if (store.state.token) {
      config.headers.Authorization = store.state.token || ''
    }
  })

  // 返回回调
  axios.onResponse((res) => {
    //   if (res.headers.refreshtoken) {
    //     Cookie.set('token', res.headers.refreshtoken)
    //   }
  })

  // 错误回调
  axios.onError((error) => {
    console.log(error.response)
    switch (error.response.status) {
      case 403:
        Vue.prototype.$message({
          message: '未授权',
          type: 'info',
          showClose: true,
        })
        redirect('/sys/login')
      case 500:
        Vue.prototype.$message({
          message: '未授权',
          type: 'info',
          showClose: true,
        })
        redirect('/sys/login')
        break
    }
  })
  Vue.prototype.$http = axios
}