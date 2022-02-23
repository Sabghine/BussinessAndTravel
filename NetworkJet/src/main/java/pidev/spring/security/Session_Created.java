package pidev.spring.security;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Session_Created implements HttpSessionListener {
	/*
	 * 
	 * To create HTTP Session Listener i need to create @Bean of
	 * HttpSessionListener Interface in class Security_Configuration
	 
	 * HttpSessionListener has two methods sessionCreated(called when session
	 * created) and sessionDestroyed (called when session destroyed)
	 * 
	 * HttpSessionAttributeListener has three methodsattributeAdded (called when
	 * new attribute add to session), attributeRemoved (called when attribute
	 * remove from the session), attributeReplaced(called when session attribute
	 * replace or updated)
	 */
	
	private static int totalActiveSessions;

	 

 

	@Override
	public void sessionCreated(HttpSessionEvent session) {
		setTotalActiveSessions(getTotalActiveSessions() + 1);
		log.info("sessionCreated - add one session into counter");

		String SessionId = session.getSession().getId();
		log.info("session created reference is : " + SessionId);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent session) {
		setTotalActiveSessions(getTotalActiveSessions() + 1);
		log.info("sessionDeleted - delete one session into counter");

		String SessionId = session.getSession().getId();
		log.info("session deleted reference is : " + SessionId);
	}

	/**
	 * @return the totalActiveSessions
	 */
	public static int getTotalActiveSessions() {
		return totalActiveSessions;
	}

	/**
	 * @param totalActiveSessions the totalActiveSessions to set
	 */
	public static void setTotalActiveSessions(int totalActiveSessions) {
		Session_Created.totalActiveSessions = totalActiveSessions;
	}
}
