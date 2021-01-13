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
    
    <div class="signupframe" style="line-height: 30px; width: 400px; border: 1px solid black;" align = "right">  
                <h2> Sign Up </h2>
                <form style="line-height: 40px; width: px; border: 10px;"> 
                    <input type="text" placeholder="First Name" id="firstName" class="form-control form-control-lg">
                    <input type="text" placeholder="Last Name" id="lastName" class="form-control form-control-lg">
                    <input type="text" placeholder="Username" id="usr" class="form-control form-control-lg">
                    <input type = "password" placeholder="Password" id="psw" class="form-control form-control-lg">
                    <input type = "password" placeholder="Confirm Password" id="confirmpsw" class="form-control form-control-lg" >
                    <input type="submit" value = "Sign Up" align="center" class ="btn" @click="goToStudentHomepage">
                    <input type="cancel" value = "Cancel" align="center" class ="btn" @click="goToHello">
                
                </form>
                <p id="demo"></p> 
                
                
              
              

          </div>
    </div>
  </div>
  </div>
    
</template>


<script src="./SignUp.js"></script>

<style scoped>

.tre {
    background-image: url("https://pbs.twimg.com/media/EJxKj4WXYAQka6j?format=jpg&name=4096x4096");
    background-color: #333;
}
  .signupframe{
    background:white;
    margin: 50px 515px 544px;
    width: 300px;
    padding: 75px;
    text-align: center;
    /* box-shadow */
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
  color: white;
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

