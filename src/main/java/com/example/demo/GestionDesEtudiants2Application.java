package com.example.demo;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.RolesRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Roles;
import com.example.demo.entities.Users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.demo.web"})
@ComponentScan({"com.example.demo.sc"})
@SpringBootApplication
public class GestionDesEtudiants2Application {

	public static void main(String[] args) {
		ApplicationContext ctx =SpringApplication.run(GestionDesEtudiants2Application.class, args);
		EtudiantRepository etudiantRpository = ctx.getBean(EtudiantRepository.class);
		RolesRepository rolesRpository = ctx.getBean(RolesRepository.class);
		UsersRepository usersRpository = ctx.getBean(UsersRepository.class);
		List<Users> users=new ArrayList<>();
		users.add(new Users("AAAA","54321"));
		users.add(new Users("BBBBB","65432"));
		users.add(new Users("CCCCCC","76543"));
		users.add(new Users("DDDDDD","87654"));
		usersRpository.save(new Users("AAAA","54321"));
		usersRpository.save(new Users("BBBBB","65432"));
		usersRpository.save(new Users("CCCCCC","76543"));
		usersRpository.save(new Users("DDDDDD","87654"));
		rolesRpository.save(new Roles("Admin"));
//		etudiantRpository.save(new Etudiant("Yousef","khayi","khayi@gmail.com","2ASI"));
//		etudiantRpository.save(new Etudiant("Yousef","khayi","khayi@gmail.com","2ASI"));
//		etudiantRpository.save(new Etudiant("Hamza","Boukricha","boukricha@gmail.com","MQL"));
//		etudiantRpository.save(new Etudiant("","Boukricha","boukricha@gmail.com","MQL"));
	}

}
