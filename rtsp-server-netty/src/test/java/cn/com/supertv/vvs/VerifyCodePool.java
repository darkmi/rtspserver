package cn.com.supertv.vvs;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class VerifyCodePool<T> {

	private static final Map<String, CacheEntity> pool = new ConcurrentHashMap<String, CacheEntity>(50);
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	private int delay = 10;

	public VerifyCodePool() {
		scheduler.scheduleAtFixedRate(new ClearTask(), 1, delay, TimeUnit.SECONDS);
	}

	public void put(String key, T t) {
		CacheEntity<T> ce = new CacheEntity<T>(t);
		pool.put(key, ce);
	}

	public T get(String key) {
		CacheEntity<T> ce = pool.get(key);
		return ce == null ? null : ce.t;
	}

	class ClearTask implements Runnable {

		public void run() {
			Set<Entry<String, CacheEntity>> e = pool.entrySet();
			for (Iterator iterator = e.iterator(); iterator.hasNext();) {

				Entry<String, CacheEntity> entry = (Entry<String, CacheEntity>) iterator.next();
				CacheEntity<T> ce = entry.getValue();
				if (ce.time < System.currentTimeMillis() - delay) {
					pool.remove(entry.getKey());
					System.out.println("remove==" + entry.getKey());
				}
			}
		}
	}

}

class CacheEntity<T> {

	T t;
	long time;

	public CacheEntity(T t) {
		this.t = t;
		this.time = System.currentTimeMillis();
	}
}
