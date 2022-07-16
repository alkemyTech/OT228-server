package com.alkemy.ong.util;

import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UsersRspository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserSeed implements CommandLineRunner {

    @Autowired
    private UsersRspository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


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

    private void loadUsers(){
        if (userRepository.count() == 0) {
            createUsers();
        }
    }

    private void createRoles() {
        Role adminRole = new Role("ADMIN","");
        roleRepository.save(adminRole);
        Role userRole = new Role("USER","");
        roleRepository.save(userRole);
    }

    private void createUsers(){
        Role adminRole = roleRepository.findByName("ADMIN");
        userRepository.save(new Users("Marcos","Fernandez","marcosadmin@gmail.com", passwordEncoder.encode("12345"), adminRole));
        userRepository.save(new Users("Federico","Gomez","fedeadmin@gmail.com",passwordEncoder.encode("12345"),adminRole));
        userRepository.save(new Users("Agustin","Garcia","agustinadmin@gmail.com",passwordEncoder.encode("12345"),adminRole));
        userRepository.save(new Users("Martina","Sosa","martinadmin@gmail.com",passwordEncoder.encode("12345"),adminRole));
        userRepository.save(new Users("Darío","Romero","darioadmin@gmail.com",passwordEncoder.encode("12345"),adminRole));
        userRepository.save(new Users("Romina","Torres","romiadmin@gmail.com",passwordEncoder.encode("12345"),adminRole));
        userRepository.save(new Users("Cintia","Flores","cintiadmin@gmail.com",passwordEncoder.encode("12345"),adminRole));
        userRepository.save(new Users("Matias","Ruiz","matiasadmin@gmail.com",passwordEncoder.encode("12345"),adminRole));
        userRepository.save(new Users("Pablo","Martinez","pabloadmin@gmail.com",passwordEncoder.encode("12345"),adminRole));
        userRepository.save(new Users("Susana","Molina","susiadmin@gmail.com",passwordEncoder.encode("12345"),adminRole));
        Role userRole = roleRepository.findByName("USER");
        userRepository.save(new Users("Pedro","Ruiz","pedrouser@gmail.com",passwordEncoder.encode("12345"),userRole));
        userRepository.save(new Users("Roberto","Castro","robertuser@gmail.com",passwordEncoder.encode("12345"),userRole));
        userRepository.save(new Users("Soledad","Ortiz","soleuser@gmail.com",passwordEncoder.encode("12345"),userRole));
        userRepository.save(new Users("Maria","Silva","mariauser@gmail.com",passwordEncoder.encode("12345"),userRole));
        userRepository.save(new Users("Alejandro","Nuñez","aleuser@gmail.com",passwordEncoder.encode("12345"),userRole));
        userRepository.save(new Users("Mauricio","Gonzalez","mauriciouser@gmail.com",passwordEncoder.encode("12345"),userRole));
        userRepository.save(new Users("Elias","Guevara","eliasuser@gmail.com",passwordEncoder.encode("12345"),userRole));
        userRepository.save(new Users("Valentina","Moreno","valenuser@gmail.com",passwordEncoder.encode("12345"),userRole));
        userRepository.save(new Users("Raul","Morales","rauluser@gmail.com",passwordEncoder.encode("12345"),userRole));
        userRepository.save(new Users("Sabrina","Peralta","sabrinauser@gmail.com",passwordEncoder.encode("12345"),userRole));
    }


}
