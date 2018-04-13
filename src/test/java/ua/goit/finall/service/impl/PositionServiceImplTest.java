package ua.goit.finall.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.finall.configuration.SpringBootWebApplication;
import ua.goit.finall.dao.PositionRepository;
import ua.goit.finall.service.PositionService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class PositionServiceImplTest {

    @MockBean
    PositionRepository positionRepository;

    @Autowired
    PositionService positionService;

    private Position position;


    @Before
    public void setup() {
        position = new Position("Nastya");

        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        position.setName("Nastya");
    }

    @Test
    @Transactional
    public void create() {
        when(positionRepository.save(position)).thenReturn(position);
        positionService.save(position);

        verify(positionRepository.save(any(Position.class)));

    }

    @Test
    @Transactional
    public void update() {
        positionService.save(position);
        position.setName("Wrong");
        positionService.update(position);
        positionRepository.save(position);
        Assert.assertEquals(position.getName() , "Wrong");

    }

    @Test
    public void delete() {
        positionService.save(position);
        positionRepository.save(position);
        Long id = position.getId();
        positionService.delete(id);
        verify(positionRepository, times(1)).delete(position);
    }
}
