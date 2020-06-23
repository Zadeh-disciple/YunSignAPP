<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>课程管理</el-breadcrumb-item>
      <el-breadcrumb-item>院校列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="primary" @click="dialogVisible = true"
            >添加院校</el-button>
        </el-col>
      </el-row>
      <el-tree
           :data="schoollist"
           show-checkbox
           node-key="id"
           :expand-on-click-node="false">
           <span class="custom-tree-node" slot-scope="{ node, data }">
             <span>{{ node.label }}</span>
             <span style="position: absolute; right: 0px;">
               <el-button
                 type="text"
                 size="mini"
                 @click="() => appendForm(data)">
                 Append
               </el-button>
               <el-button
                 type="text"
                 size="mini"
                 @click="() => remove(node, data)">
                 Delete
               </el-button>
             </span>
           </span>
         </el-tree>
    </el-card>
    <!-- 添加院校对话框 -->
    <el-dialog
      title="添加院校"
      :visible.sync="dialogVisible"
      width="50%"
      @close="addDialogClosed"
    >
      <el-form
        :model="addSchoolForm"
        :rules="addSchoolFormRules"
        ref="addSchoolFormRef"
        label-width="90px"
        class="demo-ruleForm"
      >
        <el-form-item label="学校名称" prop="schoolname">
          <el-input v-model="addSchoolForm.schoolname"></el-input>
        </el-form-item>
        <el-form-item label="学院名称" prop="xueyuanname">
          <el-input v-model="addSchoolForm.xueyuanname"></el-input>
        </el-form-item>
        <el-form-item label="专业名称" prop="zhuanyename">
          <el-input v-model="addSchoolForm.zhuanyename"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addSchool">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 修改院校对话框 -->
    <el-dialog
      title="修改院校"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <el-form
        :model="editSchoolForm"
        :rules="editSchoolFormRules"
        ref="editSchoolFormRef"
        label-width="90px"
        class="demo-ruleForm"
      >
        <el-form-item label="名称">
          <el-input v-model="editSchoolForm.name" ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="append">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
let id = 12
export default {
  data () {
    return {
      schoollist: [],
      dialogVisible: false,
      editDialogVisible: false,
      addSchoolForm: {
        schoolname: '',
        xueyuanname: '',
        zhuanyename: ''
      },
      editSchoolForm: {
        level: 1,
        name: ''
      },
      editSchoolFormRules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ]
      },
      addSchoolFormRules: {
        schoolname: [
          { required: true, message: '请输入学校名称', trigger: 'blur' },
          {
            require: true,
            min: 2,
            max: 10,
            message: '学校名长度在 2 到 10 个字符',
            trigger: 'blur'
          }
        ],
        xueyuanname: [
          { required: true, message: '请输入学院名称', trigger: 'blur' }
        ],
        zhuanyename: [
          { required: true, message: '请输入专业名称', trigger: 'blur' }
        ]
      },
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  methods: {
    handleNodeClick (data) {
      console.log(data)
    },
    async getSchoolList () {
      const { data } = await this.$http.get('school/findAll')
      // if (data.status !== 200) {
      //   return this.$message.error(data.msg)
      // }
      console.log(data)
      const list2 = []
      for (let i = 0; i < data.length; i++) {
        const obj = {
          id: '',
          label: '',
          children: [{
            id: '',
            label: '',
            children: [{
              id: '',
              label: ''
            }]
          }]
        }
        obj.id = data[i].schoolid
        obj.label = data[i].schoolname
        obj.children[0].id = data[i].xueyuan.xueyuanid
        obj.children[0].label = data[i].xueyuan.xueyuanname
        obj.children[0].children[0].id = data[i].zhuanye.zhuanyeid
        obj.children[0].children[0].label = data[i].zhuanye.zhuanyename
        list2.push(obj)
      }
      this.schoollist = list2
      console.log(this.schoollist)
      // this.schoollist = data
    },
    addDialogClosed () {
      this.$refs.addSchoolFormRef.resetFields()
    },
    addSchool () {
      this.$refs.addSchoolFormRef.validate(async valid => {
        if (!valid) return
        console.log(this.addSchoolForm)
        const newschool = {
          schoolid: 1,
          schoolname: '',
          xueyuan: {
            xueyuanid: 1,
            xueyuanname: ''
          },
          zhuanye: {
            zhuanyeid: 1,
            zhuanyename: ''
          }
        }
        newschool.schoolid = id++
        newschool.schoolname = this.addSchoolForm.schoolname
        newschool.xueyuan.xueyuanname = this.addSchoolForm.xueyuanname
        newschool.zhuanye.zhuanyename = this.zhuanyename
        const { data } = await this.$http.post('school/addSchool', newschool)
        console.log(data)
        if (data === 'addsuccess') {
          this.$message.success('添加院校成功！')
        }
        this.dialogVisible = false
        this.getSchoolList()
      })
    },
    editDialogClosed () {
      this.$refs.editSchoolFormRef.resetFields()
    },
    appendForm (data) {
      this.editSchoolForm.level = data.level
      this.editDialogVisible = true
    },
    append (data) {
      const newChild = { id: id++, label: 'testtest', children: [] }
      if (!data.children) {
        this.$set(data, 'children', [])
      }
      data.children.push(newChild)
    },
    async remove (node, data) {
      console.log(node)
      console.log(data)
      if (node.level === 1) {
        const response = await this.$http.delete(`school/deleteSchool?schoolid=${data.id}`)
        id = id - 1
        console.log(response)
        if (response.data === 'deletesuccess') {
          this.$message.success('删除院校成功！')
        }
      }
      this.getSchoolList()
    }
  },
  created () {
    const data = this.$http.get('api/parsejwt')
    console.log(data)
    this.getSchoolList()
  }
}
</script>

<style>
</style>
