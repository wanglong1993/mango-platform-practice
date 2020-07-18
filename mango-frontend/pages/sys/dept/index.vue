<template>
  <div>
    <el-container>
      <el-aside width="200px" style="background-color:white;">
        <div class="d-flex ai-center jc-between">
          <el-breadcrumb class="py-3 px-2" separator="/">
            <el-breadcrumb-item>组织机构</el-breadcrumb-item>
          </el-breadcrumb>
          <div class="pr-2">
            <i class="el-icon-phone"></i>
            <i class="el-icon-user"></i>
            <i class="el-icon-delete"></i>
          </div>
        </div>
        <el-tree class="px-2" :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
      </el-aside>
      <el-container>
        <el-main>
          <div class="px-3 py-2">
            <el-breadcrumb class="py-2" separator-class="el-icon-arrow-right">
              <el-breadcrumb-item>机构管理</el-breadcrumb-item>
            </el-breadcrumb>
            <el-table height="550" border :data="tableData" style="width: 100%;">
              <el-table-column fixed fit label="创建时间">
                <template slot-scope="scope">
                  <i class="el-icon-time"></i>
                  <span style="margin-left: 10px;">{{ scope.row.createTime }}</span>
                </template>
              </el-table-column>
              <el-table-column label="创建人">
                <template slot-scope="scope">
                  <i class="el-icon-time"></i>
                  <span style="margin-left: 10px;">{{ scope.row.createBy }}</span>
                </template>
              </el-table-column>
              <el-table-column label="最后修改时间">
                <template slot-scope="scope">
                  <i class="el-icon-time"></i>
                  <span style="margin-left: 10px;">{{ scope.row.lastUpdateTime }}</span>
                </template>
              </el-table-column>
              <el-table-column label="修改人">
                <template slot-scope="scope">
                  <i class="el-icon-time"></i>
                  <span style="margin-left: 10px;">{{ scope.row.lastUpdateBy }}</span>
                </template>
              </el-table-column>
              <el-table-column label="姓名" width="100">
                <template slot-scope="scope">
                  <el-popover trigger="hover" placement="top">
                    <p>姓名: {{ scope.row.name }}</p>
                    <p>邮箱: {{ scope.row.email }}</p>
                    <div slot="reference" class="name-wrapper">
                      <el-tag size="medium">{{ scope.row.name }}</el-tag>
                    </div>
                  </el-popover>
                </template>
              </el-table-column>
              <el-table-column label="别名" width="100">
                <template slot-scope="scope">
                  <i class="el-icon-user"></i>
                  <span style="margin-left: 10px;">{{ scope.row.nickName }}</span>
                </template>
              </el-table-column>
              <el-table-column label="手机号" width="100">
                <template slot-scope="scope">
                  <i class="el-icon-phone"></i>
                  <span style="margin-left: 10px;">{{ scope.row.mobile }}</span>
                </template>
              </el-table-column>
              <el-table-column fixed="right" label="操作">
                <template slot-scope="scope">
                  <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)"
                  >删除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination class="pt-3" background layout="prev, pager, next" :total="1000"></el-pagination>
          </div>
        </el-main>
      </el-container>
    </el-container>

    <el-dialog title="修改用户" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动区域" :label-width="formLabelWidth">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'
@Component({
  components: {},
})
export default class sysUser extends Vue {
  asyncData({ store }: any) {
    return {}
  }

  form = {
    name: '',
    region: '',
    date1: '',
    date2: '',
    delivery: false,
    type: [],
    resource: '',
    desc: '',
  }
  dialogFormVisible = false

  formLabelWidth = '120px'

  data = [
    {
      label: '一级 1',
      children: [
        {
          label: '二级 1-1',
          children: [
            {
              label: '三级 1-1-1',
            },
          ],
        },
      ],
    },
    {
      label: '一级 2',
      children: [
        {
          label: '二级 2-1',
          children: [
            {
              label: '三级 2-1-1',
            },
          ],
        },
        {
          label: '二级 2-2',
          children: [
            {
              label: '三级 2-2-1',
            },
          ],
        },
      ],
    },
    {
      label: '一级 3',
      children: [
        {
          label: '二级 3-1',
          children: [
            {
              label: '三级 3-1-1',
            },
          ],
        },
        {
          label: '二级 3-2',
          children: [
            {
              label: '三级 3-2-1',
            },
          ],
        },
      ],
    },
  ]

  handleNodeClick(data: any) {
    console.log(data)
  }
  defaultProps = {
    children: 'children',
    label: 'label',
  }

  tableData: any = []

  handleEdit(index: any, row: any) {
    this.dialogFormVisible = true
    console.log(index, row.id)
  }

  handleDelete(index: any, row: any) {
    console.log(index, row)
  }
  mounted() {
    this.fetchUserList()
  }

  async fetchUserList() {
    const res = await this.$http.post(
      'pri/user/findPage',
      {
        pageNum: 1,
        pageSize: 10,
        params: {},
      },
      { prefix: 'admin' }
    )
    this.tableData = res.data.data.content
  }
}
</script>
<style lang="scss"></style>
