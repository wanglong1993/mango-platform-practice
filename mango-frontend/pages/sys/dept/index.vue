<template>
  <div class="bg-white h-100">
    <el-container class="pt-3 px-3">
      <el-aside width="200px" class="pr-2" style="border-right:1px solid  #ececec;">
        <avue-tree ref="tree" :option="treeOption" :data="treeData" @node-click="nodeClick"></avue-tree>
      </el-aside>
      <el-main>
        <avue-crud
          :table-loading="loading"
          :page.sync="page"
          :permission="permission"
          @on-load="onLoad"
          :data="tableData"
          :option="option"
          @row-update="rowUpdate"
          @row-save="rowSave"
        >
          <template slot-scope="scope" slot="parentIdForm">
            <el-cascader
              v-model="scope.row.parentId"
              :props="casProps"
              :options="treeData"
              clearable
            ></el-cascader>
          </template>

          <template slot="orderNum" slot-scope="scope">
            <el-input v-model="scope.row.orderNum" placeholder="请输入内容">{{scope.row.orderNum}}</el-input>
          </template>
        </avue-crud>
      </el-main>
    </el-container>
  </div>
</template>
<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class sysDept extends Vue {
  // vue挂载
  http = Vue.prototype.$http

  //########### 请求数据

  tableData: any = []
  treeData: any = []
  // 权限控制
  permission = {
    delBtn: '',
    addBtn: '',
    menu: '',
  }

  //########### 弹出窗口选项
  crudOption = {
    column: [
      {
        label: '姓名',
        prop: 'name',
        placeholder: '请输入姓名',
        disabled: true,
      },
      {
        label: '昵称',
        prop: 'nickName',
        disabled: true,
      },
    ],
  }

  //########### 展示表格选项
  option = {
    height: '450',
    // sortable: true,
    // selection: true,
    menuWidth: 210,
    // index: true,
    size: 'mini',
    dialogDrag: true,
    column: [
      {
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
          },
        ],
        label: '机构名称',
        prop: 'name',
      },
      {
        label: '机构代码',
        prop: 'id',
        editDisplay: false,
        addDisplay: false,
      },

      {
        label: '上级机构',
        prop: 'parentId',
        formslot: true,
        labelslot: true,
      },

      {
        label: '创建时间',
        prop: 'createTime',
        editDisplay: false,
        overHidden: true,
        addDisplay: false,
      },
      {
        label: '创建人',
        prop: 'createBy',
        editDisplay: false,
        addDisplay: false,
      },
      {
        label: '排序',
        prop: 'orderNum',
        value: 80,
        slot: true,
      },
      {
        label: '机构状态',
        prop: 'delFlag',
        value: 1,
        addDisplay: false,
        editDisplay: false,
        // slot: true,
      },
    ],
  }
  //########### 机构树选项 右键属性
  treeOption = {
    defaultExpandAll: true,
    props: {
      label: 'name',
    },
    formOption: {
      labelWidth: 100,
      column: [
        {
          label: 'name',
          prop: 'label',
        },
      ],
    },
  }

  //########### 级联选择器属性
  casProps = {
    value: 'id',
    label: 'name',
    checkStrictly: true,
    expandTrigger: 'hover',
  }

  //########### data
  loading = true

  form: any = {}
  // 分页属性
  page: any = {
    total: 40,
    pagerCount: 5,
    currentPage: 1,
    pageSize: 10,
    pageSizes: [5, 10, 20],
    layout: 'total, sizes,prev, pager, next, jumper',
    background: false,
  }
  // 请求方法变更

  //########### 生命周期初始化
  mounted() {
    this.init()
    this.checkAuth()
  }

  async checkAuth() {
    this.permission.delBtn = await this.$store.dispatch(
      'checkAuth',
      'sys:dept:edit'
    )
    this.permission.addBtn = await this.$store.dispatch(
      'checkAuth',
      'sys:dept:add'
    )
    this.permission.menu = await this.$store.dispatch(
      'checkAuth',
      'sys:dept:edit'
    )
  }

  async init() {
    //
    const { data } = await this.http.get('/pri/role/findAll', {
      prefix: 'admin',
    })
    // this.roleData = data.data

    const { data: data1 } = await this.http.get('/pri/dept/findDeptTree', {
      prefix: 'admin',
    })
    this.treeData = data1.data
  }
  //########### 方法

  async rowSave(form: any, done: any, loading: any) {
    setTimeout(() => {
      done(form)
    }, 500)
  }

  async rowUpdate(form: any, index: any, done: any, loading: any) {
    setTimeout(() => {
      done(form)
      this.onLoad()
    }, 500)
  }

  //################### 封装的弹窗按钮的弹出数据初始化

  //################### 封装的弹窗按钮的属性

  // 表格加载
  async onLoad(id?: any) {
    this.loading = true

    //

    // 查找所有机构

    const { data: data } = await this.http.post(
      'pri/dept/findPage',
      {
        pageNum: this.page.currentPage,
        pageSize: this.page.pageSize,
        params: {},
      },
      { prefix: 'admin' }
    )

    this.page.total = data.totalSize

    setTimeout(() => {
      this.loading = false
      this.tableData = data.data.content
    }, 500)
  }

  // 节点点击
  nodeClick(data: any) {
    console.log(data)
    this.onLoad(data.id)
  }
}
</script>
<style lang="scss"></style>
