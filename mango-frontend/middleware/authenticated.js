import Vue from 'vue'
export default function ({ route, store, redirect }) {
  // If the user is not authenticated
  // true true
  if (route.path != '/sys/login' && store.state.UserNotExist) {
    return redirect('/sys/login')
  }
}
