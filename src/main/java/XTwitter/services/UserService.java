package XTwitter.services;

import XTwitter.models.User;
import XTwitter.models.Role;
import XTwitter.exceptions.UserAlreadyExistsException;
import XTwitter.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public void updateUser(User user, Map<String, String> params) {
        log.info(String.format("Начато обновление пользователя '%s'", user));
        user.setUsername(params.get("username"));
        Set<String> allRoles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        Set<Role> newUserRoles = new HashSet<>();
        allRoles.stream().filter(params::containsKey).forEach((role) -> newUserRoles.add(Role.valueOf(role)));

        user.setRoles(newUserRoles);

        userRepository.save(user);
        log.info(String.format("Пользователь обновлен: %s", user));
    }


    @Transactional
    public void updateUser(User user, String username, String password) {
        log.info(String.format("Начато обновление пользователя '%s'", user));

        user.setUsername(username);
        user.setPassword(password);

        userRepository.save(user);
        log.info(String.format("Пользователь обновлен: %s", user));
    }


    @Transactional
    public List<User> findAll() {
        List<User> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username)));

        return user;
    }

    @Transactional
    public void creteNewUser(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent((e) -> {
            log.info(String.format("Пользователь с именем '%s' уже существует", user.getUsername()));
            throw new UserAlreadyExistsException(user.getUsername());
        });

        user.setActive(true);
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Collections.singleton(Role.USER));
        }

        userRepository.save(user);     //user 100% уникальный
        log.info(String.format("Пользователь '%s' сохранён", user.getUsername()));
    }
}
