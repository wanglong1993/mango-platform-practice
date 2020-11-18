<template>
  <div class="bg-white h-100">
    <el-container class="pt-3 px-3">
      <el-main>
        <div class="px-3 py-2">
          <el-breadcrumb class="py-2" separator-class="el-icon-arrow-right">
            <el-breadcrumb-item></el-breadcrumb-item>
          </el-breadcrumb>

          <avue-crud
            :page.sync="page"
            :table-loading="loading"
            @on-load="onLoad"
            @row-update="rowUpdate"
            @refresh-change="rowRefresh"
            @row-save="rowSave"
            @row-del="rowDel"
            :permission="permission"
            :data="tableData"
            :option="option"
          >
          </avue-crud>
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
export default class index extends Vue {
  loading = true
  tableData = []
  form = {}
  http = Vue.prototype.$http
  page: any = {
    total: 10,
    pagerCount: 5,
    currentPage: 1,
    pageSize: 10,
    pageSizes: [5, 10, 20],
    layout: 'total, sizes,prev, pager, next, jumper',
    background: true,
  }

  // async checkAuth() {
  //     this.permission.delBtn = await this.$store.dispatch(
  //       'checkAuth',
  //        'sys::edit'
  //      )
  //     this.permission.addBtn = await this.$store.dispatch(
  //         'checkAuth',
  //         'sys:',${pathName},':add'
  //     )
  //             this.permission.menu = await this.$store.dispatch(
  //         'checkAuth',
  //         'sys:',${pathName},':edit'
  //     )
  // }

  permission = {
    delBtn: true,
    addBtn: true,
    menu: true,
  }

  mounted() {
    // this.checkAuth()
  }

  option = {
    height: '450',
    // sortable: true,
    // selection: true,
    menuWidth: 210,
    index: true,
    size: 'mini',
    dialogDrag: true,
    column: [
      {
        label: '授权令牌有效时间',
        prop: 'accessTokenValiditySeconds',
      },

      {
        label: '客户端名称',
        prop: 'clientId',
      },
      //   {
      //     label: '密钥',
      //     prop: 'clientSecret',
      //   },
      {
        label: '授权类型',
        prop: 'grantTypes',
      },
      {
        label: '回调地址',
        prop: 'redirectUrl',
      },
      {
        label: '刷新令牌有效时间',
        prop: 'refreshTokenValiditySeconds',
      },
      {
        label: '资源服务器名称',
        prop: 'resourceIds',
      },
      {
        label: '授权域',
        prop: 'scopes',
      },
      {
        label: '授权信息',
        prop: 'authorizations',
      },
      {
        label: '自动授权请求域',
        prop: 'autoApproveScopes',
      },
    ],
  }

  async rowSave(form: any, done: any, loading: any) {
    const res = await this.http.post('pri/sysClientDetails/save', form, {
      prefix: 'admin',
    })
    done(form)
  }

  async rowUpdate(form: any, index: any, done: any, loading: any) {
    const res = await this.http.put('pri/sysClientDetails/' + form.id, form, {
      prefix: 'admin',
    })

    done(form)
    this.onLoad()
  }

  async rowDel(form: any, index: any) {
    this.mixConfirm(async () => {
      await this.http.delete('pri/sysClientDetails/' + form.id, {
        prefix: 'admin',
      })
    })
  }

  async onLoad() {
    this.loading = true

    const { data } = await this.http.post(
      'pri/sysClientDetails/findPage',
      {
        pageNum: this.page.currentPage,
        pageSize: this.page.pageSize,
        params: {},
      },
      { prefix: 'admin' }
    )
    this.page.total = data.data.totalSize

    setTimeout(() => {
      this.loading = false
      this.tableData = data.data.content
    }, 500)
  }

  rowRefresh() {
    this.onLoad()
  }
}
</script>
<style lang="scss" scoped></style>
