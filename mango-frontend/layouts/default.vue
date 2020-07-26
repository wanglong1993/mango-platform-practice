<template>
  <el-container>
    <el-menu
      background-color="#20222A"
      text-color="hsla(0,0%,100%,.7)"
      active-text-color="#ffd04b"
      style="overflow-y: scroll"
      class="el-menu-vertical-demo"
      :collapse="isCollapse"
      :unique-opened="true"
    >
      <el-menu-item class="fs-4 text-white">
        <i class="el-icon-menu text-white"></i>
        <span slot="title">Mango-admin</span>
      </el-menu-item>
      <el-submenu v-for="(menu, index) in menus" :key="index" :index="`${index}`">
        <template slot="title">
          <i :class="menu.icon"></i>
          <span slot="title">{{ menu.name }}</span>
        </template>

        <template v-for="(submenu, index1) in menu.children">
          <el-menu-item
            v-if="submenu.type != 2"
            :key="`${index}-${index1}`"
            :index="`${submenu.url}`"
            @click="$router.push({path:submenu.url,query:{label: submenu.name }})"
          >
            <i :class="submenu.icon"></i>
            <span slot="title">{{ submenu.name }}</span>
          </el-menu-item>
        </template>
      </el-submenu>
    </el-menu>

    <el-container class="h-100">
      <el-header class="d-flex jc-between ai-center fs-8">
        <div>
          <el-button
            @click="isCollapse = !isCollapse"
            :icon="isCollapse ? `el-icon-s-unfold` : 'el-icon-s-fold'"
            class="fs-8 pl-3"
            type="text"
          ></el-button>
        </div>
        <div class="d-flex ai-center pr-4">
          <el-color-picker class="pr-2" v-model="color" size="mini"></el-color-picker>
          <div>{{this.$store.state.Auth.name}}</div>
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link">
              <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container class="d-flex flex-column bg-light">
        <div class="bg-white mb-2" style="z-index:1;">
          <Menutags></Menutags>
        </div>
        <el-main class="h-100">
          <Nuxt :keep-alive="true" class />
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>
<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'

@Component({
  components: {},
})
export default class MenuLayOut extends Vue {
  menus: any = []
  http = Vue.prototype.$http
  isCollapse = false
  color = ''
  mounted() {
    setTimeout(() => {
      this.fetchMenu()
    }, 300)
  }

  get closingPage() {
    return this.$store.state.closingPage
  }

  handleOpen(key: any, keyPath: any) {
    console.log(key, keyPath)
  }

  async fetchMenu() {
    const http = Vue.prototype.$http
    const res = await http.get('/pri/menu/findNavTree', {
      prefix: 'menu',
    })

    this.menus = res.data.data
  }

  handleCommand(command: any) {
    switch (command) {
      case 'logout':
        this.logOut()
        break

      default:
        break
    }
  }

  logOut() {
    this.$auth.logout()

    this.$router.push('sys/login')
  }
}
</script>
<style>
#__layout {
  height: 100vh;
}
.el-header,
.el-footer {
  padding: 0 !important;
  height: 50px !important;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 240px !important;
  min-height: 400px;
}

.el-aside {
  text-align: left;

  /* background-color: #545c64; */
}

.el-main {
  padding: 0 !important;
}

.el-container {
  height: 100% !important;
}
</style>
