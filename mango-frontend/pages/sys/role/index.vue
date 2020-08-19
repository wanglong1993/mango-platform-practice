<template>
  <div class="bg-white h-100">
    <el-container class="pt-3 px-3">
      <avue-crud
        :cell-style="cellStyle"
        :permission="permission"
        :table-loading="loading"
        :data="tableData"
        :option="option"
      >
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
            <template v-slot:extendField>
              <MenuTree ref="tree"></MenuTree>
            </template>
          </Crudbutton>
        </template>

        <template slot="delFlagForm" slot-scope="scope">
          <el-radio-group v-model="scope.row.delFlag">
            <el-radio :label="1">禁用</el-radio>
            <el-radio :label="0">正常</el-radio>
          </el-radio-group>
        </template>
      </avue-crud>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'

@Component({
  components: {},
})
export default class sysRole extends Vue {
  http = Vue.prototype.$http
  dialogFormVisible = false
  menuTree = []
  form: any = {}
  loading = true
  //########### 表格按钮权限
  permission = {
    // delBtn: false,
    // addBtn: false,
    // menu: false,
  }
  crudOption = {
    column: [
      {
        label: '角色编号',
        prop: 'name',
        disabled: true,
      },
      {
        label: '角色名称',
        prop: 'remark',
        disabled: true,
      },
    ],
  }

  async submit() {
    this.loading = true
    const ref: any = this.$refs.tree
    let data: any = []
    ref.$refs.tree.getCheckedNodes(false, true).map((e: any) => {
      data.push({
        roleId: this.form.id,
        menuId: e.id,
      })
    })

    const res = await this.http.post(`/pri/role/saveRoleMenus`, data, {
      prefix: 'admin',
    })

    setTimeout(() => {
      this.loading = false
      if (res.data.code === 200) {
        this.$notify({
          title: '',
          message: '保存成功',
          position: 'bottom-right',
          type: 'success',
        })
      }
    }, 500)
  }

  async initData(obj: any) {
    this.form = obj
    const res = await this.http.get(`/pri/role/findRoleMenus/${obj.id}`, {
      prefix: 'admin',
    })

    setTimeout(() => {
      let checkedKeys: any = []
      res.data.data.sysMenuList.map((e: any) => {
        if (e.type == 2) {
          checkedKeys.push(e)
        }
      })

      const tree: any = this.$refs.tree
      tree.$refs.tree.setCheckedNodes(checkedKeys)
    }, 500)
  }

  formLabelWidth = '120px'

  tableData: any = []

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
        label: '角色编码',
        editDisabled: true,
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
        label: '角色名称',

        prop: 'remark',
      },
      {
        label: '创建时间',
        prop: 'createTime',
        editDisplay: false,
        addDisplay: false,
      },
      {
        label: '更新时间',
        prop: 'lastUpdateTime',
        editDisplay: false,
        addDisplay: false,
      },

      {
        label: '角色状态',
        prop: 'delFlag',
        dicData: [
          { label: '禁用', value: 1 },
          { label: '正常', value: 0 },
        ],
        formslot: true,
        labelslot: true,
        value: 1,
        addDisplay: false,
        // slot: true,
      },
    ],
  }

  cellStyle({ row, column, rowIndex, columnIndex }: any) {
    if (columnIndex == 2) {
      if (row.remark !== '超级管理员') {
        return {
          color: 'green',
          fontWeight: 'bold',
          fontSize: '20',
        }
      } else {
        return {
          color: 'red',
          fontWeight: 'bold',
          fontSize: '20',
        }
      }
    }

    if (columnIndex == 5) {
      if (row.delFlag == 0) {
        return {
          color: 'green',
          fontWeight: 'bold',
          fontSize: '20',
        }
      } else {
        return {
          color: 'red',
          fontWeight: 'bold',
          fontSize: '20',
        }
      }
    }
  }

  mounted() {
    this.fetchRoleList()
  }

  async fetchRoleList() {
    this.loading = true
    const res = await this.http.get('pri/role/findAll', { prefix: 'admin' })

    setTimeout(() => {
      this.loading = false
      this.tableData = res.data.data
    }, 500)
  }
}
</script>
<style lang="scss"></style>
