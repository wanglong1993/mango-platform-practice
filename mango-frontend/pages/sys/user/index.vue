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
          @on-load="onLoad"
          :permission="permission"
          :data="tableData"
          :option="option"
          @refresh-change="rowRefresh"
          @row-update="rowUpdate"
          @search-change="searchChange"
          @row-save="rowSave"
          :search.sync="search"
        >
          <template slot="searchMenu" slot-scope="{row,size}">
            <el-button :size="size" @click="searchSubmit(row)">自定义提交</el-button>
          </template>
          <!-- <template slot="search" slot-scope="{row,size}">
            <el-input placeholder="自定义输入框" :size="size" style="width:200px" v-model="search.slot"></el-input>
          </template>-->
          <!-- TODO 有问题 -->
          <template slot-scope="scope" slot="sysDeptForm">
            <el-cascader
              v-if="scope.row.sysDept"
              v-model="scope.row.sysDept.deptList"
              :props="casProps"
              :options="treeData"
              clearable
            ></el-cascader>
            <el-cascader
              v-else
              v-model="scope.row.deptTree"
              :props="casProps"
              :options="treeData"
              clearable
            ></el-cascader>
          </template>

          <template slot="orderNum" slot-scope="scope">
            <el-input v-model="scope.row.orderNum" placeholder="请输入内容">{{scope.row.orderNum}}</el-input>
          </template>

          <template slot="statusForm" slot-scope="scope">
            <el-radio-group v-model="scope.row.status">
              <el-radio :label="0">禁用</el-radio>
              <el-radio :label="1">正常</el-radio>
            </el-radio-group>
          </template>

          <template
            slot="sysDept"
            slot-scope="scope"
          >{{scope.row.sysDept?scope.row.sysDept.name:''}}</template>
          <template slot-scope="scope" slot="menu">
            <Crudbutton
              icon="el-icon-check"
              size="mini"
              type="text"
              :data="form"
              :option="crudOption"
              v-if="!(scope.row.name==='admin')"
              @click="initData(scope.row)"
              @submit="submit"
              :title="'授权角色'"
            >
              <!-- 具名插槽 -->
              <template v-slot:extendField>
                <avue-crud
                  :table-loading="roleLoading"
                  ref="roleList"
                  @selection-change="handleSelectionChange"
                  :permission="roleTablePermission"
                  :data="roleData"
                  :option="roleOption"
                ></avue-crud>
              </template>
            </Crudbutton>
          </template>
        </avue-crud>
      </el-main>

      <template></template>
    </el-container>
  </div>
