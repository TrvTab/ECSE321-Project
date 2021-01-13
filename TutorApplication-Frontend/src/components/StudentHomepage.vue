<!DOCTYPE html>

<template>
  <html lang="en">
  <Ads></Ads>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.0/css/all.css" integrity="sha384-Mmxa0mLqhmOeaE8vgOSbKacftZcsNYDjQzuCOm6D02luYSzBG8vpaOykv9lFQ51Y" crossorigin="anonymous">
  </head>

  <body>
 <!-- <div>
    <carousel>
      <div class="example-slide">
          <a href="https://www.amazon.com/">
            <img alt="amazon" src="https://cdn.sellerapp.com/Blog_images/sellerapp-header-image-amazon-sellers-guide.png" width="1440" height="150">
        </a>
  </carousel>
  </div>-->
   <div class = homepage>
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

          <div class = page>
            <div class="container-fluid" id="course-title">
              <div class="container text-center">
                <div class="row">
                  <div class="col-sm-12">
                    <h1></h1>
                  </div>
                </div>
              </div>
            </div>

            <div class="container text-center">
              <div class="container-fluid" id="nav-bar">
                <div class="row">
                  <div class="col-sm-12">
                    <ul class="nav nav-tabs">
                      <li v-for="tab in tabs" :class="selectedTab === tab ? 'active' : ''" @click="selectedTab=tab">
                        <a>
                          <font size="4">{{ tab }}</font>
                        </a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
            <br>

            <transition name="slide-fade" mode="out-in" appear>
              <div v-if="selectedTab === 'Overview'" key="overview" class="container" id="overview-container">
                <div class="row">
                  <div class="col-sm-6" >
                    <div class="card border-inverse mb-3">
                      <h3 class="card-title align-items-left d-flex justify-content-left" style="margin-top:23px; margin-bottom:20px; margin-left:220px;" text-align: center><font color="black"><center>Actions</center></font></h3>
                      <div class="card-body align-items-center d-flex justify-content-center">
                        <!--Important To display the correct % change the
                        number inside the span, to make the grean bar grow the
                        correct amount change p# in " "-->
                        <input type="button" @click="goToEditProfile" class= "EditButton" value="Edit Profile">
                        <input type="button" @click="goToCreateBooking" class= "EditButton" value="Add Booking">
                      
                      </div>
                    </div>
                  </div>

                </div>
              </div>

              <div v-if="selectedTab === 'Upcoming Bookings'" key="tasks" class="container">
                <table class="table table-striped table-bordered">
                  <thead>
                    <tr>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Course</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Tutor</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Date</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Start Time</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>End Time</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Room</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Delete Booking</h4>
                      </th>
                  </tr>
                  <tr v-for="(booking,i) in bookings" v-bind:key="`booking-${i}`">
                    <th style="text-align:center; vertical-align:middle">
                      <h5 scope="col" style="text-align:center; vertical-align:middle">
                        {{bookings[i].course.courseId}}
                      </h5>
                    </th>
                    <th>
                      <h5 scope="col" style="text-align:center; vertical-align:middle">
                      {{bookings[i].tutorName}}
                      </h5>
                    </th>
                    <th>
                      <h5 scope="col" style="text-align:center; vertical-align:middle">
                      {{bookings[i].bookingDate}}
                      </h5>
                    </th>
                    <th>
                      <h5 scope="col" style="text-align:center; vertical-align:middle">
                      {{bookings[i].startTime}}
                      </h5>
                    </th>
                    <th>
                      <h5 scope="col" style="text-align:center; vertical-align:middle">
                      {{bookings[i].endTime}}
                      </h5>
                    </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h5> Pending</h5>
                      </th>
                    <th>
                      <h4 scope="col" style="text-align:center; vertical-align:middle">
                        <button id="bookingsCurrentTable" class="delete-btn" @click="deleteCurrentFunction">
                            <i class="fa fa-trash"></i>
                        </button>
                      </h4>
                    </th>
                  </tr>
                  </thead>
                </table>
              </div>
              <div v-if="selectedTab === 'Past Bookings'" class="container">
                <table class="table table-striped table-bordered">
                  <thead>
                    <tr>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>BookingId</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Course</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Tutor</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Date</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Start Time</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>End Time</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Room</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Review</h4>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h4>Rating</h4>
                      </th>
                      
                    </tr>
                    <tr v-for="(pastBooking,i) in pastBookings" v-bind:key="`pastBooking-${i}`">
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h5>{{pastBookings[i].bookingID}}</h5>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h5>{{pastBookings[i].course.courseId}}</h5>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h5>{{pastBookings[i].tutorName}}</h5>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h5>{{pastBookings[i].bookingDate}}</h5>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h5>{{pastBookings[i].startTime}}</h5>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h5>{{pastBookings[i].endTime}}</h5>
                      </th>
                      <th scope="col" style="text-align:center; vertical-align:middle">
                        <h5> Pending</h5>
                      </th>
                      <th v-if="pastBookings[i].review !==null" scope="col" style="text-align:center; vertical-align:middle">
                        <h5>{{pastBookings[i].review.comment}}</h5>
                        <th v-else scope="col" style="text-align:center; vertical-align:middle">
                          <h5> </h5>
                      </th>
                        

                      <th v-if="pastBookings[i].review !==null" scope="col" style="text-align:center; vertical-align:middle">
                        <h5>{{pastBookings[i].review.rating}}</h5>
                      <th v-else scope="col" style="text-align:center; vertical-align:middle">
                        <h5> </h5>


                        
                      </th>

                    </tr>
                  </thead>
                </table>
                <select style="width: 200px;" class="form-control" id="selecttutor" @click="checkReviewInput($event)">
                  <option v-for="(booking, i) in reviewNullBookings" v-bind:key="`booking-${i}`" > 
                    {{reviewNullBookings[i].bookingID}}
                  </option>
                </select>
                 <input type="button" @click="goToReviewPage" class= "EditButton" value="Review Booking">
              </div>


              <br>
            </transition>
          </div>
        </div>
      </div>
  </body>


  

  </html>
