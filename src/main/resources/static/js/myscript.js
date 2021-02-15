var url = "http://93.188.162.82:8080/food/search?name=";
var url_order = "http://93.188.162.82:8080/bill/create";

var menu_food = [];

var arr_order = [];
var soluong = [];
var number_line = 5;

function convertPrice(price){
  var len = price.length;
  if (len < 7)
  return price.substring(0,len-3)+"."+price.substring(len-3);
  if (len < 10)
    return price.substring(0,len-6)+"."+price.substring(len-6,len-3)+"."+price.substring(len-3);
  if (len < 13)
    return price.substring(0,len-9)+"."+price.substring(0,len-6)+"."+price.substring(len-6,len-3)+"."+price.substring(len-3);
}

function loadMenu(){
  var xmlHttp = new XMLHttpRequest();
  var name_search = document.getElementById('name_search').value;
  console.log(name_search);

  xmlHttp.open("GET",url+name_search,true);
  xmlHttp.onreadystatechange = function (){
    var parse = JSON.parse(xmlHttp.responseText);
    var menu = '';
    menu_food = parse.data;
    var x = (parse.data.length/number_line);

    for (i = 0;i<x;i++){
      menu += '<div class="row">';
      for (j = 0;j<number_line;j++)
        if (i*number_line+j<parse.data.length)
        {
          var price = parse.data[i * number_line + j].price+'';
          menu += '<div class="col-md-1 box_menu" >\n'
            + '<a>'
            + '<div><img src="images/food' + ((j + i*number_line) % 20 + 1)
            + '.jpg" class="box_menu_image"></div>\n'
            + '<div> ' + parse.data[i * number_line + j].name + ' </div>\n'
            + '<div>'
            + '<div style="float: left"><div class="price"> ' + convertPrice(price) + 'đ </div>\n'
            + '<p style="float: bottom;font-size: 15px;color: #909090">đã bán: 0 </p></div>'
            + `<button onclick='order(`+JSON.stringify(parse.data[i*number_line+j])+`)' class="btn btn-default"> Đặt </button></div>`
            + '</a>\n'
            + '</div>';
        }
      menu += '</div>';
    }
    document.getElementById("showfood").innerHTML = menu;
  };
  xmlHttp.send();
}

window.onload = function (){
  loadMenu();
  var input = document.getElementById("name_search");
  input.addEventListener("keyup", function(event) {
    if (event.keyCode === 13) {
      document.getElementById("search_btn").click();
      console.log('btn click');
      input.blur();
    }
  });
}

function order(food){
  for (i = 0;i< arr_order.length;i++){
    if (arr_order[i].id == food.id){
      return;
    }
  }
  arr_order.push(food);
  soluong.push(1);
  console.log(soluong);
  showListOrder();
}

function showListOrder(){
  var list = '<div>';
  var sum = 0;
  for (i = 0;i<arr_order.length;i++){
    var price = arr_order[i].price+0;
    list += '<div class="list_food_order">'
        + '<img src="images/special-menu-2.jpg" class="image_order">'
        + '<div style="float: left;margin-left: 20px"><div style="float: top;font-size: 17px">'+arr_order[i].name+' </div>'
        + '<div style="float: top" class="price">'+convertPrice((price*soluong[i])+'')+'đ </div>'
        + '</div>'
        + '<div><button class="btn btn-default" onclick="deleteOrder('+i+')" style="margin: 30px 20px 0 0">xóa</button>'
        + '<input type="number" class="soluong" style="margin-top: 30px" id="soluong'+i+'" value="'+soluong[i]+'" onmouseout="test()">'
        + '<p style="float: right;margin-right: 10px;margin-top: 30px">số lượng</p></div>'
        + '</div>\n';
    sum += arr_order[i].price*soluong[i];
  }
  list += '</div>';
  list += '<div style="float: right;font-size: 20px"> Tổng số tiền:'+convertPrice(sum+'')+'đ </div>';

  document.getElementById("list_order_food").innerHTML = list;
}

function test(){
  for (i=0;i<soluong.length;i++)
  {
    if (document.getElementById('soluong'+i).value != null && document.getElementById('soluong'+i).value > 0)
    soluong[i] = document.getElementById('soluong'+i).value;

    console.log("active: "+soluong[i]);
  }
  showListOrder();
}

function deleteOrder(id){
  var a1 = arr_order.slice(0,id);
  var a2 = arr_order.slice(id+1,arr_order.length);
  arr_order = a1.concat(a2);

  var a1 = soluong.slice(0,id);
  var a2 = soluong.slice(id+1,soluong.length);
  soluong = a1.concat(a2);

  console.log(arr_order);
  showListOrder();
}

function book(){
  console.log(arr_order);
  var body = '{"listFoodsOrder":{';
  for (i=0;i<arr_order.length-1;i++){
    body += '"'+arr_order[i].id+'":'+soluong[i]+',';
  }
  body += '"'+arr_order[arr_order.length-1].id+'":'+soluong[arr_order.length-1]+'},';
  body += '"userId":1'
      + '}';
  console.log(JSON.parse(JSON.stringify(body)));

  fetch(url_order,{
    method: 'post',
    headers:{
      'content-type':'application/json'
    },
    body: JSON.parse(JSON.stringify(body))
  }).then(function (response) {
    if (response.status === 200) {
        modal.style.display = "block";
    }
  });
}
var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];

span.onclick = function() {
  modal.style.display = "none";
}