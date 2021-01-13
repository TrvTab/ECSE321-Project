function courseDto(courseId){
    this.courseId = courseId
}

export default {
    name: 'SelectCourse',
    data() {
      return {
          courses: ["ECSE321","ECSE223"],
          newCourse: '',
          errorCourse: '',
          response: []
      }
    },
     
  

  /*created: function(){
    const c1 = new courseDto('ECSE 321')
    const c2 = new courseDto('ECSE 223')
    this.courses = [c1, c2]
  },*/

  methods: {
      createCourse: function (courseId){

        var c = new courseDto(courseId)
        this.courses.push(c)

        this.newCourse = ''
      }
  }
}