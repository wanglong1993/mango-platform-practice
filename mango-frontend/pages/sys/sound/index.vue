<template>
  <div class="bg-white h-100">
    <el-container class="pt-1 px-3">
      <el-main>
        <div class="px-2 py-3">
          <avue-crud
            ref="crud"
            :page.sync="page"
            :table-loading="loading"
            :cell-style="cellStyle"
            :permission="permission"
            @search-change="searchChange"
            @on-load="search==true?searchChange():onLoad()"
            @refresh-change="rowRefresh"
            @row-update="rowUpdate"
            @row-save="rowSave"
            @row-del="rowDel"
            :before-open="beforeOpen"
            :data="tableData"
            :option="option"
          >
            <template slot="menuLeft">
              <el-button type="primary" size="mini" @click="openMap">{{$t('mango.sound.openMap')}}</el-button>
            </template>

            <template slot-scope="scope" slot="audioForm">
              <el-upload
                :http-request="uploadFile"
                class="upload-demo"
                action="string"
                :on-remove="handleRemove"
                :on-change="handleChange"
                :on-success="handleSuccess"
                :limit="1"
                :before-remove="beforeRemove"
                :file-list="scope.row.fileList"
              >
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
            </template>

            <template slot-scope="scope" slot="uploadTimeForm">
              <el-date-picker v-model="scope.row.uploadTime" type="date" placeholder="选择日期"></el-date-picker>
            </template>

            <template slot-scope="scope" slot="descriptionForm">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                placeholder="请输入内容"
                v-model="scope.row.description"
              ></el-input>
            </template>

            <template slot-scope="scope" slot="delFlagForm">
              <el-radio-group v-model="scope.row.delFlag">
                <el-radio :label="'0'">保留</el-radio>
                <el-radio :label="'1'">删除</el-radio>
              </el-radio-group>
            </template>

            <template slot-scope="scope" slot="locationForm">
              <avue-input-map placeholder="请选择地图" v-model="scope.row.location"></avue-input-map>
            </template>

            <!-- <template slot="cover" slot-scope="scope">
              <el-image :src="scope.row.cover" :preview-src-list="[scope.row.cover]"></el-image>
            </template>-->
            <template slot="fileForm" slot-scope="scope">
              <vue-plyr>
                <audio>
                  <source :src="scope.row.ossUrl" type="audio/mp3" />
                </audio>
              </vue-plyr>
            </template>
          </avue-crud>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script lang='ts'>
import { Vue, Component } from 'nuxt-property-decorator'
import config from '~/plugins/config/website.js'
import store from '~/plugins/oss.js'
@Component({
  components: {},
})
export default class index extends Vue {
  config = config
  loading = true
  tableData = []
  fileList: any = {}
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

  // 权限控制
  async checkAuth() {
    // this.permission.delBtn = await this.$store.dispatch(
    //   'checkAuth',
    //   'sys:sound:edit'
    // )
    this.permission.addBtn = await this.$store.dispatch(
      'checkAuth',
      'sys:sound:add'
    )
    this.permission.menu = await this.$store.dispatch(
      'checkAuth',
      'sys:sound:edit'
    )
  }

  permission = {
    delBtn: false,
    addBtn: false,
    menu: false,
  }

  option = {
    viewBtn: true,
    height: '450',
    dialogClickModal: false,
    index: true,
    searchShow: false,
    // sortable: true,
    // selection: true,
    menuWidth: 210,

    size: 'mini',
    dialogDrag: true,
    column: [
      {
        label: '文件名',
        prop: 'name',
        search: true,
        searchRules: [
          {
            required: false,
            message: '请输入文件名称',
            trigger: 'blur',
          },
        ],
        addDisplay: false,
        editDisplay: false,
      },
      {
        label: '扩展名',
        prop: 'ext',
        addDisplay: false,
        editDisplay: false,
      },
      {
        label: '大小',
        prop: 'size',
        addDisplay: false,
        editDisplay: false,
      },
      {
        label: '上传时间',
        prop: 'uploadTime',
        addDisplay: false,
        formslot: true,
        labelslot: true,
      },
      {
        label: '路径',
        prop: 'path',
        addDisplay: false,
        editDisplay: false,
      },
      {
        label: '类型',

        prop: 'classification',
        search: true,
        searchRules: [
          {
            required: false,
            message: '请输入类型',
            trigger: 'blur',
          },
        ],
      },
      {
        label: '描述',

        overHidden: true,
        formslot: true,
        labelslot: true,
        span: 24,
        prop: 'description',
      },
      {
        label: '原始链接',
        overHidden: true,
        prop: 'url',
        // type: 'url',
        addDisplay: false,
      },
      {
        label: '下载状态',
        prop: 'statu',
        filter: true,
        dicData: [
          { label: '失败', value: 0 },
          { label: '成功', value: 1 },
        ],
        filterMethod: function (value: any, row: any, column: any) {
          return row.statu === value
        },
        viewDisplay: false,
        addDisplay: false,
        editDisplay: false,
      },
      {
        label: '地图位置',

        prop: 'location',
        type: 'array',
        span: 24,
        overHidden: true,
        dataType: 'number',
        formslot: true,
        labelslot: true,
      },

      {
        label: '封面图',
        prop: 'cover',

        type: 'upload',
        listType: 'picture-img',
        span: 12,
        propsHttp: {
          home: '',
        },

        tip: '只能上传jpg/png,不超过500kb',
        action: '/imgUpload',
      },
      {
        label: '音频上传',
        prop: 'audio',

        // type: 'upload',

        // span: 12,
        // propsHttp: {
        //   home: 'http://demo.cssmoban.com',
        // },
        // tip: '',
        // action: '/imgupload',
        labelslot: true,
        formslot: true,
        showColumn: false,
      },
      {
        label: '逻辑删除',
        prop: 'delFlag',
        labelslot: true,
        addDisplay: false,
        formslot: true,
        showColumn: false,
      },
      {
        label: '试听',
        prop: 'file',
        labelslot: true,
        formslot: true,
        showColumn: false,
      },
    ],
  }
  search = false
  params = {}
  async searchChange(params: any, done: any) {
    this.loading = true
    this.search = true
    if (params) {
      this.params = params

      this.page.currentPage = 1
    }
    const crud: any = this.$refs.crud
    crud.$refs.headerSearch.searchShow = false
    params ? params : (params = this.params)

    const { data } = await this.http.post(
      'pri/sysSoundfile/search',
      {
        pageRequest: {
          pageNum: this.page.currentPage,
          pageSize: this.page.pageSize,
          params: {},
        },
        sysSoundfile: {
          name: params.name,
          classification: params.classification,
        },
      },
      { prefix: 'sound' }
    )

    setTimeout(() => {
      done ? done() : ''
    }, 500)

    this.handleResult(data)
  }

