<template >
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
    <slot></slot>

    <slot name="extendField"></slot>

    <span slot="footer">
      <el-button @click=" logVisible= false">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </span>
  </el-dialog>
</template>
<script lang='ts'>
import { Vue, Component } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class Cruddialog extends Vue {
  logVisible = false

  submit() {
    this.logVisible = false
    this.$emit('submit')
  }
}
</script>
<style lang='scss' scoped>
</style>