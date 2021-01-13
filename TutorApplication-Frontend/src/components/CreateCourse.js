import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})




var checkInput = async (inputName) => {
	console.log("checkinput")
	
	if(inputName.trim().search(/[A-Z]*/) == -1 ){
		return 2; // Bad Password Input
	}
	else {
      try {
		let res = await axios.get('http://localhost:8080/courses/');
		var i;
		// For loop is to check if username already exists in database
		for (i = 0; i < res.data.length; i++) {
			if (inputName == res.data[i].courseId){
				return 4;
			}
		  }
        return 0; // All inputs are good
        
      } catch (e) {
        return 5; // Error
      }

    }
    return false;
  }


export default {
    name: "createcourse",
    data(){
      return{
    	courses: [],
        newCourse: '',
		errorCourse:'',
		response: [],
		res: ''
	  }
    },


	_created: function () {
		AXIOS.get('/courses').then(response => {
			this.tutors = response.data;
		})
			.catch(e => {
				this.errorCourse = e;
			});
	},

methods: {

	createCourseHandler: async function() {
        var inputName = document.getElementById("usr").value; // username inputted
        
		var result = await checkInput(inputName);
        if (result == 0) {
			this.createCourse(inputName)
        } else {
			/*if ((result == 1) || (result == 4) || (result == 5)){
				document.getElementById("usr").className = 'form-control form-control-lg is-invalid'
			}
			else if ((result == 2) || (result == 3)){
				document.getElementById("psw").className = 'form-control form-control-lg is-invalid'
			}
			else if(result == 6){
				document.getElementById("confirmpsw").className = 'form-control form-control-lg is-invalid'
			}*/
          document.getElementById("demo").style.color = 'red'
          if (result == 1) {
            document.getElementById("demo").innerHTML = "Username cannot be blank";
          } else if (result == 2) {
            document.getElementById("demo").innerHTML = "Password must start with a capital letter and contain a special character";
		  } else if(result == 3 ){
			document.getElementById("demo").innerHTML = "Password must be at least 8 characters"
		  } else if(result == 4){
			document.getElementById("demo").innerHTML = "Username already exists"
		  } else if(result == 5){
			document.getElementById("demo").innerHTML = "Unable to create account"
		  } else if(result == 6){
			document.getElementById("demo").innerHTML = "Rating must be inputted with one integer from 1-5"
		  } else if (result == 7){
			document.getElementById("demo").innerHTML = "First name cannot be empty"
		  } else if (result == 8){
			document.getElementById("demo").innerHTML = "Last name cannot be empty"

		  }
        };

	  },
	  
	createCourse: function (inputName) {
        axios.post('http://localhost:8080/courses/' + inputName).then(response =>{
			console.log(response.data)
			this.courses.push(response.data)
			this.newCourse='';
			this.errorCourse='';
		}).catch(e => {
			var errorMsg = e.message
			console.log(errorMsg)
			this.errorCourse = errorMsg
		});
	  },

	  
	},

	
	

}
