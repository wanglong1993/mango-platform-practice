<template>
  <span>
    <el-button
      :icon="$attrs.icon||''"
      :size="$attrs.size||'small'"
      :type="$attrs.type||'text'"
      @click="buttonTapped"
    >{{$attrs.title||'测试'}}</el-button>

    <el-dialog
      :modal-append-to-body="false"
      v-dialogdrag
      :title="$attrs.title||''"
      :visible.sync="logVisible"
      width="60%"
    >
      <div slot="title">
        <div class="d-flex jc-between ai-baseline pr-5">
          <div>{{$attrs.title}}</div>
        </div>
      </div>

      <el-form label-position="right" class="demo-form-inline" :model="$attrs.data">
        <el-row>
          <template v-for="(column,index) in $attrs.option.column">
            <el-col :key="index" :span="column.col||12">
              <el-form-item
                :label="column.label"
                :prop="column.prop"
                :value="column.value"
                :label-width="formLabelWidth"
              >
                <el-input
                  v-if="!column.formslot"
                  :disabled="column.disabled||false"
                  :autocomplete="`${column.autocomplete||false}`"
                  v-model="$attrs.data[column.prop]"
                  :placeholder="column.placeholder||''"
                ></el-input>
                <slot v-else :name="column.prop+`Form`"></slot>
              </el-form-item>
            </el-col>
          </template>
        </el-row>
      </el-form>

      <slot name="extendField"></slot>

      <span slot="footer">
        <el-button @click=" logVisible= false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </span>
    </el-dialog>

    <!-- <CrudDialog :title="$attrs.title" @submit="submit" ref="CrudDialog"> -->

    <!-- </CrudDialog> -->
  </span>
</template>
<script lang='ts'>
import { Vue, Component, Prop } from 'nuxt-property-decorator'

@Component({
  components: {},
})
export default class Crudbutton extends Vue {
  form = {}
  formLabelWidth = '80px'
  logVisible = false
  // @Prop() form: any

  buttonTapped() {
    this.$emit('click')
    this.logVisible = true
  }

  submit() {
    this.$emit('submit')
    this.logVisible = false
  }
}
</script>
<style lang='scss' scoped>
</style>