</template>

<style>
/* Website Frame */
.delete-btn {
  background-color: red; /* Blue background */
  border: none; /* Remove borders */
  color: black; /* White text */
  padding: 8px 8px; /* Some padding */
  cursor: pointer; /* Mouse pointer on hover */
}
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
background: white;
}
.EditButton{
  background-color: cyan;
  border: none;
  color:  black;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
.EditButton:hover{
      background: pink
  }
  #img-container:hover img {
    opacity: 0.8;
  }
  #top-container {
    margin-bottom: 0;
    background-color:cyan;
    color: #ffffff;
  }
  #top-container h2 {
    text-align: left;
    margin-top: 30px;
    margin-bottom: 20px;
  }
  #logo-button {
    color: #333335;
    background: #333335;
  }
  #Account-but {
    margin-left: 100px;
    min-width: 0%;
  }
  #Logout-but {
    background-color: tomato;
    color:black;
    margin-left: 50px;
    min-width: 50%;
  }
  #top-container a {
    display: inline-block;
    margin-top: 35px;
  }
  #course-title h1 {
    text-align: left;
    margin-top: 30px;
    margin-bottom: 40px;
  }
  #nav-bar {
    margin-top: 10px;
    margin-bottom: 40px;
    color: rgb(0, 0, 0);
  }
  /* Tab transition animations */
  .slide-fade-enter-active {
    transition: all .3s ease;
  }
  .slide-fade-leave-active {
    transition: all .3s ease;
  }
  .slide-fade-enter,
  .slide-fade-leave-to {
    transform: translateX(10px);
    opacity: 0;
  }
 
  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
  }
  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }
  .modal-container {
    width: 400px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }
  .modal-header {
    margin-top: 0;
    font-size: 24px;
  }
  .modal-body {
    margin: 0;
  }
  .modal-default-button {
    float: right;
  }
  .modal-enter {
    opacity: 0;
  }
  .modal-leave-active {
    opacity: 0;
    transition: all .3s ease;
  }
  /*.modal-enter-active {
    opacity: 0;
    transition: all .3s ease;
  }*/
  .modal-enter .modal-container,
  .modal-leave-active .modal-container,
  .modal-leave-to .modal-container {
    /*-webkit-transform: scale(1.1);*/
    transform: translateY(100px);
  }
</style>

<script>
import Ads from './Ads.vue'
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})



  export default {
    components: {
    'Ads': Ads
    },
    name: 'studenthomepage',
    data() {
      return {
        tabs: ['Overview', 'Upcoming Bookings', 'Past Bookings'],
        selectedTab: 'Overview',
        // put random values in bookings because backend services not working
        pastBookings:[],
        bookings: [],
        tutors:[],
        student: null,
        errorStudent: '',
        tutorForBooking: null,
        response:[],
        reviewIndex:'',
        reviewNullBookings:[]

      }
    },
    created(){
        // Initializing bookings from backend
        axios.get('http://localhost:8080/students/' + this.$route.params.user)
        .then(res => {
          this.student = res.data;
          console.log(this.student);
        })
        .catch(e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorStudent = errorMsg
        });

        
        axios.get('http://localhost:8080/students/' + this.$route.params.user + '/bookings').then(response => {
          var unsorted_bookings = response.data
          var today = new Date();
          var dd = today.getDate();
          var mm = today.getMonth()+1; //As January is 0.
          var yyyy = today.getFullYear();
          if(dd<10) dd='0'+dd;
          if(mm<10) mm='0'+mm;
          var today_string = yyyy + '-' + mm + '-' + dd


          for (let i = 0; i < unsorted_bookings.length; i++) {
            var bookingStart = new Date(unsorted_bookings[i].bookingDate)
            if(Date.parse(bookingStart) > Date.parse(today_string)){
              this.bookings.push(unsorted_bookings[i])
            }
            else{
              this.pastBookings.push(unsorted_bookings[i])
              if (unsorted_bookings[i].review == null){
                this.reviewNullBookings.push(unsorted_bookings[i])
              }
            }
          }
        })
        .catch(e => {
          this.errorBooking = e;
        })
      },
    methods:{

      deleteCurrentFunction: function(){
          alert("If you would like to cancel a booking please call this number: (514) 398-4455")
    },

    goToReviewPage:function(){
      var bookingToReview = this.reviewNullBookings[this.reviewIndex].bookingID
      this.$router.push({
        name:'review2',
        params:{
          user: this.$route.params.user,
          booking: bookingToReview,
        }
      })
    },

    checkReviewInput: function(value){
          console.log(value.srcElement.selectedIndex)
         this.reviewIndex = value.srcElement.selectedIndex;
       },


      goToHello: function() {
        this.$router.push({
          name: 'hello',
          params: {
            id: this.$route
          }
        })
      },
      goToCreateBooking: function(){
          this.$router.push({
          name: 'createbooking',
          params: {
            user: this.$route.params.user
          }
        })

      },

      goToEditProfile: function(){
          this.$router.push({
          name: 'editprofile',
          params: {
            user: this.$route.params.user
          }
        })

      },
       goToErrorPic: function(){
          this.$router.push({
          name: 'errorpic',
          params: {
            id: this.$route
          }
        })

      }
      

    }

  }
</script>
