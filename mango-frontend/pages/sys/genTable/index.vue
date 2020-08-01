<template>
  <div class="bg-white h-100">
    <el-container class="pt-3 px-3">
      <el-main>
        <div class="px-3 py-2">
          <el-breadcrumb class="py-2" separator-class="el-icon-arrow-right">
            <el-breadcrumb-item>代码生成器</el-breadcrumb-item>
          </el-breadcrumb>

          <avue-crud
            :page.sync="page"
            :table-loading="loading"
            @on-load="onLoad"
            :permission="permission"
            :data="tableData"
            :option="option"
          >
            <template slot-scope="{type,size,row}" slot="menu">
              <Crudbutton
                icon="el-icon-check"
                :size="size"
                :type="type"
                :data="form"
                :option="crudOption"
                @click="initData(row)"
                @submit="submit(row)"
                :title="'生成代码'"
              ></Crudbutton>
            </template>
          </avue-crud>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script lang='ts'>
import { Vue, Component } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class genTable extends Vue {
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
    background: false,
  }

  permission = {
    delBtn: false,
    addBtn: false,
    menu: true,
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
        label: '表名',

        prop: 'tablename',
      },
      {
        label: '描述',

        prop: 'tablecomment',
      },
      {
        label: '引擎',
        prop: 'engine',
      },
      {
        label: '创建时间',
        prop: 'createtime',
      },
    ],
  }

  crudOption = {
    column: [
      {
        label: '表名',
        disabled: true,
        prop: 'tablename',
      },
      {
        label: '作者',

        prop: 'author',
      },
      {
        label: '描述',
        prop: 'comments',
      },
      {
        label: '模块名',
        prop: 'moduleName',
      },
      {
        label: '包名',
        prop: 'packageName',
      },
      {
        label: '表前缀',
        prop: 'tablePrefix',
      },
    ],
  }

  initData(row: any) {
    this.form = row
  }

  async submit(row: any) {
    console.log(row)
    const data = { tableName: row.tablename }
    const res = await this.http.post('pri/codeGen/generate', data, {
      prefix: 'admin',
      responseType: 'arraybuffer',
    })

    let blob: any = new Blob([res.data], { type: 'application/zip' })
    let filename = row.tablename + '.zip'
    let link = document.createElement('a')
    link.href = URL.createObjectURL(blob)
    link.download = filename
    document.body.appendChild(link)
    link.click()
    window.setTimeout(function () {
      URL.revokeObjectURL(blob)
      document.body.removeChild(link)
    }, 0)
  }

  async onLoad() {
    this.loading = true

    const { data } = await this.http.post(
      'pri/codeGen/findPage',
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
<style lang='scss' scoped>
</style>