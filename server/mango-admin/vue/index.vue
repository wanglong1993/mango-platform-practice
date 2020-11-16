<template>
    <div class="bg-white h-100">
        <el-container class="pt-3 px-3">
            <el-main>
                <div class="px-3 py-2">
                    <el-breadcrumb class="py-2" separator-class="el-icon-arrow-right">
                        <el-breadcrumb-item>字典数据表</el-breadcrumb-item>
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
                    </avue-crud>
                </div>
            </el-main>
        </el-container>
    </div>
</template>
<script lang='ts'>
    import { Vue, Component } from 'nuxt-property-decorator'
    @Component({
        components: {},
    })
    export default class index extends Vue {
        loading = true
        tableData = []
        form = {}
        http = Vue.prototype.$http
                page: any = {
            total: 10,
            pagerCount: 5,
            currentPage: 1,
            pageSize: 10,
            pageSizes: [5, 10, 20],
            layout: 'total, sizes,prev, pager, next, jumper',
            background: false,
        }


        async checkAuth() {
            this.permission.delBtn = await this.$store.dispatch(
              'checkAuth',
               'sys:',${pathName},':edit'
             )
            this.permission.addBtn = await this.$store.dispatch(
                'checkAuth',
                'sys:',${pathName},':add'
            )
                    this.permission.menu = await this.$store.dispatch(
                'checkAuth',
                'sys:',${pathName},':edit'
            )
        }

        permission = {
            delBtn: true,
            addBtn: true,
            menu: true,
        }

        mounted() {
            this.checkAuth()
        }

        option = {
            height: '450',
            // sortable: true,
            // selection: true,
            menuWidth: 210,
            index: true,
            size: 'mini',
            dialogDrag: true,
            column: [
            {
                label: '字典编码',
                prop: 'dictCode'
            },
            {
                label: '父级编号',
                prop: 'parentCode'
            },
            {
                label: '所有父级编号',
                prop: 'parentCodes'
            },
            {
                label: '本级排序号（升序）',
                prop: 'treeSort'
            },
            {
                label: '所有级别排序号',
                prop: 'treeSorts'
            },
            {
                label: '是否最末级',
                prop: 'treeLeaf'
            },
            {
                label: '层次级别',
                prop: 'treeLevel'
            },
            {
                label: '全节点名',
                prop: 'treeNames'
            },
            {
                label: '字典标签',
                prop: 'dictLabel'
            },
            {
                label: '字典键值',
                prop: 'dictValue'
            },
            {
                label: '字典类型',
                prop: 'dictType'
            },
            {
                label: '系统内置（1是 0否）',
                prop: 'isSys'
            },
            {
                label: '字典描述',
                prop: 'description'
            },
            {
                label: 'css样式（如：color:red)',
                prop: 'cssStyle'
            },
            {
                label: 'css类名（如：red）',
                prop: 'cssClass'
            },
            {
                label: '状态（0正常 1删除 2停用）',
                prop: 'status'
            },
            {
                label: '创建者',
                prop: 'createBy'
            },
            {
                label: '创建时间',
                prop: 'createDate'
            },
            {
                label: '更新者',
                prop: 'updateBy'
            },
            {
                label: '更新时间',
                prop: 'updateDate'
            },
            {
                label: '备注信息',
                prop: 'remarks'
            },
            {
                label: '租户代码',
                prop: 'corpCode'
            },
            {
                label: '租户名称',
                prop: 'corpName'
            },
            {
                label: '扩展 String 1',
                prop: 'extendS1'
            },
            {
                label: '扩展 String 2',
                prop: 'extendS2'
            },
            {
                label: '扩展 String 3',
                prop: 'extendS3'
            },
            {
                label: '扩展 String 4',
                prop: 'extendS4'
            },
            {
                label: '扩展 String 5',
                prop: 'extendS5'
            },
            {
                label: '扩展 String 6',
                prop: 'extendS6'
            },
            {
                label: '扩展 String 7',
                prop: 'extendS7'
            },
            {
                label: '扩展 String 8',
                prop: 'extendS8'
            },
            {
                label: '扩展 Integer 1',
                prop: 'extendI1'
            },
            {
                label: '扩展 Integer 2',
                prop: 'extendI2'
            },
            {
                label: '扩展 Integer 3',
                prop: 'extendI3'
            },
            {
                label: '扩展 Integer 4',
                prop: 'extendI4'
            },
            {
                label: '扩展 Float 1',
                prop: 'extendF1'
            },
            {
                label: '扩展 Float 2',
                prop: 'extendF2'
            },
            {
                label: '扩展 Float 3',
                prop: 'extendF3'
            },
            {
                label: '扩展 Float 4',
                prop: 'extendF4'
            },
            {
                label: '扩展 Date 1',
                prop: 'extendD1'
            },
            {
                label: '扩展 Date 2',
                prop: 'extendD2'
            },
            {
                label: '扩展 Date 3',
                prop: 'extendD3'
            },
            {
                label: '扩展 Date 4',
                prop: 'extendD4'
            },
            ],
        }


        async rowSave(form: any, done: any, loading: any) {
            setTimeout(() => {
                done(form)
            }, 500)

            const res = await this.http.post('pri/sysDictData/save', form, { prefix: 'admin' })
        }


        async rowUpdate(form: any, index: any, done: any, loading: any) {
            setTimeout(() => {
                done(form)
                this.onLoad()
            }, 500)
            const res = await this.http.put('pri/sysDictData/'+form.id, form, { prefix: 'admin' })
        }


        async rowDel(form: any,index: any){
            await this.http.delete('pri/sysDictData/'+form.id, { prefix: 'admin' })
        }

        async onLoad() {
            this.loading = true

            const { data } = await this.http.post(
                    'pri/sysDictData/findPage',
                    {
                        pageNum: this.page.currentPage,
                        pageSize: this.page.pageSize,
                        params: {},
                    },
                    { prefix: 'admin' }
            )
            this.page.total = data.data.totalSize

            setTimeout(() => {
                this.loading = false
                this.tableData = data.data.content
            }, 500)

        }
    }
</script>
<style lang='scss' scoped>
</style>