import createPersistedState from 'vuex-persistedstate'
import { diff } from '~/plugins/util/util.js'
import { setStore, getStore } from '~/plugins/util/store'
import website from '~/plugins/config/website'

const isFirstPage = website.isFirstPage
const tagWel = website.fistPage

const tagObj = {
  label: '', //标题名称
  value: '', //标题的路径
  params: '', //标题的路径参数
  query: '', //标题的参数
  meta: {}, //额外参数
  group: [], //分组
}
//处理首个标签
function setFistTag(list) {
  if (list.length == 1) {
    list[0].close = false
  } else {
    list.forEach((ele) => {
      if (ele.value === tagWel.value && isFirstPage === false) {
        ele.close = false
      } else {
        ele.close = true
      }
    })
  }
}

export const state = () => ({
  tagList: [],
  tag: tagObj,
  tagWel: tagWel,
  Auth: {},
  closingPage: '',
  primaryColor: '',
})

export const mutations = {
  ADD_TAG: (state, action) => {
    state.tag = action

    setStore({ name: 'tag', content: state.tag })
    if (state.tagList.some((ele) => diff(ele, action))) return
    state.tagList.push(action)
    setFistTag(state.tagList)
    setStore({ name: 'tagList', content: state.tagList })
  },
  DEL_TAG: (state, action) => {
    state.tagList = state.tagList.filter((item) => {
      return !diff(item, action)
    })
    setFistTag(state.tagList)
    setStore({ name: 'tagList', content: state.tagList })
  },
  DEL_ALL_TAG: (state) => {
    state.tagList = [state.tagWel]
    setStore({ name: 'tagList', content: state.tagList })
  },
  DEL_TAG_OTHER: (state) => {
    state.tagList = state.tagList.filter((item) => {
      if (item.value === state.tag.value) {
        return true
      } else if (
        !website.isFirstPage &&
        item.value === website.fistPage.value
      ) {
        return true
      }
    })
    setFistTag(state.tagList)
    setStore({ name: 'tagList', content: state.tagList })
  },
  SET_TAG_LIST(state, tagList) {
    state.tagList = tagList
    setStore({ name: 'tagList', content: state.tagList })
  },
  storeAuth(state, data) {
    state.Auth = data
  },
  deleteAuth(state) {
    state.Auth = ''
  },
  deleteAll(state) {
    state = ''
  },
}

export const getters = {}

export const actions = {
  async checkAuth({ state }, permission) {
    const statu = await state.Auth.authorities.findIndex(
      (e) => e.authority === permission
    )

    console.log(statu)
    return statu === -1 ? false : true
  },
}

const vuexSession = new createPersistedState({
  key: 'state',
  storage: window.sessionStorage,
})

export const plugins = [vuexSession]
