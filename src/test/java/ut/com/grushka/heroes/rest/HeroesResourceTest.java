//package ut.com.grushka.heroes.rest;
//
//import org.junit.Test;
//import org.junit.After;
//import org.junit.Before;
//import com.grushka.heroes.rest.HeroesResource;
//import com.grushka.heroes.dto.HeroModel;
//import javax.ws.rs.core.Response;
//
//public class HeroesResourceTest {
//
//    @Before
//    public void setup() {
//
//    }
//
//    @After
//    public void tearDown() {
//
//    }
//
//    @Test
//    public void messageIsValid() {
//        HeroesResource resource = new HeroesResource(manager);
//
//        Response response = resource.getMessage();
//        final HeroModel message = (HeroModel) response.getEntity();
//
//        assertEquals("wrong message","Hello World",message.getMessage());
//    }
//}
