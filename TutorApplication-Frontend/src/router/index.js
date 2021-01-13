import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import TutorApplication from '@/components/TutorApplication'
import SignUp from '@/components/SignUp'
import SelectCourse from '@/components/SelectCourse'
import Review from '@/components/Review'
import EditProfile from '@/components/EditProfile'
import StudentHomepage from '@/components/StudentHomepage'
import CreateBooking from '@/components/CreateBooking'
import Login2 from '@/components/Login2'
import ErrorPic from '@/components/ErrorPic'
import SignUp2 from '@/components/SignUp2'
import AdminCreateTutor from '@/components/AdminCreateTutor'
import CreateCourse from '@/components/CreateCourse'
import CreateCourse2 from '@/components/CreateCourse2'
import ImageSlideshow from '@/components/ImageSlideshow'
import Ads from '@/components/Ads'
import review2 from '@/components/review2'

Vue.use(Router)

export default new Router({
    routes: [{
            path: '/',
            name: 'hello',
            component: Hello
        },
        {
            path: '/imageslideshow',
            name: 'ImageSlideshow',
            component: ImageSlideshow
        },
        {
            path: '/review2/:user/:booking/',
            name: 'review2',
            component: review2
        },
        {
            path: '/ads',
            name: 'Ads',
            component: Ads 
        },
        {
            path: '/app',
            name: 'TutorApplication',
            component: TutorApplication
        },
        {

            path: '/signup2',
            name: 'SignUp2',
            component: SignUp2
        },

        {
            path: '/sele ctcourse',
            name: 'SelectCourse',
            component: SelectCourse
        },
        {
            path: '/review/:user/:booking',
            name: 'review',
            component: Review
        },
        {
            path: '/editprofile/:user',
            name: 'editprofile',
            component: EditProfile
        },
        {
            path: '/studenthomepage/:user',
            name: 'studenthomepage',
            component: StudentHomepage
        },
        {
            path: '/createbooking/:user',
            name: 'createbooking',
            component: CreateBooking
        },
        {
          path: '/login2',
          name: 'login2',
          component: Login2
        },
        {
          path: '/errorpic',
          name: 'errorpic',
          component: ErrorPic
          
        },
        {
            path: '/createtutor',
            name: 'createtutor',
            component: AdminCreateTutor
        },
        {
            path: '/createcourse2',
            name: 'createcourse2',
            component: CreateCourse2
        }

    ]
})