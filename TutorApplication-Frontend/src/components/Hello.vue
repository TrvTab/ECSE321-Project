<template>
<div class = hello>
    <div class="frame" id="top-container">
      <div class="banner">
        <div class="topBanner">
            <input type="submit" class="signUpButton" @click="goToLogin" value="Login" >
            <input type="submit" class="loginButton" @click="goToSignUp2" value="Sign Up">
          <!-- <input type="submit" class="loginButton" @click="clearAll" value="Clear"> -->
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
      <div class="page">
        <div class="hello" id ="buttons">
          <ImageSlideshow></ImageSlideshow>
              </div>
        
    </div>
  </div>
</div>
</template>

<script>
import ImageSlideshow from './ImageSlideshow.vue'
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
    'ImageSlideshow': ImageSlideshow
  },
  name: 'hello',
  data(){
    return {
      courses:[],
      errorApp: '',
    }
  },
created: function () {
		axios.get('http://localhost:8080/courses').then(response => {
			this.courses = response.data;
		})
			.catch(e => {
				this.errorApp = e;
			});
	},

methods: {
  goToSignUp2: function() {
      console.log(this.courses.length)
      if (this.courses.length <=1){
           axios.post('http://localhost:8080/initialize/alpha').then(res => {
            console.log("Initialized");
          }).catch (e=>{
            console.log("error post")
            this.errorApp = e;
          })
      }
    
        this.$router.push({
          name: 'SignUp2',
          params: {
            id: this.$route
          }
        })
      },                
  goToLogin: function() {
    this.$router.push({
      name: 'login2',
      params: {
        id: this.$route
      }
    })
  },
  clearAll: function(){
    axios.delete('http://localhost:8080/clear')
    console.log(this.courses.length)
  }

}

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.loginButton{
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
.loginButton:hover {
color: black !important;
background: pink;
border-color: pink !important;
transition: all 0.4s ease 0s;
}

.signUpButton{
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
.signUpButton:hover {
color: black !important;
background: pink;
border-color: pink !important;
transition: all 0.4s ease 0s;
}
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
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

z-index: 0;
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
