<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>院校管理</el-breadcrumb-item>
      <el-breadcrumb-item>院校列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="4">
          <!-- <el-button type="primary" @click="dialogVisible = true"
            >添加数据</el-button> -->
          <div style="border-left-style: solid; border-left: #0086B3;">院校列表</div>
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
        label: '福州大学',
        children: [{
          label: '数学与计算机科学学院',
          children: [{
            label: '计算机科学与技术'
          }]
        }]
      }, {
        label: '福建工程学院',
        children: [{
          label: '电气工程与自动化学院',
          children: [{
            label: '自动化技术'
          }]
        }, {
          label: '材料科学与工程学院',
          children: [{
            label: '材料科学'
          }]
        }]
      }, {
        label: '福建师范大学',
        children: [{
          label: '音乐学院',
          children: [{
            label: '古典音乐学'
          }]
        }, {
          label: '计算机科学学院',
          children: [{
            label: '计算机技术'
          }]
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
