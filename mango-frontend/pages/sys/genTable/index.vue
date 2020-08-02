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
            @refresh-change="rowRefresh"
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
  form: any = {}
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
        autocomplete: true,
        prop: 'tablename',
      },
      {
        label: '作者',
        autocomplete: true,
        prop: 'author',
      },
      {
        label: '描述',
        autocomplete: true,
        prop: 'comments',
      },
      {
        label: '模块名',
        autocomplete: true,
        prop: 'moduleName',
      },
      {
        label: '包名',
        autocomplete: true,
        prop: 'packageName',
      },
      {
        label: '表前缀',
        autocomplete: true,
        prop: 'tablePrefix',
      },
      {
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
          },
        ],
        label: '生成路径',
        autocomplete: true,
        prop: 'path',
        col: 24,
      },
    ],
  }

  initData(row: any) {
    this.form = row
  }

  async submit() {
    const data = { tableName: this.form.tablename, path: this.form.path }
    const res = await this.http.post('pri/codeGen/generate', data, {
      prefix: 'admin',
      responseType: 'arraybuffer',
    })

    let blob: any = new Blob([res.data], { type: 'application/zip' })
    let filename = this.form.tablename + '.zip'
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

  rowRefresh() {
    this.onLoad()
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
    }, 100)
  }
}
</script>
<style lang='scss' scoped>
</style>