package org.joda.time;

import org.joda.time.chrono.BaseChronology;

public class BaseChronologyImp extends BaseChronology {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DateTimeZone getZone() {
        return null;
    }
    public Chronology withUTC() {
        return this;
    }
    public Chronology withZone(DateTimeZone zone) {
        return this;
    }
    public String toString() {
        return getClass().getName();
    }
}
