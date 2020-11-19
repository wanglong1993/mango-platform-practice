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
      <template v-for="(msg, index) in $store.state.socketMsg">
        <div :key="index" v-html="msg.el"></div>
      </template>
    </div>
    <div class="mx-2" style="width: 300px">
      <div class="pb-2">
        <el-input v-model="message" placeholder="请输入发送信息"></el-input>
      </div>

      <el-button type="primary" @click="sendJoin" size="mini"
        >加入群聊</el-button
      >
      <el-button type="primary" @click="sendGroup" size="mini">群聊</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'

@Component({})
export default class Socket extends Vue {
  socket: any = ''
  message = ''
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
        `<span class="text-info">${data.groupId} 群消息: ${data.fromUid} 说: ${data.message}</span>`
      )
    })
  }

  // 加入群聊
  sendJoin() {
    let joinRequest = {
      userId: this.$store.state.socketName,
      groupId: '666',
    }

    this.socket.emit('join', joinRequest, (resp: any) => {
      // 下次用这个token发送即可
      console.log(resp)
    })
  }

  sendGroup() {
    let groupRequest = {
      fromUid: this.$store.state.socketName,
      groupId: '666',
      message: this.message,
    }
    if (this.message != '') {
      this.socket.emit('group', groupRequest, (data: any) => {
        if (data.code == 200) {
          this.message = ''
        } else {
          this.$message({
            message: data,
            type: 'warning',
          })
        }
      })
    } else {
      this.$message({
        message: '要不要说点什么',
        type: 'warning',
      })
    }
  }

  pushData(message: String) {
    let currentTime = this.$dayjs().format('YYYY-MM-DD HH:mm:ss.SSS')
    message = currentTime + ' ' + message
    this.$store.commit('PUSH_MSG', { el: message })
  }
}
</script>

<style lang="scss" scoped></style>
