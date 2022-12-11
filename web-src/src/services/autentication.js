export function isLogged(){
  console.log(window.localStorage.getItem('userToken'))  
}

export function setUser(user={username:'samuel'}){
  console.log(window.localStorage.setItem('userToken',JSON.stringify(user)))
}