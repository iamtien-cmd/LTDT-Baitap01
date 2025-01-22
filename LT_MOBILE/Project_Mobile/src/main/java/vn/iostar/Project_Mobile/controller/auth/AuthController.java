package vn.iotstar.Project_Mobile.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Kiểm tra xem email đã tồn tại chưa
        if (userService.emailExists(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email đã tồn tại!");
        }

        // Tạo OTP và gửi qua email
        String otp = emailService.generateOTP();
        emailService.sendEmail(user.getEmail(), "OTP xác nhận đăng ký", "Mã OTP của bạn là: " + otp);

        // Lưu OTP và trạng thái chưa xác nhận vào cơ sở dữ liệu
        userService.saveUser(user, otp);

        return ResponseEntity.ok("OTP đã được gửi qua email.");
    }
}
