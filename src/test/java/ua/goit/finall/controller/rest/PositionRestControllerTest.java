package ua.goit.finall.controller.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ua.goit.finall.model.Position;
import ua.goit.finall.service.PositionService;

import java.util.Arrays;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PositionRestControllerTest.class)
public class PositionRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PositionSevice positionSevice;

    @Test(expected = IllegalArgumentException.class)
    public void NullPathVariable() throws Exception {
        Position position = new Position();
        position.setId(1L);
        when(positionSevice.getById(any())).thenReturn(position);
        mockMvc.perform(get("/position/{id}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(1)));
    }
}
