package XTwitter.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        log.warn(String.format("Выброшена ошибка: Пользователь '%s' уже существует", username));
    }
}
