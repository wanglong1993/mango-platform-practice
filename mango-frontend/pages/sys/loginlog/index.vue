<template>
  <div class="bg-white h-100">
    <el-container class="pt-3 px-3">
      <el-main>
        <div class="px-3 py-2">
          <el-breadcrumb class="py-2" separator-class="el-icon-arrow-right">
            <el-breadcrumb-item>在线用户</el-breadcrumb-item>
          </el-breadcrumb>

          <avue-crud
            :page.sync="page"
            :table-loading="loading"
            @refresh-change="rowRefresh"
            @on-load="onLoad"
            :permission="permission"
            :data="tableData"
            :option="option"
          >
            <template slot-scope="{ type, size }" slot="menu">
              <el-button icon="el-icon-scissors" :size="size" :type="type"
                >踢出用户</el-button
              >
            </template>
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
    delBtn: false,
    addBtn: false,
    menu: true,
    editBtn: false,
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
        label: 'ip地址',

        prop: 'ip',
      },
      {
        label: '用户名',

        prop: 'userName',
      },
      {
        label: '上次登录时间',
        prop: 'lastUpdateTime',
      },
      {
        label: '状态',
        prop: 'status',
      },
    ],
  }

  mounted() {}

  rowRefresh() {
    this.onLoad()
  }

  async onLoad() {
    this.loading = true
    const { data } = await this.http.post(
      'pub/online',
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
