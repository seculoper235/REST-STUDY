import com.example.demo.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    public void domainTest() {
        // given
        String name = "핑핑";
        String description = "개발이 취미인 수학자";

        // when
        User user = new User();
        user.setName(name);
        user.setDescription(description);

        // then
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getDescription()).isEqualTo(description);
    }
}
