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

        permission = {
            delBtn: true,
            addBtn: true,
            menu: true,
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
                label: 'ID',
                prop: 'id'
            },
            {
                label: '文件名',
                prop: 'name'
            },
            {
                label: '扩展名',
                prop: 'ext'
            },
            {
                label: '大小',
                prop: 'size'
            },
            {
                label: '路径',
                prop: 'path'
            },
            {
                label: '类型',
                prop: 'classification'
            },
            {
                label: '描述',
                prop: 'description'
            },
            {
                label: '原始链接',
                prop: 'url'
            },
            {
                label: '状态',
                prop: 'statu'
            },
            {
                label: '地图位置',
                prop: 'location'
            },
            {
                label: '封面图',
                prop: 'cover'
            },
            ],
        }


        async rowSave(form: any, done: any, loading: any) {
            setTimeout(() => {
                done(form)
            }, 500)

            const res = await this.http.post('pri/sysSoundfile/save', form, { prefix: 'admin' })
        }


        async rowUpdate(form: any, index: any, done: any, loading: any) {
            setTimeout(() => {
                done(form)
                this.onLoad()
            }, 500)
            const res = await this.http.put('pri/sysSoundfile/'+form.id, form, { prefix: 'admin' })
        }


        async rowDel(form: any,index: any){
            await this.http.delete('pri/sysSoundfile/'+form.id, { prefix: 'admin' })
        }

        async onLoad() {
            this.loading = true

            const { data } = await this.http.post(
                    'pri/sysSoundfile/findPage',
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