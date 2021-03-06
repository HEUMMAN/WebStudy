<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	request.setCharacterEncoding("EUC-KR");
%>
	<jsp:useBean id="mbean" class="model.MemberBean">
		<jsp:setProperty name="mbean" property="*"/>
	</jsp:useBean>
	
<%
	
	MemberDAO mdao = new MemberDAO();
	//mbean에 있는 id 꺼내기
	String pass = mdao.getPass(mbean.getId());		//데이터베이스에서 가져온 패스워드값
	
	//수정하기 위해서 입력한 패스워드값과 기존 데이터베이스에 저장돼 있던 패스워드를 비교래서 옳아야 삭제가능
	if(mbean.getPass1().equals(pass)){	//같다면
		//멤버테이블 삭제
		//MemberDAO클래스의 회원수정 메서드 작성 및 호출
		mdao.deleteMember(mbean.getId());	//id를 기준으로 삭제
		response.sendRedirect("MemberList.jsp");	//수정하고나서 전체목록으로 이동
	}else{	//다르다면
		//이전테이블로 보내기
%>
	<script type="text/javascript">
		alert("패스워드가 맞지 않습니다. 다시 확인해 주세요.");
		history.go(-1);
	</script>
<%
	}
%>

</body>
</html>