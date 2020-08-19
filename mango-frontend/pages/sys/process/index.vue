<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <!-- Aside content -->
      </el-aside>
      <el-container>
        <el-header height="">
          pdf转图片
        </el-header>
        <el-main>
          <el-upload
            class="avatar-uploader"
            action="http://localhost:9001/processing/api/sys/v1/pri/pdf2base64"
            :on-remove="handleRemove"
            :show-file-list="false"
            :on-success="handleSuccess"
            list-type="picture"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>

          <div v-if="imageUrlList.length != 0">
            <el-upload
              class="upload-demo"
              action="string"
              :file-list="imageUrlList"
              :on-preview="handlePreview"
              list-type="picture"
            >
            </el-upload>
          </div>

          <el-dialog title="详情" :visible.sync="showImage" width="70%">
            <el-image
              v-if="imageUrl"
              class="h-100"
              :src="imageUrl"
              fit="fit"
            ></el-image>
            <!-- <span slot="footer">
                  <el-button @click=" = false">取 消</el-button>
                  <el-button type="primary" @click="">确 定</el-button>
              </span> -->
          </el-dialog>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'nuxt-property-decorator'

@Component({})
export default class Name extends Vue {
  imageUrlList: any = []
  imageUrl = ''
  showImage = false

  handleRemove(file: File, fileList: Array<Object>) {
    console.log(file, fileList)
  }
  handlePreview(file: any) {
    this.showImage = true
    this.imageUrl = file.url
  }

  handleSuccess(response: any, file: any, fileList: Array<Object>) {
    console.log(file)
    file.response.map((e: any) => {
      this.imageUrlList.push({
        name: 'test' + new Date(),
        url: 'data:image/png;base64,' + e,
      })
    })

    // console.log(file, fileList)
  }
}
</script>

<style lang="scss">
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
