export default {
  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'NuxtProject',
    htmlAttrs: {
      lang: 'en'
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
    // "element-ui/lib/theme-chalk/index.css"
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    '@/plugins/element-ui',
    // '@/plugins/axios'
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    '@nuxtjs/axios',
    '@nuxtjs/proxy',
  ],

  axios: {
    proxy: true // Can be also an object with default options
  },
  proxy: {
    '/api': {
    changeOrigin: true,
    target: 'http://localhost:7777/', // 允许跨域的服务器地址
    pathRewrite: {
      '^/api': '/'
    }
   }
  },
  server: {
    host: 'localhost',
    port: 8080
  },
  build: {
    

  }
}
