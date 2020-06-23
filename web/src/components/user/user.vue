<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="7">
          <el-input
            placeholder="请输入用户名"
            clearable
            @clear="getUserList"
            v-model="queryInfo.query"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="searchUser"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="dialogVisible = true"
            >添加用户</el-button
          >
        </el-col>
      </el-row>
      <el-table :data="userlist" border stripe style="width: 100%">
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="username" label="姓名"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="telephone" label="电话"></el-table-column>
        <el-table-column prop="role.rolename" label="角色"></el-table-column>
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
            <!-- 分配角色 -->
            <el-tooltip
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

    <!-- 添加用户对话框 -->
    <el-dialog
      title="添加用户"
      :visible.sync="dialogVisible"
      width="50%"
      @close="addDialogClosed"
    >
      <el-form
        :model="addUserForm"
        :rules="addUserFormRules"
        ref="addUserFormRef"
        label-width="70px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addUserForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addUserForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addUserForm.email"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="telephone">
          <el-input v-model="addUserForm.telephone"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改用户对话框 -->
    <el-dialog
      title="修改用户"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <el-form
        :model="editUserForm"
        :rules="editUserFormRules"
        ref="editUserFormRef"
        label-width="70px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户名">
          <el-input v-model="editUserForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editUserForm.email"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="telephone">
          <el-input v-model="editUserForm.telephone"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 分配角色对话框 -->
    <el-dialog
      title="分配角色"
      :visible.sync="setRoleDialogVisible"
      @close="setRoleDialogClosed"
      width="50%"
    >
      <div>
        <p>当前的用户:{{ userInfo.username }}</p>
        <p>当前的角色:{{ role }}</p>
        <p>分配新角色:
          <el-select v-model="selectRoleId" placeholder="请选择新角色">
            <el-option
              v-for="item in rolesList"
              :key="item.roleid"
              :label="item.rolename"
              :value="item.roleid">
            </el-option>
          </el-select>
        </p>
      </div>
      <div slot="footer">
        <el-button @click="setRoleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleInfo"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
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
    var checkTelephone = (rule, value, cb) => {
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
        pagesize: 2
      },
      userlist: [],
      viruserlist: [
        { userid: '101', username: 'cai', password: 'cai', email: 'cai@163.com', telephone: '13859697981', role: { roleid: 1, roledetail: 'superadmin', rolename: '超级管理员' } },
        { userid: '102', username: 'wang', password: 'wang', email: 'wang@163.com', telephone: '13859697982', role: { roleid: 2, roledetail: 'teacher', rolename: '教师' } },
        { userid: '103', username: 'du', password: 'du', email: 'du@163.com', telephone: '13858697983', role: { roleid: 3, roledetail: 'assitant', rolename: '助教' } },
        { userid: '104', username: 'lin', password: 'lin', email: 'lin@163.com', telephone: '13859697984', role: { roleid: 4, roledetail: 'student', rolename: '学生' } }
      ],
      total: 0,
      dialogVisible: false,
      editDialogVisible: false,
      addUserForm: {
        username: '',
        password: '',
        email: '',
        telephone: '',
        role: {
          roleid: 1,
          rolename: ''
        }
      },
      setRoleDialogVisible: false,
      addUserFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          {
            require: true,
            min: 3,
            max: 10,
            message: '用户名长度在 3 到 10 个字符',
            trigger: 'blur'
          }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          {
            require: true,
            min: 6,
            max: 15,
            message: '密码长度在 6 到 15 个字符',
            trigger: 'blur'
          }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        telephone: [
          { required: true, message: '请输入手机', trigger: 'blur' },
          { validator: checkTelephone, trigger: 'blur' }
        ]
      },
      editUserForm: {},
      editUserFormRules: {
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        telephone: [
          { required: true, message: '请输入手机', trigger: 'blur' },
          { validator: checkTelephone, trigger: 'blur' }
        ]
      },
      userInfo: '',
      rolesList: [],
      selectRoleId: '',
      role: ''
    }
  },
  methods: {
    async getUserList () {
      const { data } = await this.$http.get('user/findAll', {
        params: this.queryInfo
      })
      console.log(data)
      this.userlist = data
      this.total = data.length
    },
    searchUser () {
      const newuserlist = []
      const list = this.userlist
      for (let i = 0; i < list.length; i++) {
        if (list[i].username === this.queryInfo.query) {
          newuserlist.push(list[i])
        }
      }
      this.userlist = newuserlist
      this.total = newuserlist.length
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
        const newuser = {
          username: this.addUserForm.username,
          password: this.addUserForm.password,
          email: this.addUserForm.email,
          telephone: this.addUserForm.telephone,
          role: {
            roleid: 1,
            rolename: ''
          }
        }
        const { data } = await this.$http.post('user/addUser', newuser)
        if (data.status !== 201) {
          this.$message.error('添加用户失败！')
        }
        this.$message.success('添加用户成功！')
        this.dialogVisible = false
        this.getUserList()
      })
    },
    async showEditDialog (id) {
      // const { data } = await this.$http.get(`users/${id}`)
      // if (data.meta.status !== 200) {
      //   this.$message.error('查询用户失败！')
      // }
      console.log(id)
      this.editUserForm = id
      this.editDialogVisible = true
    },
    editDialogClosed () {
      this.$refs.editUserFormRef.resetFields()
    },
    editUser () {
      this.$refs.editUserFormRef.validate(async valid => {
        if (!valid) return
        const { data } = await this.$http.post('user/updateUser', this.editUserForm)
        if (data.status !== 200) {
          this.$message.error('修改用户信息失败！')
        }
        this.$message.success('修改成功！')
        this.editDialogVisible = false
        this.getUserList()
      })
    },
    async deleteUser (id) {
      // this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
      //   confirmButtonText: '确定',
      //   cancelButtonText: '取消',
      //   type: 'warning'
      // })
      // async () => {
      const { data } = await this.$http.post(`user/deleteUser?userid=${id.userid}`)
      // if (data.status !== 200) {
      //   this.$message.error(data.msg)
      // } else {
      console.log(data)
      this.$message({
        type: 'success',
        message: '删除成功!'
      })
      this.getUserList()
      // }
      // }
      // .catch(() => {
      //   this.$message({
      //     type: 'info',
      //     message: '已取消删除'
      //   })
      // })
    },
    async setRole (userInfo) {
      const { data } = await this.$http.get('role/findAll')
      // if (data.status !== 200) {
      //   return this.$message.error(data.msg)
      // }
      // const list1 = []
      // for (let i = 0; i < data.length; i++) {
      //   list1[i] = data[i].rolename
      // }
      // console.log(userInfo)
      this.rolesList = data
      this.userInfo = userInfo
      this.role = userInfo.role.rolename
      console.log(this.userInfo)
      this.setRoleDialogVisible = true
    },
    async saveRoleInfo () {
      if (!this.selectRoleId) {
        return this.$message.error('请选择要分配的角色！')
      }
      console.log(this.selectRoleId)
      this.userInfo.role.roleid = this.selectRoleId
      const { data } = await this.$http.post('user/updateUser', this.userInfo)
      // if (data.status !== 200) {
      //   return this.$message.error(data.msg)
      // }
      console.log(data)
      this.$message.success('分配角色成功！')
      this.getUserList()
      this.setRoleDialogVisible = false
    },
    setRoleDialogClosed () {
      this.selectRoleId = ''
      this.userInfo = {}
    }
  },
  created () {
    const data = this.$http.get('api/parsejwt')
    console.log(data)
    this.getUserList()
  }
}
</script>

<style lang="less" scoped></style>
