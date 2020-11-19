<template>
  <div class="amqp-wrap h-100">
    <el-amap
      class="amap-box"
      vid="map"
      :zoom="zoom"
      :center="center"
      :events="events"
    >
      <!-- <el-amap-marker vid="marker" :position="center" :label="label"></el-amap-marker> -->
      <el-amap-marker
        v-for="(marker, index) in markers"
        :key="index"
        :content="marker.content"
        :position="marker.location"
        :events="marker.events"
      >
        <div
          @click="markerClick(marker)"
          style="
            text-align: center;
            background-color: hsla(180, 100%, 50%, 0.7);
            height: 24px;
            width: 24px;
            border: 1px solid hsl(180, 100%, 40%);
            border-radius: 12px;
            box-shadow: hsl(180, 100%, 50%) 0px 0px 1px;
          "
        ></div>
      </el-amap-marker>
      <el-drawer
        :destroy-on-close="true"
        :withHeader="false"
        :visible.sync="drawer"
        :direction="direction"
        :before-close="handleClose"
      >
        <!-- <el-row>
        <el-col :span="24">-->
        <div
          style="background-color: #20222a"
          class="pl-4 d-flex flex-column ai-left jc-center h-100"
        >
          <div class="d-flex">
            <el-image
              style="width: 120px; height: 120px"
              :src="target.cover"
              :preview-src-list="[target.cover]"
            ></el-image>
            <div class="text-light pl-3">
              <div class="fs-6">{{ target.name }}</div>
              <div class="pt-2 fs-3">{{ target.description }}</div>
              <div class="pt-2 fs-3">分类: {{ target.classification }}</div>
              <div class="pt-2 fs-3" v-if="target.distrition != ''">
                来自：{{ target.distrition }}
              </div>
            </div>
          </div>
          <vue-plyr>
            <audio>
              <source :src="target.ossUrl" type="audio/mp3" />
            </audio>
          </vue-plyr>
        </div>
        <!-- </el-col> -->
        <!-- </el-row> -->
      </el-drawer>
    </el-amap>
  </div>
</template>
<script>
import { Vue, Component } from 'nuxt-property-decorator'
import { AMapManager, lazyAMapApiLoaderInstance } from 'vue-amap'
import config from '~/plugins/config/website.js'
@Component({
  components: {},
})
export default class index extends Vue {
  config = config
  http = Vue.prototype.$http
  drawer = false
  target = {}
  direction = 'btt'
  markers = []
  markerRefs = []
  self = this
  lng = 0
  lat = 0
  zoom = 3
  loaded = false
  label = {
    content: '',
    offset: [10, 12],
  }

  events = {
    init: this.init1,
  }

  markerClick(row) {
    console.log(row)
    this.drawer = true
    this.target = row
  }
  handleClose(done) {
    this.$confirm('确认关闭？')
      .then((_) => {
        done()
      })
      .catch((_) => {})
  }
  init1(o) {
    setTimeout(() => {
      let cluster = new AMap.MarkerClusterer(o, this.markerRefs, {
        gridSize: 80,
        renderCluserMarker: this._renderCluserMarker,
      })
    }, 3000)
  }
  center = [121.406051, 31.179695]

  created() {
    this.mapInit()
  }
  async mapInit() {
    const { data } = await this.http.get('pri/sysSoundfile/findAll', {
      prefix: 'sound',
    })
    data.data.filter((e) => {
      if (e.location != null) {
        const res = e.location.split(',')
        let t = []
        let distrition = ''
        for (let index = 0; index < res.length; index++) {
          if (index != 2) {
            t.push(parseFloat(res[index]))
          } else {
            distrition = res[index]
          }
        }
        if (isNaN(t[0])) {
          e.location = [121, 31]
        } else {
          e.location = t
        }
        e.events = {
          init: this.init,
          // click: this.markerClick,
        }

        e.distrition = distrition
        e.content = `'<div style="text-align:center; background-color: hsla(180, 100%, 50%, 0.7); height: 24px; width: 24px; border: 1px solid hsl(180, 100%, 40%); border-radius: 12px; box-shadow: hsl(180, 100%, 50%) 0px 0px 1px;"></div>`
      }
    })
    // console.log(data.data)
    this.markers = data.data
  }
  init(o) {
    this.markerRefs.push(o)
  }

  _renderCluserMarker(context) {
    const count = this.markers.length

    let factor = Math.pow(context.count / count, 1 / 18)
    let div = document.createElement('div')
    let Hue = 180 - factor * 180
    let bgColor = 'hsla(' + Hue + ',100%,50%,0.7)'
    let fontColor = 'hsla(' + Hue + ',100%,20%,1)'
    let borderColor = 'hsla(' + Hue + ',100%,40%,1)'
    let shadowColor = 'hsla(' + Hue + ',100%,50%,1)'
    div.style.backgroundColor = bgColor
    let size = Math.round(30 + Math.pow(context.count / count, 1 / 5) * 20)
    div.style.width = div.style.height = size + 'px'
    div.style.border = 'solid 1px ' + borderColor
    div.style.borderRadius = size / 2 + 'px'
    div.style.boxShadow = '0 0 1px ' + shadowColor
    div.innerHTML = context.count
    div.style.lineHeight = size + 'px'
    div.style.color = fontColor
    div.style.fontSize = '14px'
    div.style.textAlign = 'center'
    context.marker.setOffset(new AMap.Pixel(-size / 2, -size / 2))
    context.marker.setContent(div)
  }
}
</script>
<style lang="scss">
.plyr--audio .plyr__controls {
  background-color: #20222a !important;
}
</style>
