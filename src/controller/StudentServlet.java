package controller;

import bean.Student;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name="StudentServlet",urlPatterns={"/showStudent","/addStudent","/delStudent","/getStudent","/editStudent"})
public class StudentServlet extends HttpServlet {
    ArrayList<Student> students;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Student student1 = new Student("201401","小黑",19,"00000001");
        Student student2 = new Student("201402","小红",23,"00000002");
        students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);

		ServletContext context = config.getServletContext();
		
		context.setAttribute("students", students);
		System.out.println("初始化");
		System.out.println("context path:"+context.getContextPath());
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ServletContext context = this.getServletContext();
    	req.setCharacterEncoding("utf-8");
		String url = req.getRequestURI();	
		
		if(url.endsWith("showStudent")){
			req.setAttribute("students", students);
			System.out.println("students="+students);
			req.getRequestDispatcher("showStudent.jsp").forward(req, resp);
		}		
		
		//添加学生
		else if (url.endsWith("addStudent")) {
			String name = req.getParameter("name");
			String id = req.getParameter("id");
			String qq = req.getParameter("QQ");
			Integer age = Integer.valueOf(req.getParameter("age"));
			
//			if(id!=null && name!=null && qq!=null && age!=null) {
				Student s1 = new Student(id, name, age, qq);
				System.out.println(s1.toString());
				students.add(s1);
				context.setAttribute("students", students);
				System.out.println(students);
//				req.getRequestDispatcher("showStudent").forward(req, resp);
//			}
			req.getRequestDispatcher("showStudent").forward(req, resp);
		}
		
		//删除学生
		else if (url.endsWith("delStudent")) {
			System.out.println("进入delStudent");
			String id = req.getParameter("id");
			System.out.println(id);
			for (Student student : students) {
				if(student.getId().equals(id)) {
					students.remove(student);
					System.out.println("删除成功" + student.toString());
					req.getRequestDispatcher("showStudent").forward(req, resp);
					return;
				}
			}
		}
		
		//得到要修改的学生对象并跳转到editStudent.jsp
		else if(url.endsWith("getStudent")){			
			System.out.println("进入getStudent");
			String id = req.getParameter("id");
			System.out.println(id);
			if (id!=null&&id!="") {
				System.out.println("ID不为空");
				for (Student student : students) {
					if(student.getId().equals(id)) {
						System.out.println("查询到");
						req.setAttribute("students", Arrays.asList(student));
						req.getRequestDispatcher("showStudent.jsp").forward(req, resp);
						return;
					}
				}
			}else if(id==""||id==null){
				req.getRequestDispatcher("showStudent").forward(req, resp);
			}
		}
		
		//修改学生
		else if (url.endsWith("editStudent")) {
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			if(name==null) {
				for (Student student : students) {
					if(student.getId().equals(id)) {
						req.setAttribute("student", student);
						req.getRequestDispatcher("editStudent.jsp").forward(req, resp);
						return;
					}
				}
				
			}else {
				String qq =(String)req.getParameter("qq");
				Integer age = Integer.valueOf(req.getParameter("age"));
				for (Student student : students) {
					if(student.getId().equals(id)) {
						student.setId(id);
						student.setName(name);
						student.setAge(age);
						student.setQq(qq);
						req.getRequestDispatcher("showStudent").forward(req, resp);
						return;
					}
				}
			}
//			System.out.println("进入editStudent");
//			
//			context.setAttribute("student", new Student());
//			String id = req.getParameter("id");
//			System.out.println("id="+id);
//			if(req.getParameter("name")!=null) {
//				name = new String(req.getParameter("name").getBytes("iso-8859-1"), "utf-8");
//			}
//			System.out.println("姓名"+name);
//			String qq =(String)req.getParameter("qq");
//			System.out.println("qq="+qq);
//			Integer age = Integer.valueOf(req.getParameter("age"));
//			System.out.println(id);
//			// 问题： 1.修改id 无法再进行接下来的操作(解决) 2.修改后 name 会变成乱码
//			if (id!=null) {
//				for (Student student : students) {
//					if(student.getId().equals(id)) {
//						if (name!=null) {
//							student.setId(id);
//							student.setName(name);
//							student.setAge(age);
//							student.setQq(qq);
//							
//							System.out.println(students);
//							context.setAttribute("students", students);
//							req.getRequestDispatcher("showStudent.jsp").forward(req, resp);
//							return ; 
//						}
//						req.setAttribute("editStu", student);
//						
//					}
//				}
//			}
//			req.getRequestDispatcher("editStudent.jsp").forward(req, resp);
				
		}

}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }

}
