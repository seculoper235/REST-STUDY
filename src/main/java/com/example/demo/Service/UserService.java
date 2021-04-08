package com.example.demo.Service;

import com.example.demo.Domain.User;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* orElseThrow()?
     * JPA의 findById() 메소드는 옵셔널 객체를 반환한다.
     * 이 객체는 NULL 값 체크를 유용하게 할 수 있는 객체인데, 뒤따라 붙는 메소드로 처리한다.
     * orElse(), orElseGet(), orElseThrow() 등 여러가지가 있는데, 이중 orElsehrow()는 파라미터로 지정한 예외를 던진다.
     * 예외 클래스라면 어떤 것이든 가능하며, 다만 Supplier 형식으로 작성하여야 한다. */
    @Transactional(readOnly = true)
    public User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public User getUserById(Integer id) {
        return userRepository.getOne(id);
    }

    @Transactional(readOnly = true)
    public List<User> findUserAll() {
        return userRepository.findAll();
    }

    /* 생성 작업에 readOnly = true를 하면 에러가 생기는 것 정상이다.
     * 하지만 JPA에 한해 모든게 정상이고 그냥 결과만 반영되지 않는다.
     * 그 이유는 바로 하이버나이트의 자동 설정 때문이다.
     * readOnly가 true로 설정되면 세션 flush = MANUAL 이 되어, 트랜잭션이 커밋되더라도 flush()가 실행되지 않는다.
     * 즉, DB에 적용되지 않는다!
     * DB 에는 정작 아무런 쿼리도 날리지 않았고, 반환값은 영속성 컨텍스트의 값을 그대로 가져오니 무사히 수행된 것처럼 보이는 것이다
     */
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new UserNotFoundException();
        }
    }
}
