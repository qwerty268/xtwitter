package XTwitter;

import XTwitter.controllers.RegistrationController;
import XTwitter.models.User;
import XTwitter.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


// @AutoConfigureMockMvc

// @SpringBootTest
// @TestPropertySource("/application-test.properties")
// public class RegistrationTest {

//     @Mock
//     private UserService userService;
//     @Autowired
//     private MockMvc mockMvc;
//     @InjectMocks
//     private RegistrationController registrationController;

//     @Test
//     public void testRegistration() {
//         String result = registrationController.registration();
//         assertEquals("registration", result);
//     }

//     @Test
//     public void testAddUser() {
//         User user = new User();
//         RedirectAttributes redirectAttrs = new RedirectAttributesModelMap();

//         String result = registrationController.addUser(user, redirectAttrs);

//         verify(userService, times(1)).creteNewUser(user);
//         assertEquals("redirect:/login", result);
//     }

//     @Test
//     public void testAddUserWithException() throws Exception {
//         User user = new User();
//         user.setUsername("user");
//         user.setPassword("user");

//         ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

//         String json = ow.writeValueAsString(user);
//         this.mockMvc.perform(post("/registration").content(json))
//                 .andDo(print())
//                 .andExpect(content().string(containsString("")));
//     }

//     @Test
//     public void testShowAlert() {
//         RedirectAttributes redirectAttrs = new RedirectAttributesModelMap();
//         Model model = new ExtendedModelMap();

//         String result = registrationController.showAlert(redirectAttrs, model);

//         assertTrue(redirectAttrs.getFlashAttributes().containsValue("Пользователь стаким именем уже существует"));
//         assertEquals("registration", result);
//     }
// }