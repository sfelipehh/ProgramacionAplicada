/**
 * @type {import('gatsby').GatsbyConfig}
 */
module.exports = {
  siteMetadata: {
    title: `web-src`
  },
  plugins: [
    {
    resolve: `gatsby-plugin-layout`,
    options: {
      component: require.resolve(`./src/components/layout`),
      },
    },
  ],
};