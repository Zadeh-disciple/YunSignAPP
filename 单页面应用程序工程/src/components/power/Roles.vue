<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-row>
        <el-col>
          <el-button type="primary" @click="dialogVisible = true">添加角色</el-button>
        </el-col>
      </el-row>

      <el-table :data="rolelist" border stripe>
        <el-table-column type="expand">
          <template v-slot="scope">
            <el-row :class="['bd-bottom','vcenter',index1==0?'bd-top':'']" v-for="(item1,index1) in scope.row.children" :key="item1.id">
              <!-- 一级权限 -->
              <el-col :span="5">
                <el-tag closable @close="removeRightById(scope.row,item1.id)" >{{item1.authName}}</el-tag>
                <i class="el-icon-caret-right"></i>
              </el-col>
              <!-- 二级和三级权限 -->
              <el-col :span="19">
                <el-row :class="[index2==0?'':'bd-top','vcenter']" v-for="(item2,index2) in item1.children" :key="item2.id">
                  <el-col :span="6">
                    <el-tag closable @close="removeRightById(scope.row,item2.id)" type="success">{{item2.authName}}</el-tag>
                    <i class="el-icon-caret-right"></i>
                  </el-col>
                  <el-col :span="18">
                    <el-tag closable @close="removeRightById(scope.row,item3.id)" type="warning" v-for="(item3) in item2.children" :key="item3.id">{{item3.authName}}</el-tag>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </template>
        </el-table-column>
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="rolename" label="角色名称"></el-table-column>
        <el-table-column prop="roledetail" label="角色描述"></el-table-column>
        <el-table-column label="操作" width="450px">
          <template v-slot="scope">
            <el-button type="primary" icon="el-icon-edit" @click="showEditDialog(scope.row)">编辑</el-button>
            <el-button type="danger" icon="el-icon-delete" @click="deleteUser(scope.row)">删除</el-button>
            <el-button type="warning" icon="el-icon-setting" @click="showSetRightDialog(scope.row)">分配权限</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 添加用户对话框 -->
    <el-dialog
      title="添加角色"
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
        <el-form-item label="角色名称" prop="rolename">
          <el-input v-model="addUserForm.rolename"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roledetail">
          <el-input v-model="addUserForm.roledetail"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改用户对话框 -->
    <el-dialog
      title="修改角色"
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
        <el-form-item label="角色名称" prop="rolename">
          <el-input v-model="editUserForm.rolename"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roledetail">
          <el-input v-model="editUserForm.roledetail"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 分配权限对话框 -->
    <el-dialog
      title="分配权限"
      :visible.sync="setRightDialogVisible"
      @close="setRightDialogClosed"
      width="50%"
      >
      <el-tree
        :data="rightslist"
        node-key="id"
        default-expand-all
        show-checkbox>
      </el-tree>
      <div slot="footer">
        <el-button @click="setRightDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="allotRights">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      rolelist: [],
      dialogVisible: false,
      editDialogVisible: false,
      editUserForm: {},
      editUserFormRules: {
        name: [
          { required: true, message: '请输入角色名称', trigger: 'blur' },
          {
            require: true,
            min: 1,
            max: 10,
            message: '角色名称长度在 1 到 10 个字符',
            trigger: 'blur'
          }
        ],
        detail: [
          { required: true, message: '请输入角色描述', trigger: 'blur' },
          {
            require: true,
            min: 1,
            max: 10,
            message: '角色描述长度在 1 到 10 个字符',
            trigger: 'blur'
          }
        ]
      },
      addUserForm: {
        id: 0,
        name: '',
        detail: ''
      },
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
      setRightDialogVisible: false,
      rightslist: [
        {
          id: '1',
          label: '用户管理',
          children: [{
            id: '101',
            label: '用户列表'
          }]
        },
        {
          id: '2',
          label: '权限管理',
          children: [{
            id: '201',
            label: '角色列表'
          },
          {
            id: '202',
            label: '权限列表'
          }]
        },
        {
          id: '3',
          label: '课程管理',
          children: [{
            id: '301',
            label: '院校列表'
          },
          {
            id: '302',
            label: '课程列表'
          }]
        },
        {
          id: '4',
          label: '菜单管理',
          children: [{
            id: '401',
            label: '菜单列表'
          }]
        },
        {
          id: '5',
          label: '系统管理',
          children: [{
            id: '501',
            label: '数据字典'
          },
          {
            id: '502',
            label: '参数设置'
          }]
        }
      ],
      treeProps: {
        children: 'children',
        label: 'authName'
      },
      defKeys: [],
      roleId: ''
    }
  },
  methods: {
    async getRoleList () {
      const { data } = await this.$http.get('role/findAll')
      // if (data.status !== 200) {
      //   return this.$message.error(data.msg)
      // }
      console.log(data)
      this.rolelist = data
    },
    addDialogClosed () {
      this.$refs.addUserFormRef.resetFields()
    },
    addUser () {
      this.$refs.addUserFormRef.validate(async valid => {
        if (!valid) return
        const data = await this.$http.post('role/addRole', this.addUserForm)
        if (data.status !== 200) {
          this.$message.error('添加角色失败！')
        }
        // this.addUserForm.id = this.addUserForm.id + 1
        // // service.addCourse(this.addUserForm)
        // const adddata = {
        //   name: this.addUserForm.name,
        //   value: this.addUserForm.value
        // }
        // this.userlist.push(adddata)
        this.$message.success('添加角色成功！')
        this.dialogVisible = false
        this.getRoleList()
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
        const data = await this.$http.post('role/updateRole', this.editUserForm)
        if (data.status !== 200) {
          this.$message.error('修改角色信息失败！')
        } else {
          this.$message.success('修改成功！')
        }
        this.editDialogVisible = false
        this.getRoleList()
      })
    },
    async deleteUser (id) {
      // this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
      //   confirmButtonText: '确定',
      //   cancelButtonText: '取消',
      //   type: 'warning'
      // })
      // async () => {
      console.log(id)
      const data = await this.$http.post(`role/deleteRole?roleid=${id.roleid}`)
      if (data.status !== 200) {
        this.$message.error('删除失败')
      } else {
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }
      console.log(data)
      this.getRoleList()
      // }
      // }
      // .catch(() => {
      //   this.$message({
      //     type: 'info',
      //     message: '已取消删除'
      //   })
      // })
    },
    removeRightById (role, rightId) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const { data } = await this.$http.delete(`roles/${role.id}/rights/${rightId}`)
        if (data.meta.status !== 200) {
          return this.$message.error(data.meta.msg)
        }
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
        role.children = data.data
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    async showSetRightDialog (role) {
      this.roleId = role.id
      const data = await this.$http.get('power/findAll')
      console.log(data)
      if (data.status !== 200) {
        return this.$message.error(data.meta.msg)
      }
      // this.rightslist = data.data
      this.getLeafKeys(role, this.defKeys)
      this.setRightDialogVisible = true
    },
    getLeafKeys (node, arr) {
      if (!node.children) {
        return arr.push(node.id)
      }
      node.children.forEach(item => this.getLeafKeys(item, arr))
    },
    setRightDialogClosed () {
      this.defKeys = []
    },
    async allotRights () {
      // const keys = [
      //   ...this.$refs.treeRef.getCheckedKeys(),
      //   ...this.$refs.treeRef.getHalfCheckedKeys()
      // ]
      // const idStr = keys.join(',')
      // const { data } = await this.$http.post(`roles/${this.roleId}/rights`, { rids: idStr })
      // if (data.meta.status !== 200) {
      //   return this.$message.error(data.meta.msg)
      // }
      this.$message.success('分配权限成功！')
      this.setRightDialogVisible = false
      this.getRoleList()
    }
  },
  created () {
    this.getRoleList()
  }
}
</script>

<style lang="less" scoped>
.el-tag {
  margin: 7px;
}

.bd-top {
  border-top: 1px solid #eee;
}

.bd-bottom {
  border-bottom: 1px solid #eee;
}

.vcenter {
  display: flex;
  align-items: center;
}
</style>
