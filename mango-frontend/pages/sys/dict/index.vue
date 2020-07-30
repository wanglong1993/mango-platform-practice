<template>
  <div class="bg-white h-100">
    <el-container class="pt-3 px-3">
      <el-main>
        <div class="px-3 py-2">
          <el-breadcrumb class="py-2" separator-class="el-icon-arrow-right">
            <el-breadcrumb-item>字典管理</el-breadcrumb-item>
          </el-breadcrumb>

          <avue-crud
            :page.sync="page"
            :table-loading="loading"
            @refresh-change="rowRefresh"
            @on-load="onLoad"
            :permission="permission"
            :data="tableData"
            :option="option"
          ></avue-crud>
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
export default class sysRole extends Vue {
  http = Vue.prototype.$http
  loading = true
  tableData: any = []
  page: any = {
    total: 10,
    pagerCount: 5,
    currentPage: 1,
    pageSize: 10,
    pageSizes: [5, 10, 20],
    layout: 'total, sizes,prev, pager, next, jumper',
    background: false,
  }
  permission = {
    delBtn: '',
    addBtn: '',
    menu: '',
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
        label: '标签',

        prop: 'label',
      },
      {
        label: '描述',

        prop: 'description',
      },
      {
        label: '类型',
        prop: 'type',
      },
      {
        label: '值',
        prop: 'value',
      },
    ],
  }

  mounted() {
    this.checkAuth()
  }

  async checkAuth() {
    this.permission.delBtn = await this.$store.dispatch(
      'checkAuth',
      'sys:dict:edit'
    )
    this.permission.addBtn = await this.$store.dispatch(
      'checkAuth',
      'sys:dict:add'
    )
    this.permission.menu = await this.$store.dispatch(
      'checkAuth',
      'sys:dict:edit'
    )
  }

  rowRefresh() {
    this.onLoad()
  }

  async onLoad() {
    this.loading = true
    const { data } = await this.http.post(
      'pri/dict/findPage',
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
    console.log(data)
  }
}
</script>
<style lang="scss"></style>
