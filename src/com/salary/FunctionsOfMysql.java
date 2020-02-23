/**
 * auther:xpl
 * create date:2019.1.8
 */
package com.salary;

import java.sql.*;
import java.util.ArrayList;

public class FunctionsOfMysql {
    private int StaffConnState=0;//数据库员工用户登录状态,0未登录，1已登录
    private int AdminConnState=0;//数据库管理员用户登录状态，同上
    private Connection conn=null;
    public FunctionsOfMysql(){}
    public void getStaffConn(){ //1数据库员工账号登录
        String url = "jdbc:mysql://localhost:3306/salarysystem"+"?serverTimezone=GMT%2B8";//后面不加时区会报错
        String user="root";
        String password="666634";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        StaffConnState=1;
    }

    public void getAdminConn(){ //2数据库管理员用户登录
        String url = "jdbc:mysql://localhost:3306/salarysystem"+"?serverTimezone=GMT%2B8";//后面不加时区会报错
        String user="root";
        String password="666634";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AdminConnState=1;
    }

    public int StaffLogin(String id,String pw) throws SQLException { //3员工登录，返回1允许登录，返回0不允许登录,返回2密码错误
        String sql="select * from staff where sid=\'"+id+"\'";
        getStaffConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        if(!result.next()){//不存在这个用户
            System.out.println("不存在");
            return 0;}
        if(!result.getString(3).equals(pw)) { //如果密码不匹配
            System.out.println(" 密码错误");
            return 2;
        }
        System.out.println("登录成功");
        return 1; //账户密码匹配
    }


    public int AdminLogin(String id,String pw) throws SQLException {//4主管登录
        String sql="select * from admin where aid=\'"+id+"\'";
        getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        if(!result.next())  //不存在这个用户
            return 0;
        if(!result.getString(3).equals(pw)) //如果密码不匹配
            return 2;
        return 1; //账户密码匹配
    }

    public ArrayList getStaffInfoByIdByStaff(String id) throws SQLException { //5 员工根据id查询自己所有信息
        String sql="select * from staff where sid=\'"+id+"\'";
        ArrayList info=new ArrayList();
        if(StaffConnState==0){
            getStaffConn();
        }
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        result.next();
        info.add(result.getString(1));//id
        info.add(result.getString(2));//姓名
        info.add(result.getString(3));//密码
        info.add(result.getString(4));//性别
        info.add(result.getString(5));//年龄
        info.add(result.getString(6));//电话
        info.add(result.getString(7));//所在部门
        return info;
    }


    public ArrayList getAdminInfoByIdByAdmin(String id) throws SQLException { //6主管查询自己的所有信息
        String sql="select * from admin where aid=\'"+id+"\'";
        ArrayList info=new ArrayList();
        if(AdminConnState==0){
            getAdminConn();
        }
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        result.next();
        info.add(result.getString(1));
        info.add(result.getString(2));
        info.add(result.getString(3));
        info.add(result.getString(4));
        info.add(result.getString(5));
        info.add(result.getString(6));
        info.add(result.getString(7));
        return info;
    }

    public ArrayList getStaffInfoByName(String name) throws SQLException { //7主管根据姓名查询员工信息（可能重名）
        String sql="select * from staff where sname=\'"+name+"\'";
        ArrayList info =new ArrayList();
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        while(result.next()){
            info.add(result.getString(1));
            info.add(result.getString(2));
            info.add("**********");
            info.add(result.getString(4));
            info.add(result.getString(5));
            info.add(result.getString(6));
            info.add(result.getString(7));
        }
        return info;
    }

    public ArrayList getDepInfoByName(String name) throws SQLException { //8主管根据部门名称查找部门信息
        String sql="select department.dname,aname,info from department,admin where department.aid=admin.aid and department.dname=\'"
                +name+"\'";
        ArrayList info=new ArrayList();
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        result.next();
        info.add(result.getString(1));//部门名称
        info.add(result.getString(2));//部门主管
        info.add(result.getString(3));//部门信息
        return info;
    }

