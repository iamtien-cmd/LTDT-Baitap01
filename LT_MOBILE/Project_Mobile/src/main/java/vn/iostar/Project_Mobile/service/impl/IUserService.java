package vn.iostar.Project_Mobile.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.iostar.Project_Mobile.entity.User;

@Service
public class UserService extends IUserService{

	 @Autowired
	    private UserRepository userRepository;

	    public boolean emailExists(String email) {
	        return userRepository.existsByEmail(email);
	    }

	    public void saveUser(User user, String otp) {
	        user.setOtp(otp);
	        user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));  // OTP có hiệu lực trong 10 phút
	        userRepository.save(user);
	    }

	    public Optional<User> findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    public void saveOtp(User user, String otp) {
	        user.setOtp(otp);
	        user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));  // OTP có hiệu lực trong 10 phút
	        userRepository.save(user);
	    }

}
