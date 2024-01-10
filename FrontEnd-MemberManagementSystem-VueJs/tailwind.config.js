/** @type {import('tailwindcss').Config} */
module.exports = {
  plugins: [
    require('flowbite/plugin')
],
  content: ["./src/**/*.{html,js,vue}", "./node_modules/flowbite/**/*.js"],
  theme: {
    extend: {},
  },
  plugins: [],
}

