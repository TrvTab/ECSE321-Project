<template>
  <div class = login>
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
      <div class="page"> 
    
    <div class="loginframe" style="line-height: 30px; width: 400px; border: 1px solid black;" align = "right">  
         <h2> Login </h2>
        <form style="line-height: 40px; width: px; border: 10px;" > 
            <input type="text" placeholder="Username" id="usr" class="form-control form-control-lg">
            <input type = "password"  placeholder="Password" id="psw" class="form-control form-control-lg">
            <input type="submit" value = "Login" align="center" class ="btn" @click="goToStudentHomepage">
        
        </form>
         <p id="demo"></p> 

      <span v-if="errorUser && !newUser" style="color:red">Error: {{errorUser}} </span>
      

       <!-- </div>-->
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

// MUST BE CHANGED TO IMPLEMENT PASSWORD VERTIFICATION
var checkInput = async (inputName, inputPass) => {
    if (inputName.trim().search(/\S{1,}/) == -1) {
      return 1; // Bad input
    } else {
      try {
        // We know this is a gross url call
        let res = await axios.get('http://localhost:8080/students/passwordcheck/'+inputName + "?password=" + inputPass);
        console.log(res.data)
        console.log("here|")
        console.log(res)
        if (res.data.value == 0) {
          console.log("reaches 0")
          return 0; // User found and correct password was inputted
        }else if(res.data.value == 1){
          return 4; // User name is found but wrong password
        }else if (res.data.value == 2) {
          return 2; // User non-existant
        }
      } catch (e) {
        return 3; // Error
      }

    }
    return false;
  }


export default {
    name: 'login' ,
    data() {
        return {
            student: null,
            res:'',

        }
    },

    methods: {
        /*goToStudentHomepage: function() {
        this.$router.push({
          name: 'studenthomepage',
          params: {
            id: this.$route
          }
        })
      },*/
      goToStudentHomepage: async function() {
        var inputName = document.getElementById("usr").value;
        var inputPass = document.getElementById("psw").value
        var result = await checkInput(inputName, inputPass);
        if (result == 0) {
          this.$router.push({
            name: 'studenthomepage',
            params: {
              user: inputName
            }
          })
        } else {
          document.getElementById("usr").className = 'form-control form-control-lg is-invalid'
          document.getElementById("demo").style.color = 'red'
          if (result == 1) {
            document.getElementById("demo").innerHTML = "Please enter a valid username and password";
          } else if (result == 2) {
            document.getElementById("demo").innerHTML = "There are no users with the username " + inputName;
          }  else if (result == 3) {
            document.getElementById("demo").innerHTML = "There are no users with the username " + inputName;
          }else if (result == 4){
            document.getElementById("demo").innerHTML = "Password is incorrect";
          }
        };
        //var result = checkInput(input).then(ret);
        console.log("hey")

      },
  
  
    }
  }

</script>

<style scoped>


  .loginframe {
    background:white;
    margin: 50px 515px 544px;
    width: 300px;
    padding: 75px;
    text-align: center;
    /* box-shadow*/ 
    -webkit-box-shadow: rgba(0,0,0,0.2) 0px 1px 3px;
    -moz-box-shadow: rgba(0,0,0,0.2) 0px 1px 3px;
    box-shadow: rgba(0,0,0,0.2) 0px 5px 3px;
    background-color:white;
   }
  .btn{
    display: inline-block;
    border: none;
    background: #333;
    color: cyan;
    padding: 7px 20px;
    cursor: pointer;
  }
  .btn:hover{
      background: pink
  }
  input [type=password] {
    padding: 50px 12px;
    border: 10px black;
    border-radius: 100px;
}

input[type=text] :focus {
  border: 10px black;
}
.header {
  padding: 0px;
  text-align: center;
  color: black;
  font-size: 30px;
  
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