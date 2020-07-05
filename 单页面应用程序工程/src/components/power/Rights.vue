<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>权限列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-table :data="rightsList" border stripe style="width: 100%">
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="powername" label="权限名称"></el-table-column>
        <el-table-column prop="powerlujin" label="路径"></el-table-column>
        <el-table-column label="权限等级">
          <template v-slot="scope">
            <el-tag v-if="scope.row.powerlevel==='一级'">一级</el-tag>
            <el-tag v-if="scope.row.powerlevel==='二级'" type="success">二级</el-tag>
            <el-tag v-if="scope.row.powerlevel==='三级'" type="info">三级</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      rightsList: []
    }
  },
  methods: {
    async getRightsList () {
      const { data } = await this.$http.get('power/findAll')
      // if (data.meta.status !== 200) {
      //   return this.$message.error(data.meta.msg)
      // }
      this.rightsList = data
    }
  },
  created () {
    this.getRightsList()
  }
}
</script>

<style lang="less" scoped>
</style>
