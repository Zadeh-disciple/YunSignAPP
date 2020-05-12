<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>数据字典</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="7">
          <el-input
            placeholder="请输入内容"
            clearable
            @clear="getUserList"
            v-model="queryInfo.query"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getUserList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="dialogVisible = true"
            >添加数据</el-button
          >
        </el-col>
      </el-row>
      <el-table :data="userlist" border stripe style="margin-top: 15px; width: 100%">
        <!-- <el-table-column type="index"></el-table-column> -->
        <el-table-column prop="username" label="ID"></el-table-column>
        <el-table-column prop="email" label="名称"></el-table-column>
        <el-table-column prop="mobile" label="CODE"></el-table-column>
        <!-- <el-table-column prop="school" label="学校"></el-table-column>
        <el-table-column prop="school" label="院系">
          <template v-slot="scope">
            <el-switch
              v-model="scope.row.mg_state"
              @change="userStateChanged(scope.row)"
            ></el-switch>
          </template>
        </el-table-column> -->
        <el-table-column label="操作">
          <!-- 编辑用户 -->
          <template v-slot="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              @click="showEditDialog(scope.row)"
              size="mini"
              style="width: 25px;"
            ></el-button>
            <!-- 删除用户 -->
            <el-button
              type="danger"
              @click="showDialog(scope.row)"
              icon="
