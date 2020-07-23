<template>
  <div style="height: 150vh;">
    <!-- <template>
      <chartjs :options="options" type="pie" :data="data"></chartjs>
    </template>-->

    <avue-data-tabs :option="option"></avue-data-tabs>
    <div class="py-2 pr-2">
      <avue-data-icons :option="iconsOption"></avue-data-icons>
    </div>
    <div class="px-3 pt-3 fs-6 text-primary">微服务监控</div>
    <el-row class="pt-3 px-3" :gutter="12">
      <el-col :span="8">
        <el-card shadow="always">
          <div>
            <el-link href="http://localhost:8848/nacos" target="_blank">nacos服务监控</el-link>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="always">
          <div>
            <el-link href="http://127.0.0.1:8849" target="_blank">sentinel服务限流</el-link>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="always">
          <div>
            <el-link href="http://127.0.0.1:9001/swagger-ui.html" target="_blank">接口文档</el-link>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <div class="px-3 pt-3 fs-6 text-primary">系统监控</div>
    <el-row class="pt-3 px-3" :gutter="12">
      <el-col :span="8">
        <el-card shadow="always">
          <div>
            <el-link href="http://localhost:8090" target="_blank">admin服务监控</el-link>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <div class="pt-3">
      <avue-data-progress :option="progressOption"></avue-data-progress>
    </div>
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

  iconsOption = {
    span: 4,
    data: [
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '今日注册',
        count: 12678,
        icon: 'el-icon-tickets',
        href: '',
        target: '_blank',
      },
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '今日登录',
        count: 22139,
        icon: 'el-icon-success',
        href: '',
        target: '_blank',
      },
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '今日订阅',
        count: 35623,
        icon: 'el-icon-info',
        href: '',
        target: '_blank',
      },
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '今日评论',
        count: 16826,
        icon: 'el-icon-message',
        href: '',
        target: '_blank',
      },
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '今日评论',
        count: 16826,
        icon: 'el-icon-message',
        href: '',
        target: '_blank',
      },
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '今日评论',
        count: 16826,
        icon: 'el-icon-message',
        href: '',
        target: '_blank',
      },
    ],
  }

  progressOption = {
    span: 6,
    data: [
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '转化率（日同比 28%）',
        color: 'rgb(178, 159, 255)',
        count: 32,
        href: '',
        target: '_blank',
      },
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '签到率（日同比 11%）',
        color: 'rgb(230, 71, 88)',
        count: 32,
        href: '',
        target: '_blank',
      },
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: 'CPU使用率',
        color: 'rgb(27, 201, 142)',
        count: 56,
        href: '',
        target: '_blank',
      },
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '使用人数',
        color: 'red',
        count: 56,
        href: '',
        target: '_blank',
      },
    ],
  }
  option = {
    data: [
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '分类统计',
        subtitle: '实时',
        count: 7993,
        allcount: 10222,
        text: '当前分类总记录数',
        color: 'rgb(27, 201, 142)',
        key: '类',
      },
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '附件统计',
        subtitle: '实时',
        count: 3112,
        allcount: 10222,
        text: '当前上传的附件数',
        color: 'rgb(230, 71, 88)',
        key: '附',
      },
      {
        click: function (item: any) {
          alert(JSON.stringify(item))
        },
        title: '文章统计',
        subtitle: '实时',
        count: 908,
        allcount: 10222,
        text: '评论次数',
        color: 'rgb(178, 159, 255)',
        key: '评',
      },
    ],
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
