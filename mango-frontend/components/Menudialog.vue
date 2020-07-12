<template>
  <el-dialog fullscreen @close="closeForm" :title="$attrs.title" :visible.sync="$attrs.visible">
    <el-form style="width:500px;" :model="form">
      <!-- <el-form-item v-for="(item,index) in data" :key="index"  >
            
      </el-form-item>-->
      <el-form-item label="菜单名称" :label-width="$attrs.formLabelWidth">
        <el-input v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="上级菜单" :label-width="$attrs.formLabelWidth">
        <el-input :disabled="true" v-model="form.parentName" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="链接(href)" :label-width="$attrs.formLabelWidth">
        <el-input v-model="form.url" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="排序(升序)" :label-width="$attrs.formLabelWidth">
        <el-input v-model="form.orderNum" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="权限标识" :label-width="$attrs.formLabelWidth">
        <el-input v-model="form.perms" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="菜单图标" :label-width="$attrs.formLabelWidth">
        <el-input v-model="form.icon" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="菜单类型" prop="type">
        <el-radio-group v-model="form.type">
          <el-radio label="1">菜单</el-radio>
          <el-radio label="2">权限</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="closeForm">取 消</el-button>
      <el-button type="primary" @click="formSubmit">确 定</el-button>
    </div>
  </el-dialog>
</template>
<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class Roledialog extends Vue {
  form = {}
  mounted() {}

  data = []

  closeForm() {
    this.$emit('closeForm')
  }

  async formSubmit() {
    this.$emit('closeForm')
    const res = await this.$http.post('/pri/menu/save', this.form, {
      prefix: 'menu',
    })
    this.$emit('refreshScope')
    console.log(res.data)
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
