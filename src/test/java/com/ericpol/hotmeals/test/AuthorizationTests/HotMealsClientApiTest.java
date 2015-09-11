package com.ericpol.hotmeals.test.AuthorizationTests;

import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import com.ericpol.hotmeals.model.Supplier;
import com.ericpol.hotmeals.client.HotmealsApi;
import com.ericpol.hotmeals.client.SecuredRestBuilder;
import com.ericpol.hotmeals.client.SecuredRestException;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.ApacheClient;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * 
 * This integration test sends a POST request to the VideoServlet to add a new
 * video and then sends a second GET request to check that the video showed up
 * in the list of videos. Actual network communication using HTTP is performed
 * with this test.
 * 
 * The test requires that the VideoSvc be running first (see the directions in
 * the README.md file for how to launch the Application).
 * 
 * To run this test, right-click on it in Eclipse and select
 * "Run As"->"JUnit Test"
 * 
 * Pay attention to how this test that actually uses HTTP and the test that just
 * directly makes method calls on a VideoSvc object are essentially identical.
 * All that changes is the setup of the hotmealsService variable. Yes, this could
 * be refactored to eliminate code duplication...but the goal was to show how
 * much Retrofit simplifies interaction with our service!
 * 
 * @author jules
 *
 */
public class HotMealsClientApiTest {

	private final String TOKEN_PATH = "/oauth/token";
	private final String USERNAME = "user0";
	private final String PASSWORD = "pass";
	private final String CLIENT_ID = "mobile";
	private final String CLIENT_SECRET = "secret";
	private final String READ_ONLY_CLIENT_ID = "mobileReader";

	private final String TEST_URL = "https://localhost:8080";

	private HotmealsApi hotmealsService = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + TOKEN_PATH)
			.setUsername(USERNAME)
			.setPassword(PASSWORD)
			.setClientId(CLIENT_ID)
			.setClientSecret(CLIENT_SECRET)
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(HotmealsApi.class);

	private HotmealsApi readOnlyHotmealsService = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + TOKEN_PATH)
			.setUsername(USERNAME)
			.setPassword(PASSWORD)
			.setClientId(READ_ONLY_CLIENT_ID)
			.setClientSecret(CLIENT_SECRET)
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(HotmealsApi.class);

	private HotmealsApi invalidClientHotmealsService = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + TOKEN_PATH)
			.setUsername(UUID.randomUUID().toString())
			.setPassword(UUID.randomUUID().toString())
			.setClientId(UUID.randomUUID().toString())
			.setClientSecret(CLIENT_SECRET)
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(HotmealsApi.class);

	/**
	 * This test creates a Video, adds the Video to the VideoSvc, and then
	 * checks that the Video is included in the list when getVideoList() is
	 * called.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSuppliersList() throws Exception {

		// We should get back the video that we added above
		Collection<Supplier> suppliers = hotmealsService.fetchSuppliers();
		assertTrue(suppliers != null);
	}

	/**
	 * This test ensures that clients with invalid credentials cannot get
	 * access to videos.
	 * 
	 * @throws Exception
	 */

	@Test
	public void testAccessDeniedWithIncorrectCredentials() throws Exception {

		try {
			Collection<Supplier> suppliers = invalidClientHotmealsService.fetchSuppliers();

			fail("Invalid user was granted permission to read data");
		} catch (RetrofitError e) {
			assert (e.getCause() instanceof SecuredRestException);
		}
	}

}
