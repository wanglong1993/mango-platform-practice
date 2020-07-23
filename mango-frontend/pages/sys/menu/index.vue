<template>
  <div>
    <div class="d-flex jc-between ai-center pl-3 py-2">
      <el-page-header content="菜单管理"></el-page-header>
    </div>

    <avue-crud :table-loading="loading" :option="option" :data="tableData">
      <template slot="icon" slot-scope="scope">
        <i :class="scope.row.icon" style="font-size:24px"></i>
      </template>
      <template slot-scope="scope" slot="menu">
        <Crudbutton
          icon="el-icon-check"
          size="mini"
          type="text"
          :data="form"
          :option="crudOption"
          @click="initData(scope.row)"
          @submit="submit"
          :title="'授权菜单'"
        >
          <template slot-scope="scope" slot="extendField">
            <el-cascader :props="casProps" :options="treeData" clearable></el-cascader>
          </template>
        </Crudbutton>
      </template>
    </avue-crud>

    <template>
      <!-- <Menudialog
        ref="Menudialog"
        :title="title"
        @refreshScope="refresh()"
        @closeForm="dialogFormVisible = false"
        :formLabelWidth="formLabelWidth"
        :visible="dialogFormVisible"
      ></Menudialog>-->
    </template>
  </div>
</template>
<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class MenuIndex extends Vue {
  title = '修改菜单信息'
  tableData = []
  treeData = []
  form = {}
  loading = true

  dialogFormVisible = false
  formLabelWidth = '120px'
  formInline = {
    keyword: '',
  }

  casProps = {
    value: 'id',
    label: 'name',
    checkStrictly: true,
  }

  initData(obj: any) {
    this.form = obj
  }

  submit() {}

  crudOption = {
    column: [
      { label: '上级菜单', prop: 'parentName' },
      {
        label: '菜单名称',
        prop: 'name',
      },
      {
        label: '链接',
        prop: 'url',
      },
    ],
  }

  option = {
    headerAlign: 'center',
    align: 'center',
    border: true,
    index: true,
    defaultExpandAll: false,
    column: [
      {
        label: '菜单名称',
        prop: 'name',
        align: 'left',
        width: 200,
      },
      {
        label: '类型',
        prop: 'type',
        dicData: [
          {
            label: '一',
            value: 0,
          },
          {
            label: '菜单',
            value: 1,
          },
          {
            label: '权限',
            value: 2,
          },
        ],
      },
      {
        label: '链接',
        prop: 'url',
      },
      {
        label: '自定义图标',
        prop: 'icon',
        slot: true,
      },
      {
        label: '权限标识',
        prop: 'perms',
      },
    ],
  }

  mounted() {
    this.fetchMenu()
  }

  handleOpen(key: any, keyPath: any) {
    console.log(key, keyPath)
  }

  async fetchMenu() {
    const http = Vue.prototype.$http
    const res = await http.get('/pri/menu/findMenuTree', {
      prefix: 'menu',
    })
    setTimeout(() => {
      this.loading = false
      this.tableData = res.data.data
      this.treeData = res.data.data
    }, 500)
  }
  handleInsert(index: any, row: any) {
    this.title = '新增菜单'
    this.dialogFormVisible = true

    const dialog: any = this.$refs.Menudialog
    let newone = {
      parentName: row.name,
      parentId: row.id,
      level: row.level + 1,
      type: '1',
      children: [null],
    }

    dialog.form = newone
  }

  handleEdit(index: any, row: any) {
    this.title = '修改菜单'
    this.dialogFormVisible = true

    const dialog: any = this.$refs.Menudialog

    dialog.form = row
  }
  handleDelete(index: any, row: any) {
    this.$confirm('此操作将删除该菜单权限, 是否继续?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    })
      .then(() => {
        let data = []
        data.push({ id: row.id })

        const res = this.$http.post('/pri/menu/delete', data, {
          prefix: 'menu',
        })

        this.$message({
          type: 'success',
          message: '删除成功!',
        })
        setTimeout(() => {}, 500)
      })
      .catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除',
        })
      })
  }
}
</script>
<style lang="scss">
.tableBox {
  th {
    padding: 0 !important;
    height: 15px;
    line-height: 15xp;
  }
  td {
    padding: 0 !important;
    height: 15px;
    line-height: 15xp;
  }
}
</style>
