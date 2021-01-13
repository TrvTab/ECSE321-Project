<template>
    <div id="login">
    <div id=made style="line-height: 30px; width: 400px; border: 1px solid black;" align = "right">  
         <h2> Login </h2>
        <form style="line-height: 40px; width: px; border: 10px;" > 
            <input type="text" v-model="Username" placeholder="Username">
            <input type = "password" v-model="Password" placeholder="Password">
            <input type="submit" value = "Login" align="center" class ="btn" @click="createUser(newUsername, newPassword)">

        

      <span v-if="errorUser && !newUser" style="color:red">Error: {{errorUser}} </span>
      

    </div>
</template>

<script src="./Login.js"></script>

<style scoped>


   made{
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