  cellStyle({ row, column, rowIndex, columnIndex }: any) {
    if (columnIndex == 9) {
      if (row.statu === 0) {
        return {
          color: 'red',
          fontWeight: 'bold',
          fontSize: '20',
        }
      } else {
        return {
          color: 'green',
          fontWeight: 'bold',
          fontSize: '20',
        }
      }
    }
  }

  beforeOpen(done: Function, type: any) {
    done()
    setTimeout(() => {
      this.$message.success('查看控制台')
      console.log('text的ref对象')
      // console.log(this.$refs.crud.getPropRef(''))
    }, 0)
  }

  handleChange(file: any, fileList: any) {
    console.log(file, fileList)
  }

  async uploadFile(param: any) {
    this.fileList = {}
    // const params = new FormData()
    console.log(param)
    // params.append('Cover', param.file, param.file.name)
    const date = this.$dayjs().format('YYYY-MM-DD')

    await store
      .put('soundFile/' + date + '/' + param.file.name, param.file)
      .then((result: any) => {
        let pattern = /.*(?=\.)/
        let pattern1 = /(?=\.).*/
        this.fileList.name = param.file.name.match(pattern)[0]
        this.fileList.ext = param.file.name.match(pattern1)[0]
        this.fileList.ossUrl = result.url
        this.fileList.path = 'soundFile/' + date
        this.fileList.uploadTime = date
        var num: any = (param.file.size / 1024 / 1024).toFixed(2)

        this.fileList.size = num + 'mb'

        console.log(this.fileList)
      })

    this.$notify({
      title: '成功',
      type: 'success',
      message: '上传成功',
    })
  }

  handleSuccess(response: any, file: any) {
    console.log(response, file)
    // this.avatorUrl = URL.createObjectURL(file.raw)
  }

  // getObjectInfo(path: String) {
  //   const res = store.get(path)
  //   console.log(res)
  // }

  openMap() {
    this.$router.push({ path: '/sys/map', query: { label: '声音地图' } })
  }

  async rowSave(form: any, done: any, loading: any) {
    setTimeout(() => {
      done(form)
    }, 500)
    const newObj = Object.assign(form, this.fileList)

    console.log(newObj)
    // const res = await this.http.post('pri/sysSoundfile/save', form, {
    //   prefix: 'sound',
    // })
  }

  async rowUpdate(form: any, index: any, done: any, loading: any) {
    setTimeout(() => {
      done(form)
      this.onLoad()
    }, 500)

    const newObj = Object.assign(form, this.fileList)

    console.log(newObj)
    // const res = await this.http.put('pri/sysSoundfile/' + form.id, form, {
    //   prefix: 'sound',
    // })
  }

  // 暂时先不删除
  async rowDel(form: any, index: any) {
    // await this.http.delete('pri/sysSoundfile/' + form.id, { prefix: 'sound' })
  }

  // async uploadBefore(file: any, done: any, loading: any, column: any) {
  //   //如果你想修改file文件,由于上传的file是只读文件，必须复制新的file才可以修改名字，完后赋值到done函数里,如果不修改的话直接写done()即可

  //   const date = this.$dayjs().format('YYYY-MM-DD')

  //   await store
  //     .put('soundFile/' + date + '/' + file.name, file)
  //     .then((result) => {
  //       console.log(result)
  //     })
  //   // var newFile = new File([file], '1234', { type: file.type })
  //   // console.log(file, column)
  //   done(file)
  //   this.$message.success('上传前的方法')
  // }

  rowRefresh() {
    this.onLoad()
  }
  mounted() {
    this.checkAuth()
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
    this.handleResult(data)
  }

  handleResult(data: any) {
    data.data.content.filter((e: any) => {
      if (e.location != null) {
        const res = e.location.split(',')
        let t: any = []
        let fileList = []
        fileList.push({ name: e.name, url: e.ossUrl })
        res.forEach((e: any) => {
          t.push(e)
        })
        e.fileList = fileList
        e.location = t
      }
    })

    this.page.total = data.data.totalSize

    setTimeout(() => {
      this.loading = false
      this.tableData = data.data.content
    }, 500)
  }

  handleRemove(file: any, fileList: any) {
    this.fileList = {}
    console.log(file, fileList)
  }

  beforeRemove(file: any, fileList: any) {
    return this.$confirm(`确定移除 ${file.name}？`)
  }
}
</script>
<style lang='scss' scoped>
</style>