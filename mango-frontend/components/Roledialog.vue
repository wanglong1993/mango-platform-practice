<template>
  <el-dialog fullscreen @close="closeForm" :title="$attrs.title" :visible.sync="$attrs.visible">
    <el-form :model="form">
      <!-- <el-form-item v-for="(item,index) in data" :key="index"  >
            
      </el-form-item>-->

      <el-form-item label="角色编码" :label-width="$attrs.formLabelWidth">
        <el-input :disabled="true" v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="角色名称" :label-width="$attrs.formLabelWidth">
        <el-input :disabled="true" v-model="form.remark" autocomplete="off"></el-input>
      </el-form-item>
      <div>授权功能菜单</div>
      <div>
        <el-tree
          :data="data"
          show-checkbox
          default-expand-all
          node-key="id"
          ref="tree"
          highlight-current
          :props="defaultProps"
        >
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}</span>
            <span>
              <el-button type="text" size="mini">{{data.url}}</el-button>
              <el-button type="text" size="mini">{{data.perms}}</el-button>
            </span>
          </span>
        </el-tree>
      </div>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="closeForm">取 消</el-button>
      <el-button type="primary" @click="submmitForm">确 定</el-button>
    </div>
  </el-dialog>
</template>
<script lang="ts">
import { Vue, Component, Watch, Prop } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class Roledialog extends Vue {
  form: any = []
  data = []
  http = Vue.prototype.$http

  defaultProps = {
    children: 'children',
    label: 'name',
  }
  mounted() {
    this.init()
  }

  async init() {
    const res = await this.http.get('/pri/menu/findMenuTree', {
      prefix: 'menu',
    })
    this.data = res.data.data
  }

  @Watch('form')
  async findRoleMenus(newval: any, oldval: any) {
    const res = await this.http.get(`/pri/role/findRoleMenus/${this.form.id}`, {
      prefix: 'admin',
    })

    let checkedKeys: any = []
    res.data.data.sysMenuList.map((e: any) => {
      checkedKeys.push(e.id)
    })
    this.$refs.tree.setCheckedKeys(checkedKeys)
  }

  closeForm() {
    this.$emit('closeForm')
  }

  async submmitForm() {
    let roleMenuLists: any = []

    this.$refs.tree.getCheckedKeys().forEach((e: any) => {
      roleMenuLists.push({ roleId: this.form.id, menuId: e })
    })

    const res = await this.http.post('/pri/role/saveRoleMenus', roleMenuLists, {
      prefix: 'admin',
    })

    console.log(res.data.data)
    //  this.$emit('closeForm')
  }
}
</script>
<style lang="scss" scoped>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>