</template>
<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class sysUser extends Vue {
  // vue挂载
  http = Vue.prototype.$http

  //########### 请求数据
  roleData: any = []
  search: any = {}
  tableData: any = []
  treeData: any = []
  //########### 弹出窗口角色表格的选项
  roleOption = {
    border: true,
    selection: true,
    selectionFixed: false,
    title: '角色分配',
    size: 'mini',
    align: 'center',
    selectable: (row: any, index: any) => {
      return index !== 0
    },
    menuAlign: 'center',
    column: [
      // { label: '上级菜单', prop: 'parentMe' },
      {
        label: '角色名称',
        prop: 'remark',
      },
      {
        label: '角色编码',
        prop: 'name',
      },
    ],
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
  //########### 表格按钮权限

  async checkAuth() {
    this.permission.delBtn = await this.$store.dispatch(
      'checkAuth',
      'sys:user:edit'
    )
    this.permission.addBtn = await this.$store.dispatch(
      'checkAuth',
      'sys:user:add'
    )
    this.permission.menu = await this.$store.dispatch(
      'checkAuth',
      'sys:user:edit'
    )
  }
  // 弹窗默认没有任何按钮
  roleTablePermission = {
    delBtn: false,
    addBtn: false,
    menu: false,
  }

  permission = {
    delBtn: '',
    addBtn: '',
    menu: '',
  }
  //########### 展示表格选项
  option = {
    excelBtn: true,
    dialogClickModal: false,
    height: '450',
    // sortable: true,
    // selection: true,
    menuWidth: 210,
    index: true,
    size: 'mini',
    dialogDrag: true,
    searchShow: false,
    column: [
      {
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
          },
        ],
        label: '登陆账号',
        prop: 'name',
        searchSpan: 8,
        search: true,
        searchRules: [
          {
            required: true,
            message: '请输入账号名称',
            trigger: 'blur',
          },
        ],
      },
      {
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
          },
        ],
        value: '123456',
        label: '登陆密码',
        overHidden: true,
        editDisplay: false,
        prop: 'password',
      },
      {
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
          },
        ],
        label: '用户昵称',
        prop: 'nickName',
      },
      {
        label: '手机号',
        overHidden: true,
        prop: 'mobile',
        searchSpan: 8,
        search: true,
        searchRules: [
          {
            required: true,
            message: '请输入手机号',
            trigger: 'blur',
          },
        ],
      },
      {
        // rules: [
        //   {
        //     required: true,
        //     message: '',
        //     trigger: 'blur',
        //   },
        // ],
        formslot: true,
        labelslot: true,
        // errorslot: true,
        label: '归属机构',
        prop: 'sysDept',
        slot: true,
      },
      {
        label: '邮箱',
        overHidden: true,
        prop: 'email',
      },
      {
        label: '创建时间',
        prop: 'createTime',
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
        label: '账号状态',
        prop: 'status',
        formslot: true,
        labelslot: true,
        value: 1,
        addDisplay: false,
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
  id = ''
  roleLoading = true
  multipleSelection: any = []
  form: any = {}
  // 分页属性
  page: any = {
    total: 0,
    pageCount: 5,
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
  async init() {
    const { data } = await this.http.get('/pri/role/findAll', {
      prefix: 'admin',
    })
    this.roleData = data.data

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
    console.log(form.deptTree)
    if (form.deptTree) {
      form.deptId = form.deptTree[form.deptTree.length - 1]
      form.deptTree = form.deptTree.toString()
    }

    const res = await this.http.post('pri/user/save', form, { prefix: 'admin' })
  }

  async rowUpdate(form: any, index: any, done: any, loading: any) {
    setTimeout(() => {
      done(form)
      this.onLoad()
    }, 500)
    if (form.sysDept.deptList !== null) {
      form.deptTree = form.sysDept.deptList.toString()
      form.deptId = form.sysDept.deptList[form.sysDept.deptList.length - 1]
    }

    const res = await this.http.post('pri/user/save', form, { prefix: 'admin' })
  }

  toggleSelection(val?: any) {
    const ref: any = this.$refs.roleList
    ref.toggleSelection()
    setTimeout(() => {
      if (val) {
        ref.toggleSelection(val)
        this.roleLoading = false
      }
    }, 500)
  }

  handleSelectionChange(val: any) {
    this.multipleSelection = val
    // console.log(val)
  }
  //################### 封装的弹窗按钮的弹出数据初始化
  async initData(obj: any) {
    this.roleLoading = true
    this.form = obj

    const { data } = await this.http.get('pri/user/findUserRoles/' + obj.id, {
      prefix: 'admin',
    })
    let array: any = []
    data.data.forEach((el: any) => {
      let ind = this.roleData.findIndex((e: any) => e.name === el.name)
      if (ind !== -1) {
        array.push(this.roleData[ind])
      }
    })
    // 初始选中按钮
    this.toggleSelection(array)
  }

  //################### 封装的弹窗按钮的属性

  rowRefresh() {
    this.onLoad()
  }
  // 表格加载
  async onLoad() {
    this.loading = true

    let data: any = []
    if (this.id) {
      data = await this.findDeptUser(this.id)
    } else {
      data = await this.findUser()
    }

    this.page.total = data.data.totalSize

    setTimeout(() => {
      this.loading = false
      this.tableData = data.data.content
    }, 200)
  }

  async findUser() {
    const { data } = await this.http.post(
      'pri/user/findPage',
      {
        pageNum: this.page.currentPage,
        pageSize: this.page.pageSize,
        params: {},
      },
      { prefix: 'admin' }
    )

    return data
  }

  async findDeptUser(id: any) {
    const { data } = await this.http.post(
      'pri/user/findDeptUser/' + id,
      {
        pageNum: this.page.currentPage,
        pageSize: this.page.pageSize,
        params: {},
      },
      { prefix: 'admin' }
    )
    return data
  }
  // 角色授权表单提交
  async submit() {
    const data: any = []
    this.multipleSelection.forEach((e: any) => {
      data.push({
        roleId: e.id,
        userId: this.form.id,
      })
    })
    const res = await this.http.post('/pri/user/saveRole', data, {
      prefix: 'admin',
    })
    if (res.data.code === 200) {
      this.$notify({
        title: '',
        message: '保存成功',
        position: 'bottom-right',
        type: 'success',
      })
    }
  }

  searchSubmit(row: any) {}

  searchChange(params: any, done: any) {
    done()
    this.$message.success(JSON.stringify(params))
  }

  // 节点点击
  nodeClick(data: any) {
    this.id = data.id

    this.onLoad()
  }
}
</script>
<style lang="scss"></style>
