import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import store from '../store'
import Information from '@/components/Information'
import emergencytakein from '@/components/emergencytakein'
import changeinformation from '@/components/changeinformation'
import allpatients from '@/components/allpatients'
import sickroommypatient from '@/components/sickroommypatient'
import doctormypatient from '@/components/doctormypatient'
import mainnursemypatient from '@/components/mainnursemypatient'
import mainnursenewpatient from '@/components/mainnursenewpatient'
import doctormygoodpatient from '@/components/doctormygoodpatient'
import sickroomrecord from '@/components/sickroomrecord'
import doctorrecord from '@/components/doctorrecord'
import doctormainnurse from '@/components/doctormainnurse'
import doctorallsickroom from '@/components/doctorallsickroom'
import mainnurseallsickroom from '@/components/mainnurseallsickroom'
import mainnursebed from '@/components/mainnursebed'
import mainnurseaddsickroom from '@/components/mainnurseaddsickroom'
import doctorchecksickroompatient from '@/components/doctorchecksickroompatient'
import doctorchangepatientinformation from '@/components/doctorchangepatientinformation'
import logout from '@/components/logout'

Vue.use(Router)

export const router = new Router({
  linkActiveClass:'is-active',
  mode:'history',//改为history模式
  routes: [
    {
      path: '/',
      redirect:'/Login',
      meta:{
        keepalive:true,
        keepinitial:true,
      }
    },

    {
      path: '/Login',
      name: 'Login',
      component: Login,
      meta:{
        keepalive:true,
      }
    },
    {
      path: '/emergencytakein',
      name: 'emergencytakein',
      component: emergencytakein,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },
    {
      path: '/allpatients',
      name: 'allpatients',
      component:  allpatients,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },

    {
      path: '/sickroommypatient',
      name: 'sickroommypatient',
      component:  sickroommypatient,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },
    {
      path: '/doctormypatient',
      name: 'doctormypatient',
      component:  doctormypatient,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },
    {
      path: '/mainnursemypatient',
      name: 'mainnursemypatient',
      component:  mainnursemypatient,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },
    {
      path: '/mainnursenewpatient',
      name: 'mainnursenewpatient',
      component:  mainnursenewpatient,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },
    {
      path: '/mainnursebed',
      name: 'mainnursebed',
      component:  mainnursebed,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },

    {
      path: '/doctorchecksickroompatient',
      name: 'doctorchecksickroompatient',
      component:  doctorchecksickroompatient,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },

    {
      path: '/doctormygoodpatient',
      name: 'doctormygoodpatient',
      component:  doctormygoodpatient,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },


    {
      path: '/sickroomrecord',
      name: 'sickroomrecord',
      component:  sickroomrecord,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },
    {
      path: '/doctorrecord',
      name: 'doctorrecord',
      component:  doctorrecord,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },

    {
      path: '/Information',
      name: 'Information',
      component: Information,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },

    {
      path: '/doctormainnurse',
      name: 'doctormainnurse',
      component: doctormainnurse,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },
    {
      path: '/doctorallsickroom',
      name: 'doctorallsickroom',
      component: doctorallsickroom,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },
    {
      path: '/mainnurseallsickroom',
      name: 'mainnurseallsickroom',
      component: mainnurseallsickroom,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },
    {
      path: '/mainnurseaddsickroom',
      name: 'mainnurseaddsickroom',
      component: mainnurseaddsickroom,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },

    {
      path: '/doctorchangepatientinformation',
      name: 'doctorchangepatientinformation',
      component: doctorchangepatientinformation,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },

    {
      path: '/changeinformation',
      name: 'changeinformation',
      component: changeinformation,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },

    {
      path: '/logout',
      name: 'logout',
      component:logout,
      meta:{
        requireAuth: true,
        keepalive:true,
        keepaside:true,
        keepinitial:true,
      }
    },


  ]
})

// 前端登录拦截
router.beforeEach(function (to, from ,next) {
  if (to.matched.some(record => record.meta.requireAuth)) {
    if (store.state.token) {
      next()
    } else {
      next({
        path: '/Login',
        query: {redirect: to.fullPath} // 登录成功之后重新跳转到该路由
      })
    }
  } else {
    next()
  }
})
