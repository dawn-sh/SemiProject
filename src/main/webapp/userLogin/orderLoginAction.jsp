<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="data.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Dongle:wght@300&family=Gaegu:wght@300&family=Nanum+Pen+Script&family=Sunflower:wght@300&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
</head>
<body>
<%
	String userId=request.getParameter("userId");
	String userPass=request.getParameter("userPass");
	String uSave=request.getParameter("uSave");
	String s_id=request.getParameter("s_id");
	
	UserDao dao=new UserDao();
	boolean b=dao.isUserIdPass(userId, userPass);
	
	if(b){ //로그인 성공 시
		session.setMaxInactiveInterval(60*60*2);  //로그인 지속시간 2시간 설정
		session.setAttribute("loginok", "yes");  //로그인 된 상태인지 다른 페이지에서 볼 수 있도록
		session.setAttribute("id", userId); //로그인 후, 누가 로그인 했는지 표시
		session.setAttribute("saveok", uSave==null?null:"uyes"); //로그인 시, 아이디 저장 체크박스 표시하면 로그인폼에 아이디값 고정
		session.setAttribute("grade","user");	
		request.setCharacterEncoding("utf-8");
		LocalDateTime now=LocalDateTime.now();
		String sdfNow=now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0"));
		
		dao.updateLL_date(userId, sdfNow);
		
		response.sendRedirect("../shop/shopMenu.jsp?s_id="+s_id);
	} else{%> <!--로그인 실패 시 -->
		<script type="text/javascript">
			alert("아이디/비밀번호가 일치하지 않습니다");
			history.back();
		</script>
	
	<%}
	
%>
</body>
</html>