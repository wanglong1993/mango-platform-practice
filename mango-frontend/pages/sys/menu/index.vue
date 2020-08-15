<template>
  <div class="bg-white h-100">
    <el-container class="pt-3 px-3">
      <avue-crud
        @row-update="rowUpdate"
        @row-save="rowSave"
        @row-del="rowDel"
        @refresh-change="rowRefresh"
        :before-close="beforeClose"
        :table-loading="loading"
        :option="option"
        :data="tableData"
      >
        <template slot="icon" slot-scope="scope">
          <i :class="scope.row.icon" style="font-size:24px"></i>
        </template>

        <template slot="iconForm" slot-scope="scope">
          <avue-input-icon
            :disabled="scope.row.type==2"
            v-model="scope.row.icon"
            placeholder="请选择图标"
            :icon-list="iconList"
          ></avue-input-icon>
        </template>

        <template slot="permsForm" slot-scope="scope">
          <el-input :disabled="scope.row.type!=2" v-model="scope.row.perms" placeholder></el-input>
        </template>

        <template slot="urlForm" slot-scope="scope">
          <el-input :disabled="scope.row.type!=1" v-model="scope.row.url"></el-input>
        </template>

        <template slot="typeForm" slot-scope="scope">
          <el-radio-group @change="radioChange(scope.row)" v-model="scope.row.type">
            <el-radio :label="0">父菜单</el-radio>
            <el-radio :label="1">菜单</el-radio>
            <el-radio :label="2">权限</el-radio>
          </el-radio-group>
        </template>

        <template slot="parentIdForm" slot-scope="scope">
          <el-cascader
            :disabled="scope.row.type==0"
            v-model="scope.row.parentId"
            collapse-tags
            :props="casProps"
            :options="treeData"
            clearable
          ></el-cascader>
        </template>

        <template slot-scope="{size,type,row}" slot="menu">
          <el-button :size="size" :type="type" @click="initData(row)">新增下级菜单</el-button>
        </template>
      </avue-crud>

      <!-- 自己的弹窗 -->
      <Cruddialog ref="dialog" :data="form" :option="crudOption" @submit="submit" :title="'新增下级菜单'">
        <template slot="urlForm" slot-scope="scope">
          <el-input
            :disabled="scope.row.type!=1"
            v-model="scope.row.url"
            placeholder="链接形式：/sys/***"
          ></el-input>
        </template>

        <template slot="typeForm" slot-scope="scope">
          <el-radio-group @change="radioChange(scope.row)" v-model="scope.row.type">
            <el-radio :label="1">菜单</el-radio>
            <el-radio :label="2">权限</el-radio>
          </el-radio-group>
        </template>

        <template slot="parentNameForm" slot-scope="scope">
          <el-cascader
            v-model="scope.row.parentId"
            collapse-tags
            :props="casProps"
            :options="treeData"
          ></el-cascader>
        </template>

        <template slot="iconForm" slot-scope="scope">
          <avue-input-icon
            :disabled="scope.row.type==2"
            v-model="scope.row.icon"
            placeholder="请选择图标"
            :icon-list="iconList"
          ></avue-input-icon>
        </template>

        <template slot="permsForm" slot-scope="scope">
          <el-input :disabled="scope.row.type!=2" v-model="scope.row.perms" placeholder></el-input>
        </template>
      </Cruddialog>
    </el-container>
  </div>
