package db.product;

import java.util.Properties;

import db.DBtypes;

public class ProductDbFactory {
	private static ProductDbFactory factory = new ProductDbFactory();

	private ProductDbFactory() {
	}

	public static ProductDbFactory getProductDbFactory() {
		return factory;
	}

	public ProductDbRepository createProductDb(DBtypes type, Properties properties) {
		ProductDbRepository db = null;
		switch (type) {
		case SQLDB:
			db = new ProductSQLRepository(properties);
			break;
		case LOCALDB:
			db = new ProductLocalRepository();
			break;
		}
		return db;
	}
}
