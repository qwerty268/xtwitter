package XTwitter.services;

import XTwitter.models.User;
import XTwitter.models.Message;
import XTwitter.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class MessageService {


    @Value("${upload.path}")
    private String uploadPath;

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void addMessage(String text, String tag, User user, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            messageRepository.save(new Message(text, tag, user, resultFilename));
        } else messageRepository.save(new Message(text, tag, user));
    }

    public Iterable<Message> findMessagesByTag(String tag) {
        return messageRepository.findMessagesByTag(tag);
    }

    public Iterable<Message> findAll() {
        return messageRepository.findAll();
    }
}
