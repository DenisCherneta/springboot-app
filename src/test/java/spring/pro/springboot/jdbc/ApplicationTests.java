package spring.pro.springboot.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import spring.pro.springboot.jdbc.dao.interfaces.repositories.SingerRepository;

@SpringBootTest
class ApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private static GenericApplicationContext context;
	private static SingerRepository dao;

	@BeforeAll
	public static void setUp(){
		context = new AnnotationConfigApplicationContext(Application.class);
//		dao = context.getBean(SingerDao.class);
//		Assertions.assertNotNull(dao, "Dao is null!");
	}

//	@Test
//	void contextLoads() {
//		Singer singer = new Singer();
//		singer.setFirstName("BB");
//		singer.setLastName("King");
//		singer.setBirthDate(new Date(
//				(new GregorianCalendar(1940, 8, 16))
//						.getTime().getTime()));
//		Album album = new Album();
//		album.setTitle("My Kind of Blues");
//		album.setReleaseDate(new java.sql.Date(
//				(new GregorianCalendar(1961, 7, 18))
//						. getTime().getTime()));
//		singer.addAlbum(album);
//		album = new Album();
//		album.setTitle("A Heart Full of Blues");
//		album.setReleaseDate(new java.sql.Date(
//				(new GregorianCalendar(1962, 3, 20))
//						. getTime().getTime ()));
//		singer.addAlbum(album);
//
//		dao.update(singer);
//		Assertions.assertNotNull(singer.getId());
//		List<Singer> singers = dao.findAllWithAlbum();
//		Assertions.assertEquals (4, singers. size ());
//		listSingersWithAlbum(singers);
//	}
//
//	@Test
//	public void testFindAll(){
//		List<Singer> singers = dao.findAll();
//		Assertions.assertEquals(3, singers.size());
//		listSingers(singers);
//	}
//
//	@Test
//	public void testFindAllWithAlbum() {
//		List<Singer> singers = dao.findAllWithAlbum();
//		Assertions.assertEquals(3, singers.size());
//		listSingersWithAlbum(singers);
//	}
//
//	@Test
//	public void testFindByID () {
//		Singer singer = dao.findById(1L);
//		Assertions.assertNotNull(singer);
//		logger.info(singer.toString());
//	}
//
//	private static void listSingers(List<Singer> singers) {
//		logger.info(" ---- Listing singers:");
//		for (Singer singer : singers) {
//			logger.info(singer.toString());
//		}
//	}
//
//	private static void listSingersWithAlbum(List<Singer> singers) {
//		logger.info(" ---- Listing singers with instruments:");
//		for (Singer singer : singers) {
//			logger.info(singer.toString());
//			if (singer.getAlbums() != null) {
//				for (Album album : singer.getAlbums()) {
//					logger.info("\t" + album.toString());
//				}
//			}
//			if (singer.getInstruments() != null) {
//				for (Instrument instrument : singer.getInstruments()) {
//					logger.info("\tinstrument: " + instrument.getInstrumentId());
//				}
//			}
//		}
//	}

	@AfterAll
	public static void closeAll(){
		context.close();
	}

}
