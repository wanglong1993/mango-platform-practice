/**
 * 全局配置文件
 */
export default {
  RTMQ_SERVICE: 'ws://106.54.247.224:15674/ws',
  MONITOR_SERVICE: 'http://localhost:8090',
  DRUID_SERVICE: 'http://localhost:8083/druid/index.html',
  RTBOARD_SERVICE: 'http://106.54.247.224:15672',
  SWAGGER_SERVICE: 'http://127.0.0.1:9001/swagger-ui.html',
  SENTINEL_SERVICE: 'http://127.0.0.1:8849',
  NACOS_SERVICE: 'http://106.54.247.224:8848/nacos',
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
