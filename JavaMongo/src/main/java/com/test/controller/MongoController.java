package com.test.controller;

import com.test.bean.User;
import com.test.bean.pageBean;
import com.test.service.MongoService;
import com.test.service.UserService;
import com.test.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/mongo")
@Controller
public class MongoController {

	@Autowired
	private MongoService mongoService;

	@Autowired
	private UserService userService;

	/*@RequestMapping(value="/receive",method=RequestMethod.POST)
	public void receiveDate(HttpServletRequest request){
		System.out.println(request.getParameter("name")+request.getParameter("age")+request.getParameter("sex"));
		//mongoService.add(user);
		System.out.println("------");
	}*/
	@RequestMapping(value = "/receive", method = RequestMethod.POST)
	public ResponseEntity<Map<Object, Object>> receiveDate(@RequestBody User user) {
		mongoService.add(user);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("status", "200");
		Map<Object, Object> map2 = new HashMap<Object, Object>();
		map2.put("result", map);
		return ResponseEntity.ok(map);
	}

	@RequestMapping(value = "/show", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody pageBean queryUser(Model model,@RequestParam(value = "currentPage",defaultValue = "0") int currentPage,@RequestParam(value = "rows",defaultValue = "20") int pageSize) {
		//返回userId
		List<User> users = mongoService.queryUser(currentPage,pageSize);
		//total 所有条数
		int total  = mongoService.queryUserCount();
		/*model.addAttribute("users", users);
		model.addAttribute("total",total);
		return "userlist";*/
		//返回json
		pageBean pb = new pageBean();
		pb.setList(users);
		pb.setTotal(total);
		pb.setPage(currentPage);
		pb.setRows(pageSize);
		return pb;
	}

	@RequestMapping(value = "/remove/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<Object,Object>> remove(@PathVariable("userId") String userId) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			if (StringUtils.isNotBlank(userId)) {
				mongoService.remove(userId);
				//return ResponseEntity.status(HttpStatus.OK).build();
				 map = new HashMap<Object,Object>();
				map.put("msg","remove successful");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
	@RequestMapping(value="/checkName",method=RequestMethod.GET)
	public ResponseEntity<Map<Object, Object>> checkName(@RequestParam("name") String name){
		int count = mongoService.checkname(name);
		Map<Object,Object> map = new HashMap<Object, Object>();
			map.put("count",count);
			return ResponseEntity.ok(map);
	}
	@RequestMapping(value="/angular",method = {RequestMethod.GET})
	public String angular(){
			return "angular";
	}

	@RequestMapping(value = "/showpage", method = {RequestMethod.GET})
	public String showpage(Model model,@RequestParam(value = "currentPage",defaultValue = "0") int currentPage,@RequestParam(value = "rows",defaultValue = "20") int pageSize) {
		List<User> users = mongoService.queryUser(currentPage,pageSize);
		model.addAttribute("users", users);
		return "userlist";
	}
	@RequestMapping(value="/login",method = {RequestMethod.POST})
	/*public ResponseEntity<Map> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {//ajax请求方式*/
	public ResponseEntity<Map> login( HttpServletRequest request,HttpServletResponse response,@RequestParam String name,@RequestParam String password){
		User user = new User();
		//user.setName(request.getParameter("name"));
		//user.setName(request.getParameter("password"));
		user.setName(name);
		user.setPassword(password);
		String ticket = userService.login(user);
		Map map = new HashMap();

		if (StringUtils.isNotBlank(ticket)) {
			CookieUtils.setCookie(request, response, "hang", ticket);
			try {
				//重定向可以  转发不行
				//response.sendRedirect("/mongo/showpage");
				map.put("msg","request success");
				map.put("status",200);
				return ResponseEntity.ok(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("msg","no user");
		map.put("status",500);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
}