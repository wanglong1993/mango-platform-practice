<template>
  <div class="pt-3">
    <el-container>
      <el-aside width="200px">
        <avue-tree :option="treeOption" :data="treeData" @node-click="nodeClick"></avue-tree>
      </el-aside>
      <el-main>
        <avue-crud
          :table-loading="loading"
          :page.sync="page"
          @on-load="onLoad"
          :data="tableData"
          :option="option"
        >
          <template slot="orderNum" slot-scope="scope">
            <el-input v-model="scope.row.orderNum" placeholder="请输入内容">{{scope.row.orderNum}}</el-input>
          </template>
          <template slot-scope="scope" slot="menu">
            <Crudbutton
              icon="el-icon-check"
              size="mini"
              type="text"
              :data="form"
              :option="crudOption"
              @initData="initData(scope.row)"
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
  // 用户角色列表需要请求获取
  roleData: any = []
  http = Vue.prototype.$http
  tableData: any = []
  loading = true
  roleLoading = true
  treeData = [
    {
      value: 0,
      label: '一级部门',
      children: [
        {
          value: 1,
          label: '一级部门1',
        },
      ],
    },
  ]

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

  mounted() {
    this.init()
  }

  async init() {
    const { data: data1 } = await this.http.get('/pri/role/findAll', {
      prefix: 'admin',
    })
    this.roleData = data1.data
  }

  toggleSelection(val?: any) {
    setTimeout(() => {
      if (val) {
        const ref: any = this.$refs.roleList

        ref.toggleSelection(val)
        this.roleLoading = false
      }
    }, 500)
  }
  multipleSelection = []

  handleSelectionChange(val: any) {
    this.multipleSelection = val
    console.log(val)
  }
  //################### 封装的弹窗按钮的属性
  form = {}

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
    this.toggleSelection(array)
  }
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

  //################### 封装的弹窗按钮的属性

  treeOption = {
    defaultExpandAll: true,
    formOption: {
      labelWidth: 100,
      column: [
        {
          label: '自定义项',
          prop: 'test',
        },
      ],
    },
  }

  props = {
    labelText: '标题',
    label: 'label',
    value: 'value',
    children: 'children',
  }

  permission = {
    delBtn: false,
    addBtn: false,
    menu: false,
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
        label: '登陆账号',
        prop: 'name',
      },
      {
        label: '用户昵称',
        prop: 'nickName',
      },
      {
        label: '手机号',
        prop: 'mobile',
      },
      {
        label: '邮箱',
        prop: 'email',
      },
      {
        label: '创建时间',
        prop: 'createTime',
        editDisplay: false,
      },
      {
        label: '排序',
        prop: 'orderNum',
        slot: true,
      },
      {
        label: '账号状态',
        prop: 'status',
      },
    ],
  }

  // 拖拽排序
  // sortableChange(oldindex: any, newindex: any, row: any, list: any) {
  //   const num = Math.abs(
  //     this.tableData[newindex].orderNum - this.tableData[oldindex].orderNum
  //   )
  //   if (oldindex > newindex) {
  //     this.tableData[newindex].orderNum += num + 1
  //   } else {
  //     this.tableData[newindex].orderNum -= num + 1
  //   }
  // }

  page: any = {
    total: 40,
    pagerCount: 5,
    currentPage: 1,
    pageSize: 10,
    pageSizes: [5, 10, 20],
    layout: 'total, sizes,prev, pager, next, jumper',
    background: false,
  }

  async onLoad() {
    this.loading = true
    const { data } = await this.$http.post(
      'pri/user/findPage',
      {
        pageNum: this.page.currentPage,
        pageSize: this.page.pageSize,
        params: {},
      },
      { prefix: 'admin' }
    )

    this.page.total = data.data.totalSize

    this.tableData = data.data.content
    setTimeout(() => {
      this.loading = false
    }, 500)
  }

  submit() {
    console.log(this.form)
  }

  nodeClick(data: any) {
    // if (data.id == 0) {
    //   this.data = this.data0
    // } else if (data.id == 1) {
    // }
    // this.$message.success(JSON.stringify(data))
  }
}
</script>
<style lang="scss"></style>
