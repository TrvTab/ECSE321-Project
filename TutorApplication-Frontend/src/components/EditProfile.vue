<template>
<div id=editprofile> 
    <div class="frame" id="top-container">
      <div class="banner">
        <div class="topBanner">
          <div class="toolbar">
            <div class="logo">
              <div style="display: inline-block;" id="img-container">
                <img src="https://pbs.twimg.com/media/EKQHbbtXYAA04Uz?format=png&name=small" width="200" height="100">
              </div>
            </div>
          </div>
        </div>
        <div class="leftbanner">

        </div>
        <div class="rightbanner">

        </div>
      </div>
      <div>
        <button class="btn btn-danger" @click="goToHello" id="logoutbutton">
          <div class="buttonframe"></div>
            <div class="logoutText">Logout</div>
        </button>
      </div> 
      <div>
        <button class="btn btn-danger2" @click="goBackToStudentHomepage" id="homebutton">
          <div class="buttonframe2"></div>
            <div class="homeText">HomePage</div>
        </button>
      </div>
      <div class="page"> 
            <div class="container-fluid" id="background">
              <div class="container text-center">
                <div class="row">
                  <br>
                  <div class="container-fluid">
                    <div class="container text-left" id="Edit-Profile">
                      <h2>
                        <font>Edit Profile</font>
                      </h2>
                    </div>
                    <hr>
                  </div>
                  </div>

                  <div class="panel panel-default text-center">
                    <div class="panel-body">
                      <br>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-sm-6">
                          <div class="editprofileblock">
                            <form action="/action_page.php">
                              <input id="oldpswd" type="oldpassword" name="Old Password" placeholder="Old Password">
                              <br>
                              <br>
                              <input id="newpswd" type= "newpassword" name="New Password" placeholder="New Password">
                              <br>
                                <br>
                              <input id="cnfrmpswd" type= "confirmpassword" name="Confirm New Password" placeholder="Confirm New Password">
                              <br>
                          
                              <br>
                                  
                             
                              <input type="submit" class="confirmButton" @click="editProfile" value="Confirm">
                              <br><br>
                                <p id="demo"></p> 
                            </form>
                          </div>
                          </div>
                          <br>
                        </div>
                        
                        <div class="container-fluid">
                          <hr>
                        </div>

                        <div class="row">
                        </div>
                        <br>
                    </div>
                    <br>
                    <div class="row">
                      <div class="col-sm-4">
                      </div>
                      <div class="col-sm-4">
                      </div>
                    </div>
                    <br>
                  </div>
                </div>
              </div>
            </div>
      </div>
      </div>
    </div>
</template>


<script>
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'https://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl
})

