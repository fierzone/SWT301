package org.example;

public class AccountService {
    
    public boolean registerAccount(String username, String password, String email) {
        // Kiểm tra username không được null hoặc rỗng
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra password phải lớn hơn 6 ký tự
        if (password == null || password.length() <= 6) {
            return false;
        }
        
        // Kiểm tra email hợp lệ
        if (!isValidEmail(email)) {
            return false;
        }
        
        // Nếu tất cả điều kiện đều thỏa mãn, đăng ký thành công
        return true;
    }
    
    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        // Regex kiểm tra email phải có @ và ít nhất một dấu chấm sau @
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
} 