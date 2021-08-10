package com.example.hr.domain;

import java.util.Objects;

@ValueObject // since Java 14+ -> record
// http://binkurt.blogspot.com/2020/04/java-14de-record-kullanm.html
public final class FullName {
	private final String first;
	private final String last;

	private FullName(String first, String last) {
		this.first = first;
		this.last = last;
	}

	public static FullName of(String first, String last) {
		Objects.requireNonNull(first);
		Objects.requireNonNull(last);
		return new FullName(first, last);
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FullName other = (FullName) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FullName [first=" + first + ", last=" + last + "]";
	}

}
