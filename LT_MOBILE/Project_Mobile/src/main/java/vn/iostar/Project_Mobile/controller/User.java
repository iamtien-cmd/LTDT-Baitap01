package vn.iostar.Project_Mobile.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@RestController
public class UserController {
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
		Optional<User> userOpt = userService.findByEmail(loginRequest.getEmail());
		if (!userOpt.isPresent() || !userOpt.get().getPassword().equals(loginRequest.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Thông tin đăng nhập không chính xác!");
		}
		return ResponseEntity.ok("Đăng nhập thành công.");
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
		Optional<User> userOpt = userService.findByEmail(request.getEmail());
		if (!userOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email không tồn tại.");
		}

		// Gửi OTP qua email
		String otp = emailService.generateOTP();
		emailService.sendEmail(request.getEmail(), "OTP quên mật khẩu", "Mã OTP của bạn là: " + otp);

		// Lưu OTP vào cơ sở dữ liệu
		userService.saveOtp(userOpt.get(), otp);

		return ResponseEntity.ok("OTP đã được gửi qua email.");
	}
}
