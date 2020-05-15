<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>参数设置</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="primary" @click="dialogVisible = true"
            >添加数据</el-button
          >
        </el-col>
      </el-row>
      <el-table :data="userlist" border stripe style="margin-top: 15px; width: 100%">
        <!-- <el-table-column type="index"></el-table-column> -->
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="name" label="名称"></el-table-column>
        <el-table-column prop="value" label="VALUE"></el-table-column>
        <el-table-column label="操作">
          <!-- 编辑用户 -->
          <template v-slot="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              @click="showEditDialog(scope.row)"
              size="mini"
            ></el-button>
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
        <el-form-item label="名称" prop="name">
          <el-input v-model="addUserForm.name"></el-input>
        </el-form-item>
        <el-form-item label="VALUE" prop="value">
          <el-input v-model="addUserForm.value"></el-input>
        </el-form-item>
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
        <!-- <el-form-item label="ID">
          <el-input v-model="editUserForm.username" disabled></el-input>
        </el-form-item> -->
        <el-form-item label="名称" prop="name">
          <el-input v-model="editUserForm.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="VALUE" prop="value">
          <el-input v-model="editUserForm.value"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUser">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
// import service from '../../../service.js'
export default {
  data () {
    return {
      queryInfo: {
        query: '',
        pagenum: 1,
        pagesize: 5
      },
      userlist: [],
      inited: false,
      total: 0,
      dialogVisible: false,
      editDialogVisible: false,
      addUserForm: {
        id: 0,
        name: '',
        value: ''
        // email: '',
        // mobile: '',
        // school: '',
        // apartment: ''
      },
      showUserForm: {},
      showDialogVisible: false,
      setRoleDialogVisible: false,
      addUserFormRules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          {
            require: true,
            min: 2,
            max: 10,
            message: '用户名长度在 2 到 10 个字符',
            trigger: 'blur'
          }
        ],
        value: [
          { required: true, message: '请输入value', trigger: 'blur' },
          {
            require: true,
            trigger: 'blur'
          }
        ]
      },
      editUserForm: {
        name: '',
        value: ''
      },
      editUserFormRules: {
        value: [
          { required: true, message: '请输入value', trigger: 'blur' },
          {
            require: true,
            trigger: 'blur'
          }
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
      // const data = await service.getCourse()
      if (!this.inited) {
        const data = [
          {
            name: '经验值',
            value: '2'
          }
        ]
        this.userlist = data
        this.inited = true
      }
      // const data = [
      //   {
      //     name: '经验值',
      //     value: '2'
      //   }
      // ]
      // // console.log(data)
      // this.userlist = data
      console.log(this.userlist)
      this.total = this.userlist.length
    },
    handleSizeChange (newSize) {
      this.queryInfo.pagesize = newSize
      this.getUserList()
    },
    handleCurrentChange (newPage) {
      this.queryInfo.pagenum = newPage
      this.getUserList()
    },
    // async userStateChanged (userinfo) {
    //   const { data } = await this.$http.put(
    //     `/users/${userinfo.id}/state/${userinfo.mg_state}`
    //   )
    //   if (data.meta.status !== 200) {
    //     this.$message.error('获取信息失败！')
    //   }
    // },
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
        // service.addCourse(this.addUserForm)
        const adddata = {
          name: this.addUserForm.name,
          value: this.addUserForm.value
        }
        this.userlist.push(adddata)
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
      this.editUserForm.name = id.name
      this.editUserForm.value = id.value
      // this.editUserForm.email = id.email
      // this.editUserForm.mobile = id.mobile
      // this.editUserForm.school = id.school
      // this.editUserForm.apartment = id.apartment
      console.log(this.editUserForm)
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
        // service.updateCourse(this.editUserForm)
        console.log(this.editUserForm.value)
        this.userlist[0].value = this.editUserForm.value
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
