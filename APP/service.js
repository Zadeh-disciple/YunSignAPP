// 管理课程信息
const COURSE_KEY = 'COURSE_KEY';
const STATE_KEY = 'STATE_KEY';

const getCourse = function() {
	let ret = '';
	ret = uni.getStorageSync(COURSE_KEY);
	if (!ret) {
		ret = '[]';
	}
	return JSON.parse(ret);
}

const addCourse = function(courseInfo) {
	let course = getCourse();
	course.push({
		cass: courseInfo.cass,
		course:courseInfo.course
		// password: userInfo.password
	});
	uni.setStorageSync(COURSE_KEY, JSON.stringify(course));
}

export default {
	getCourse,
	addCourse
}
