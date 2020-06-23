<template>
  <el-container style="height:100vh">
    <el-header>
      <div>
        <img src="../assets/logo.png" alt />
        <span>Yunsign</span>
      </div>
      <div>
        <img src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80" />
        <!-- <el-button type="info" @click="logout">退出</el-button> -->
        <el-dropdown >
          <span class="el-dropdown-link">
            {{ username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="toggle-button" @click="toggleCollapse">|||</div>
        <el-menu
          router
          background-color="#333744"
          text-color="#fff"
          active-text-color="#409EFF"
          unique-opened
          :collapse="isCollapse"
          :collapse-transition="false"
          :default-active="$route.path"
        >
          <el-submenu :index="item.id+''" v-for="item in menulist" :key="item.id">
            <template slot="title">
              <i :class="iconObj[item.id]"></i>
              <span>{{item.authName}}</span>
            </template>
            <el-menu-item :index="'/'+subItem.path" v-for="subItem in item.children" :key="subItem.id">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>{{subItem.authName}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { MessageBox } from 'element-ui'
export default {
  data () {
    return {
      username: '',
      menulist: [
        {
          authName: '用户管理',
          children: [{
            authName: '用户列表',
            id: 110,
            path: 'users'
          }],
          id: 125,
          path: 'users'
        },
        {
          authName: '权限管理',
          children: [{
            authName: '角色列表',
            id: 111,
            path: 'roles'
          },
          {
            authName: '权限列表',
            id: 112,
            path: 'rights'
          }],
          id: 103,
          path: 'rights'
        },
        {
          authName: '课程管理',
          children: [{
            authName: '院校列表',
            id: 121,
            path: 'schools'
          },
          {
            authName: '课程列表',
            id: 122,
            path: 'course'
          }],
          id: 120,
          path: 'schools'
        },
        {
          authName: '菜单管理',
          children: [{
            authName: '菜单列表',
            id: 131,
            path: 'menu'
          }],
          id: 130,
          path: 'menu'
        },
        {
          authName: '系统管理',
          children: [{
            authName: '数据字典',
            id: 107,
            path: 'datadictionary'
          },
          {
            authName: '参数设置',
            id: 108,
            path: 'parameter'
          }],
          id: 102,
          path: 'orders'
        }
      ],
      iconObj: {
        125: 'el-icon-user-solid',
        103: 'el-icon-box',
        102: 'el-icon-s-tools',
        130: 'el-icon-s-unfold',
        120: 'el-icon-school'
      },
      isCollapse: false
    }
  },
  methods: {
    logout () {
      MessageBox.confirm('确认退出吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        window.sessionStorage.clear()
        this.$router.push('/login')
      }).catch(() => {})
      // window.sessionStorage.clear()
      // this.$router.push('/login')
    },
    // async getMenuList () {
    //   const { data } = await this.$http.get('menus')
    //   if (data.meta.status === 200) {
    //     const list = []
    //     for (let i = 0; i < data.data.length; i++) {
    //       if (i <= 1) {
    //         list[i] = data.data[i]
    //         // console.log(data.data[i])
    //       }
    //       if (i === 3) {
    //         data.data[i].authName = '系统管理'
    //         data.data[i].children[0].authName = '数据字典'
    //         // data.data[i].children[1].authName = '参数设置'
    //         data.data[i].children[0].path = 'dataDictionary'
    //         // data.data[i].children[1].path = 'parameter'
    //         list[i - 1] = data.data[i]
    //       }
    //     }
    //     this.menulist = list
    //     console.log(list)
    //   } else {
    //     this.$message({
    //       type: 'error',
    //       message: data.meta.msg
    //     })
    //   }
    // },
    toggleCollapse () {
      this.isCollapse = !this.isCollapse
    }
  },
  created () {
    this.username = sessionStorage.getItem('username')
    console.log(this.username)
  }
}
</script>

<style lang="less" scoped>
.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 20px;
  > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
  img {
    width: 50px;
    height: 50px;
  }
}

.el-aside {
  background-color: #333744;
  .el-menu {
    border-right: none;
  }
}

.el-main {
  background-color: #eaedf1;
}

.iconfont {
  margin-right: 10px;
}

.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
.el-dropdown-link {
    cursor: pointer;
    color: #FFFFFF;
  }
  .el-icon-arrow-down {
    font-size: 16px;
  }
</style>
