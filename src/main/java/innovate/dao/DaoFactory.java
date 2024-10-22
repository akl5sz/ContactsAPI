package innovate.dao;

public final class DaoFactory {
	private static final String IMPL_TYPE = "jdbc";
	private DaoFactory() {
		
	}
	public static ContactsDAO getContactsDao() throws DaoException {
		switch(IMPL_TYPE) {
		case "jdbc": 
			return new JdbcContactsDAO();
		default:
			throw new DaoException("No suitable implementation available.");
		}
	}

}
