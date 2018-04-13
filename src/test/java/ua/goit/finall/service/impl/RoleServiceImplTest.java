package ua.goit.finall.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.finall.configuration.SpringBootWebApplication;
import ua.goit.finall.dao.RoleRepository;
import ua.goit.finall.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class RoleServiceImplTest {

    @MockBean
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    private Role role;


    @Before
    public void setup() {
        role = new Role("Nastya");

        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        role.setName("Nastya");
    }

    @Test
    @Transactional
    public void create() {
        when(roleRepository.save(role)).thenReturn(role);
        roleService.save(role);

        verify(roleRepository.save(any(Role.class)));

    }

    @Test
    @Transactional
    public void update() {
        roleService.save(role);
        role.setName("Wrong");
        roleService.update(role);
        roleRepository.save(role);
        Assert.assertEquals(role.getName() , "Wrong");

    }

    @Test
    public void delete() {
        roleService.save(role);
        roleRepository.save(role);
        Long id = role.getId();
        roleService.delete(id);
        verify(roleRepository, times(1)).delete(role);
    }
}