</template>
<script lang="ts">
import { Vue, Component, Watch } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class MenuIndex extends Vue {
  http = Vue.prototype.$http
  title = '修改菜单信息'
  tableData = []
  treeData = []
  formType = 0
  iconList = [
    {
      label: '基本图标',
      list: [
        'el-icon-info',
        'el-icon-error',
        'el-icon-error',
        'el-icon-success',
        'el-icon-warning',
        'el-icon-question',
      ],
    },
    {
      label: '方向图标',
      list: [
        'el-icon-info',
        'el-icon-back',
        'el-icon-arrow-left',
        'el-icon-arrow-down',
        'el-icon-arrow-right',
        'el-icon-arrow-up',
      ],
    },
    {
      label: '符号图标',
      list: ['el-icon-plus', 'el-icon-minus', 'el-icon-close', 'el-icon-check'],
    },
  ]
  form: any = { type: '' }
  loading = true

  casProps = {
    value: 'id',
    label: 'name',
    emitPath: false,
    checkStrictly: true,
    expandTrigger: 'hover',
    // multiple: true,
  }

  radioChange(row: any) {
    this.formType = row.type

    if (row.type == 0) {
      row.parentId = ''
    }
    row.url = ''

    row.icon = ''
    row.perms = ''
  }

  initData(obj: any) {
    console.log(obj)
    const ref: any = this.$refs.dialog
    ref.logVisible = true
    let dto = {
      type: obj.type == 0 ? 1 : obj.type,
      parentId: obj.id,
      orderNum: 30,
      url: '',
      perms: '',
    }
    this.form = dto
  }

  async submit() {
    const res = await this.http.post('/pri/menu/save', this.form, {
      prefix: 'admin',
    })

    setTimeout(() => {
      this.fetchMenu()
    }, 300)
  }

  beforeClose(done: any) {
    this.formType = 0
    done()
  }

  validateParentId(rule: any, value: any, callback: any) {
    console.log(this.formType)
    if (this.formType == 1 || this.formType == 2) {
      if (value == '') {
        callback(new Error('请选择上级菜单'))
      } else {
        callback()
      }
    } else {
      callback()
    }
  }

  validatePermission(rule: any, value: any, callback: any) {
    console.log(value)
    if (this.formType == 2) {
      if (value == '') {
        callback(new Error('请输入权限信息'))
      } else {
        callback()
      }
    } else {
      callback()
    }
  }

  crudOption = {
    column: [
      {
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
            validator: this.validateParentId,
          },
        ],
        label: '上级菜单',
        prop: 'parentName',
        formslot: true,
      },
      {
        label: '类型',
        prop: 'type',
        formslot: true,
      },
      {
        rules: [
          {
            required: true,
            message: '请添加菜单名称',
            trigger: 'blur',
          },
        ],
        label: '菜单名称',
        prop: 'name',
      },

      {
        label: '链接',
        prop: 'url',
        formslot: true,
      },

      {
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
            validator: this.validatePermission,
          },
        ],
        label: '权限标识',
        prop: 'perms',
        formslot: true,
      },
      {
        label: '选择标签',
        prop: 'icon',
        formslot: true,
      },
    ],
  }

  option = {
    headerAlign: 'center',
    align: 'center',
    border: true,
    // index: true,
    size: 'mini',
    dialogDrag: true,
    defaultExpandAll: false,
    column: [
      {
        label: '菜单名称',
        prop: 'name',
        align: 'left',
        width: 200,
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
          },
        ],
      },
      {
        label: '类型',
        prop: 'type',
        formslot: true,
        value: 0,
        editDisplay: false,
        labelslot: true,
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
        overHidden: true,
        formslot: true,
        labelslot: true,
      },
      {
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
            validator: this.validateParentId,
          },
        ],
        label: '上级菜单',
        prop: 'parentId',
        formslot: true,
        labelslot: true,
      },
      {
        label: '排序',
        prop: 'orderNum',
        value: 60,
      },
      {
        label: '自定义图标',
        prop: 'icon',
        formslot: true,
        labelslot: true,
        slot: true,
      },
      {
        rules: [
          {
            required: true,
            message: '',
            trigger: 'blur',
            validator: this.validatePermission,
          },
        ],
        label: '权限标识',
        prop: 'perms',
        overHidden: true,
        formslot: true,
        labelslot: true,
      },
    ],
  }

  mounted() {
    this.fetchMenu()
  }

  async fetchMenu() {
    this.loading = true
    const res = await this.http.get('/pri/menu/findMenuTree', {
      prefix: 'admin',
    })
    setTimeout(() => {
      this.loading = false
      this.tableData = res.data.data
      this.treeData = res.data.data
    }, 300)
  }

  async rowSave(form: any, done: any, loading: any) {
    const res = await this.http.post('/pri/menu/save', form, {
      prefix: 'admin',
    })
    setTimeout(() => {
      done(form)
    }, 300)

    this.fetchMenu()
  }

  rowRefresh() {
    this.fetchMenu()
  }

  rowDel(form: any, index: any) {
    this.$confirm('此操作将删除' + form.name + ', 是否继续?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    })
      .then(async () => {
        let data: any = []
        data.push(form)
        const res = await this.http.post('/pri/menu/delete', data, {
          prefix: 'admin',
        })

        this.fetchMenu()

        this.$message({
          type: 'success',
          message: '删除成功!',
        })
      })
      .catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除',
        })
      })
  }

  async rowUpdate(form: any, index: any, done: any, loading: any) {
    const res = await this.http.post('/pri/menu/save', form, {
      prefix: 'admin',
    })
    setTimeout(() => {
      done(form)
    }, 300)
    this.fetchMenu()
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
