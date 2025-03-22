package org.example.service.auth;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.dto.SignUpRequest;
import org.example.dto.User;
import org.example.entity.UserEntity;
import org.example.enums.UserRole;
import org.example.repository.UserDao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserDao dao;

    @PostConstruct
    public void createAdminAccount(){
        Optional<UserEntity> admin = dao.findByUserRole(UserRole.ADMIN);
        if (admin.isEmpty()){
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("admin@gmail.com");
            userEntity.setName("admin");
            userEntity.setUserRole(UserRole.ADMIN);
            userEntity.setPassword(new BCryptPasswordEncoder().encode("admin123"));
            System.out.println("Admin Account Successful !");
            dao.save(userEntity);
        }else{
            System.out.println("Admin Account Already exist !");
        }
    }
    public User createUser(SignUpRequest signUpRequest){
        if (dao.findByEmail(signUpRequest.getEmail()).isPresent()){
            System.out.println("User already present with email "+signUpRequest.getEmail());
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signUpRequest.getEmail());
        userEntity.setName(signUpRequest.getName());
        userEntity.setUserRole(UserRole. CUSTOMER);
        userEntity.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        UserEntity createUserEntity = dao.save(userEntity);
        return createUserEntity.getUser();
    }
}