    public int setInfoByStaff(ArrayList info)  { //9员工修改自己信息,该方法不用于密码、部门修改,修改成功返回1，否则返回0
        String id=(String)info.get(0);//id
        String name=(String) info.get(1);//新姓名
        String sex=(String) info.get(2);//新性别
        String age=(String)info.get(3);//新年龄
        String phone=(String)info.get(4);//新号码
        String sql="update staff set sname=\'"+name+"\' where sid=\'"+id+"\';"
                +"update staff set ssex=\'"+sex+"\' where sid=\'"+id+"\';"
                +"update staff set sage=\'"+age+"\' where sid=\'"+id+"\';"
                +"update staff set sphone=\'"+phone+"\'where sid=\'"+id+"\'";
        if(StaffConnState==0)
            getStaffConn();
        try {
            Statement stat = conn.createStatement();
            int result=stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public ArrayList getStaffInfoByDepartment(String name) throws SQLException {//10根据部门名查找部门全部员工
        String sql="select * from staff where dname=\'"+name+"\'";
        ArrayList info=new ArrayList();
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        while(result.next()){
            info.add(result.getString(1));
            info.add(result.getString(2));
            info.add(result.getString(3));
            info.add(result.getString(4));
            info.add(result.getString(5));
            info.add(result.getString(6));
            info.add(result.getString(7));
        }
        return info;
    }
    public int updateOneStaffInfo(String i,String n,String s,String a,String p,String d) throws SQLException { //11根据员工id更改员工信息,不能改id和密码
        //参数表:id,名字，性别，年龄，电话，部门
        String sql="update staff set sname=\'"+n+"\',ssex=\'"+s+"\',sage=\'"+a+"\',sphone=\'"
                +p+"\',dname=\'"+d+"\' where sid=\'"+i+"\'";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int result=stat.executeUpdate(sql);
        return result;
    }
    public int updateOneAdminInfo(String i,String n,String s,String a,String p,String d) throws SQLException { //12根据主管id更改主管信息,不能改id和密码
        //参数表:id,名字，性别，年龄，电话，部门
        String sql="update admin set aname=\'"+n+"\',asex=\'"+s+"\',aage=\'"+a+"\',aphone=\'"
                +p+"\',dname=\'"+d+"\' where aid=\'"+i+"\'";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int result=stat.executeUpdate(sql);
        return result;
    }
    public ArrayList getSalaryByStaff(String id) throws SQLException { //13员工根据id查找员工工资信息
        String sql="select * from staff left join salary on staff.sid=salary.sid where staff.sid=\'"+id+"\' ";
        if(AdminConnState==0)
            getAdminConn();
        ArrayList salaryinfo=new ArrayList();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        result.next();
        salaryinfo.add(result.getString(1));//id
        salaryinfo.add(result.getString(2));//姓名
        salaryinfo.add(result.getString(10));//基本工资
        salaryinfo.add(result.getString(11));//奖金
        salaryinfo.add(result.getString(12));//职务
        salaryinfo.add(result.getString(13));//房租
        salaryinfo.add(result.getString(14));//个人所得税
        salaryinfo.add(result.getString(15));//水电费
        salaryinfo.add(result.getString(16));//总计
        return salaryinfo;
    }
    public ArrayList getSalaryByAdmin(String department) throws SQLException { //14主管根据部门名称查找员工工资信息
        String sql="select * from staff left join salary on staff.sid=salary.sid where staff.dname=\'"+department+"\'";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ArrayList salaryinfo=new ArrayList();
        ResultSet result = stat.executeQuery(sql);
        while(result.next()){
            salaryinfo.add(result.getString(1));//id
            salaryinfo.add(result.getString(2));//姓名
            salaryinfo.add(result.getString(10));//基本工资
            salaryinfo.add(result.getString(11));//奖金
            salaryinfo.add(result.getString(12));//职务
            salaryinfo.add(result.getString(13));//房租
            salaryinfo.add(result.getString(14));//个人所得税
            salaryinfo.add(result.getString(15));//水电费
            salaryinfo.add(result.getString(16));//总计
        }
        return salaryinfo;
    }
    public ArrayList SerchStaffInfo(String str,String dep) throws SQLException { //15主管输入id或名字或性别或电话（全部或部分）字符查找员工信息(部门主管只能查自己部门的员工)
        String sql="select * from staff where dname=\'"+dep+"\' and (sid like \'%"+str+"%\' or sname like \'%"+str
                +"%\' or sphone like \'%"+str+"%\' or ssex like \'"+str+"\')";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        ArrayList info = new ArrayList();
        while(result.next()){
            info.add(result.getString(1));//id
            info.add(result.getString(2));//姓名
            info.add(result.getString(3));//密码
            info.add(result.getString(4));//性别
            info.add(result.getString(5));//年龄
            info.add(result.getString(6));//电话
            info.add(result.getString(7));//所在部门
        }
        return info;
    }
    public void DeleteStaffs(String[] ids) throws SQLException {//16根据id来删除员工并且删除对应的工资信息
        String sql="delete from staff where sid=\'"+ids[0]+"\'";
        for(int i=1;i<ids.length;i++){
            sql=sql+" or sid=\'"+ids[i]+"\'";
        }
        String sql2="delete from salary where sid=\'"+ids[0]+"\'";
        for(int i=1;i<ids.length;i++){
            sql2=sql2+" or sid=\'"+ids[i]+"\'";
        }
        System.out.println(sql);
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
        int flag1=stat.executeUpdate(sql2);
    }

    public int AddOneStaff(ArrayList info) throws SQLException {//17添加员工
        String sql="insert into staff value (\'"+info.get(0)+"\',\'"+info.get(1)+"\',\'"+info.get(2)+"\',\'"
                +info.get(3)+"\',"+info.get(4)+",\'"+info.get(5)+"\',\'"+info.get(6)+"\')";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
        return flag;
    }
    public int updateOneStaffSalary(String i, String s, String b, String d, String r, String t, String e,String sum) throws SQLException { //11根据员工id更改员工信息,不能改id和密码
        //参数表:id,名字，收入，奖金‘’‘
        String sql = "update salary set ssalary=" + s + ",bonus=" + b + ",duty=" + d + ",rent="
                + r + ",tax=" + t + ",expense=" + e +",sum="+sum+ " where sid=\'" + i + "\'";
        if (AdminConnState == 0)
            getAdminConn();
        Statement stat = conn.createStatement();
        int result = stat.executeUpdate(sql);
        return result;
    }
    public ArrayList getSalaryStaticByAdmin(String department) throws SQLException { //14主管根据部门名称查找员工工资信息并排序
        //String sql="select * from staff left join salary on staff.sid=salary.sid where staff.dname=\'"+department+"\'"+" order by sum desc";
        String sql = "select school, count(*) from tiezi where type = '"+department+"' group by school";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ArrayList salaryinfo=new ArrayList();
        ResultSet result = stat.executeQuery(sql);
        while(result.next()){
            salaryinfo.add(result.getString(1));//id
            salaryinfo.add(result.getString(2));//姓名
//            salaryinfo.add(result.getString(3));//基本工资
//            salaryinfo.add(result.getString(4));//奖金
//            salaryinfo.add(result.getString(5));//职务
//            salaryinfo.add(result.getString(6));//房租
//            salaryinfo.add(result.getString(7));//个人所得税
//            salaryinfo.add(result.getString(8));//水电费
//            salaryinfo.add(result.getString(9));//总计
        }
        System.out.println(salaryinfo);
        return salaryinfo;
    }
    public void DeleteSalary(String[] ids) throws SQLException {//16根据id来删除员工工资信息
        String sql="delete from salary where sid=\'"+ids[0]+"\'";
        for(int i=1;i<ids.length;i++){
            sql=sql+" or sid=\'"+ids[i]+"\'";
        }
        System.out.println(sql);
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
    }
    public ArrayList SearchSalaryByAdmin(String str,String dep) throws SQLException { //14主管根据部门名称查找员工工资信息
        String sql="select * from staff left join salary on staff.sid=salary.sid where dname=\'"+dep+"\' and (staff.sid like \'%"+str+"%\' or sname like \'%"+str
                +"%\')";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ArrayList salaryinfo=new ArrayList();
        ResultSet result = stat.executeQuery(sql);
        System.out.println(sql);
        while(result.next()){
            salaryinfo.add(result.getString(1));//id
            salaryinfo.add(result.getString(2));//姓名
            salaryinfo.add(result.getString(10));//基本工资
            salaryinfo.add(result.getString(11));//奖金
            salaryinfo.add(result.getString(12));//职务
            salaryinfo.add(result.getString(13));//房租
            salaryinfo.add(result.getString(14));//个人所得税
            salaryinfo.add(result.getString(15));//水电费
            salaryinfo.add(result.getString(16));//总计
        }
        return salaryinfo;
    }
    public void ChangePassword(String id,String type,String nps) throws SQLException {//员工修改自己密码
        String sql="";
        if(type.equals("staff"))
            sql="update staff set spassword=\'"+nps+"\' where sid=\'"+id+"\'";
        else
            sql="update admin set apassword=\'"+nps+"\' where aid=\'"+id+"\'";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
    }



    //安卓服务


    public int UserLogin(String id,String pw) throws SQLException { //3用户登录，返回1允许登录，返回0不允许登录,返回2密码错误
        String sql="select * from user where id=\'"+id+"\'";
        getStaffConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        if(!result.next()){//不存在这个用户
            System.out.println("不存在");
            return 0;}
        if(!result.getString(3).equals(pw)) { //如果密码不匹配
            System.out.println(" 密码错误");
            return 2;
        }
        System.out.println("登录成功");
        return 1; //账户密码匹配
    }
    public ArrayList getUserInfoByIdByStaff(String id) throws SQLException { //5 用户根据id查询自己所有信息
        String sql="select * from user where id='"+id+"'";
        ArrayList info=new ArrayList();
        if(StaffConnState==0){
            getStaffConn();
        }
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        result.next();
        info.add(result.getString(1));//id
        info.add(result.getString(2));//姓名
        info.add(result.getString(3));//密码
        info.add(result.getString(4));//性别
        info.add(result.getString(5));//年龄
        info.add(result.getString(6));//邮箱
        info.add(result.getString(7));//学校
        System.out.println(info);
        return info;
    }
    public int adUpdaUser(String n,String ph,String sc,String pw) throws SQLException { //11根据用户电话号更改用户信息,不能改id和密码
        //参数表:id,名字，性别，年龄，电话，部门
        String sql="insert user set name=\'"+n+"\',id=\'"+ph+"\',school=\'"+sc+"\',password=\'"
                +pw+"\'";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
        return flag;
    }
    public int adAddUser(ArrayList info) throws SQLException {//17添加用户
        String sql="insert into user(`user`.id,`user`.name,`user`.school,`user`.password) VALUES('"+info.get(1)+"','"+info.get(0)+"','"+info.get(2)+"','"
                +info.get(3)+"');";
        if(AdminConnState==0)
            getAdminConn();
        System.out.println(info);
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
        return flag;
    }
    public int adUpdateUser(String i, String s, String b, String d, String r, String t, String e,String sh) throws SQLException { //11根据用户id更改用户信息,不能改id和密码
        //参数表:id,名字，收入，奖金‘’‘
        String sql = "update user set name='" + s + "',password='" + b + "',sex='" + d + "',age='"
                + r + "',email='" + t + "',school='" + e +"',shenhe='"+sh+"' where id='" + i + "';";
        if (AdminConnState == 0)
            getAdminConn();
        Statement stat = conn.createStatement();
        int result = stat.executeUpdate(sql);
        return result;
    }
    public ArrayList getUserbyuser(String id) throws SQLException { //13员工根据id查找员工工资信息
        String sql="select * from staff left join salary on staff.sid=salary.sid where staff.sid=\'"+id+"\' ";
        if(AdminConnState==0)
            getAdminConn();
        ArrayList salaryinfo=new ArrayList();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        result.next();
        salaryinfo.add(result.getString(1));//id
        salaryinfo.add(result.getString(2));//姓名
        salaryinfo.add(result.getString(10));//基本工资
        salaryinfo.add(result.getString(11));//奖金
        salaryinfo.add(result.getString(12));//职务
        salaryinfo.add(result.getString(13));//房租
        salaryinfo.add(result.getString(14));//个人所得税
        salaryinfo.add(result.getString(15));//水电费
        salaryinfo.add(result.getString(16));//总计
        return salaryinfo;
    }
    public ArrayList getuserByAdmin(String department) throws SQLException { //14查找所有用户信息
        String sql="select * from user ";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ArrayList salaryinfo=new ArrayList();
        ResultSet result = stat.executeQuery(sql);
        while(result.next()){
            salaryinfo.add(result.getString(1));//id
            salaryinfo.add(result.getString(2));//姓名
            salaryinfo.add(result.getString(3));//基本工资
            salaryinfo.add(result.getString(4));//奖金
            salaryinfo.add(result.getString(5));//职务
            salaryinfo.add(result.getString(6));//房租
            salaryinfo.add(result.getString(7));//个人所得税
            salaryinfo.add(result.getString(8));//审核
        }
        return salaryinfo;
    }
    public void adDeleteUser(String[] ids) throws SQLException {//16根据id来删除用户
        String sql="delete from user where id=\'"+ids[0]+"\'";
        for(int i=1;i<ids.length;i++){
            sql=sql+" or id=\'"+ids[i]+"\'";
        }
        System.out.println(sql);
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
    }
    public ArrayList adgetTieziinfoBytype(String name) throws SQLException {//10根据帖子分类查找该类全部帖子
        String sql="select * from tiezi where type=\'"+name+"\'";
        ArrayList info=new ArrayList();
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        while(result.next()){
            info.add(result.getString(1));
            info.add(result.getString(2));
            info.add(result.getString(3));
            info.add(result.getString(4));
            info.add(result.getString(5));
            info.add(result.getString(6));
            info.add(result.getString(7));
            info.add(result.getString(8));
            info.add(result.getString(9));
        }
        return info;
    }
    public int adupdateOneTieziInfo(String i,String si,String t,String c,String im,String tm,String sc,String ty,String sh) throws SQLException { //11根据帖子id更改帖子信息,不能改id和用户
        //参数表:id,用户，标题，内容，img，时间，学校，类型
        String sql="update tiezi set id='"+si+"',title='"+t+"',content='"
                +c+"',img='"+im+"',time='"+tm+"',school='"+sc+"',type='"+ty+"',tshenhe='"+sh+"' where tid='"+i+"'";
        System.out.println(i+si+t+c+im+tm+sc+ty);
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int result=stat.executeUpdate(sql);
        return result;
    }
    public void adDeleteTiezi(String[] ids) throws SQLException {//16根据id来删除帖子
        String sql="delete from tiezi where tid='"+ids[0]+"'";
        String sql1="delete from reply where tid='"+ids[0]+"'";
        for(int i=1;i<ids.length;i++){
            sql=sql+" or tid=\'"+ids[i]+"\'";
        }
        System.out.println(sql);
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
        stat.executeUpdate(sql1);
    }
    public int adAddOneTiezi(ArrayList info) throws SQLException {//17添加帖子
        String sql="insert into tiezi(`tiezi`.id,`tiezi`.title,`tiezi`.content,`tiezi`.img,`tiezi`.time,`tiezi`.school,`tiezi`.type) VALUES('"+info.get(0)+"','"+info.get(1)+"','"+info.get(2)+"','"+info.get(3)+"','"+info.get(4)+"','"+info.get(5)+"','"+info.get(6)+"');";
        System.out.println(sql);
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
        return flag;
    }
    public ArrayList adSearchUserByAdmin(String str,String dep) throws SQLException { //14主管根据用户ID名称查找用户信息
        String sql="select * from user  where id like '%"+str+"%'or name like '%"+str+"%'";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ArrayList salaryinfo=new ArrayList();
        ResultSet result = stat.executeQuery(sql);
        System.out.println(sql);
        while(result.next()){
            salaryinfo.add(result.getString(1));//id
            salaryinfo.add(result.getString(2));//姓名
            salaryinfo.add(result.getString(3));//基本工资
            salaryinfo.add(result.getString(4));//奖金
            salaryinfo.add(result.getString(5));//职务
            salaryinfo.add(result.getString(6));//房租
            salaryinfo.add(result.getString(7));//个人所得税
            salaryinfo.add(result.getString(8));//个人所得税
        }
        return salaryinfo;
    }
    public ArrayList adSerchTieziInfo(String str,String dep) throws SQLException { //15主管输入id或标题或时间地点内容用户查找帖子
        String sql="select * from tiezi  where id like '%"+str+"%'or tid like '%"+str+"%'or title like '%"+str+"%'or content like '%"+str+"%'or school like '%"+str+"%'";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        ArrayList info = new ArrayList();
        System.out.println(sql);
        while(result.next()){
            info.add(result.getString(1));//id
            info.add(result.getString(2));//
            info.add(result.getString(3));//
            info.add(result.getString(4));//
            info.add(result.getString(5));//
            info.add(result.getString(6));//
            info.add(result.getString(7));//
            info.add(result.getString(8));//
            info.add(result.getString(9));//
        }
        return info;
    }
    public ArrayList getschoolByAdmin(String name) throws SQLException {//10查找该全部学校
        String sql="select * from school ";
        ArrayList info=new ArrayList();
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        while(result.next()){
            info.add(result.getString(1));
            info.add(result.getString(2));
            info.add(result.getString(3));
            info.add(result.getString(4));
            info.add(result.getString(5));
            info.add(result.getString(6));
            info.add(result.getString(7));
        }
        return info;
    }
    public int adAddOneSchool(ArrayList info) throws SQLException {//17添加学校
        String sql="insert into school(`school`.sname,`school`.snews,`school`.country,`school`.province,`school`.city,`school`.area) VALUES('"+info.get(0)+"','"+info.get(1)+"','"+info.get(2)+"','"+info.get(3)+"','"+info.get(4)+"','"+info.get(5)+"');";
        System.out.println(sql);
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
        return flag;
    }
    public int adupdateSchoolInfo(String i,String si,String t,String c,String im,String tm,String sc) throws SQLException { //11根据学校id更改学校信息,不能改id
        //参数表:id,用户，标题，内容，img，时间，学校，类型
        String sql="update school set sname='"+si+"',snews='"+t+"',country='"
                +c+"',province='"+im+"',city='"+tm+"',area='"+sc+"' where sid='"+i+"'";
        System.out.println(i+si+t+c+im+tm+sc);
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int result=stat.executeUpdate(sql);
        return result;
    }
    public void adDeleteSchool(String[] ids) throws SQLException {//16根据id来删除学校
        String sql="delete from school where sid='"+ids[0]+"'";
        for(int i=1;i<ids.length;i++){
            sql=sql+" or sid=\'"+ids[i]+"\'";
        }
        System.out.println(sql);
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
    }
    public ArrayList adgetTieziBytype(String ttype) throws SQLException {//10用户根据帖子分类查找该类全部帖子
        String sql="select * from tiezi where type='"+ttype+"'";
        ArrayList info=new ArrayList();
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        while(result.next()){
            info.add(result.getString(1));
            info.add(result.getString(2));
            info.add(result.getString(3));
            info.add(result.getString(4));
            info.add(result.getString(5));
            info.add(result.getString(6));
            info.add(result.getString(7));
            info.add(result.getString(8));
            info.add(result.getString(9));
        }
        return info;
    }
    public ArrayList adgetreply(String ttid) throws SQLException {//10根据帖子id寻找回复
        String sql="select * from reply where tid='"+ttid+"'";
        ArrayList info=new ArrayList();
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        while(result.next()){
            info.add(result.getString(1));
            info.add(result.getString(2));
            info.add(result.getString(3));
            info.add(result.getString(4));
            info.add(result.getString(5));
            info.add(result.getString(6));
        }
        return info;
    }
    public int adAddreply(ArrayList info) throws SQLException {//17添加评论
        String sql="insert into reply(`reply`.tid,`reply`.uid,`reply`.ruser,`reply`.id,`reply`.rcontent,`reply`.rtime) VALUES('"+info.get(0)+"','"+info.get(1)+"','"+info.get(2)+"','"
                +info.get(3)+"','"+info.get(4)+"','"+info.get(5)+"');";
        if(AdminConnState==0)
            getAdminConn();
        System.out.println(info);
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
        return flag;
    }
    public ArrayList adgetxiaoxi(String usid) throws SQLException {//10根据用户id寻找回复
        String sql="select * from reply where uid='"+usid+"'";
        ArrayList info=new ArrayList();
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        while(result.next()){
            info.add(result.getString(1));
            info.add(result.getString(2));
            info.add(result.getString(3));
            info.add(result.getString(4));
            info.add(result.getString(5));
            info.add(result.getString(6));
        }
        return info;
    }
    public ArrayList adSerchtiezibyxiaoxi(String str,String dep) throws SQLException { //15用户点击回复消息根据帖子id获取帖子
        String sql="select * from tiezi  where tid='"+str+"'";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        ArrayList info = new ArrayList();
        System.out.println(sql);
        while(result.next()){
            info.add(result.getString(1));//id
            info.add(result.getString(2));//
            info.add(result.getString(3));//
            info.add(result.getString(4));//
            info.add(result.getString(5));//
            info.add(result.getString(6));//
            info.add(result.getString(7));//
            info.add(result.getString(8));//
            info.add(result.getString(9));//
        }
        return info;
    }
    public ArrayList getreplyByAdmin(String department) throws SQLException { //14管理员查找所有评论信息
        String sql="select * from reply ";
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ArrayList salaryinfo=new ArrayList();
        ResultSet result = stat.executeQuery(sql);
        while(result.next()){
            salaryinfo.add(result.getString(1));//id
            salaryinfo.add(result.getString(2));//用户id
            salaryinfo.add(result.getString(3));//评论人姓名
            salaryinfo.add(result.getString(4));//评论人id
            salaryinfo.add(result.getString(5));//内容
            salaryinfo.add(result.getString(6));//评论时间
        }
        return salaryinfo;
    }
    public ArrayList owntiezi(String usid) throws SQLException {//10用户获得所有自己发布的帖子
        String sql="select * from tiezi where id='"+usid+"'";
        ArrayList info=new ArrayList();
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        ResultSet result=stat.executeQuery(sql);
        while(result.next()){
            info.add(result.getString(1));
            info.add(result.getString(2));
            info.add(result.getString(3));
            info.add(result.getString(4));
            info.add(result.getString(5));
            info.add(result.getString(6));
            info.add(result.getString(7));
            info.add(result.getString(8));
            info.add(result.getString(9));
        }
        return info;
    }
    public void adDeleteowntiezi(String ids) throws SQLException {//用户删除自己帖子
        String sql="delete from tiezi where tid='"+ids+"'";
        String sql1="delete from reply where tid='"+ids+"'";
        System.out.println(sql);
        System.out.println(sql1);
        if(AdminConnState==0)
            getAdminConn();
        Statement stat=conn.createStatement();
        int flag=stat.executeUpdate(sql);
        int flbg=stat.executeUpdate(sql1);
    }
}
