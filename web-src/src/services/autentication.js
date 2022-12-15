export function isLogged(){
  setUser()
  console.log(window.localStorage.getItem('userToken'))
  return true  
}

export function setUser(user={username:'samuel'}){
  console.log(window.localStorage.setItem('userToken',JSON.stringify(user)))
}