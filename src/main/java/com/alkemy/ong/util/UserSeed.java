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
        userRepository.save(new Users("Marcos","Fernandez","marcosadmin@gmail.com","12345",adminRole));
        userRepository.save(new Users("Federico","Gomez","fedeadmin@gmail.com","12345",adminRole));
        userRepository.save(new Users("Agustin","Garcia","agustinadmin@gmail.com","12345",adminRole));
        userRepository.save(new Users("Martina","Sosa","martinadmin@gmail.com","12345",adminRole));
        userRepository.save(new Users("Darío","Romero","darioadmin@gmail.com","12345",adminRole));
        userRepository.save(new Users("Romina","Torres","romiadmin@gmail.com","12345",adminRole));
        userRepository.save(new Users("Cintia","Flores","cintiadmin@gmail.com","12345",adminRole));
        userRepository.save(new Users("Matias","Ruiz","matiasadmin@gmail.com","12345",adminRole));
        userRepository.save(new Users("Pablo","Martinez","pabloadmin@gmail.com","12345",adminRole));
        userRepository.save(new Users("Susana","Molina","susiadmin@gmail.com","12345",adminRole));
        Role userRole = roleRepository.findByName("USER");
        userRepository.save(new Users("Pedro","Ruiz","pedrouser@gmail.com","12345",userRole));
        userRepository.save(new Users("Roberto","Castro","robertuser@gmail.com","12345",userRole));
        userRepository.save(new Users("Soledad","Ortiz","soleuser@gmail.com","12345",userRole));
        userRepository.save(new Users("Maria","Silva","mariauser@gmail.com","12345",userRole));
        userRepository.save(new Users("Alejandro","Nuñez","aleuser@gmail.com","12345",userRole));
        userRepository.save(new Users("Mauricio","Gonzalez","mauriciouser@gmail.com","12345",userRole));
        userRepository.save(new Users("Elias","Guevara","eliasuser@gmail.com","12345",userRole));
        userRepository.save(new Users("Valentina","Moreno","valenuser@gmail.com","12345",userRole));
        userRepository.save(new Users("Raul","Morales","rauluser@gmail.com","12345",userRole));
        userRepository.save(new Users("Sabrina","Peralta","sabrinauser@gmail.com","12345",userRole));
    }


}
