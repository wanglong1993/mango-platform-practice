<template>
  <div style="height: 150vh;">
    <template>
      <chartjs :options="options" type="pie" :data="data"></chartjs>
    </template>

    <div class="px-3 pt-3 fs-6 text-primary">微服务监控</div>
    <el-row class="pt-3 px-3" :gutter="12">
      <el-col :span="8">
        <el-card shadow="always">
          <div>
            <el-link href="http://localhost:8848/nacos" target="_blank"
              >nacos服务监控</el-link
            >
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="always">
          <div>
            <el-link href="http://127.0.0.1:8849" target="_blank"
              >sentinel服务限流</el-link
            >
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="always">
          <div>
            <el-link
              href="http://127.0.0.1:9001/swagger-ui.html"
              target="_blank"
              >接口文档</el-link
            >
          </div>
        </el-card>
      </el-col>
    </el-row>

    <div class="px-3 pt-3 fs-6 text-primary">系统监控</div>
    <el-row class="pt-3 px-3" :gutter="12">
      <el-col :span="8">
        <el-card shadow="always">
          <div>
            <el-link href="http://localhost:8090" target="_blank"
              >admin服务监控</el-link
            >
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class index extends Vue {
  options = {}

  data = {
    datasets: [
      {
        data: [10, 20, 30],
      },
    ],

    // These labels appear in the legend and in the tooltips when hovering different arcs
    labels: ['Red', 'Yellow', 'Blue'],
  }
}
</script>

<style>
.container {
  margin: 0 auto;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.title {
  font-family: 'Quicksand', 'Source Sans Pro', -apple-system, BlinkMacSystemFont,
    'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}

.subtitle {
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}

.links {
  padding-top: 15px;
}
</style>