var checkInput = async (oldpassword, newpassword, confirmpassword) => {
	console.log("checkinput")
  if(oldpassword.trim().search(/^[A-Z][A-Za-z0-9]+[^A-Za-z0-9]+.*/) == -1 ){
		return 2; // Bad Password Input
  }
  
  
  if (firstName.trim().search(/\S{1,}/) == -1){
		return 7;
	}
	if (lastName.trim().search(/\S{1,}/) == -1){
		return 8;
	}
	if (inputName.trim().search(/\S{1,}/) == -1) {
      return 1; // Bad username input
	} else if(inputPass.trim().search(/^[A-Z][A-Za-z0-9]+[^A-Za-z0-9]+.*/) == -1 ){
		return 2; // Bad Password Input

	}else if(inputPass.length<8){
		return 3; // Password is not long enough
	} else if (confirmPass !== inputPass){
		return 6; // Confirm password does not match input password
	}
	else {
      try {
		let res = await axios.get('http://localhost:8080/students/specificstudent/' + inputName);
		
		if (res.data == true){
			return 4;
		}
		//var i;
		// For loop is to check if username already exists in database
		/*for (i = 0; i < res.data.length; i++) {
			if (inputName == res.data[i].studentId){
				return 4;
			}
		  }*/
        return 0; // All inputs are good
        
      } catch (e) {
        return 5; // Error
      }

    }
    return false;
  }


    export default {
      name: 'editprofile',
      data() {
        return {
          newUsername: '',
          newPassword: '',
          errorUsername: '',
          errorPassword: '',
          response: [],
        }
      },
       created: function(){
        // Initializing bookings from backend
        AXIOS.get('/student/').then(response => {
          this.username = response.data
        })
        .catch(e => {
          this.errorUser = e;
        })
      },
      methods: {
        goToHello: function() {
        this.$router.push({
          name: 'hello',
          params: {
            id: this.$route
          }
        })
      },
      /* Method to go to Student HomePage */
      goBackToStudentHomepage: function() {
        this.$router.push({
          name: 'studenthomepage',
          params: {
            user: this.$route.params.user
          }
        })
      },
  checkEditProfileInput : async function(oldpassword, newpassword, confirmpassword){
    console.log("checkinput")
    if(newpassword.trim().search(/^[A-Z][A-Za-z0-9]+[^A-Za-z0-9]+.*/) == -1){
      return 2; //Bad Password input for edit profile
    } else if(inputPass.length<8){
      return 3; // Password is not long enough
    } else if (confirmpassword !== newpassword){
      return 6; // Confirm password does not match input password
    }
    else {
        try {
        let res = await axios.get('http://localhost:8080/students/specificstudent/' + inputName);
      
      if (res.data == true){
        return 4;
      }
          return 0; // All inputs are good
          
        } catch (e) {
          return 5; // Error
        }

      }
      return false;
    },

    editProfile: async function() {
          consol.log(this.$route.params.user)
          var oldPassword = document.getElementById("oldpswd").value; // old password inputted
	      	var newPassword = document.getElementById("newpswd").value; // new password inputted
          var confirmPassword = document.getElementById("cnfrmpswd").value; // confirm new passwordinputted
          let res = axios.get('http://localhost:8080/students/passwordcheck/'+ this.$route.params.user + "?password=" + oldpassword);
          console.log(res.data)
          console.log(res.data.value)
          if (res.data == 0){
                var result = checkEditProfileInput(oldPassword, newPassword, confirmPassword);
                if (result == 0 ) {
                      axios.put("http://localhost:8080/students/" + this.$route.params.user + "?oldPassword=" + oldPassword + "&newPassword=" + newPassword).then(response =>{
                          console.log(response.data)
                          console.log(response)
			                    this.errorStudent='';
                      }).catch(e => {
                          var errorMsg = e.message
		                    	console.log(errorMsg)
			                    this.errorUser = errorMsg
                      });
                }
          }
                  else if(res.data == 1) {
                       document.getElementById("demo").innerHTML = "Password is not valid for this user"
                      } else if(res.data == 2){
                      document.getElementById("demo").innerHTML = "Password is not valid for this user"
                  }
                 else {
                    document.getElementById("demo").styPle.color = 'red'
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
                      document.getElementById("demo").innerHTML = "Password must match confirmed password"
                    } else if (result == 7){
                      document.getElementById("demo").innerHTML = "First name cannot be empty"
                    } else if (result == 8){
                    document.getElementById("demo").innerHTML = "Last name cannot be empty"
                    }
              } 
          }
      }
    }
</script>
<style>

.confirmButton{
color:black !important;
text-transform: uppercase;
text-decoration: none;
background: cyan;
padding: 20px 50px;
border: 4px solid #494949 !important;
display: inline-block;
transition: all 0.4s ease 0s;
margin: 10px;
}

