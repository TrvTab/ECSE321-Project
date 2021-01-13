import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})




var checkInput = async (firstName, lastName, inputName, inputPass) => {
	console.log("checkinput")
	if (firstName.trim().search(/\S{1,}/) == -1){
		return 7;
	}
	if (lastName.trim().search(/\S{1,}/) == -1){
		return 8;
    }
    if ((rating>5) || (rating<1)){
        return 6;
    }o
	if (inputName.trim().search(/\S{1,}/) == -1) {
      return 1; // Bad username input
	} else if(inputPass.trim().search(/^[A-Z][A-Za-z0-9]+[^A-Za-z0-9]+.*/) == -1 ){
		return 2; // Bad Password Input

	}else if(inputPass.length<8){
        return 3; // Password is not long enough
    } 
	else {
      try {
		let res = await axios.get('http://localhost:8080/tutors/');
		var i;
		// For loop is to check if username already exists in database
		for (i = 0; i < res.data.length; i++) {
			if (inputName == res.data[i].tutorId){
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
    name: "createTutor",
    data(){
      return{
    	tutors: [],
        newTutor: '',
		errortutor:'',
		response: [],
		res: ''
	  }
    },


	_created: function () {
		AXIOS.get('/tutors').then(response => {
			this.tutors = response.data;
		})
			.catch(e => {
				this.errorStudent = e;
			});
	},

methods: {

	createTutorHandler: async function() {
        var inputName = document.getElementById("usr").value; // username inputted
        var inputPass = document.getElementById("psw").value; // password inputted
        var rating = parseInt(document.getElementById("rating").value); // rating inputted
		var firstN = document.getElementById("firstName").value; // First name inputted
        var lastN = document.getElementById("lastName").value; // Last name inputted
        
		var result = await checkInput(firstN, lastN, inputName, inputPass);
        if (result == 0) {
			var fullName = firstN + " " + lastN;
			this.createTutor(fullName, inputName, inputPass, rating)
		
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
	  
	createTutor: function (inputName, fullName, inputPass, rating) {
        axios.post('http://localhost:8080/tutors/' + inputName + "?name=" + fullName + "&password=" + inputPass + "&rating=" + rating).then(response =>{
			console.log(response.data)
			this.tutors.push(response.data)
			this.newTutor='';
			this.errorTutor='';
		}).catch(e => {
			var errorMsg = e.message
			console.log(errorMsg)
			this.errorTutor = errorMsg
		});
	  },

	  
	},

	
	

}
