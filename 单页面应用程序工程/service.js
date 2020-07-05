// 管理课程信息
const COURSE_KEY = 'DATA_KEY'
// const STATE_KEY = 'STATE_KEY'

const getCourse = function () {
  let ret = ''
  ret = window.localStorage.getItem(COURSE_KEY)
  if (!ret) {
    ret = '[]'
  }
  // console.log(JSON.parse(ret))
  return JSON.parse(ret)
}

const addCourse = function (userInfo) {
  const course = getCourse()
  course.push({
    username: userInfo.username,
    email: userInfo.email,
    mobile: userInfo.mobile,
    school: userInfo.school,
    apartment: userInfo.apartment
    // password: userInfo.password
  })
  // let id = 0
  window.localStorage.setItem(COURSE_KEY, JSON.stringify(course))
  window.localStorage.setItem(String(userInfo.id), JSON.stringify(course))
  // id = id + 1
}

const updateCourse = function (userInfo) {
  const course = getCourse()
  course[2].username = userInfo.username
  course[2].email = userInfo.email
  course[2].mobile = userInfo.mobile
  // let id = 0
  window.localStorage.setItem(COURSE_KEY, JSON.stringify(course))
  // id = id + 1
}

export default {
  getCourse,
  addCourse,
  updateCourse
}