.confirmButton:hover {
color: black !important;
background: pink;
border-color: pink !important;
transition: all 0.4s ease 0s;
}

  #img-container:hover img {
    opacity: 0.8;
  }

  .panel {
    min-height: 80%;
    min-width: 100%;
  }

  #editprofileblock {
        width: 100%;
        text-align: center;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
  }

  #top-container {
    margin-bottom: 0;
    margin-top: 0;
    background-color: #333335;
    color: #ffffff;
  }

  #top-container h2 {
    text-align: left;
    margin-bottom: 20px;
  }

  #BackToStudentHomepage {
    margin-left: 100px;
    min-width: 0%;
  }

  #Edit-Profile {
    max-width: 100%;
    margin-top: 20px;
    color: black
  }

  #Acceptance-Form h2 {
    text-align: center;
    margin-top: 15px;
    margin-bottom: 10px;
    font-size: 37px
  }

  #Fall {
    margin-left: 20px;
  }

  #Winter {
    margin-left: 30px;
  }

  #Summer {
    margin-left: 30px;
  }

  #W-Yes {
    margin-left: 30px;
  }

  #W-No {
    margin-left: 20px;
  }

  #Job-Information {
    max-width: 100%;
    margin-top: 0px;
  }

  #Job-Information h3 {
    text-align: center;
  }

  #Employer-Information {
    max-width: 100%;
    margin-top: 0px;
  }

  #Employer-Information h3 {
    text-align: center;
  }

  #JobID {
    margin-left: 10px;
    min-width: 27.7%;
  }

  #CourseID {
    margin-left: 10px;
    min-width: 27.7%;
  }

  #CoopTerm {
    margin-left: 10px;
    min-width: 27.7%;
  }

  #Start {
    margin-left: 20px;

  }

  #End {
    margin-left: 20px;
    min-width: 0%;
  }

  #Company-Name {
    margin-left: 13px;
    min-width: 51%;
  }

  #Employer-Name {
    margin-left: 13px;
    min-width: 30%;
  }

  #Employer-Email {
    margin-left: 10px;
    min-width: 51%;
  }

  #Address-Line-1 {
    min-width: 100%;
  }

  #Address-Line-2 {
    min-width: 100%;
  }

  #City {
    margin-left: 66px;
    min-width: 65%;
  }

  #Province {
    margin-left: 5px;
    min-width: 65%;
  }

  #Postal-Code {
    margin-left: 10px;
    min-width: 65%;
  }

  #Country {
    margin-left: 10px;
    min-width: 65%;
  }

  #Employer-Contract {
    margin-left: 15px;
    min-width: 73%;
  }
  /* Website Frame */

.frame{
position: relative;
width: 1440px;
height: 900px;

background: #FFFFFF;
}

/* Banner */

.banner{
position: absolute;
width: 1440px;
height: 900px;
left: 0px;
top: 0px;
}

/* Top Banner */

.topBanner{
position: absolute;
width: 1440px;
height: 102px;
left: 0px;
top: 0px;

background: #79E0EF;
}

/* ToolBar */

.toolbar{
position: absolute;
left: 0px;
right: 0px;
top: 0px;
bottom: 798px;

background: #79E0EF;
}

/* Logo */

.logo{
position: absolute;
left: 28px;
right: 1239px;
top: 0px;
bottom: 798px;

background: url(https://pbs.twimg.com/media/EKQHbbtXYAA04Uz?format=png&name=small), #79E0EF;
}

/* Left-Side Banner */

.leftbanner{
position: absolute;
left: 0px;
right: 1414px;
top: 102px;
bottom: 0px;

background: #79E0EF;
}

/* Right-Side Banner */

.rightbanner{
position: absolute;
left: 1414px;
right: 0px;
top: 102px;
bottom: 0px;

background: #79E0EF;
}

/* Logout Button */

.logoutbutton{
position: absolute;
width: 152px;
height: 56px;
left: 1251px;
top: 23px;
}

/* Button Frame */

.buttonframe{
position: absolute;
width: 152px;
height: 56px;
left: 1251px;
top: 23px;

background: #000000;
border-radius: 15px;
}

/* Log Out */

.logoutText{
position: absolute;
width: 152px;
height: 33px;
left: 1251px;
top: 30px;

font-family: Arial, Helvetica, sans-serif;
font-style: normal;
font-weight: normal;
font-size: 24px;
line-height: 42px;
text-align: center;
text-transform: uppercase;

color: #79E0EF;
}

/* HomePage Button */

.homebutton{
position: absolute;
width: 152px;
height: 56px;
left: 1073px;
top: 23px;
}

/* Button Frame2 */

.buttonframe2{
position: absolute;
width: 152px;
height: 56px;
left: 1073px;
top: 23px;

background: #000000;
border-radius: 15px;
}

.homeText{
position: absolute;
width: 152px;
height: 33px;
left: 1073px;
top: 30px;

font-family: Arial, Helvetica, sans-serif;
font-style: normal;
font-weight: normal;
font-size: 24px;
line-height: 42px;
text-align: center;
text-transform: uppercase;

color: #79E0EF;
}

/* Page */

.page{
position: absolute;
width: 1388px;
height: 798px;
left: 26px;
top: 102px;

background: #FFFFFF;
}

/* Review Page */

.reviewpage{
position: absolute;
width: 650px;
height: 551px;
left: 395px;
top: 136px;

background: #FFFFFF;
}

</style>