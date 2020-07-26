import dotenv from 'dotenv'
dotenv.config()
export default {
  loadingIndicator: {
    name: 'circle',
    color: '#3B8070',
    background: 'white',
  },

  /*
   ** Nuxt rendering mode
   ** See https://nuxtjs.org/api/configuration-mode
   */
  mode: 'spa',
  /*
   ** Nuxt target
   ** See https://nuxtjs.org/api/configuration-target
   */
  target: 'server',
  /*
   ** Headers of the page
   ** See https://nuxtjs.org/api/configuration-head
   */
  head: {
    title: process.env.npm_package_name || '',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      {
        hid: 'description',
        name: 'description',
        content: process.env.npm_package_description || '',
      },
    ],
    script: [
      {
        src: 'https://cdn.staticfile.org/Sortable/1.10.0-rc2/Sortable.min.js',
      },
      // {
      //   src: 'https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js',
      // },
      // {
      //   src: 'https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.14.1/xlsx.full.min.js',
      // },
    ],
    link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }],
  },
  /*
   ** Global CSS
   */
  css: [
    'element-ui/lib/theme-chalk/index.css',
    'element-ui/lib/theme-chalk/display.css',
    './css/global.scss',
  ],
  /*
   ** Plugins to load before mounting the App
   ** https://nuxtjs.org/guide/plugins
   */

  router: {
    middleware: ['authenticated', 'worktab'],
  },
  plugins: ['@/plugins/axios', '@/plugins/element-ui', '@/plugins/directive'],
  /*
   ** Auto import components
   ** See https://nuxtjs.org/api/configuration-components
   */
  components: true,
  /*
   ** Nuxt.js dev-modules
   */
  buildModules: ['@nuxt/typescript-build'],
  /*
   ** Nuxt.js modules
   */
  modules: [
    // Doc: https://axios.nuxtjs.org/usage
    '@nuxtjs/axios',
    '@nuxtjs/pwa',
    '@nuxtjs/auth',
    // Doc: https://github.com/nuxt/content
    '@nuxt/content',
  ],
  /*
   ** Axios module configuration
   ** See https://axios.nuxtjs.org/options
   */
  axios: {
    prefix: process.env.baseUrl,
    credentials: true,
  },
  /*
   ** Content module configuration
   ** See https://content.nuxtjs.org/configuration
   */
  auth: {
    strategies: {
      local: {
        endpoints: {
          token: {
            // property: 'data.data.token',
            // required: true,
            // type: 'Bearer'
          },
          login: {
            url: 'http://localhost:9001/admin/api/sys/v1/pub/login',
            method: 'post',
            propertyName: 'token',
          },
          logout: {
            url: 'http://localhost:9001/admin/api/sys/v1/pub/logout',
            method: 'get',
            propertyName: 'token',
          },
          user: false,
        },
        // tokenRequired: true,
        // tokenType: 'bearer'
      },
    },
  },

  content: {},
  /*
   ** Build configuration
   ** See https://nuxtjs.org/api/configuration-build/
   */
  build: {
    transpile: [/^element-ui/],
  },
  env: {
    baseUrl: process.env.API_URL,
  },
}
