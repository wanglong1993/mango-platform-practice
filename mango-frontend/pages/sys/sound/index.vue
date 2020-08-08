<template>
  <div class="bg-white h-100">
    <el-container class="pt-1 px-3">
      <el-main>
        <div class="px-2 py-3">
          <avue-crud
            ref="crud"
            :uploadBefore="uploadBefore"
            :page.sync="page"
            :table-loading="loading"
            @on-load="onLoad"
            @refresh-change="rowRefresh"
            @row-update="rowUpdate"
            @row-save="rowSave"
            @row-del="rowDel"
            :data="tableData"
            :option="option"
          >
            <template slot-scope="scope" slot="locationForm">
              <avue-input-map placeholder="请选择地图" v-model="scope.row.location"></avue-input-map>
            </template>

            <!-- <template slot="cover" slot-scope="scope">
              <el-image :src="scope.row.cover" :preview-src-list="[scope.row.cover]"></el-image>
            </template>-->

            <!-- <template slot="coverForm">
              <el-upload
                action="http://localhost:9001"
                list-type="picture-card"
                multiple
                :limit="5"
              >
                <i class="el-icon-plus"></i>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
            </template>-->
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
export default class index extends Vue {
  loading = true
  tableData = []
  //   form = {}
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
    delBtn: true,
    addBtn: true,
    menu: true,
  }

  option = {
    viewBtn: true,
    height: '450',
    // sortable: true,
    // selection: true,
    menuWidth: 210,

    size: 'mini',
    dialogDrag: true,
    column: [
      {
        label: 'Id',
        prop: 'id',
        addDisplay: false,
      },
      {
        label: '文件名',
        prop: 'name',
        addDisplay: false,
      },
      {
        label: '扩展名',
        prop: 'ext',
        addDisplay: false,
      },
      {
        label: '大小',
        prop: 'size',
        addDisplay: false,
      },
      {
        label: '路径',
        prop: 'path',
        addDisplay: false,
      },
      {
        label: '类型',
        prop: 'classification',
      },
      {
        label: '描述',
        overHidden: true,
        prop: 'description',
      },
      {
        label: '原始链接',
        overHidden: true,
        prop: 'url',
        type: 'url',
        addDisplay: false,
      },
      {
        label: '状态',
        prop: 'statu',
        addDisplay: false,
      },
      {
        label: '地图位置',
        prop: 'location',
        type: 'array',
        dataType: 'number',
        formslot: true,
        labelslot: true,
      },
      {
        label: '封面图',
        prop: 'cover',
        type: 'upload',
        listType: 'picture-img',
        span: 24,
        propsHttp: {
          home: '',
        },
        tip: '只能上传jpg/png用户头像，且不超过500kb',
        action: '/imgupload',
      },
    ],
  }

  async rowSave(form: any, done: any, loading: any) {
    setTimeout(() => {
      done(form)
    }, 500)

    const res = await this.http.post('pri/sysSoundfile/save', form, {
      prefix: 'sound',
    })
  }

  async rowUpdate(form: any, index: any, done: any, loading: any) {
    setTimeout(() => {
      done(form)
      this.onLoad()
    }, 500)
    console.log(form)
    // const res = await this.http.put('pri/sysSoundfile/' + form.id, form, {
    //   prefix: 'sound',
    // })
  }

  // 暂时先不删除
  async rowDel(form: any, index: any) {
    await this.http.delete('pri/sysSoundfile/' + form.id, { prefix: 'sound' })
  }

  uploadBefore(file: any, done: any, loading: any, column: any) {
    //如果你想修改file文件,由于上传的file是只读文件，必须复制新的file才可以修改名字，完后赋值到done函数里,如果不修改的话直接写done()即可
    var newFile = new File([file], '1234', { type: file.type })
    console.log(newFile)
    done(newFile)
    this.$message.success('上传前的方法')
  }

  rowRefresh() {
    this.onLoad()
  }

  async onLoad() {
    this.loading = true

    const { data } = await this.http.post(
      'pri/sysSoundfile/findPage',
      {
        pageNum: this.page.currentPage,
        pageSize: this.page.pageSize,
        params: {},
      },
      { prefix: 'sound' }
    )
    data.data.content.filter((e: any) => {
      if (e.location != null) {
        const res = e.location.split(',')
        let t: any = []
        res.forEach((e: any) => {
          t.push(e)
        })
        e.location = t
      }
    })
    this.page.total = data.data.totalSize
    console.log(data.data.content)
    setTimeout(() => {
      this.loading = false
      this.tableData = data.data.content
    }, 500)
  }
}
</script>
<style lang='scss' scoped>
</style>