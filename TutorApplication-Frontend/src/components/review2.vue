<template>
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
        <div class="reviewpage">   
                <h2> Review {{$route.params.tutor}}</h2>
                <p> Please leave a rating and any comments you might have about the tutor you worked with.
                    All your reviews are important to us so we can keep providing you with the best in the business!
                </p>
            <form action="/html/tags/html_form_tag_action.cfm" method="post">
                    <div>
                        <textarea name="comments" id="comments" style="font-family:sans-serif;font-size:1.2em;">
                        </textarea>
                    </div>
                    <div>
                     
                     <input type="text" placeholder="Rating /5" id="rating">
                      </div>
                </form>

                <div class="dropdown">
                    <button class="reviewButton" @click="checkReviewInput">Submit Review</button>
                    <p id="demo"></p> 
                        <div class="dropdown-content">
                        <li v-for="(review, i) in reviews" v-bind:key="`reviews-${i}`" style="list-style-type: none;">
                            <a href="#"> </a></li>
                        </div>
                </div>
        </div>  
      </div> 
    </div>
  </div>
</template>

<script src="./Review.js">
</script>

<style>

/* Website Frame */
.reviewButton{
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
.reviewButton:hover {
color: black !important;
background: pink;
border-color: pink !important;
transition: all 0.4s ease 0s;
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



.dropbtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px;
  min-width: 160px;
  font-size: 16px;
  border: none;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {background-color: #ddd;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: #3e8e41;}
</style>