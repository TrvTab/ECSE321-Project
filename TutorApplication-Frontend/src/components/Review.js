import axios from 'axios'
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


export default {
    name: "review",
    data(){
      return{
        Review: '',
        comment: '',
        rating: '',
        errorReview:'',
        
      }
    },


    created: function(){
      
    },
    

    methods: {
      createReview: function (inputRating, inputComment){
        axios.post('http://localhost:8080/reviews/'+ makeid(7)+ '?bookingId=' + this.$route.params.booking + "&rating=" 
        + inputRating + '&comment=' + inputComment).then(response =>{
          console.log(response.data)
          console.log(response)
          this.$router.push({
            name: 'studenthomepage',
            params: {
              user: this.$route.params.user
            }
          })
  
        }).catch(e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorReview = errorMsg
        });
        },

      checkReviewInput: function(){

        var inputrating = document.getElementById("rating").value;
        var rawComment = document.getElementById("comments").value;
        var inputComment = encodeURI(rawComment);
        this.createReview(inputrating, inputComment)
        
      },
      goBackToStudentHomepage: function() {
        this.$router.push({
          name: 'studenthomepage',
          params: {
            user: this.$route.params.user
          }
        })
      },
      goToHello: function() {
        this.$router.push({
          name: 'hello',
          params: {
            id: this.$route
          }
        })
      },


       
            
        

        submitReview: function(){

        }
    }
}
