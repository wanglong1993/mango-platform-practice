<template>
  <div class="bg-white h-100">
    <el-container class="pt-3 px-3">
      <el-main>
        <div class="px-3 py-2">
          <el-breadcrumb class="py-2" separator-class="el-icon-arrow-right">
            <el-breadcrumb-item>邮件通知</el-breadcrumb-item>
          </el-breadcrumb>

          <el-form :model="mail" ref="mail" label-width="130px">
            <el-form-item label="发送邮箱地址">
              <el-input v-model="mail.to"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">立即发送</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'

@Component({
  components: {},
})
export default class sysMail extends Vue {
  http = Vue.prototype.$http

  mail = { to: '943452349@qq.com' }

  onSubmit() {
    this.http.post('/pub/mail/send', this.mail, { prefix: 'admin' })
  }
}
</script>
<style lang="scss"></style>
