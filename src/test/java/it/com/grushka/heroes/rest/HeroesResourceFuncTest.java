//package it.com.grushka.heroes.rest;
//
//import org.junit.Test;
//import org.junit.After;
//import org.junit.Before;
//import com.grushka.heroes.dto.HeroModel;
//import org.apache.wink.client.Resource;
//import org.apache.wink.client.RestClient;
//
//public class HeroesResourceFuncTest {
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
//
//        String baseUrl = System.getProperty("baseurl");
//        String resourceUrl = baseUrl + "/rest/heroes/1.0/message";
//
//        RestClient client = new RestClient();
//        Resource resource = client.resource(resourceUrl);
//
//        HeroModel message = resource.get(HeroModel.class);
//
//        assertEquals("wrong message","Hello World",message.getMessage());
//    }
//}
