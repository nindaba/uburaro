/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        primary: '#61B297', // Removed FF for opacity, Tailwind handles opacity separately
        'light-green': 'rgba(165, 222, 201, 0.89)',
        danger: '#E34A4A', // Assuming $danger is $red
        'light-grey': '#D3D6DA',
        'dark-grey': '#c2c2c2',
        'font-color': '#4B4B4B', // From _variable.scss, might be useful
        'bg-color': '#F8F9FA',   // From _variable.scss, might be useful
        pink: 'rgba(227, 74, 74, 0.5)',
      },
      spacing: {
        'spacing': '20px', // For $spacing
      },
      height: {
        'top-nav': '80px', // For $top-nav-height
      },
      width: {
        'content-left': '260px', // For $content-left-width
        'input': '240px', // For $input-width
      },
      margin: {
        'side-nav-left': '30px', // For $side-nav-left-margin
      }
    },
  },
  plugins: [],
}
