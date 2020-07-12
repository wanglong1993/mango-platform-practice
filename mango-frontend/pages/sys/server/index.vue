<template>
  <div>
    <!-- <iframe
      style="height:100%;width:100%"
      cols="100%,*"
      rows="*"
      frameborder="NO"
      border="0"
      framespacing="0"
      id="ifr"
      src="http://localhost:8848/nacos"
    ></iframe>-->
  </div>
</template>
<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class index extends Vue {
  asyncData({ store }: any) {
    return {}
  }
  head() {
    return {
      title: '',
    }
  }
}
</script>
<style lang="scss" scoped></style>
