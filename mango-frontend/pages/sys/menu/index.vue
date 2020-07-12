<template>
  <div>
    <div class="d-flex jc-between ai-center pl-3 py-2">
      <el-page-header content="菜单管理"></el-page-header>
      <div class="pr-3">
        <el-button size="mini" @click="searchShow = !searchShow" plain
          >查询</el-button
        >
        <el-button size="mini" @click="refresh" plain>刷新</el-button>
        <el-button size="mini" plain>展开</el-button>
        <el-button size="mini" plain>折叠</el-button>
        <el-button size="mini" plain>新增</el-button>
      </div>
    </div>
    <el-form v-if="searchShow" :inline="true" :model="formInline" class="pl-3">
      <el-form-item label="菜单名称">
        <el-input v-model="formInline.keyword" placeholder></el-input>
      </el-form-item>

      <el-form-item>
        <el-button size="mini" type="primary" @click="onSearch">查询</el-button>
        <el-button size="mini" plain>重置</el-button>
      </el-form-item>
    </el-form>
    <div class="px-3 py-2">
      <el-table
        class="tableBox"
        :data="tableData"
        style="width: 100%; margin-bottom: 20px;"
        row-key="id"
        highlight-current-row
        height="500"
        stripe
        border
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      >
        <el-table-column
          prop="name"
          label="菜单名称"
          sortable
          width="180"
        ></el-table-column>
        <el-table-column
          prop="date"
          label="日期"
          sortable
          width="180"
        ></el-table-column>
        <el-table-column
          prop="type"
          label="类型"
          sortable
          width="180"
        ></el-table-column>
        <el-table-column prop="url" label="链接"></el-table-column>
        <el-table-column prop="orderNum" label="排序"></el-table-column>
        <el-table-column prop="perms" label="权限"></el-table-column>

        <el-table-column fixed="right" label="操作">
          <template slot-scope="scope">
            <el-button
              type="text"
              icon="el-icon-edit"
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              class="text-danger"
              size="mini"
              type="text"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
            <el-button
              @click="handleInsert(scope.$index, scope.row)"
              icon="el-icon-circle-plus"
              class="text-blue"
              size="mini"
              type="text"
              >新增菜单</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>

    <template>
      <Menudialog
        ref="Menudialog"
        :title="title"
        @refreshScope="refresh()"
        @closeForm="dialogFormVisible = false"
        :formLabelWidth="formLabelWidth"
        :visible="dialogFormVisible"
      ></Menudialog>
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
  searchShow = false
  dialogFormVisible = false
  formLabelWidth = '120px'
  formInline = {
    keyword: '',
  }

  mounted() {
    this.fetchMenu()
  }

  refresh() {
    this.fetchMenu()
  }

  onSearch() {}

  handleOpen(key: any, keyPath: any) {
    console.log(key, keyPath)
  }

  async fetchMenu() {
    const http = Vue.prototype.$http
    const res = await http.get('/pri/menu/findMenuTree', {
      prefix: 'menu',
    })

    this.tableData = res.data.data
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
        setTimeout(() => {
          this.refresh()
        }, 500)
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
