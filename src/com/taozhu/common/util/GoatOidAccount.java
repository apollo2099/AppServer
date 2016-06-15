package com.taozhu.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GoatOidAccount {
    private static GoatOidAccount account;
    Map<Integer, OIdAccount> map = new ConcurrentHashMap<Integer, OIdAccount>();
    private GoatOidAccount() {

    }

    public static GoatOidAccount getGoatOidAccount() {
        if (account == null) {
            account = new GoatOidAccount();
        }
        return account;
    }

    public void setInit(int classId, long initValue) {
        map.put(classId, new OIdAccount(initValue));
    }
    
    public long getNextIdInit(int classId){
    	if(map.get(classId)==null){
    		map.put(classId, new OIdAccount(1000));
    	}
    	return getNextId(classId);
    }

    public long getNextId(int classId) {
        return map.get(classId).getNext(1);
    }
    
    public long getNextId(int serverId, int classId) {
        return map.get(classId).getNext(100) / 100 * 100 + serverId;
    }
    
	public boolean isInitComplate(int classId) {
		return map.get(classId) != null;
	}
}
