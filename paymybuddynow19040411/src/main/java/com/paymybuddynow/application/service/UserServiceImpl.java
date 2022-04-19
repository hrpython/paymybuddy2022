package com.paymybuddynow.application.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddynow.application.exception.BankTransactionException;
import com.paymybuddynow.application.model.Connexion;
import com.paymybuddynow.application.model.Role;
import com.paymybuddynow.application.model.User;
import com.paymybuddynow.application.repository.ConnexionRepository;
import com.paymybuddynow.application.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ConnexionRepository connexionRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, ConnexionRepository connexionRepository) {
		super();
		this.userRepository = userRepository;
		this.connexionRepository = connexionRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	

	@Override
	public User saveUser(User user) {//permet de créer un nouvel user
		User user1 = new User(user.getFirstName(), user.getLastName(), user.getEmail(),
				passwordEncoder.encode(user.getPassword()), user.getIban(), user.getisConnexion(), user.getBalance(),
				Arrays.asList(new Role("ROLE_USER")), user.getConnexions(), user.getEnvoiTransaction(),
				user.getReceptionTransaction());
		return userRepository.save(user1);
	}

	

	@Override
	public Connexion saveUserConnexion(Long id) {//permet d'enregistrer un user dans la liste des connéctés de l'user loggé
		// permet de récupérer l'id de l'user courant
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Long idlogged = (userRepository.findByEmail(name)).getId();

		User user = userRepository.getOne(id);
		Connexion connexion = new Connexion(idlogged, id, user.getEmail(), user.getLastName());
		//user.setisConnexion(true);
		userRepository.save(user);
		return connexionRepository.save(connexion);
	}

	/*
	 * @Override public Optional<Connexion> getAllConnexions() {
	 * 
	 * return connexionRepository.findById(cache.get("id")); }
	 */

	@Override
	public List<User> getAllUsersMinusOne(Long id) {
		
		return userRepository.getAllMinusOne(id);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.getOne(id);

	}
//@Transactionnal permet de faire un roll back avant enregistrement de la transaction
//	si un défaut apparait pas d'emmetteur, pas de receveur, avoir de l'emetteur insuffisant
	@Transactional(propagation = Propagation.MANDATORY)
	public void addAmount(Long id, double amount) throws BankTransactionException {
		User user = userRepository.getOne(id);
		if (user == null) {
			throw new BankTransactionException("Account not found " + id);
		}

		double newBalance = user.getBalance() + amount;
		if (user.getBalance() + amount < 0) {
			throw new BankTransactionException("Money in your account is not enough (" + user.getBalance() + ")");
		}
		user.setBalance(newBalance);
	}

	// on ne capture pas BankTransactionException dans cette  methode
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
	public void sendMoney(Long fromAccountId, Long toAccountId, double amount, String description)
			throws BankTransactionException {

		addAmount(toAccountId, amount);// on vérifie que le receveur existe
		addAmount(fromAccountId, -amount);// on vérifie que l'émetteur existe et que sa balance est au minimum nulle après
		// la transaction
	}

	@Override
	public User getOne(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);

	}

	

	

}
