import createPersistedState from 'vuex-persistedstate'

export const state = () => ({
  token: '',
  UserNotExist: true,
})

export const mutations = {
  storeToken(state, data) {
    state.token = data
    state.UserNotExist = false
  },
  deleteToken(state) {
    state.token = ''
    state.UserNotExist = true
  },
}

export const getters = {}

export const actions = {}

const vuexSession = new createPersistedState({
  key: 'state',
  storage: window.sessionStorage,
})

export const plugins = [vuexSession]
