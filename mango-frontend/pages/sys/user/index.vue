<template>
  <div class="pt-3">
    <el-container>
      <el-aside width="200px">
        <avue-tree ref="tree" :option="treeOption" :data="treeData" @node-click="nodeClick"></avue-tree>
      </el-aside>
      <el-main>
        <avue-crud
          :table-loading="loading"
          :page.sync="page"
          @on-load="onLoad"
          :data="tableData"
          :option="option"
          @row-update="rowUpdate"
          @row-save="rowSave"
        >
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

          <!-- <template slot-scope="{}" slot="sysDeptLabel">
            <span>机构名称&nbsp;&nbsp;</span>
            <el-tooltip class="item" effect="dark" content="文字提示" placement="top-start">
              <i class="el-icon-warning"></i>
            </el-tooltip>
          </template>-->
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
                  :permission="permission"
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
  //########### 弹出窗口表格按钮
  permission = {
    delBtn: false,
    addBtn: false,
    menu: false,
  }
  //########### 展示表格选项
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
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
          },
        ],
        label: '登陆账号',
        prop: 'name',
      },
      {
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
          },
        ],
        value: 123456,
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
  }

  //########### data
  loading = true
  roleLoading = true
  multipleSelection: any = []
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
  fetchDeptUser = false

  //########### 生命周期初始化
  mounted() {
    this.init()
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

    if (form.deptTree !== null) {
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

  // 表格加载
  async onLoad(id?: any) {
    this.loading = true

    let data: any = []
    if (this.fetchDeptUser) {
      data = await this.findDeptUser(id)
    } else {
      data = await this.findUser()
    }

    this.page.total = data.data.totalSize

    this.tableData = data.data.content
    setTimeout(() => {
      this.loading = false
    }, 500)
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
  submit() {
    const data: any = []
    this.multipleSelection.forEach((e: any) => {
      data.push({
        roleId: e.id,
        userId: this.form.id,
      })
    })
    this.http.post('/pri/user/saveRole', data, { prefix: 'admin' })
    console.log(data)
  }

  // 节点点击
  nodeClick(data: any) {
    console.log(data)
    this.fetchDeptUser = true
    this.onLoad(data.id)
  }
}
</script>
<style lang="scss"></style>
