<template>
  <div class="bg-white h-100">
    <el-container class="pt-3 px-3">
      <avue-crud
        @row-update="rowUpdate"
        @row-save="rowSave"
        @refresh-change="rowRefresh"
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
          <el-input
            :disabled="scope.row.type!=1&&scope.row.type!=0"
            v-model="scope.row.url"
            placeholder
          ></el-input>
        </template>

        <template slot="typeForm" slot-scope="scope">
          <el-radio-group v-model="scope.row.type">
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

      <Cruddialog ref="dialog" :data="form" :option="crudOption" @submit="submit" :title="'新增下级菜单'">
        <template v-slot:urlForm>
          <el-input :disabled="form.type!=1" v-model="form.url" placeholder></el-input>
        </template>

        <template v-slot:typeForm>
          <el-radio-group v-model="form.type">
            <el-radio :label="0">父菜单</el-radio>
            <el-radio :label="1">菜单</el-radio>
            <el-radio :label="2">权限</el-radio>
          </el-radio-group>
        </template>

        <template slot="parentNameForm">
          <el-cascader v-model="form.parentId" collapse-tags :props="casProps" :options="treeData"></el-cascader>
        </template>

        <template slot="iconForm">
          <avue-input-icon
            :disabled="form.type==2"
            v-model="form.icon"
            placeholder="请选择图标"
            :icon-list="iconList"
          ></avue-input-icon>
        </template>

        <template slot="permsForm">
          <el-input :disabled="form.type!=2" v-model="form.perms" placeholder></el-input>
        </template>
      </Cruddialog>
    </el-container>
  </div>
</template>
<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class MenuIndex extends Vue {
  http = Vue.prototype.$http
  title = '修改菜单信息'
  tableData = []
  treeData = []
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
  form = {}
  loading = true

  casProps = {
    value: 'id',
    label: 'name',
    emitPath: false,
    checkStrictly: true,
    expandTrigger: 'hover',
    // multiple: true,
  }

  initData(obj: any) {
    console.log(obj)
    const ref: any = this.$refs.dialog
    ref.logVisible = true
    let dto = { type: obj.type, parentId: obj.id, orderNum: 30 }
    this.form = dto
  }

  submit() {
    console.log(this.form)
  }

  crudOption = {
    column: [
      {
        rules: [
          {
            message: '请添加上级菜单',
            trigger: 'blur',
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
      },
      {
        label: '类型',
        prop: 'type',
        formslot: true,
        value: 0,
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
        formslot: true,
        labelslot: true,
      },
      {
        label: '上级菜单',
        prop: 'parentId',
        formslot: true,
        labelslot: true,
      },
      {
        label: '自定义图标',
        prop: 'icon',
        formslot: true,
        labelslot: true,
        slot: true,
      },
      {
        label: '权限标识',
        prop: 'perms',
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
      prefix: 'menu',
    })
    setTimeout(() => {
      this.loading = false
      this.tableData = res.data.data
      this.treeData = res.data.data
    }, 300)
  }

  async rowSave(form: any, done: any, loading: any) {
    setTimeout(() => {
      done(form)
    }, 300)
    console.log(form)
  }

  rowRefresh() {
    this.fetchMenu()
  }
  async rowUpdate(form: any, index: any, done: any, loading: any) {
    const res = await this.http.post('/pri/menu/save', form, {
      prefix: 'menu',
    })

    setTimeout(() => {
      this.fetchMenu()
      done(form)
    }, 300)
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
