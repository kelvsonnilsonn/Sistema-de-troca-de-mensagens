package com.orizon.system.message.domain.services;

import com.orizon.system.message.domain.model.User;
import com.orizon.system.message.exceptions.InvalidIdentifierException;
import com.orizon.system.message.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userDAO;

    public UserService(@Autowired UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Procedimento de criação de usuário.
     *
     * @param username Refere-se ao nome do usuário a ser criado.
     * @param password Refere-se à senha do usuário.
     *
     * @throws IllegalArgumentException Se username for nulo ou não seguir o padrão de nomes (Lançado pelo construtor da classe Username).
     * @throws IllegalArgumentException Se a senha for nula ou menor que 3 caracteres (Lançado pelo construtor da classe Password).
     * */

    public void create(String username, String password) {
        User user = new User(username, password);
        userDAO.save(user);
    }

    /**
     * Procedimento de remoção de mensagens.
     *
     * @param userId Refere-se ao 'id' da mensagem a ser removida.
     *
     * @throws InvalidIdentifierException Se o 'id' não for encontrado (Lançado pelo método checkIfUserExists).
     * */

    public void delete(Long userId) {
        User user = checkIfUserExists(userId);
        userDAO.delete(user);
    }

    /**
     * Método responsável por realizar o login do usuário.
     *
     * @param username Refere-se ao nome do usuário.
     * @param password Refere-se à senha do usuário.
     *
     * @throws InvalidIdentifierException Se o usuário não for encontrado ou a senha estiver incorreta.
     * */

    public User login(String username, String password) {

        return userDAO.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new InvalidIdentifierException("Usuário ou senha inválidos"));
    }

    public User findById(Long id) {
        return checkIfUserExists(id);
    }

    public void findAll(){
        userDAO.findAll().forEach(System.out::println);
    }

    private User checkIfUserExists(Long id){
        User user = userDAO.findById(id).orElse(null);
        if(user == null){
            throw new InvalidIdentifierException();
        }
        return user;
    }
}
