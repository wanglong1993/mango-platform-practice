<template>
  <span>
    <el-button
      :icon="$attrs.icon||''"
      :size="$attrs.size||'small'"
      :type="$attrs.type||'text'"
      @click="buttonTapped"
    >{{$attrs.title||'测试'}}</el-button>

    <CrudDialog :title="$attrs.title" @submit="submit" ref="CrudDialog">
      <slot>
        <el-form label-position="right" class="demo-form-inline" :model="$attrs.data">
          <el-row>
            <template v-for="(column,index ) in $attrs.option.column">
              <el-col :key="index" :span="12">
                <el-form-item
                  :label="column.label"
                  :prop="column.prop"
                  :label-width="formLabelWidth"
                >
                  <el-input
                    :disabled="column.disabled||false"
                    v-model="$attrs.data[column.prop]"
                    :placeholder="column.placeholder||''"
                  ></el-input>
                </el-form-item>
              </el-col>
            </template>
          </el-row>
        </el-form>
      </slot>

      <slot name="extendField"></slot>
    </CrudDialog>
  </span>
</template>
<script lang='ts'>
import { Vue, Component } from 'nuxt-property-decorator'
import CrudDialog from './Cruddialog/Cruddialog.vue'
@Component({
  components: { CrudDialog },
})
export default class Crudbutton extends Vue {
  form = this.$attrs.data
  formLabelWidth = '80px'
  buttonTapped() {
    this.$emit('click')

    const ref: any = this.$refs.CrudDialog
    ref.logVisible = true
  }

  submit() {
    this.$emit('submit')
  }
}
</script>
<style lang='scss' scoped>
</style>