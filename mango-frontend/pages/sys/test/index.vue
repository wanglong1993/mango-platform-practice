<template>
  <avue-form
    :option="option"
    v-model="form"
    :upload-preview="uploadPreview"
    :upload-error="uploadError"
    :upload-delete="uploadDelete"
    :upload-before="uploadBefore"
    :upload-after="uploadAfter"
  ></avue-form>
</template>
<script>
export default{
  data() {
    return {
      form: {
          video:'/i/movie.ogg',
          imgUrl:[
            { "label": "avue@226d5c1a_image.png", "value": "/images/logo-bg.jpg" },
            {"label": "avue@226d5c1a_video.png", "value": 'https://www.w3school.com.cn/i/movie.ogg'}
          ],
          imgUrl3:'/images/logo-bg.jpg',
          string:'/images/logo-bg.jpg,/images/logo-bg.jpg',
          img:['/images/logo-bg.jpg','/images/logo-bg.jpg']
      },
      option: {
        labelWidth: 120,
        column: [
          {
            label: '视频',
            prop: 'video',
            type: 'upload',
            propsHttp: {
              home: 'https://www.w3school.com.cn'
            },
            listType: 'picture-img',
            span: 24,
            tip: '只能上传jpg/png用户头像，且不超过500kb',
            action: '/imgupload'
          },
          {
            label: '附件上传',
            prop: 'imgUrl',
            type: 'upload',
            loadText: '附件上传中，请稍等',
            span: 24,
            propsHttp: {
              res: 'data'
            },
            tip: '只能上传jpg/png文件，且不超过500kb',
            action: '/imgupload'
          },
          {
            label: '水印头像',
            prop: 'imgUrl3',
            type: 'upload',
            listType: 'picture-img',
            span: 24,
            propsHttp: {
              res: 'data'
            },
            canvasOption: {
              text: 'avue',
              ratio: 0.1
            },
            tip: '只能上传jpg/png用户头像，且不超过500kb',
            action: '/imgupload'
          },
          {
            label: '照片墙',
            prop: 'imgUrl',
            type: 'upload',
            span: 24,
            listType: 'picture-card',
            tip: '只能上传jpg/png文件，且不超过500kb',
            propsHttp: {
              res: 'data'
            },
            action: '/imgupload'
          },
          {
            label: '数组图片组',
            prop: 'img',
            dataType: 'array',
            type: 'upload',
            propsHttp: {
              res: 'data.0'
            },
            span: 24,
            listType: 'picture-card',
            tip: '只能上传jpg/png文件，且不超过500kb',
            action: '/imgupload'
          },
          {
            label: '字符串图片组',
            prop: 'string',
            dataType: 'string',
            type: 'upload',
            propsHttp: {
              res: 'data'
            },
            span: 24,
            listType: 'picture-card',
            tip: '只能上传jpg/png文件，且不超过500kb',
            action: '/imgupload'
          },
          {
            label: '拖拽上传',
            prop: 'imgUrl',
            type: 'upload',
            span: 24,
            drag: true,
            propsHttp: {
              res: 'data'
            },
            tip: '只能上传jpg/png文件，且不超过500kb',
            action: '/imgupload'
          },
          {
            label: '缩略图上传',
            prop: 'imgUrl',
            type: 'upload',
            limit: 3,
            span: 24,
            propsHttp: {
              res: 'data'
            },
            listType: 'picture',
            tip: '只能上传jpg/png文件，且不超过500kb',
            action: '/imgupload'
          }
        ]
      }
    }
  },
  methods: {
    uploadDelete(column,file) {
      console.log(column,file)
      return this.$confirm(`这里是自定义的，是否确定移除该选项？`);
    },
    uploadBefore(file, done, loading,column) {
      console.log(file,column)
      //如果你想修改file文件,由于上传的file是只读文件，必须复制新的file才可以修改名字，完后赋值到done函数里,如果不修改的话直接写done()即可
      var newFile = new File([file], '1234', { type: file.type });
      done(newFile)
      this.$message.success('上传前的方法')
    },
    uploadError(error, column) {
      this.$message.success('上传失败')
      console.log(error, column)
    },
    uploadAfter(res, done, loading,column) {
      console.log(res,column)
      done()
      this.$message.success('上传后的方法')
    },
    uploadPreview(file,column,done){
      console.log(file,column)
      done()//默认执行打开方法
      this.$message.success('自定义查看方法,查看控制台')
    },
    submit() {
      this.$message.success('当前数据' + JSON.stringify(this.form))
    }
  }
}
</script>

