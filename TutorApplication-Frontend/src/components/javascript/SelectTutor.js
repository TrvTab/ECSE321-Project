import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
   // headers: { 'Access-Control-Allow-Origin': frontendUrl }
  })
  
  function tutorDto(name,tutorId,rating,schedule){
      this.name = name
      this.tutorId = tutorId
      this.rating = rating
      this.schedule = schedule;
  }
  
  export default {
      name: 'SelectTutor',
      data() {
        return {
            tutor: [],
            newTutor: '',
            errorTutor: '',
            response: []
        }
      },
       
    
  
    created: function(){
      // Initializing people from backend
      AXIOS.get(`/tutors`)
      .then(response => {
        // JSON responses are automatically parsed.
        this.tutors = response.data
        this.tutors.forEach(tutor => this.getCourses(course.courseId))
      })
      .catch(e => {
        this.errorTutor = e;
      });
    },

    methods: {
      createTutor: function (tutorId){
        AXIOS.post(`/tutors`+tutorId, {}, {})
        .then(response => {
          // JSON responses are automatically parsed.
          this.tutors.push(response.data)
          this.newTutor = ''
          this.errorTutor = ''
        })
        .catch(e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorTutor = errorMsg
        });
      }
  }
}