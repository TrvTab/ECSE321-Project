<template>
 <div  style="line-height: 30px; width: 400px; border: 1px solid black;" align = "right">  
         <h2> Sign Up </h2>
        <form style="line-height: 40px; width: px; border: 10px;"> 
            <input type="text" placeholder="First Name" id="firstName" class="form-control form-control-lg">
            <input type="text" placeholder="Last Name" id="lastName" class="form-control form-control-lg">
            <input type="text" placeholder="Username" id="usr" class="form-control form-control-lg">
            <input type="text" placeholder="Rating" id="rating" class="form-control form-control-lg">
            <input type = "password" placeholder="Password" id="psw" class="form-control form-control-lg">
            <input type="submit" value = "Create Tutor" align="center" class ="btn" @click="createTutorHandler">
           
        
        </form>
         <p id="demo"></p> 
         
        
      <span v-if="errorTutor && !newTutor" style="color:red">Error: {{errorTutor}} </span>
      

    </div>
</template>


<script src="./CreateTutor.js"></script>

<style scoped>

.tre {
    background-image: url("https://pbs.twimg.com/media/EJxKj4WXYAQka6j?format=jpg&name=4096x4096");
    background-color: #333;
}
  div {
    background:white;
    margin: 0 auto;
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
</style>

