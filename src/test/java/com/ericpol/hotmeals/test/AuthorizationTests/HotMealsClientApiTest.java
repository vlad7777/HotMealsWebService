package com.ericpol.hotmeals.test.AuthorizationTests;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ericpol.hotmeals.model.Category;
import com.ericpol.hotmeals.model.Dish;
import com.ericpol.hotmeals.model.Receipt;
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
	 * This test ensures that new suppliers can be added to service's database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddSupplier() throws Exception {

		Supplier s = hotmealsClient.addSupplier(new Supplier("\"Смач\" - домашние обеды"));
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
	 * This test ensures that new categories can be added to service's database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddCategory() throws Exception {

		Category c = hotmealsClient.addCategory(new Category(1, "Первые блюда"));
		assertTrue(c.getId() > 0);
	}

	/**
	 * This test ensures that categories will distinct by name in the service's database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testModifyCategory() throws Exception {

		Supplier s = hotmealsClient.getSupplier("Кафе \"Умида\"");
		Category oldCat = hotmealsClient.getCategory(s.getId(), "Супы");
		Category newCat = new Category(s.getId(), "Первые блюда");
		newCat.setId(oldCat.getId());
		hotmealsClient.addCategory(newCat);
		assert(oldCat.getId() == newCat.getId());
	}
	/**
	 * This test ensures that categories will distinct by name in the service's database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDuplicateCategory() throws Exception {

		Supplier s = hotmealsClient.getSupplier("Кафе \"Умида\"");
		Category oldCat = hotmealsClient.getCategory(s.getId(), "Первые блюда");
		Category newCat = hotmealsClient.addCategory(new Category(s.getId(), "Первые блюда"));
		assert(oldCat.getId() == newCat.getId());
	}

	/**
	 * This test ensures that new dishes can be added to service's database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddDish() throws Exception {

		Dish d = hotmealsClient.addDish(new Dish(1, 1, "Рассольник ленинградский с курицей", 9700.0, "20150804", "20160804"));
		assertTrue(d.getId() > 0);
	}

	/**
	 * This test ensures that the dishes can be modified in the service's database
	 * 
	 * @throws Exception
	 */
	@Test
	public void testModifyDish() throws Exception {

		Supplier s = hotmealsClient.getSupplier("Кафе \"Умида\"");
		Category c = hotmealsClient.getCategory(s.getId(), "Первые блюда");
		Dish oldDish = hotmealsClient.getDish(s.getId(), c.getId(), "Рассольник ленинградский с курицей");

		oldDish.setPrice(9700.0);
		Dish newDish = hotmealsClient.addDish(oldDish);

		assertTrue(oldDish.getId() == newDish.getId());
	}
	
	/**
	 * This test ensures that there could be multiple dishes in the service's database
	 * with the same supplierId, categoryId and name.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDuplicateDish() throws Exception {

		Supplier s = hotmealsClient.getSupplier("Кафе \"Умида\"");
		Category c = hotmealsClient.getCategory(s.getId(), "Первые блюда");
		Dish oldDish = hotmealsClient.getDish(s.getId(), c.getId(), "Рассольник ленинградский с курицей");

		Dish newDish = new Dish(s.getId(), c.getId(), "Рассольник ленинградский с курицей", 10000.0, "20150804", "20160804");
		newDish = hotmealsClient.addDish(newDish);

		assertTrue(oldDish.getId() != newDish.getId());
	}
	
	/**
	 * This test ensures that new users can be added to service's database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddUser() throws Exception {

		User u1 = hotmealsClient.addUser(new User("user0", "pass", "Вася", "Пупкин", "vp@google.com", null));
		User u2 = hotmealsClient.addUser(new User("user1", "pass", "Вася", "Пупкин", "vp@google.com", null));
		
		assertTrue(u1.getId() > 0);
		assertTrue(u2.getId() > 0);
	}

	/**
	 * This test ensures that users can be modified in the service's database.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testModifyUser() throws Exception {

		User oldUser = hotmealsClient.getUser("user0");
		oldUser.setFirstName("Александр");
		oldUser.setLastName("Пушкин");
		oldUser.setLogin("alpushkin");
		oldUser.setPassword("123456");
		oldUser.setEmail("alpushkin@yandex.ru");
		User newUser = hotmealsClient.addUser(oldUser);
		assertTrue(newUser.getId() == oldUser.getId());
	}

	/**
	 * This test ...
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFetchUsers() throws Exception {

		Collection<User> users = hotmealsClient.fetchUsers();
		assertTrue(users != null);
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
	 * This test ensures that clients can store their orders with the help of the service.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddOrder() throws Exception {

		User u = hotmealsClient.getUser("alpushkin");
		Supplier s = hotmealsClient.getSupplier("Кафе \"Умида\"");
		
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");

		double total = 0.0;
		List<Dish> dishes = new ArrayList<Dish>();

		Category c = hotmealsClient.getCategory(s.getId(), "Первые блюда");
		Dish d = hotmealsClient.getDish(s.getId(), c.getId(), "Суп из овощей с курицей");
		dishes.add(d);
		total += d.getPrice();

		c = hotmealsClient.getCategory(s.getId(), "Вторые блюда");
		d = hotmealsClient.getDish(s.getId(), c.getId(), "Плов по-узбекски");
		dishes.add(d);
		total += d.getPrice();

		c = hotmealsClient.getCategory(s.getId(), "Напитки");
		d = hotmealsClient.getDish(s.getId(), c.getId(), "Сок \"Настоящий\"");
		dishes.add(d);
		total += d.getPrice();

		c = hotmealsClient.getCategory(s.getId(), "Десерты");
		d = hotmealsClient.getDish(s.getId(), c.getId(), "Хлеб чёрный");
		dishes.add(d);
		total += d.getPrice();

		Receipt r = new Receipt(u.getId(), s.getId(), df.format(dt), "Дзержинского, 52-6/510", "Позвонить на рецепцию", total);
		Dish[] ds = new Dish[dishes.size()];
		ds = dishes.toArray(ds);
		r.setDishes(ds);

		r = hotmealsClient.addOrder(r);
		assertTrue(r.getId() > 0);
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

		Category c = hotmealsClient.addCategory(new Category(s.getId(), "Первые блюда"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Рассольник ленинградский с курицей", 9700.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Суп из овощей с курицей", 8500.0, "20150804", "20160804"));

		c = hotmealsClient.addCategory(new Category(s.getId(), "Вторые блюда"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Блинчики с творожным фаршем", 14000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Манты на пару со сметаной", 18400.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Плов по-узбекски", 18500.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Самса с куриными крылышками", 14000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Самса(тесто слоёное, фарш из телятины)", 17000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Тефтели(свекла, морковь, лук зелёный, огурцы св., вода, сахар)", 10600.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Филе скумбрии со шпинатом и помидорами(скумбрия с/м, помидоры св., майонез, сыр, шпинат)", 10600.0, "20150804", "20160804"));

		c = hotmealsClient.addCategory(new Category(s.getId(), "Гарниры и добавки"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Горчица", 3100.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Каша гречневая с помидорами", 6400.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Кетчуп", 1300.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Майонез", 2000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Пюре картофельное с помидорами", 8400.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Сметана", 2900.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Соус розовый", 1900.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Хрен", 3100.0, "20150804", "20160804"));
		
		c = hotmealsClient.addCategory(new Category(s.getId(), "Салаты"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Блинчики с семгой сл.солёной", 17200.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Буженина с помидорами", 12200.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Салат 'Нежность'(помидоры св., огурцы св., кукуруза, кр.мясо, перец сл.)", 9000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Салат из свежих помидоров с огурцами с заправкой(помидоры св., огурцы св., лук репч., масло оливк., уксус)", 8500.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Салат овощной с колбасой(картофель, морковь, лук, колбаса, яйцо, огурцы св., горошек)", 8800.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Яйца под майонезом(яйцо, майонез, помидоры, зелёный горошек, зелень)", 8900.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Яйца фаршированные грибами(яйца, шампиньоны, лук репч., майонез, зелень)", 6600.0, "20150804", "20160804"));

		c = hotmealsClient.addCategory(new Category(s.getId(), "Напитки"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Напиток апельсиновый", 2800.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Сок \"Настоящий\"", 4600.0, "20150804", "20160804"));

		c = hotmealsClient.addCategory(new Category(s.getId(), "Десерты"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Вафельные трубочки \"Вивайли\"", 3100.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Десерт \"Умида\"(клюква, сливки, зефир, грецкий орех, желатин, сахар)", 19300.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Зефир бело-розовый(пачка)", 11100.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Зефирка бело-розовая", 3700.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Карамель на палочке \"GoodФрукт\"", 5300.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Конфета \"Баунти\"", 9300.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Крем 'Снежок'", 11400.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Лепёшка", 2500.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Лимон с сахаром", 3100.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Мороженое", 9500.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Печенье песочное 'Твикс'", 9300.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Хлеб чёрный", 400.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Чипсы 'Мегачипс'", 8000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Шок.батончик 'Сникерс'", 9300.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Шоколад 'Алёнка' молочный", 6700.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Шоколад 'Коммунарка'", 6000.0, "20150804", "20160804"));

		s = hotmealsClient.addSupplier(new Supplier("\"Смач\" - домашние обеды"));
		c = hotmealsClient.addCategory(new Category(s.getId(), "Суп"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Суп с фрикадельками", 12000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Щи кислые с грибами(овощи, шамп., сметана)", 12000.0, "20150804", "20160804"));
		c = hotmealsClient.addCategory(new Category(s.getId(), "Второе"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Гречка отв.+ куриные кусочки в соусе", 30000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Картофельное пюре + Бифштекс Смак", 30000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Гречка с шампиньонами ВЕГЕТАРИАНСКОЕ", 30000.0, "20150804", "20160804"));
		c = hotmealsClient.addCategory(new Category(s.getId(), "Салат"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Огурцы 'Пятиминутка'", 15000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Салат 'Летний'", 15000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Гречка отв. + куриные кусочки в соусе + Салат 'Летний'", 40000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Гречка с шампиньонами ВЕГЕТАРИАНСКОЕ + Огурцы 'Пятиминутка'(2)", 40000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Картофельное пюре + Бифштекс Смак(2) + Огурцы 'Пятиминутка'", 40000.0, "20150804", "20160804"));
		c = hotmealsClient.addCategory(new Category(s.getId(), "Комплекс суп+второе+салат"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Суп с фрикадельками(2); Картофельное пюре + Бифштекс Смак(2) + Огурцы 'Пятиминутка'(2)", 45000.0, "20150804", "20160804"));
		hotmealsClient.addDish(new Dish(s.getId(), c.getId(), "Щи кислые с грибами(1); Гречка отв. + куриные кусочки в соусе(1) + Салат 'Летний'(1)", 45000.0, "20150804", "20160804"));
	}

}
