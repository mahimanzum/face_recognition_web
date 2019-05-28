package report;

import common.RequestFailureException;

public interface SubqueryBuilder {
	String createSubquery(String value, Integer... moduleID) throws RequestFailureException;
}
