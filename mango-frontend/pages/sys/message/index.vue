<template>
  <div class="bg-white h-100">
    <el-container class="pt-3 px-3">
      <div>
        <el-button type="primary" @click="doConnect">开启连接</el-button>
      </div>
      <div class="px-3">
        <el-button type="primary" @click="disconnect">关闭连接</el-button>
      </div>
    </el-container>
  </div>
</template>
<script  >
import { Vue, Component, Watch } from 'nuxt-property-decorator'
import stomp from 'stompjs'
import config from '~/plugins/config/website.js'
import { connect } from 'net'
@Component({
  components: {},
})
export default class index extends Vue {
  client= null

  doConnect() {
    this.client = stomp.client(config.RTMQ_SERVICE)
    this.connect()
  }

  connect() {
    this.client.connect(
      'admin',
      'heshenghao',
      this.on_connect,
      this.on_error,
      'my_vhost'
    )
    // this.client.heartbeat.incoming = 5000
    // this.client.heartbeat.outgoing = 5000
    console.log('>>>成功连接服务器')
  }

  on_connect() {
    console.log(234)
    this.client.subscribe('/queue/amqp.queue', (data) => {
      this.$notify({
        title: '成功',
        message: data.body,
        type: 'success',
      })
    })
  }

  disconnect() {
    this.client.disconnect(() => {
      this.$notify({
        title: '成功',
        message: 'See you next time!',
        type: 'success',
      })
    })
  }

  on_error() {
    console.log('MQ 连接失败，5秒后重连')
    // window.setTimeout(() => {
    //   this.doConnect()
    // }, 5000)
  }
}
</script>
<style lang='scss' scoped>
</style>