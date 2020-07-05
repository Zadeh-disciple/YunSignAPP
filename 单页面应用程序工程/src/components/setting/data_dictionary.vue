<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>数据字典</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="20">
        <!-- <el-col :span="7">
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
        </el-col> -->
        <el-col :span="4">
          <el-button type="primary" @click="dialogVisible = true"
            >添加数据</el-button
          >
        </el-col>
      </el-row>
      <el-table :data="userlist" border stripe style="margin-top: 15px; width: 100%">
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="dictionaryname" label="CODE"></el-table-column>
        <el-table-column prop="dictionarydescribe" label="类型"></el-table-column>
        <el-table-column prop="dictionaryvalue" label="VALUE"></el-table-column>
        <el-table-column prop="dictionaryvaluename" label="VALUENAME"></el-table-column>
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
            >编辑</el-button>
            <!-- 删除用户 -->
            <el-button
              type="warning"
              @click="showDialog(scope.row)"
              icon="
el-icon-folder-opened
"
              size="mini"
            >详情</el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="deleteUser(scope.row)"
            >删除</el-button>
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
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="CODE" prop="dictionaryname">
          <el-input v-model="addUserForm.dictionaryname"></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="dictionarydescribe">
          <el-input v-model="addUserForm.dictionarydescribe"></el-input>
        </el-form-item>
        <el-form-item label="VALUE" prop="dictionaryvalue">
          <el-input v-model="addUserForm.dictionaryvalue"></el-input>
        </el-form-item>
        <el-form-item label="VALUENAME" prop="dictionaryvaluename">
          <el-input v-model="addUserForm.dictionaryvaluename"></el-input>
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
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="CODE" prop="dictionaryname">
          <el-input v-model="editUserForm.dictionaryname"></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="dictionarydescribe">
          <el-input v-model="editUserForm.dictionarydescribe"></el-input>
        </el-form-item>
        <el-form-item label="VALUE" prop="dictionaryvalue">
          <el-input v-model="editUserForm.dictionaryvalue"></el-input>
        </el-form-item>
        <el-form-item label="VALUENAME" prop="dictionaryvaluename">
          <el-input v-model="editUserForm.dictionaryvaluename"></el-input>
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
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="CODE" prop="dictionaryname">
          <el-input v-model="showUserForm.dictionaryname" disabled></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="dictionarydescribe">
          <el-input v-model="showUserForm.dictionarydescribe" disabled></el-input>
        </el-form-item>
        <el-form-item label="VALUE" prop="dictionaryvalue">
          <el-input v-model="showUserForm.dictionaryvalue" disabled></el-input>
        </el-form-item>
        <el-form-item label="VALUENAME" prop="dictionaryvaluename">
          <el-input v-model="showUserForm.dictionaryvaluename" disabled></el-input>
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
export default {
  data () {
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
        dictionaryname: '',
        dictionarydescribe: '',
        dictionaryvalue: '',
        dictionaryvaluename: ''
      },
      showUserForm: {},
      showDialogVisible: false,
      setRoleDialogVisible: false,
      addUserFormRules: {
        dictionaryname: [
          { required: true, message: '请输入CODE', trigger: 'blur' }
        ],
        dictionarydescribe: [
          { required: true, message: '请输入类型', trigger: 'blur' }
        ],
        dictionaryvalue: [
          { required: true, message: '请输入value', trigger: 'blur' }
        ],
        dictionaryvaluename: [
          { required: true, message: '请输入valuename', trigger: 'blur' }
        ]
      },
      editUserForm: {
        dictionaryid: 1,
        dictionaryname: '',
        dictionarydescribe: '',
        dictionaryvalue: '',
        dictionaryvaluename: ''
      },
      editUserFormRules: {
        dictionaryvalue: [
          { required: true, message: '请输入value', trigger: 'blur' }
        ],
        dictionaryvaluename: [
          { required: true, message: '请输入valuename', trigger: 'blur' }
        ]
      },
      userInfo: '',
      rolesList: [],
      selectRoleId: ''
    }
  },
  methods: {
    async getUserList () {
      const data = await this.$http.get('dictionary/findAll')
      console.log(data)
      // console.log(data)
      this.userlist = data.data
      this.total = data.data.length
    },
    handleSizeChange (newSize) {
      this.queryInfo.pagesize = newSize
      this.getUserList()
    },
    handleCurrentChange (newPage) {
      this.queryInfo.pagenum = newPage
      this.getUserList()
    },
    addDialogClosed () {
      this.$refs.addUserFormRef.resetFields()
    },
    addUser () {
      this.$refs.addUserFormRef.validate(async valid => {
        if (!valid) return
        const data = await this.$http.post('dictionary/addDictionary', this.addUserForm)
        if (data.status !== 200) {
          this.$message.error('添加字典失败！')
        } else {
          this.$message.success('添加字典成功！')
        }
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
      this.editUserForm.dictionaryid = id.dictionaryid
      this.editUserForm.dictionaryname = id.dictionaryname
      this.editUserForm.dictionarydescribe = id.dictionarydescribe
      this.editUserForm.dictionaryvalue = id.dictionaryvalue
      this.editUserForm.dictionaryvaluename = id.dictionaryvaluename
      // console.log(this.editUserForm)
      this.editDialogVisible = true
    },
    editDialogClosed () {
      this.$refs.editUserFormRef.resetFields()
    },
    editUser () {
      this.$refs.editUserFormRef.validate(async valid => {
        if (!valid) return
        const data = await this.$http.post('dictionary/updateDictionary', this.editUserForm)
        console.log(data)
        if (data.status !== 200) {
          this.$message.error('修改字典信息失败！')
        } else {
          this.$message.success('修改成功！')
        }
        // window.localStorage.clear('DATA_KEY')
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
      this.showUserForm.dictionaryname = id.dictionaryname
      this.showUserForm.dictionarydescribe = id.dictionarydescribe
      this.showUserForm.dictionaryvalue = id.dictionaryvalue
      this.showUserForm.dictionaryvaluename = id.dictionaryvaluename
      this.showDialogVisible = true
    },
    async deleteUser (id) {
      // this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
      //   confirmButtonText: '确定',
      //   cancelButtonText: '取消',
      //   type: 'warning'
      // })
      // async () => {
      const data = await this.$http.post(`dictionary/deleteDictionary?dictionaryid=${id.dictionaryid}`)
      if (data.status !== 200) {
        this.$message.error('删除失败')
      } else {
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }
      console.log(data)
      this.getUserList()
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
