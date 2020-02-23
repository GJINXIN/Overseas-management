
function LoginCheck(form) { //防止提交空表单，检查输入是否合法
        if (form.name.value == "") {
            alert("请输入账号");
            return false;
        }
        else if (form.password.value == "") {
            alert("请输入密码");
            return false;
        }
        var reg=/^[0-9]{3,12}$/;
        var f=form.name.value.match(reg);
        if(f==null){
            alert("输入的账号不合法");
            return false;
        }
        return true;
}
function back(type){
    if(type=='staff'){
        window.location.href="http://127.0.0.1/SalarySystem/staffpage.jsp"
    }
    else
        window.location.href="http://127.0.0.1/SalarySystem/adminpage.jsp"
}
function check1(type) { //判断主管是否可以查看该部门员工信息,跳转到相应的页面
    if(type=='IT'){
        window.location.href="http://127.0.0.1/SalarySystem/staffcharts.jsp"

    }
}
function change(){//主管编辑员工页面
    var a=confirm("确定保存您的修改？");
    if(a==true)
        return true;
    else{
        window.location.reload();
        return false;
    }
}

function change2(){
    var a=confirm("确定保存您的修改？");
    if(a==true)
        return true;
    else{
        window.location.reload();
        return false;
    }
}
function deleteinfo(){
    var a=confirm("确定删除所选员工信息？");
    if(a==true){
        var arr=Array();
        var elem=document.getElementsByName("Fruits");
        for(var i=0;i<elem.length;i++){
            if(elem[i].checked){
                arr.push(elem[i].value);
            }
        }
        var str=arr.join("-");
        window.location.href="servlet/adDeleteTieziServlet?ids="+str;
    }
    else
        window.location.reload();
}

function logout(){
    if(confirm("您确定要退出当前用户吗？")){
        window.location.href="/SalarySystem/login.jsp";
    }
}
function change3(){
    var a=confirm("确定添加已填信息？");
    if(a==true)
        return true;
    else{
        window.location.reload();
        return false;
    }
}
function changepassword(){
    var t1=document.getElementById('oldppw');
    var t2=document.getElementById('nppw');
    var t3=document.getElementById('confirmppw');
    if(t1.value==""||t2.value==""||t3.value==""){
        alert("请将信息补全！");
        return false;
    }
    if(t2.value!=t3.value) {
        alert("密码不一致！");
        return false;
    }
    return(confirm("确定修改密码？"));
}