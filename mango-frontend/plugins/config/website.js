/**
 * 全局配置文件
 */

const BASE_URL =
  process.env.NODE_ENV !== 'production'
    ? 'http://localhost:'
    : 'http://106.54.247.224:'
export default {
  RTMQ_SERVICE: 'ws://106.54.247.224:15674/ws',
  MONITOR_SERVICE: BASE_URL + '8090',
  DRUID_SERVICE: BASE_URL + '8083/druid/index.html',
  RTBOARD_SERVICE: BASE_URL + '15672',
  SWAGGER_SERVICE: BASE_URL + '9001/swagger-ui.html',
  SENTINEL_SERVICE: BASE_URL + '8849',
  NACOS_SERVICE: BASE_URL + '8848/nacos',
  API_URL: BASE_URL + '9001/*/api/sys/v1/',
  LOGIN_URL: BASE_URL + '9001/admin/api/sys/v1/pub/login',
  LOGOUT_URL: BASE_URL + '9001/admin/api/sys/v1/pub/logout',
  CAPTCHA_SERVICE: BASE_URL + '9001/admin/api/sys/v1/pub/captcha.jpg',
  SOUND_FILE_PLAY_URL:
    BASE_URL + '9001/sound/api/sys/v1/pri/sysSoundfile/play/',
  title: '',
  logo: 'A',
  key: 'state', //配置主键,目前用于存储
  indexTitle: '后台快速开发模版',
  lockPage: '/lock',
  tokenTime: 6000, //token过期时间
  Authorization: 'Authorization',
  //http的status默认放行不才用统一处理的,
  statusWhiteList: [400],
  //配置首页不可关闭
  isFirstPage: false,
  fistPage: {
    label: '首页',
    value: '/',
    params: {},
    query: {},
    meta: {
      i18n: 'dashboard',
    },
    group: [],
    close: false,
  },
  //配置菜单的属性
  menu: {
    iconDefault: 'icon-caidan',
    props: {
      label: 'label',
      path: 'path',
      icon: 'icon',
      children: 'children',
    },
  },
}
