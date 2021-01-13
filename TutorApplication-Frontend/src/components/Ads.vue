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
        '<a href="https://www.amazon.com/"><div class="amazon-slide"></div></a>',
        '<a href="https://www.apple.com/ca/"><div class="apple-slide"></div></a>',
        '<a href="https://www.netflix.com/"><div class="netflix-slide"></div></a>',
        '<a href="https://www.spotify.com/ca-en/"><div class="spotify-slide"></div></a>',
        '<a href="https://www.match.com/"><div class="match-slide"></div></a>',
        '<a href="https://www.christianmingle.com/en-us"><div class="mingle-slide"></div></a>',
    ],
    };
},
};

</script>

<style>
  .amazon-slide {
    align-items: center;
    background-image: url("https://cdn.sellerapp.com/Blog_images/sellerapp-header-image-amazon-sellers-guide.png");
    background-size: contain;
    color: black;
    display: flex;
    font-size: 1.5rem;
    justify-content: center;
    min-height: 100px;
    z-index: -1;
  }
  .apple-slide {
    align-items: center;
    background-image: url("https://i.pinimg.com/originals/18/33/cc/1833cc955726fbb5faa48c2e577bfcf0.png");
    background-size: contain;
    color: black;
    display: flex;
    font-size: 1.5rem;
    justify-content: center;
    min-height: 100px;
    z-index: -1;
  }
  .spotify-slide {
    align-items: center;
    background-image: url("https://powerdigitalmarketing.com/wp-content/uploads/2018/03/How-to-Advertise-on-Spotify-01-812x271.jpg");
    background-size: contain;
    color: black;
    display: flex;
    font-size: 1.5rem;
    justify-content: center;
    min-height: 100px;
    z-index: -1;
  }
  .netflix-slide {
    align-items: center;
    background-image: url("https://www.similarweb.com/blog/wp-content/uploads/2014/06/netflix-creative.png");
    background-size: contain;
    color: black;
    display: flex;
    font-size: 1.5rem;
    justify-content: center;
    min-height: 100px;
    z-index: -1;
  }
  .match-slide {
    align-items: center;
    background-image: url("https://match.mediaroom.com/image/newapp1700.png");
    background-size: contain;
    color: black;
    display: flex;
    font-size: 1.5rem;
    justify-content: center;
    min-height: 100px;
    z-index: -1;
  }
  .mingle-slide {
    align-items: center;
    background-image: url("https://cminglestudy.files.wordpress.com/2013/09/christianmingle.png");
    background-size: contain;
    color: black;
    display: flex;
    font-size: 1.5rem;
    justify-content: center;
    min-height: 100px;
    z-index: -1;
  }

  
</style>