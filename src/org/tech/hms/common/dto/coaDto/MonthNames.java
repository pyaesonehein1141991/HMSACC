package org.tech.hms.common.dto.coaDto;

public enum MonthNames {
	January(0), February(1), March(2), April(3), May(4), June(5), July(6), August(7), September(8), October(9), November(10), December(11);

	private int value;

	private MonthNames(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}