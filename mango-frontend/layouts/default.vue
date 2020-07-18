<template>
  <el-container>
    <el-header class="d-flex jc-between ai-center fs-8 text-white">
      <div>
        Mango-Admin
        <el-button
          @click="isCollapse = !isCollapse"
          :icon="isCollapse ? `el-icon-s-unfold` : 'el-icon-s-fold'"
          class="text-white fs-6 pl-2"
          type="text"
        ></el-button>
      </div>
      <div class="d-flex ai-center">
        <i class="el-icon-thumb px-2"></i>
        <i class="el-icon-share px-2"></i>
        <i class="el-icon-delete px-2"></i>

        <el-color-picker></el-color-picker>

        <el-avatar
          size="small"
          src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
        ></el-avatar>
        <span class="fs-3 px-2">超级管理员</span>
        <el-button type="primary" @click="logOut">退出登陆</el-button>
      </div>
    </el-header>
    <el-container class="d-flex">
      <el-menu
        style="overflow-y: scroll;"
        class="el-menu-vertical-demo mr-2"
        :collapse="isCollapse"
        router
      >
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
            >
              <i :class="submenu.icon"></i>
              <span slot="title">{{ submenu.name }}</span>
            </el-menu-item>
          </template>
        </el-submenu>
      </el-menu>

      <!-- <el-header>
          <WorkTab></WorkTab>
      </el-header>-->
      <el-main>
        <Nuxt />
      </el-main>
    </el-container>
  </el-container>
</template>
<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'
import WorkTab from '~/components/worktab/WorkTab.vue'
@Component({
  components: { WorkTab },
})
export default class MenuLayOut extends Vue {
  menus: any = []
  isCollapse = false

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
  background-color: #1890ff;

  height: 50px !important;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.el-aside {
  text-align: center;

  /* background-color: #545c64; */
}

.el-main {
  padding: 0 !important;
}

.el-container {
  height: 100% !important;
}
</style>
