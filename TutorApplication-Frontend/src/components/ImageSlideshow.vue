<template>
  <carousel :data="data"></carousel>
</template>

<script>
import Vue from 'vue';
import VueCarousel from '@chenfengyuan/vue-carousel';

Vue.component(VueCarousel.name, VueCarousel);

export default {
data() {
    return {
    data: [
        '<div class="example-slide1"><b>Be like a swan! Graceful but furiously paddling through your exams!</b> </div>',
        '<div class="example-slide2"><b>The best tutors in the business are here to help you with all your classes!</b></div>',
        '<div class="example-slide3"><b>Swan Tutoring: The platform that helps you achieve success!</b></div>',
    ],
    };
},
};
</script>

<style>
  .example-slide1 {
    align-items: center;
    background-image: url("https://media.giphy.com/media/KcmvqugQMiDgPscbTR/source.gif");
    
    background-size: cover;
    color: black;
    display: flex;
    font-size: 1.5rem;
    justify-content: center;
    min-height: 798px;
    z-index: -1;
  }

  .example-slide2 {
    align-items: center;
    background-image: url("https://res.cloudinary.com/highereducation/image/upload/v1533591754/TheBestColleges.org/study-notebooks.jpg");
    
    background-size: cover;
    color: black;
    display: flex;
    font-size: 1.5rem;
    justify-content: center;
    min-height: 798px;
    z-index: -1;
  }

  .example-slide3 {
    align-items: center;
    background-image: url("https://www.thesil.ca/wp-content/uploads/2019/01/Peer-Tutoring-Network-Photo-1024x683.jpg");
    
    background-size: cover;
    color: black;
    display: flex;
    font-size: 1.5rem;
    justify-content: center;
    min-height: 798px;
    z-index: -1;
  }
  
</style>