const number_bill = 10;
const number_menu = 10;
var page_bill = 0;
var page_menu = 0;

const url_menu = 'http://93.188.162.82:8080/food/search?name=';
const url_menu_delete = 'http://93.188.162.82:8080/food/delete?id=';

const url_bill = "http://93.188.162.82:8080/bill/search?name=";
const url_bill_delete = "http://93.188.162.82:8080/bill/delete?id=";

const url_bill_dashboard = "http://localhost:8080/bill/dashboard?from=2021-02-01&to=2021-03-01";
function getDaysOfMonth(year, month) {return new Date(year, month, 0).getDate();}

function convertPrice(price){
  var len = price.length;
  if (len < 7)
    return price.substring(0,len-3)+"."+price.substring(len-3);
  if (len < 10)
    return price.substring(0,len-6)+"."+price.substring(len-6,len-3)+"."+price.substring(len-3);
  if (len < 13)
    return price.substring(0,len-9)+"."+price.substring(0,len-6)+"."+price.substring(len-6,len-3)+"."+price.substring(len-3);
}

function chart() {
  var x = [];
  var y = [];
  var date_now = new Date();
  for (i = 1; i <= getDaysOfMonth(date_now.getFullYear(), date_now.getMonth() + 1); i++) {
    x.push(i + '/' + (date_now.getMonth() + 1));
    y.push(0);
  }

  var chart = document.getElementById('chart').getContext('2d');
  Chart.defaults.global.defaultFontColor = '#000';
  Chart.defaults.global.defaultFontFamily = 'Lato';
  Chart.defaults.global.defaultFontSize = 12;

  fetch(url_bill_dashboard, {
    method: 'get'
  }).then(function (response) {
    if (response.status === 200) {
      response.json().then(function (text) {
        var data = JSON.parse(JSON.stringify(text)).data;
        console.log(data);

        for (i = 0; i < data.length; i++)
            y[data[i].day-1] = data[i].soluong;
        console.log(y);

        var lineChart = new Chart(chart, {
          type: 'bar',
          data: {
            labels: x,
            backgroundColor: "rgba(155,0,0,1)",
            datasets: [{
              fillColor: "rgba(155,0,0,1)",
              borderWidth: "20px",
              backgroundColor: "rgba(155,000,0,1)",
              strokeColor: "rgba(0,155,0,1)",
              data: y
            }]
          },
          options: {
            title: {
              display: true,
              text: 'Số đơn trong tháng',
              fontSize: 25
            },
            legend: {
              display: true,
              position: 'top',
              labels: {
                fontColor: '#000'
              }
            },
            tooltips: {
              enabled: true
            }
          }
        });
      })
    }
  });
}

function showBill(){

  fetch(url_bill,{
    method: 'get',
  }).then(function (response){
    if (response.status === 200)
    {
      console.log('successful');
      response.json().then(function (text){
        var body = JSON.parse(JSON.stringify(text));
        var arr = body.data;

        var list = '<div>';
        for (i = 0;i<arr.length&&i<number_bill;i++){
          list += '<div onclick="changeHidden('+arr[i].id+')" class="list_food_order">'
              + '<div style="float: left;margin-left: 20px;width: 300px"><div style="float: top;font-size: 17px"><b>id</b>: '+arr[i].id+' </div>'
              + '<div style="float: top;font-size: 17px"><b>người đặt</b>: '+arr[i].user.name+'</div>'
              + '<div style="float: top;font-size: 17px"><b>thời gian</b>: '+arr[i].date+'</div>'
              + '</div>'
              + '<div style="float: left;margin-left: 20px"><div style="float: top;font-size: 17px"><b>tổng tiền</b>: '+convertPrice(arr[i].total+'')+' </div>'
              + '<div style="float: top;font-size: 17px"><b>trạng thái</b>: '+arr[i].status+'</div>'
              + '</div>'
              + '<div><button class="btn btn-default" onclick="deleteBill('+arr[i].id+')" style="margin: 30px 20px 0 0">xóa</button>'
              + '</div>'
              + '</div>\n'
              + '<div id="bill_id_'+arr[i].id+'" style="display: none">'
              ;
          var hiddenDiv = '<div style="font-size: 17px">';
          hiddenDiv += '<div><table>'
              + '<tr>'
              + '<th>stt</th>'
              + '<th>tên</th>'
              + '<th>số lượng</th>'
              + '<th>giá</th>'
              + '</tr>';
          for (j = 0;j<arr[i].foods.length;j++){
            hiddenDiv += '<tr>'
                + '<th>'+(j+1)+'</th>'
                + '<td>'+arr[i].foods[j].food.name+'</td>'
                + '<td>'+arr[i].foods[j].number+'</td>'
                + '<td>'+arr[i].foods[j].price+'</td>'
                + '</tr>';
          }
          hiddenDiv+='<tr>'
              + '<th></th>'
              + '<th></th>'
              + '<th> tổng tiền: </th>'
              + '<th>'+arr[i].total+'</th>'
              + '</tr></table></div></div>';

          list += hiddenDiv+'</div>';
        }
        list += '</div>';
        document.getElementById("list_bill").innerHTML = list;

      });
    }
    else
    {
      console.log('unsuccessful: ' + response.status);
    }
  })
}

function changeHidden(id) {
  var x = document.getElementById("bill_id_"+id);

  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

function showListMenu(){

  fetch(url_menu,{
    method: 'get',
  }).then(function (response) {
    if (response.status === 200) {
      response.json().then(function (text){
        var body = JSON.parse(JSON.stringify(text));
        var arr = body.data;

        var list = '<div>';
        for (i = 0;i<arr.length&&i<number_bill;i++){
          var price = arr[i+page_menu].price+0;
          list += '<div class="list_food_order">'
              + '<img src="images/food'+(i+1)+'.jpg" class="image">'
              + '<div style="width:300px;float: left;margin-left: 20px"><div style="float: top;font-size: 17px"><b>id</b>: '+arr[i+page_menu].id+' </div>'
              + '</div>'
              + '<div style="float: left;margin-left: 20px"><div style="float: top;font-size: 17px"><b>tên</b>: '+arr[i+page_menu].name+' </div>'
              + '<div style="float: top;font-size: 17px"><b>giá</b>: '+price+'đ </div>'
              + '</div>'
              + '<div><button class="btn btn-default" onclick="" style="margin: 30px 20px 0 0">xóa</button>'
              + '<button class="btn btn-default" onclick="" style="margin: 30px 20px 0 0">chỉnh sửa</button>'
              + '</div></div>\n';
        }
        list += '</div>';
        document.getElementById("list_menu").innerHTML = list;

      })
    }
  });
}

function goMenuR(){
  page_menu+=number_menu;
  showListMenu();
}
function goMenuL(){
  if (page_menu>0)
  page_menu-=number_menu;
  showListMenu();
}

function goBillR(){
  page_bill+=number_bill;
}
function goBillL(){
  if (page_bill>0)
    page_bill-=number_bill;
}

function deleteMenu(id){
  fetch(url_menu_delete+id,{
    method: 'delete',
  }).then(function (response) {
    if (response.status === 200){
      showListMenu();
    }
  });
}

function deleteBill(id){
  fetch(url_bill_delete+id,{
    method: 'delete',
  }).then(function (response) {
    if (response.status === 200){
      showBill();
    }
  });
  showBill();
}


window.onload = function (){
  showBill();
  showListMenu();
  chart();
}
