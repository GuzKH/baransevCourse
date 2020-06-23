package soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("93.100.300.193");
        assertEquals(geoIp, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
    }

    @Test
    public void invalidtestMyIp(){
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("200.100.249.400");
        assertEquals(geoIp, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
    }
}
