<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
      <el-breadcrumb-item>菜单列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="4">
          <!-- <el-button type="primary" @click="dialogVisible = true"
            >添加数据</el-button> -->
          <div style="border-left-style: solid; border-left: #0086B3;">菜单列表</div>
        </el-col>
      </el-row>
      <el-tree
        :data="data"
        :props="defaultProps"
        accordion
        @node-click="handleNodeClick"
        style="margin-top: 20px;">
      </el-tree>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      data: [{
        label: '用户管理',
        children: [{
          label: '用户列表'
        }]
      }, {
        label: '权限管理',
        children: [{
          label: '角色列表'
        }, {
          label: '权限列表'
        }]
      }, {
        label: '院校管理',
        children: [{
          label: '院校列表'
        }]
      },
      {
        label: '菜单管理',
        children: [{
          label: '菜单列表'
        }]
      },
      {
        label: '系统管理',
        children: [{
          label: '数据字典'
        },
        {
          label: '参数设置'
        }]
      }],
      addSchoolForm: {

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
    }
  }
}
</script>

<style>
</style>
