package com.smart.comm.utils;

import org.apache.log4j.Logger;

public class OOLogUtil {

	private static final Logger log = Logger.getLogger(OOLogUtil.class);
	private static final boolean LOG_KAIGUAN = true;

	public static void info(Object msg, Class<?> class1, Throwable t) {
		if (LOG_KAIGUAN) {
			if (t != null) {
				log.info(class1.getName() + ":" + msg, t);
			} else {
				log.info(class1.getName() + ":" + msg);
			}
		}

	}

}
