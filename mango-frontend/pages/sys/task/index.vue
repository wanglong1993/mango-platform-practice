<template>
  <div class="bg-white h-100">
    <el-container class="pt-3 px-3">
      <el-main>
        <div class="px-3 py-2">
          <el-breadcrumb class="py-2" separator-class="el-icon-arrow-right">
            <el-breadcrumb-item></el-breadcrumb-item>
          </el-breadcrumb>

          <avue-crud
            :page.sync="page"
            :table-loading="loading"
            @on-load="onLoad"
            @row-update="rowUpdate"
            @row-save="rowSave"
            @row-del="rowDel"
            :permission="permission"
            :data="tableData"
            :option="option"
          >
            <template slot-scope="{ type, size }" slot="menu">
              <el-button icon="el-icon-check" :size="size" :type="type"
                >暂停</el-button
              >
              <el-button icon="el-icon-check" :size="size" :type="type"
                >恢复</el-button
              >
            </template>
            <template slot="jobClassNameForm" slot-scope="scope">
              <el-select v-model="scope.row.jobClassName" placeholder="">
                <el-option
                  v-for="(item, index) in JobList"
                  :key="index"
                  :label="item.jobClassName"
                  :value="item.jobClassName"
                >
                </el-option>
              </el-select>
            </template>
          </avue-crud>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class index extends Vue {
  loading = true
  tableData = []
  form = {}
  http = Vue.prototype.$http
  JobList = []
  page: any = {
    total: 10,
    pagerCount: 5,
    currentPage: 1,
    pageSize: 10,
    pageSizes: [5, 10, 20],
    layout: 'total, sizes,prev, pager, next, jumper',
    background: false,
  }

  permission = {
    delBtn: true,
    // editBtn: false,
    addBtn: true,
    menu: true,
  }

  option = {
    height: '450',
    // sortable: true,
    // selection: true,
    menuWidth: 210,
    labelWidth: 100,
    index: true,
    size: 'mini',
    dialogDrag: true,
    column: [
      {
        label: '任务类名',
        prop: 'jobClassName',
        formslot: true,
        labelslot: true,
        rules: [
          {
            required: true,
            trigger: 'blur',
          },
        ],
      },
      {
        label: '任务名称',
        prop: 'jobName',
        addDisplay: false,
      },
      {
        label: '定时任务组',
        prop: 'jobGroup',
        rules: [
          {
            required: true,
            trigger: 'blur',
          },
        ],
      },

      {
        label: '触发器名称',
        prop: 'triggerName',
        addDisplay: false,
      },
      {
        label: '触发器组',
        prop: 'triggerGroup',
        addDisplay: false,
      },
      {
        label: '重复间隔',
        prop: 'repeatInterval',
        addDisplay: false,
      },
      {
        label: '触发次数',
        prop: 'timesTriggered',
        addDisplay: false,
      },
      {
        label: 'cron 表达式',
        prop: 'cronExpression',

        rules: [
          {
            required: true,
            trigger: 'blur',
          },
        ],
      },
      {
        label: '时区',
        prop: 'timeZoneId',
        addDisplay: false,
      },
      {
        label: '定时任务状态',
        prop: 'triggerState',
        addDisplay: false,
      },
    ],
  }

  mounted() {
    this.getJobList()
  }
  async getJobList() {
    const res = await this.http.get('pri/job/jobList', { prefix: 'task' })
    this.JobList = res.data.data
  }

  async rowSave(form: any, done: any, loading: any) {
    setTimeout(() => {
      done(form)
    }, 500)

    const res = await this.http.post(
      '/pri/job',
      {
        jobClassName: form.jobClassName,
        cronExpression: form.cronExpression,
        jobGroupName: form.jobGroup,
      },
      {
        prefix: 'task',
      }
    )
    this.onLoad()
  }

  async rowUpdate(form: any, index: any, done: any, loading: any) {
    setTimeout(() => {
      done(form)
      this.onLoad()
    }, 500)
    const res = await this.http.put('pri/qrtzJobDetails/' + form.id, form, {
      prefix: 'admin',
    })
  }

  async rowDel(form: any, index: any) {
    await this.http.delete(
      'pri/job?jobClassName=' +
        form.jobClassName +
        '&jobGroupName=' +
        form.jobGroup,
      { prefix: 'task' }
    )
    this.onLoad()
  }

  async onLoad() {
    this.loading = true

    const { data } = await this.http.post(
      'pri/job/findPage',
      {
        pageNum: this.page.currentPage,
        pageSize: this.page.pageSize,
        params: {},
      },
      { prefix: 'task' }
    )
    this.page.total = data.data.totalSize

    setTimeout(() => {
      this.loading = false
      this.tableData = data.data.content
    }, 500)
  }
}
</script>
<style lang="scss" scoped></style>
