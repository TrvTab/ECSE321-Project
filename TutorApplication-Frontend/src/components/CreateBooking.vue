
<template>
<div id=createbooking>
    <div class = review>
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
        <button class="btn btn-danger" @click="goToLogin" id="logoutbutton">
          <div class="buttonframe"></div>
            <div class="logoutText">Logout</div>
        </button>
      </div> 
      <div>
        <button class="btn btn-danger2" @click="goToStudentHomePage" id="homebutton">
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
                      <div class="container text-left" id="Acceptance-Form">
                        <h2>
                          <font>Book An Appointment</font>
                        </h2>
                      </div>
                      <hr>
                    </div>
                    </div>

                    <div class="panel panel-default text-center">
                      <div class="panel-body">
                        <br>
                        <div class="form-group">

                          <div class="container-fluid">
                            <div class="container text-left" id="Job-Information">
                              <h3>
                                <font><b>Course and Tutor Selection</b></font>
                              </h3>
                            </div>
                            <hr>
                          </div>
                          <br>
                          <div class="row">
                            <div class="col-sm-6">
                              <form class="form-inline" action="/action_page.php">
                                <label for="selectcourse" class="mb-2 mr-sm-2">Select Course:</label>
                                <select class="form-control" id="selectcourse" @change="checkCourseInput($event)">
                               <!-- <select class="form-control" id="selectcourse"> -->

                                  <!-- $('.school').on('change',function() -->
                                  <option v-for="(course, i) in availableCourses" v-bind:key="`course-${i}`"> 
                                    {{courses[i].courseId}} 
                                  </option>

                                </select>
                              </form>
                            </div>
                            <br>
                            <div class="col-sm-6">
                              <div class="form-group form-inline">
                                <label for="selecttutor">Select Tutor</label>
                                <select class="form-control" id="selecttutor" @change="checkTutorInput($event)">

                                  <option v-for="(tutor, i) in availableTutors" v-bind:key="`tutor-${i}`" > 
                                    {{availableTutors[i].name}} {{availableTutors[i].rating}}/5

                             </option>
                                </select>
                              </div>
                            </div>
                          </div>
                          
                          <div class="container-fluid">
                            <div class="container text-left" id="Job-Information">
                              <h3>
                                <font><b>Booking Information</b></font>
                              </h3>
                            </div>
                            <hr>
                          </div>

                          <div class="row">
                              <form class="form-inline" action="/action_page.php">
                                <div class="date-label">
                                  <div class="row">
                                  </div>
                                </div>
                                <div class="form-group form-inline">
                                  <label for="start2" class="mb-2 mr-sm-2">Start Time:</label>
                                <input type= "text" class="form-control mb-2 mr-sm-2" id="start" name="Start" placeholder="Start Time (24 hour)">
                                </div>
                                <label for="end2" class="mb-2 mr-sm-2">End Time:</label>
                                <input type= "text" class="form-control mb-2 mr-sm-2" id="end" name="End" placeholder="End Time (24 hour)">
                                <datepicker id="thedate" placeholder="Select Date" :format="format" v-model="vModelExample"></datepicker>
                              </form>
                          </div>
                          <br>
                      </div>
                      <br>
                      <div class="row">
                        <div class="col-sm-4">
                        </div>
                        <div class="col-sm-4">
                          <div class="row">
                            <div class="col-sm-6 text-center">
                              <button @click="goToStudentHomePage" type="button" class="btn btn-outline-secondary">

                                <font size="4">Cancel</font>
                              </button>
                            </div>
                            <div class="col-sm-6 text-center">
                              <button  type="button" class="btn btn-outline-primary">
                                <font size="4" @click="createBooking">Submit</font>
                              </button>
                            </div>
                          </div>
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
    </div>
</template>

<script>


import axios from 'axios'
import Datepicker from 'vuejs-datepicker'
var config = require('../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost  + ':' + config.dev.backendport

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: {'Access-Control-Allow-Origin': frontendUrl }

});

function makeid(length) {
   var result           = '';
   var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
   var charactersLength = characters.length;
   for ( var i = 0; i < length; i++ ) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
   }
   return result;
}


