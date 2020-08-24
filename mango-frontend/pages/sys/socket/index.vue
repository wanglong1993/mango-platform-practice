<template>
  <div class="h-100">
    <div
      class="bg-white mx-2 my-2"
      style="
        overflow-y: scroll;
        width: 700px;
        height: 500px;
        border: 1px solid #cbcbcb;
      "
    >
      <template v-for="(msg, index) in data">
        <div :key="index" v-html="msg.element"></div>
      </template>
    </div>
    <div class="mx-2">
      <el-button type="primary" @click="sendJoin">加入群聊</el-button>
      <el-button type="primary" @click="sendGroup">群聊</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'

@Component({})
export default class Socket extends Vue {
  socket: any = ''

  data: any = []

  mounted() {
    this.socket = this.$nuxtSocket({
      query: { token: this.$store.state.Auth.token },
      channel: '',
    })

    this.socket.on('connection', (resp: any) => {
      // 下次用这个token发送即可
      this.$store.commit('SET_SOCKET_NAME', resp.username)
    })

    this.socket.on('join', (data: any) => {
      this.pushData(`${data.groupId} 群通知: 新人 ${data.userId} 请爆照`)
    })

    this.socket.on('group', (data: any) => {
      this.pushData(
        `<span class="text-red">${data.groupId} 群消息: ${data.fromUid} 说: ${data.message}</span>`
      )
    })
  }

  sendJoin() {
    let joinRequest = {
      userId: this.$store.state.socketName,
      groupId: '666',
    }
    console.log(this.socket)
    this.socket.emit('join', joinRequest, (resp: any) => {
      // 下次用这个token发送即可
      console.log(resp)
    })
  }

  sendGroup() {
    let groupRequest = {
      fromUid: this.$store.state.socketName,
      groupId: '666',
      message: '测试',
    }
    this.socket.emit('group', groupRequest, (data: any) => {
      if (data) {
        console.log(data)
      }
    })
  }

  pushData(message: String) {
    let currentTime = this.$dayjs().format('YYYY-MM-DD HH:mm:ss.SSS')
    this.data.push({ element: currentTime + ' ' + message })
  }
}
</script>

<style lang="scss" scoped></style>
