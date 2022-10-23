package com.example.demo.web;

import java.security.MessageDigest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.RolesRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.dao.UsersServiceImpl;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Roles;
import com.example.demo.entities.Users;

@Controller

public class ControleurEtudiant {
	
	
	
	private UsersServiceImpl usersServiceImpl;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private UsersRepository usersRepository;
	
	@RequestMapping(value="/user/index")
	public String index(Model model,@RequestParam(value="page",defaultValue="0")int p,@RequestParam(value="size",defaultValue="5")int s,@RequestParam(value="nom",defaultValue="")String nom) {
		Page<Etudiant> etudiant = etudiantRepository.EtudaintParNom("%"+nom+"%",PageRequest.of(p,s));
		model.addAttribute("etudaint",etudiant.getContent());
		model.addAttribute("nom",nom);
		model.addAttribute("s",s);
		model.addAttribute("p",p);
		int[] pages = new int[etudiant.getTotalPages()];
		model.addAttribute("pages",pages);
		return "index";
	}
	@RequestMapping(value="/")
	public String home() {
		return "redirect:/user/index";
	}
	@RequestMapping(value="/Error")
	public String access() {
		return "403";
	}
	@RequestMapping(value="/admin/saisie",method=RequestMethod.GET)
	public String saisie(Model model)
	{
		model.addAttribute("etudiant",new Etudiant());
		return "saisie";
	}
	@RequestMapping(value="/admin/delete",method=RequestMethod.GET)
	public String delete(@RequestParam(value="id",defaultValue="0")Long id) {
		
		etudiantRepository.deleteById(id);
		return "redirect:/user/index";
	}
	@RequestMapping(value="login")
	public String login(){
		return "login";
	}
	@RequestMapping(value="/admin/register",method=RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("users",new Users());
		model.addAttribute("roles",new Roles());
		
		return "register";
	}
	public String CryptagePassword(String password) throws Exception
	{
		MessageDigest md=MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte byteData[] =md.digest();
		StringBuffer sb= new StringBuffer();
		for(int i=0;i<byteData.length;i++) {
			sb.append(Integer.toString((byteData[i] & 0xff)+ 0x100,16).substring(1));
		}
		return sb.toString();
	}
	@RequestMapping(value="/admin/saveUser",method=RequestMethod.POST)
	public String saveUser(Model model,Users users,Roles roles) throws Exception {
		String pass = CryptagePassword(users.getPass());
		users.setPass(pass);
		
		usersRepository.save(users);
		rolesRepository.save(roles);
		//usersServiceImpl.addRoleToUser(roles.getRole(),users.getLogin());
		model.addAttribute("users",users);
		model.addAttribute("roles",roles);
		
		return "confirmationUser";
	}
	
	@RequestMapping(value="/admin/save",method=RequestMethod.POST)
	public String save(Model model,@Valid Etudiant etudiant,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
		{
			return "saisie";
		}
		etudiantRepository.save(etudiant);
		model.addAttribute("etudiant",etudiant);
		return "confirmation";
	}
	@RequestMapping(value="/admin/edit",method=RequestMethod.GET)
	public String edit(Model model,Long id) {
		Etudiant etudiant = etudiantRepository.findId(id);
		model.addAttribute("etudiant",etudiant);
		return "editEtudiant";
		
	}

}
