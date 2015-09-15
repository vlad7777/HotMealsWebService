package com.ericpol.hotmeals.test.AuthorizationTests;

import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import com.ericpol.hotmeals.model.Category;
import com.ericpol.hotmeals.model.Dish;
import com.ericpol.hotmeals.model.Supplier;
import com.ericpol.hotmeals.model.User;
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

		Supplier s = hotmealsClient.addSupplier(new Supplier("Test Supplier"));
		assertTrue(s.getId() > 0);
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
		assertTrue(c.getId() > 0);
	}

	/**
	 * This test ...
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddDish() throws Exception {

		Dish d = hotmealsClient.addDish(new Dish(1, 1, "Рассольник ленинградский с курицей", 9700.0));
		assertTrue(d.getId() > 0);
	}

	/**
	 * This test ...
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddUser() throws Exception {

		User u = hotmealsClient.addUser(new User("user0", "pass", "Вася", "Пупкин", "vp@google.com", null));
		assertTrue(u.getId() > 0);
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

		hotmealsClient.addUser(new User("user0", "pass", "Вася", "Пупкин", "vp@google.com", null));
		
		Supplier s = hotmealsClient.addSupplier(new Supplier("Кафе \"Умида\""));

		String [] cs = { "Напитки" };
		Category c = hotmealsClient.addCategory(new Category(s.getId(), "Первые блюда"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Рассольник ленинградский с курицей", 9700.0));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Суп из овощей с курицей", 8500.0));

		c = hotmealsClient.addCategory(new Category(s.getId(), "Вторые блюда"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Блинчики с творожным фаршем", 14000.0));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Манты на пару со сметаной", 18400.0));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Плов по-узбекски", 18500.0));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Самса с куриными крылышками", 14000.0));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Самса(тесто слоёное, фарш из телятины)", 17000.0));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Тефтели(свекла, морковь, лук зелёный, огурцы св., вода, сахар)", 10600.0));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Филе скумбрии со шпинатом и помидорами(скумбрия с/м, помидоры св., майонез, сыр, шпинат)", 10600.0));

		
		// We should get back the supplier that we added above
		Collection<Supplier> suppliers = hotmealsClient.fetchSuppliers();
		assertTrue(suppliers.contains(s));
	}

}
