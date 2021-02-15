const url = 'http://93.188.162.82:8080/login';
const login_url = 'http://93.188.162.82:8080/management.html';
const home_url = 'http://93.188.162.82:8080/index.html';

function login(){
  const form = document.getElementById('form_login');

  form.addEventListener('click',function (e){
    // e.preventDefault();

    console.log('login function');

    fetch(url,{
      method: 'post',
      headers:{
      'content-type':'application/json'
    },
      body: JSON.stringify({
        username: "admin",
        password: "admin"
      })
    }).then(function (response){
      console.log('unsuccessful: ' + response.status);
      if (response.status === 200)
      {
        location.replace(login_url);
        console.log('successful');
      }
      else
      {
        location.replace(home_url);
      }
    })
    .then(function (data){
      console.log(data);
      console.log('log');
    })

    console.log('submit .... *******************');
  });
}
window.onload = function (){
  login();
}