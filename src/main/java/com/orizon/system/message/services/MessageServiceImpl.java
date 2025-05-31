package com.orizon.system.message.services;

import com.orizon.system.message.config.AccountConfigurations;
import com.orizon.system.message.domain.model.Message;
import com.orizon.system.message.domain.model.User;
import com.orizon.system.message.domain.ports.services.MessageService;
import com.orizon.system.message.exceptions.InvalidIdentifierException;
import com.orizon.system.message.exceptions.message.InvalidMessageContentException;
import com.orizon.system.message.exceptions.message.InvalidMessageReceiverException;
import com.orizon.system.message.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageDAO;

    public MessageServiceImpl(@Autowired MessageRepository messageDAO) {
        this.messageDAO = messageDAO;
    }

    /**
     * Procedimento responsável pelo envio de mensagens.
     *
     * @param receiver Refere-se ao usuário que receberá a mensagem.
     * @param content Refere-se ao conteúdo da mensagem.
     *
     * @throws InvalidMessageContentException Lançado caso conteúdo seja nulo ou vazio.
     * */

    @Override
    public void send(User receiver, String content) {
        if(content == null || content.isEmpty()){
            throw new InvalidMessageContentException();
        }
        Message message = new Message(content, AccountConfigurations.currentUser, receiver);
        messageDAO.save(message);
    }


    /**
     * Método responsável por ler uma mensagem recebida.
     *
     * @param messageId Refere-se à mensagem a ser lida.
     *
     * @throws InvalidIdentifierException Se o 'id' não for encontrado (Lançado pelo método checkIfMessageExists).
     * @throws InvalidMessageReceiverException Lançado caso o usuário atual não seja o recebedor da mensagem.
     * */

    @Override
    public String receive(Long messageId) {
        Message message = checkIfMessageExists(messageId);

        if(!Objects.equals(message.getReceiver().getId(), AccountConfigurations.currentUser.getId())){
            throw new InvalidMessageReceiverException();
        }

        message.setRead(true);
        messageDAO.save(message);
        return message.getContent();
    }

    /**
     * Procedimento de remoção de mensagens.
     *
     * @param messageId Refere-se ao 'id' da mensagem a ser removida.
     *
     * @throws InvalidIdentifierException Se o 'id' não for encontrado (Lançado pelo método checkIfMessageExists).
     * */

    @Override
    public void delete(Long messageId) {
        Message messageToDelete = checkIfMessageExists(messageId);
        messageDAO.delete(messageToDelete);
    }

    /**
     * Procedimento responsável por atualizar uma mensagem.
     *
     * @param messageId Refere-se ao 'id' mensagem a ser atualizada.
     * @param newContent Refere-se ao novo conteúdo a ser colocado na mensagem.
     *
     * @throws InvalidIdentifierException Se o 'id' não for encontrado (Lançado pelo método checkIfMessageExists).
     * */
    @Override
    public void update(Long messageId, String newContent) {
        Message message = checkIfMessageExists(messageId);
        message.setContent(newContent);
        messageDAO.save(message);
    }

    private Message checkIfMessageExists(Long id){
        Message message = messageDAO.findById(id).orElse(null);
        if(message == null){
            throw new InvalidIdentifierException();
        }
        return message;
    }
}
