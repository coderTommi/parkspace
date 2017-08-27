package test.rules;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class CachedData implements Cache {
	protected Log log = LogFactory.getLog(getClass());
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	protected abstract void updateCache();

	public String getCachedDataId() {
		return null;
	}
	protected void lockCache() {
		this.lock.readLock().lock();
		if (isDirty()) {
			this.lock.readLock().unlock();
			this.lock.writeLock().lock();
			if (isDirty())
				try {
					updateCache();
				} catch (Throwable localThrowable) {
					this.log.error("updateCache", localThrowable);
				}
			this.lock.readLock().lock();
			this.lock.writeLock().unlock();
		}
	}
	protected void unlockCache() {
		this.lock.readLock().unlock();
	}
}
