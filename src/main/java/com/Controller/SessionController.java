package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.UserDto;
import com.entity.UserEntity;
import com.repositry.UserRepositry;

@RestController
@CrossOrigin
public class SessionController {

	@Autowired
	UserRepositry userepo;

	@PostMapping("signup")
	public ResponseBean<UserEntity> signup(@RequestBody UserEntity users) {

		ResponseBean<UserEntity> resposne = new ResponseBean<>();

		if (users != null) {
			userepo.save(users);
			resposne.setData(users);
			resposne.setMsg("User Signuped!!");
			resposne.setStatus(200);
		} else {
			resposne.setMsg("Some Data is Missing");
			resposne.setStatus(201);
		}

		return resposne;

	}

	@PostMapping("login")
	public ResponseBean<UserEntity> login(@RequestBody UserDto dto) {
		UserEntity users = null;
		ResponseBean<UserEntity> response = new ResponseBean<>();

		users = userepo.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

		if (users != null) {
			response.setData(users);
			response.setMsg("User login SuccessFully");
			response.setStatus(200);
		} else {

			response.setMsg("User Does Not exixts");
			response.setStatus(201);
		}

		return response;
	}

	@GetMapping("users")
	public ResponseBean<List<UserEntity>> listUsers() {
		ResponseBean<List<UserEntity>> respsonse = new ResponseBean<>();
		List<UserEntity> users = userepo.findAll();
		if (users != null) {
			respsonse.setData(users);
			respsonse.setMsg("List of Users");
			respsonse.setStatus(200);
		} else {
			respsonse.setMsg("Users Does Not Exits");
			respsonse.setStatus(201);
		}
		return respsonse;
	}

	@DeleteMapping("delete/{userid}")
	public ResponseBean<UserEntity> deleteUser(@PathVariable("userid") Long userid) {

		ResponseBean<UserEntity> response = new ResponseBean<>();
		UserEntity tuser = userepo.findByUserid(userid);
		if (tuser != null) {
			userepo.deleteById(userid);
			response.setData(tuser);
			response.setMsg("User Deleted");
			response.setStatus(200);
		}

		else {

			response.setMsg("User Deleted");
			response.setStatus(200);
		}
		return response;
	}

	@PutMapping("update")
	public ResponseBean<UserEntity> updateUser( @RequestBody UserEntity users) {
		ResponseBean<UserEntity> response = new ResponseBean<>();
		UserEntity tusers = userepo.findByUserid(users.getUserid());

		tusers.setFirstName(users.getFirstName());
		tusers.setLastName(users.getLastName());
		tusers.setAge(users.getAge());
		tusers.setEmail(users.getEmail());
		tusers.setPassword(users.getPassword());

		userepo.save(tusers);

		response.setData(tusers);
		response.setMsg("User Updated");
		response.setStatus(200);
		return response;
	}

}
