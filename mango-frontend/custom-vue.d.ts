import Vue from '~/custom-vue'
import { AxiosInstance } from 'axios'
import { Route } from 'vue-router'
import { Store } from 'vuex/types'
declare module 'vue/types/vue' {
  interface Vue {
    $http: AxiosInstance
    $route: Route
    $store: Store<any>
    $auth: any
  }
}
