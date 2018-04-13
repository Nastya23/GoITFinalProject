package ua.goit.finall.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.finall.configuration.SpringBootWebApplication;
import ua.goit.finall.dao.RoleRepository;
import ua.goit.finall.dao.UserRepository;
import ua.goit.finall.model.User;
import ua.goit.finall.service.UserService;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class UserServiceImplTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    RoleRepository roleRepository;

    @MockBean
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    private TestEntityManager entityManager;

    private User user;


    @Before
    public void setup() {
        user = new User("Nastya");

        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        user.setName("Nastya");
    }

    @Test
    public void testExample() throws Exception {
        entityManager.persist(new User("sboot", "1234"));
        User user = userRepository.findByUsername("sboot");
        assertThat(user.getUsername()).isEqualTo("sboot");
        assertThat(user.getPassword()).isEqualTo("1234");
    }

    @Test
    @Transactional
    public void create() {
        when(userRepository.save(user)).thenReturn(user);
        userService.save(user);

        verify(userRepository.save(any(User.class)));

    }

    @Test
    @Transactional
    public void update() {
        userService.save(user);
        user.setName("Wrong");
        userService.update(user);
        userRepository.save(user);
        Assert.assertEquals(user.getName() , "Wrong");

    }

    @Test
    public void delete() {
        userService.save(user);
        userRepository.save(user);
        Long id = user.getId();
        userService.delete(id);
        verify(userRepository, times(1)).delete(user);
    }
}
