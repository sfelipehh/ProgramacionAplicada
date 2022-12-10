exports.onCreatePage = ({ page, actions }) => {
  const { createPage } = actions
  console.log(page)
  createPage(page)
}