var state =  {
  date: new Date()
}


    export default {
      name: 'CreateBooking',
      components:{
        Datepicker
      },
      data() {
        return {
          bookings:[],
          currentBookings: [],
          courses:[],
          availableTutors: [],
          errorTutor:'',
          errorCourse: '',
          availableCourses:["ECSE 321", "ECSE 224", "COMP 206"],
          newBooking: null,
          bookingTutor: null,
          bookingStudent: null,
          errorBooking: '',
          response: [],
          tutorIndex:'',
          courseIndex:'',
          vModelExample: null,
          format:"yyyy-MM-dd",
        }
      },
      

      created: function (){
        // Intitializing Students from backend
        axios.get('http://localhost:8080/students/' + this.$route.params.user + '/bookings').then(reponse => {
            this.bookings = response.data;
          })
          .catch(e => {
            console.log("errorbooking")
            this.errorBooking = e;
          });
        },

      created: function () {
          axios.get('http://localhost:8080/courses').then(response => {
            this.courses = response.data;
          })
            .catch(e => {
              this.errorCourse = e;
            });
        },
        


      
      methods: {
        
      
      goToLogin: function() {
        this.$router.push({
          name: 'login2',
          params: {
            id: this.$route
          }
        })
      },
      //@PathVariable("student") String studentId,@RequestParam String bookingId, @RequestParam String tutorId,
		//	@RequestParam String courseId, @RequestParam String date, @RequestParam String startTime, @RequestParam String endTime) {
      createBooking: function (){
        // TODO CONTINUE
          var bookingNumber = this.bookings.length;
          console.log("booking length" + this.bookings.length)
          var inputTutorId = this.availableTutors[this.tutorIndex].tutorId;
          var inputCourseId = this.courses[this.courseIndex].courseId;
          var inputDate = document.getElementById("thedate").value;
          var inputStartTime = document.getElementById("start").value;
          var inputEndTime = document.getElementById("end").value;
          
          console.log("user" + this.$route.params.user);

         axios.post('http://localhost:8080/'+ this.$route.params.user + '/booking' + '?bookingId='+ makeid(7) +
         "&tutorId=" + inputTutorId + '&courseId=' + inputCourseId + '&date=' + inputDate + '&startTime=' + inputStartTime
         + '&endTime=' + inputEndTime)
         .then(reponse =>{
        //JSON responses are autoamtically parsed.
          this.bookings.push(response.data)
          console.log(response.data)
          this.errorBooking = ''
          this.$router.push({
              name: 'studenthomepage',
              params: {
                user: this.$route.params.user
              }
            })
        })
        .catch(e => {
          console.log(inputTutorId)
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorBooking = errorMsg
              this.$router.push({
              name: 'studenthomepage',
              params: {
                user: this.$route.params.user
              }
            })

          



        });
      }, 
      goToStudentHomePage: function(){
              this.$router.push({
              name: 'studenthomepage',
              params: {
                user: this.$route.params.user
              }
            })

          },

      getTutorsOfCourse: function (chosenCourse){
        axios.get('http://localhost:8080/courses/' + chosenCourse + '/tutors').then(response =>{
        this.availableTutors = (response.data)
        this.errorTutor = ''
        }).catch (e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorTutor = errorMsg
        })
      },

      checkCourseInput: function (value){
          console.log(value)
          this.courseIndex = value.srcElement.selectedIndex;
          console.log(this.courseIndex)
          console.log(this.courses[this.courseIndex])
          this.getTutorsOfCourse(this.courses[this.courseIndex].courseId)
       },


       checkTutorInput: function(value){
         this.tutorIndex = value.srcElement.selectedIndex;
          

       }
      

  // onclick submit button we have to create the booking takin ghte value from all the inputs of the user

      

      

    }
  }
</script>


<style>

  #img-container:hover img {
    opacity: 0.8;
  }

  .panel {
    min-height: 80%;
    min-width: 100%;

    color: black;
  }

  #top-container {
    margin-bottom: 0;
    margin-top: 0;
    background-color: cyan;
    color: black;
  }

  #top-container h2 {
    text-align: left;
    margin-bottom: 20px;
  }

  #Account-but {
    margin-left: 100px;
    min-width: 0%;
  }

  #Logout-but {
    margin-left: 50px;
    min-width: 50%;
  }

  #Acceptance-Form {
    max-width: 100%;
    margin-top: 20px;
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

  #Booking-Information {
    max-width: 100%;
    margin-top: 0px;
  }

  #Booking-Information h3 {
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

.booking-info{
  position: absolute;
  right: 400px;
  top: 325px;
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
.btn-outline-secondary{
  color: red;
  outline-color: red
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