el-icon-folder-opened
"
              size="mini"
            ></el-button>
            <!-- 分配角色 -->
            <!-- <el-tooltip
              :enterable="false"
              effect="dark"
              content="分配角色"
              placement="top-start"
            >
              <el-button
                type="warning"
                icon="el-icon-setting"
                size="mini"
                @click="setRole(scope.row)"
              ></el-button>
            </el-tooltip> -->
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

    <!-- 添加用户对话框 -->
    <el-dialog
      title="添加数据"
      :visible.sync="dialogVisible"
      width="50%"
      @close="addDialogClosed"
    >
      <el-form
        :model="addUserForm"
        ref="addUserFormRef"
        label-width="70px"
        class="demo-ruleForm"
      >
        <el-form-item label="ID" prop="username">
          <el-input v-model="addUserForm.username"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="email">
          <el-input v-model="addUserForm.email"></el-input>
        </el-form-item>
        <el-form-item label="CODE" prop="mobile">
          <el-input v-model="addUserForm.mobile"></el-input>
        </el-form-item>
        <!-- <el-form-item label="学校" prop="school">
          <el-input v-model="addUserForm.school"></el-input>
        </el-form-item>
        <el-form-item label="院系" prop="apartment">
          <el-input v-model="addUserForm.apartment"></el-input>
        </el-form-item> -->
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改用户对话框 -->
    <el-dialog
      title="修改数据"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <el-form
        :model="editUserForm"
        ref="editUserFormRef"
        label-width="70px"
        class="demo-ruleForm"
      >
        <el-form-item label="ID">
          <el-input v-model="editUserForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="email">
          <el-input v-model="editUserForm.email"></el-input>
        </el-form-item>
        <el-form-item label="CODE" prop="mobile">
          <el-input v-model="editUserForm.mobile"></el-input>
        </el-form-item>
        <!-- <el-form-item label="学校" prop="school">
          <el-input v-model="editUserForm.school"></el-input>
        </el-form-item>
        <el-form-item label="院系" prop="apartment">
          <el-input v-model="editUserForm.apartment"></el-input>
        </el-form-item> -->
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改用户对话框 -->
    <el-dialog
      title="详情"
      :visible.sync="showDialogVisible"
      width="50%"
      @close="DialogClosed"
    >
      <el-form
        :model="showUserForm"
        ref="showUserFormRef"
        label-width="70px"
        class="demo-ruleForm"
      >
        <el-form-item label="ID">
          <el-input v-model="showUserForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="email">
          <el-input v-model="showUserForm.email" disabled></el-input>
        </el-form-item>
        <el-form-item label="CODE" prop="mobile">
          <el-input v-model="showUserForm.mobile" disabled></el-input>
        </el-form-item>
        <!-- <el-form-item label="学校" prop="school">
          <el-input v-model="showUserForm.school" disabled></el-input>
        </el-form-item>
        <el-form-item label="院系" prop="apartment">
          <el-input v-model="showUserForm.apartment" disabled></el-input>
        </el-form-item> -->
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="showDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import service from '../../../service.js'
export default {
  data () {
    // 自定义邮箱校验规则
    var checkEmail = (rule, value, cb) => {
      const regEail = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/
      if (regEail.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的邮箱'))
    }
    // 自定义手机号校验规则
    var checkMobile = (rule, value, cb) => {
      const regMobile = /^[1][3,4,5,7,8][0-9]{9}$/
      if (regMobile.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的手机号'))
    }
    return {
      queryInfo: {
        query: '',
        pagenum: 1,
        pagesize: 5
      },
      userlist: [],
      total: 0,
      dialogVisible: false,
      editDialogVisible: false,
      addUserForm: {
        id: 0,
        username: '',
        email: '',
        mobile: '',
        school: '',
        apartment: ''
      },
      showUserForm: {},
      showDialogVisible: false,
      setRoleDialogVisible: false,
      addUserFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          {
            require: true,
            min: 2,
            max: 10,
            message: '用户名长度在 2 到 10 个字符',
            trigger: 'blur'
          }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        mobile: [
          { required: true, message: '请输入手机', trigger: 'blur' },
          { validator: checkMobile, trigger: 'blur' }
        ],
        school: [
          { required: true, message: '请输入学校', trigger: 'blur' },
          {
            require: true,
            trigger: 'blur'
          }
        ],
        apartment: [
          { required: true, message: '请输入院系', trigger: 'blur' },
          {
            require: true,
            trigger: 'blur'
          }
        ]
      },
      editUserForm: {
        username: '',
        email: '',
        mobile: '',
        school: '',
        apartment: ''
      },
      editUserFormRules: {
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        mobile: [
          { required: true, message: '请输入手机', trigger: 'blur' },
          { validator: checkMobile, trigger: 'blur' }
        ]
      },
      userInfo: '',
      rolesList: [],
      selectRoleId: ''
    }
  },
  methods: {
    async getUserList () {
      // const { data } = await this.$http.get('users', {
      //   params: this.queryInfo
      // })
      const data = await service.getCourse()
      // console.log(data)
      this.userlist = data
      this.total = data.length
    },
    handleSizeChange (newSize) {
      this.queryInfo.pagesize = newSize
      this.getUserList()
    },
    handleCurrentChange (newPage) {
      this.queryInfo.pagenum = newPage
      this.getUserList()
    },
    async userStateChanged (userinfo) {
      const { data } = await this.$http.put(
        `/users/${userinfo.id}/state/${userinfo.mg_state}`
      )
      if (data.meta.status !== 200) {
        this.$message.error('获取信息失败！')
      }
    },
    addDialogClosed () {
      this.$refs.addUserFormRef.resetFields()
    },
    addUser () {
      this.$refs.addUserFormRef.validate(async valid => {
        if (!valid) return
        // const { data } = await this.$http.post('users', this.addUserForm)
        // if (data.meta.status !== 201) {
        //   this.$message.error('添加用户失败！')
        // }
        this.addUserForm.id = this.addUserForm.id + 1
        service.addCourse(this.addUserForm)
        this.$message.success('添加字典成功！')
        this.dialogVisible = false
        this.getUserList()
      })
    },
    async showEditDialog (id) {
      console.log(id)
      // const data = await service.getCourse()
      // const { data } = await this.$http.get(`users/${id}`)
      // if (data.meta.status !== 200) {
      //   this.$message.error('查询用户失败！')
      // }
      this.editUserForm.username = id.username
      this.editUserForm.email = id.email
      this.editUserForm.mobile = id.mobile
      this.editUserForm.school = id.school
      this.editUserForm.apartment = id.apartment
      // console.log(this.editUserForm)
      this.editDialogVisible = true
    },
    editDialogClosed () {
      this.$refs.editUserFormRef.resetFields()
    },
    editUser () {
      this.$refs.editUserFormRef.validate(async valid => {
        if (!valid) return
        // const { data } = await this.$http.put(`users/${this.editUserForm.id}`, {
        //   email: this.editUserForm.email,
        //   mobile: this.editUserForm.mobile
        // })
        // if (data.meta.status !== 200) {
        //   this.$message.error('修改用户信息失败！')
        // }
        // window.localStorage.clear('DATA_KEY')
        service.updateCourse(this.editUserForm)
        this.$message.success('修改成功！')
        this.editDialogVisible = false
        this.getUserList()
      })
    },
    async showDialog (id) {
      console.log(id)
      // const data = await service.getCourse()
      // const { data } = await this.$http.get(`users/${id}`)
      // if (data.meta.status !== 200) {
      //   this.$message.error('查询用户失败！')
      // }
      // this.showUserForm = data[0]
      // console.log(this.editUserForm)
      this.showUserForm.username = id.username
      this.showUserForm.email = id.email
      this.showUserForm.mobile = id.mobile
      this.showUserForm.school = id.school
      this.showUserForm.apartment = id.apartment
      this.showDialogVisible = true
    },
    DialogClosed () {
      this.$refs.showUserFormRef.resetFields()
    }
  },
  created () {
    this.getUserList()
  }
}
</script>

<style lang="less" scoped></style>
