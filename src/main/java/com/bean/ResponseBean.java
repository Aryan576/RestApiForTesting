package com.bean;

import java.util.Optional;

import com.entity.UserEntity;

import lombok.Data;

@Data
public class ResponseBean <T>{
	
	T data;
	String msg;
	int status;

}
