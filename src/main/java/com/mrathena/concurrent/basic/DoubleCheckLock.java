package com.mrathena.concurrent.basic;

public class DoubleCheckLock {

	// Do I need add "volatile" here?
	private volatile static DoubleCheckLock instance;
	// Should I add "final" here? Is a "final" enough here? Or I should use "volatile"?
	private final Element element = new Element();

	private DoubleCheckLock() {}

	public static DoubleCheckLock getInstance() {
		if (null == instance)
			synchronized (DoubleCheckLock.class) {
				if (null == instance)
					instance = new DoubleCheckLock();
				// the writes which initialize instance and the write to the
				// instance field can be reordered without "volatile"
			}
		return instance;
	}

	public Element getElement() {
		return element;
	}

}

class Element {
    public String name = new String("abc");
}