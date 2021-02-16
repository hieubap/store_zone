const url_login = 'http://93.188.162.82:8080/login';
const login_url = 'http://93.188.162.82:8080/management.html';
const home_url = 'http://93.188.162.82:8080/index.html';

function login(){
  const form = document.getElementById('form_login');
  form.addEventListener('click',function (e){
    fetch(url_login,{
      method: 'post',
      headers:{'content-type':'application/json'},
      body: JSON.stringify({username: "admin", password: "admin"})
    }).then(function (response){
      if (response.status === 200)
      {location.replace(login_url);}
      else {location.replace(home_url);}
    })
  });
}
window.onload = function (){login();}