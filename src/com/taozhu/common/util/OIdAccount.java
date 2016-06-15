package com.taozhu.common.util;
import java.util.concurrent.atomic.AtomicLong;
public class OIdAccount {
	 private AtomicLong oid;
	    public OIdAccount(long init) {
	        this.oid = new AtomicLong(init);
	    }

	    public synchronized long getNext(int num) {
	        return oid.addAndGet(num);
	    }

}
