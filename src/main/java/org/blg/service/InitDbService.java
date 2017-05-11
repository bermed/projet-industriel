package org.blg.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.blg.entity.Blog;
import org.blg.entity.Item;
import org.blg.entity.Role;
import org.blg.entity.User;
import org.blg.repository.BlogRepository;
import org.blg.repository.ItemRepository;
import org.blg.repository.RoleRepository;
import org.blg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class InitDbService {
	
	@Autowired //on va injecter l'instance de la repo(c'est une classe qui est un bean)
	private RoleRepository roleRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void init(){
		//Test sur les Roles
		Role roleuser = new Role();
		roleuser.setName("ROLE_USER");
		roleRepository.save(roleuser);
		
		Role roleadmin = new Role();
		roleadmin.setName("ROLE_ADMIN");
		roleRepository.save(roleadmin);
		
		
		//Test sur les Users
		User useradmin = new User();
		useradmin.setName("USER_ADMIN");
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleadmin);
		roles.add(roleuser);
		useradmin.setRoles(roles);
		userRepository.save(useradmin);
		
		//Test sur les Blog
		Blog blog = new Blog();
		blog.setUrl("http://www.javatips.net/blog/feed/entries/rss");
		blog.setUsers(useradmin);
		blogRepository.save(blog);
		
		//Test sur les items
		
		Item itm = new Item();
		itm.setBlog(blog);
		itm.setTitle("Preemier Item");
		itm.setLink("http://www.javatips.net");
		itm.setDatepublication(new Date());
		itemRepository.save(itm);
		
		Item itm2 = new Item();
		itm2.setBlog(blog);
		itm2.setTitle("Second Item");
		itm2.setLink("http://www.javatips.net");
		itm2.setDatepublication(new Date());
		itemRepository.save(itm2);
		
		
	}
	
}
