import createPersistedState from 'vuex-persistedstate'
import router from 'vue-router'
export const state = () => ({
  token: '',
  closingPage: '',
  worktab: {
    list: [
      {
        name: 'home',
        tabname: '仪表盘',
        path: '/',
      },
    ],
    current: {
      name: 'home',
      tabname: '仪表盘',
      path: '/',
    },
  },
})

export const mutations = {
  storeToken(state, data) {
    state.token = data
  },
  deleteToken(state) {
    state.token = ''
  },
  worktabRoute(state, p) {
    let ind = state.worktab.list.findIndex((s) => s.path === p.path)
    if (ind > -1) {
      // 标签已存在
      state.worktab.current = state.worktab.list[ind]
    } else {
      // 标签不存在，现在新建
      state.worktab.list.push(p)
      state.worktab.current = p
    }
    state.closingPage = ''
  },
  worktabRemove(state, p) {
    // 关闭标签
    let ind = state.worktab.list.findIndex((s) => s.path === p)
    console.log(ind)
    if (ind > -1) {
      // 清理 keep alive - start
      state.closingPage = state.worktab.list[ind].path
      // 清理 keep alive - end
      state.worktab.list.splice(ind, 1)
    }
    if (p === state.worktab.current.path) {
      // 是当前页，返回上一页
      // window.location.href = 'javascript:history.go(-1)'
    }
  },
}

export const getters = {}

export const actions = {
  worktabRemove({ commit }, p) {
    commit('worktabRemove', p)
  },
  worktabRoute({ commit }, p) {
    commit('worktabRoute', p)
  },
}

const vuexSession = new createPersistedState({
  key: 'state',
  storage: window.sessionStorage,
})

export const plugins = [vuexSession]
