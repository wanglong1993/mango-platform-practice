<template>
  <div>
    <el-container>
      <el-main>
        <div class="px-3 py-2">
          <el-breadcrumb class="py-2" separator-class="el-icon-arrow-right">
            <el-breadcrumb-item>角色管理</el-breadcrumb-item>
          </el-breadcrumb>
          <el-table height="550" border :data="tableData" style="width: 100%;">
            <el-table-column fixed fit label="创建时间">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px;">
                  {{ scope.row.createTime }}
                </span>
              </template>
            </el-table-column>
            <el-table-column label="创建人">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px;">{{ scope.row.createBy }}</span>
              </template>
            </el-table-column>
            <el-table-column label="最后修改时间">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px;">
                  {{ scope.row.lastUpdateTime }}
                </span>
              </template>
            </el-table-column>
            <el-table-column label="修改人">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px;">
                  {{ scope.row.lastUpdateBy }}
                </span>
              </template>
            </el-table-column>
            <el-table-column label="备注" width="120">
              <template slot-scope="scope">
                <i class="el-icon-user"></i>
                <span style="margin-left: 10px;">{{ scope.row.remark }}</span>
              </template>
            </el-table-column>
            <el-table-column label="角色名称" width="100">
              <template slot-scope="scope">
                <i class="el-icon-user"></i>
                <span style="margin-left: 10px;">{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column label="是否停用" width="100">
              <template slot-scope="scope">
                <i class="el-icon-phone"></i>
                <span style="margin-left: 10px;">{{ scope.row.delFlag }}</span>
              </template>
            </el-table-column>
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
                <el-dropdown class="pointer" @command="handleCommand">
                  <span class="el-dropdown-link">
                    <i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="authMenu"
                      >授权菜单</el-dropdown-item
                    >
                    <el-dropdown-item command="authUser"
                      >分配用户</el-dropdown-item
                    >
                  </el-dropdown-menu>
                </el-dropdown>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            class="pt-3"
            background
            layout="prev, pager, next"
            :total="1000"
          ></el-pagination>
        </div>
      </el-main>
    </el-container>

    <template>
      <Roledialog
        ref="dialog"
        :title="`修改角色信息`"
        @closeForm="dialogFormVisible = false"
        :formLabelWidth="formLabelWidth"
        :visible="dialogFormVisible"
      ></Roledialog>
    </template>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'

@Component({
  components: {},
})
export default class sysRole extends Vue {
  dialogFormVisible = false

  formLabelWidth = '120px'

  tableData: any = []

  mounted() {
    this.fetchRoleList()
  }

  async fetchRoleList() {
    const res = await this.$http.get('pri/role/findAll', { prefix: 'admin' })
    this.tableData = res.data.data
  }

  handleCommand(command: any) {
    switch (command) {
      case 'authMenu':
        this.dialogFormVisible = true
        break
      case 'authUser':
        break
      default:
        break
    }
  }

  handleEdit(index: any, row: any) {
    this.dialogFormVisible = true
    const dialog: any = this.$refs.dialog

    dialog.form = row
  }

  handleDelete(index: any, row: any) {
    console.log(index, row)
  }
}
</script>
<style lang="scss"></style>
