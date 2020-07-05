<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>课程管理</el-breadcrumb-item>
      <el-breadcrumb-item>课程列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="7">
          <el-input
            placeholder="请输入课程名称"
            clearable
            @clear="getCourseList"
            v-model="queryInfo.query"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="searchcourse"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="dialogVisible = true"
            >添加课程</el-button>
        </el-col>
      </el-row>
      <el-table :data="courselist" border stripe style="width: 100%">
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="coursename" label="课程名称"></el-table-column>
        <el-table-column prop="courseinfo" label="课程描述"></el-table-column>
        <el-table-column prop="classid" label="班级"></el-table-column>
        <el-table-column prop="semester" label="学期"></el-table-column>
        <el-table-column prop="school.schoolname" label="所属院校">
          <template v-slot="scope">
            {{scope.row.school.schoolname}}/{{scope.row.school.xueyuan.xueyuanname}}/{{scope.row.school.zhuanye.zhuanyename}}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <!-- 编辑用户 -->
          <template v-slot="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row)"
            ></el-button>
            <!-- 删除用户 -->
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="deleteUser(scope.row)"
            ></el-button>
            <!-- 分配院校 -->
            <el-tooltip
              :enterable="false"
              effect="dark"
              content="分配院校"
              placement="top-start"
            >
              <el-button
                type="warning"
                icon="el-icon-setting"
                size="mini"
                @click="setSchool(scope.row)"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </el-card>

    <!-- 添加课程对话框 -->
    <el-dialog
      title="添加课程"
      :visible.sync="dialogVisible"
      width="50%"
      @close="addDialogClosed"
    >
      <el-form
        :model="addCourseForm"
        :rules="addCourseFormRules"
        ref="addCourseFormRef"
        label-width="90px"
        class="demo-ruleForm"
      >
        <el-form-item label="课程名称" prop="coursename">
          <el-input v-model="addCourseForm.coursename"></el-input>
        </el-form-item>
        <el-form-item label="课程描述" prop="courseinfo">
          <el-input v-model="addCourseForm.courseinfo"></el-input>
        </el-form-item>
        <el-form-item label="班级" prop="classid">
          <el-input v-model="addCourseForm.classid"></el-input>
        </el-form-item>
        <el-form-item label="学期" prop="semester">
          <el-input v-model="addCourseForm.semester"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCourse">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改课程对话框 -->
    <el-dialog
      title="修改课程"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <el-form
        :model="editCourseForm"
        :rules="editCourseFormRules"
        ref="editCourseFormRef"
        label-width="90px"
        class="demo-ruleForm"
      >
        <el-form-item label="课程名称">
          <el-input v-model="editCourseForm.coursename" disabled></el-input>
        </el-form-item>
        <el-form-item label="课程描述" prop="courseinfo">
          <el-input v-model="editCourseForm.courseinfo"></el-input>
        </el-form-item>
        <el-form-item label="班级" prop="classid">
          <el-input v-model="editCourseForm.classid"></el-input>
        </el-form-item>
        <el-form-item label="学期" prop="semester">
          <el-input v-model="editCourseForm.semester"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editCourse">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 分配院校对话框 -->
    <el-dialog
      title="分配院校"
      :visible.sync="setSchoolDialogVisible"
      @close="setSchoolDialogClosed"
      width="50%"
    >
      <div>
        <p>当前的课程:{{ courseInfo.coursename }}</p>
        <p>当前的院校:{{ school }}</p>
        <p>分配新院校:
          <!-- <el-select v-model="selectSchoolId" placeholder="请选择新院校">
            <el-option
              v-for="item in rolesList"
              :key="item.roleid"
              :label="item.rolename"
              :value="item.roleid">
            </el-option>
          </el-select> -->
          <el-cascader
              v-model="selectSchoolId"
              :options="schoolList"
              @change="handleChange"></el-cascader>
        </p>
      </div>
      <div slot="footer">
        <el-button @click="setSchoolDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveSchoolInfo"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      queryInfo: {
        query: '',
        pagenum: 1,
        pagesize: 2
      },
      courselist: [],
      // school: {
      //   schoolid: 2,
      //   schoolname: '福建工程学院',
      //   schoolxueyuan: [{
      //     id: 5,
      //     name: '电气工程及其自动化学院',
      //     schoolzhuanye: [{
      //       id: 8,
      //       name: '自动化技术'
      //     }]
      //   },
      //   {
      //     id: 6,
      //     name: '材料科学与工程学院',
      //     schoolzhuanye: [{
      //       id: 9
      //       name: '材料科学'
      //     }]
      //   }]
      // }
      total: 0,
      dialogVisible: false,
      editDialogVisible: false,
      addCourseForm: {
        coursename: '',
        courseinfo: '',
        classid: '',
        semester: '',
        school: {
          schoolid: 1
        }
      },
      setSchoolDialogVisible: false,
      addCourseFormRules: {
        coursename: [
          { required: true, message: '请输入课程名称', trigger: 'blur' },
          {
            require: true,
            min: 2,
            max: 10,
            message: '课程名长度在 2 到 10 个字符',
            trigger: 'blur'
          }
        ],
        courseinfo: [
          { required: true, message: '请输入课程描述', trigger: 'blur' }
        ],
        classid: [
          { required: true, message: '请输入班级', trigger: 'blur' }
        ],
        semester: [
          { required: true, message: '请输入学期', trigger: 'blur' }
        ]
      },
      editCourseForm: {},
      editCourseFormRules: {
        courseinfo: [
          { required: true, message: '请输入课程描述', trigger: 'blur' }
        ],
        classid: [
          { required: true, message: '请输入班级', trigger: 'blur' }
        ],
        semester: [
          { required: true, message: '请输入学期', trigger: 'blur' }
        ]
      },
      courseInfo: '',
      schoolList: [],
      selectSchoolId: [],
      school: ''
    }
  },
  methods: {
    async getCourseList () {
      const { data } = await this.$http.get('course/findAll', {
        params: this.queryInfo
      })
      console.log(data)
      this.courselist = data
      this.total = data.length
    },
    searchcourse () {
      const newcourselist = []
      const list = this.courselist
      for (let i = 0; i < list.length; i++) {
        if (list[i].coursename === this.queryInfo.query) {
          newcourselist.push(list[i])
        }
      }
      this.courselist = newcourselist
      this.total = newcourselist.length
    },
    handleSizeChange (newSize) {
      this.queryInfo.pagesize = newSize
      this.getCourseList()
    },
    handleCurrentChange (newPage) {
      this.queryInfo.pagenum = newPage
      this.getCourseList()
    },
    addDialogClosed () {
      this.$refs.addCourseFormRef.resetFields()
    },
    addCourse () {
      this.$refs.addCourseFormRef.validate(async valid => {
        if (!valid) return
        // const newuser = {
        //   username: this.addCourseForm.username,
        //   password: this.addCourseForm.password,
        //   email: this.addCourseForm.email,
        //   telephone: this.addCourseForm.telephone,
        //   role: {
        //     roleid: 1,
        //     rolename: ''
        //   }
        // }
        const { data1 } = await this.$http.get('api/parsejwt', {
        })
        console.log(data1.message.success)
        if (data1.message.success !== true) {
          this.$message.error('登录过期，请重新登录！')
          window.sessionStorage.clear()
          this.$router.push('/login')
        }
        const data = await this.$http.post('course/addCourse', this.addCourseForm)
        console.log(data)
        if (data.status !== 200) {
          this.$message.error('添加课程失败！')
        } else {
          this.$message.success('添加课程成功！')
        }
        this.dialogVisible = false
        this.getCourseList()
      })
    },
    async showEditDialog (id) {
      // const { data } = await this.$http.get(`users/${id}`)
      // if (data.meta.status !== 200) {
      //   this.$message.error('查询用户失败！')
      // }
      console.log(id)
      this.editCourseForm = id
      this.editDialogVisible = true
    },
    editDialogClosed () {
      this.$refs.editCourseFormRef.resetFields()
    },
    editCourse () {
      this.$refs.editCourseFormRef.validate(async valid => {
        if (!valid) return
        const data = await this.$http.post('course/updateCourse', this.editCourseForm)
        console.log(data)
        if (data.status !== 200) {
          this.$message.error('修改课程信息失败！')
        } else {
          this.$message.success('修改课程信息成功！')
        }
        this.editDialogVisible = false
        this.getCourseList()
      })
    },
    async deleteUser (id) {
      // this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
      //   confirmButtonText: '确定',
      //   cancelButtonText: '取消',
      //   type: 'warning'
      // })
      // async () => {
      const data = await this.$http.post(`course/deleteCourse?courseid=${id.courseid}`)
      if (data.status !== 200) {
        this.$message.error('删除失败！')
      } else {
        this.$message.success('删除成功！')
      }
      console.log(data)
      this.getCourseList()
      // }
      // }
      // .catch(() => {
      //   this.$message({
      //     type: 'info',
      //     message: '已取消删除'
      //   })
      // })
    },
    async setSchool (courseInfo) {
      const { data } = await this.$http.get('school/findAll')
      // if (data.status !== 200) {
      //   return this.$message.error(data.msg)
      // }
      // const list1 = []
      const list2 = []
      // const obj = {
      //   value: '',
      //   label: '',
      //   children: [{
      //     value: '',
      //     label: '',
      //     children: [{
      //       value: '',
      //       label: ''
      //     }]
      //   }]
      // }
      for (let i = 0; i < data.length; i++) {
        const obj = {
          value: '',
          label: '',
          children: [{
            value: '',
            label: '',
            children: [{
              value: '',
              label: ''
            }]
          }]
        }
        obj.value = data[i].schoolid
        obj.label = data[i].schoolname
        obj.children[0].value = data[i].xueyuan.xueyuanid
        obj.children[0].label = data[i].xueyuan.xueyuanname
        obj.children[0].children[0].value = data[i].zhuanye.zhuanyeid
        obj.children[0].children[0].label = data[i].zhuanye.zhuanyename
        list2.push(obj)
      }
      // console.log(userInfo)
      this.schoolList = list2
      console.log(this.schoolList)
      this.courseInfo = courseInfo
      this.school = this.courseInfo.school.schoolname + '/' + this.courseInfo.school.xueyuan.xueyuanname + '/' + this.courseInfo.school.zhuanye.zhuanyename
      // console.log(this.courseInfo)
      this.setSchoolDialogVisible = true
    },
    async saveSchoolInfo () {
      if (!this.selectSchoolId) {
        return this.$message.error('请选择要分配的院校！')
      }
      console.log(this.selectSchoolId)
      console.log(this.courseInfo)
      this.courseInfo.school.schoolid = this.selectSchoolId[0]
      this.courseInfo.school.xueyuan.xueyuanid = this.selectSchoolId[1]
      this.courseInfo.school.zhuanye.zhuanyeid = this.selectSchoolId[2]
      console.log(this.courseInfo)
      const { data } = await this.$http.post('course/updateCourse', this.courseInfo)
      // if (data.status !== 200) {
      //   return this.$message.error(data.msg)
      // }
      console.log(data)
      this.$message.success('分配院校成功！')
      this.getCourseList()
      this.setSchoolDialogVisible = false
    },
    setSchoolDialogClosed () {
      this.selectSchoolId = []
      this.courseInfo = {}
    },
    handleChange (selectSchoolId) {
      console.log(selectSchoolId)
    }
  },
  created () {
    const data = this.$http.get('api/parsejwt')
    console.log(data)
    this.getCourseList()
  }
}
</script>

<style lang="less" scoped></style>
