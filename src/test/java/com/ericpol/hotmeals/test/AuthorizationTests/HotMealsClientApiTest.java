package com.ericpol.hotmeals.test.AuthorizationTests;

import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import com.ericpol.hotmeals.model.Category;
import com.ericpol.hotmeals.model.Supplier;
import com.ericpol.hotmeals.client.HotmealsApi;
import com.ericpol.hotmeals.client.SecuredRestBuilder;
import com.ericpol.hotmeals.client.SecuredRestException;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.ApacheClient;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class HotMealsClientApiTest {

	private final String TOKEN_PATH = "/oauth/token";
	private final String USERNAME = "user0";
	private final String PASSWORD = "pass";
	private final String CLIENT_ID = "mobile";
	private final String CLIENT_SECRET = "secret";
	private final String READ_ONLY_CLIENT_ID = "mobileReader";

	private final String TEST_URL = "https://localhost:8080";

	private HotmealsApi hotmealsClient = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + TOKEN_PATH)
			.setUsername(USERNAME)
			.setPassword(PASSWORD)
			.setClientId(CLIENT_ID)
			.setClientSecret(CLIENT_SECRET)
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(HotmealsApi.class);

	private HotmealsApi readOnlyHotmealsClient = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + TOKEN_PATH)
			.setUsername(USERNAME)
			.setPassword(PASSWORD)
			.setClientId(READ_ONLY_CLIENT_ID)
			.setClientSecret(CLIENT_SECRET)
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(HotmealsApi.class);

	private HotmealsApi invalidHotmealsClient = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + TOKEN_PATH)
			.setUsername(UUID.randomUUID().toString())
			.setPassword(UUID.randomUUID().toString())
			.setClientId(UUID.randomUUID().toString())
			.setClientSecret(CLIENT_SECRET)
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(HotmealsApi.class);

	/**
	 * This test ...
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddSupplier() throws Exception {

		Supplier s = new Supplier("Test Supplier");
		s = hotmealsClient.addSupplier(s);
		
		// We should get back the supplier that we added above
		Collection<Supplier> suppliers = hotmealsClient.fetchSuppliers();
		assertTrue(suppliers.contains(s));
	}

	/**
	 * This test ...
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFetchSuppliers() throws Exception {

		Collection<Supplier> suppliers = hotmealsClient.fetchSuppliers();
		assertTrue(suppliers != null);
	}
	
	/**
	 * This test ...
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddCategory() throws Exception {

		Category c = hotmealsClient.addCategory(new Category(1, "Первые блюда"));
		
		// We should get back the category that we added above
		Collection<Category> categories = hotmealsClient.fetchCategories();
		assertTrue(categories.contains(c));
	}

	/**
	 * This test ensures that clients with invalid credentials cannot get
	 * access to the service.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAccessDeniedWithIncorrectCredentials() throws Exception {

		try {
			Collection<Supplier> suppliers = invalidHotmealsClient.fetchSuppliers();
			fail("Invalid user was granted permission to read data");
		}
		catch (RetrofitError e) {
			assert (e.getCause() instanceof SecuredRestException);
		}
	}

	/**
	 * This test ...
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInitialSetup() throws Exception {

		Supplier s = hotmealsClient.addSupplier(new Supplier("Энергия"));

		String [] cs = { "Первые блюда", "Вторые блюда", "Напитки" };
		for (int i = 0; i < cs.length; i++) {
			Category c = hotmealsClient.addCategory(new Category(s.getId(), cs[i]));
		}
		
		// We should get back the supplier that we added above
		Collection<Supplier> suppliers = hotmealsClient.fetchSuppliers();
		assertTrue(suppliers.contains(s));
	}

}
