package vn.iostar.Project_Mobile.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.iostar.Project_Mobile.entity.User;

@Service
public interface IUserService {

	Optional<User> findByUsername(String username);

}
