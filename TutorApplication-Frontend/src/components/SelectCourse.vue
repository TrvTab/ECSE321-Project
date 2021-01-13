<template>
  <div class = SelectCourse>
    <h2>Select A Course!</h2>
    <p>Select the course that you wished to be tutored in!
    We'll show you a list of experienced tutors who can help you out!</p>

  <div class="dropdown">
      <button class="dropbtn">Courses</button>
        <div class="dropdown-content">
            <li v-for="(course, i) in courses" v-bind:key="`course-${i}`" style="list-style-type: none;">
              <a href="#"> {{course.courseId}}</a>
            </li>
        </div>
    </div>
  </div>
  
</template>
<script src="./javascript/SelectCourse.js"></script>
<style>

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