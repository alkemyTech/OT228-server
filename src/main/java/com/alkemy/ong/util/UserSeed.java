package com.alkemy.ong.util;

import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UsersRspository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class UserSeed implements CommandLineRunner {

    @Autowired
    private UsersRspository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        loadRoles();
        loadUsers();

    }

    private void loadRoles(){
        if (roleRepository.count() == 0) {
            createRoles();
        }
    }

    private void createRoles() {
        Role adminRole = new Role("ADMIN","");
        roleRepository.save(adminRole);
        Role userRole = new Role("USER","");
        roleRepository.save(userRole);
    }

    private void loadUsers(){
        Role adminRole = roleRepository.findByName("ADMIN");
        userRepository.save(new Users("admin","1","admin1@gmail.com","12345",adminRole));
        userRepository.save(new Users("admin","2","admin2@gmail.com","12345",adminRole));
        userRepository.save(new Users("admin","3","admin3@gmail.com","12345",adminRole));
        userRepository.save(new Users("admin","4","admin4@gmail.com","12345",adminRole));
        userRepository.save(new Users("admin","5","admin5@gmail.com","12345",adminRole));
        userRepository.save(new Users("admin","6","admin6@gmail.com","12345",adminRole));
        userRepository.save(new Users("admin","7","admin7@gmail.com","12345",adminRole));
        userRepository.save(new Users("admin","8","admin8@gmail.com","12345",adminRole));
        userRepository.save(new Users("admin","9","admin9@gmail.com","12345",adminRole));
        userRepository.save(new Users("admin","10","admin10@gmail.com","12345",adminRole));
        Role userRole = roleRepository.findByName("USER");
        userRepository.save(new Users("user","1","user1@gmail.com","12345",userRole));
        userRepository.save(new Users("user","2","user2@gmail.com","12345",userRole));
        userRepository.save(new Users("user","3","user3@gmail.com","12345",userRole));
        userRepository.save(new Users("user","4","user4@gmail.com","12345",userRole));
        userRepository.save(new Users("user","5","user5@gmail.com","12345",userRole));
        userRepository.save(new Users("user","6","user6@gmail.com","12345",userRole));
        userRepository.save(new Users("user","7","user7@gmail.com","12345",userRole));
        userRepository.save(new Users("user","8","user8@gmail.com","12345",userRole));
        userRepository.save(new Users("user","9","user9@gmail.com","12345",userRole));
        userRepository.save(new Users("user","10","user10@gmail.com","12345",userRole));
    }


}
